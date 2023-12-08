package pers.sea.shield.batch.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import pers.sea.shield.batch.common.constant.BatchProperty;
import pers.sea.shield.batch.common.enums.BatchErrorInfo;
import pers.sea.shield.batch.service.IExportAndImportService;
import pers.sea.shield.common.core.constant.DateConstant;
import pers.sea.shield.common.core.exception.CloudShieldException;
import pers.sea.shield.common.core.pojo.CommonResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static pers.sea.shield.common.core.constant.SymbolConstant.DOT;

/**
 * 批量模块文件上传和下载服务
 *
 * @author moon on 2023/11/22
 */
@Service
@Slf4j
public class ExportAndImportServiceImpl implements IExportAndImportService {

    final String fileBasePath = BatchProperty.monitorDir;


    @Override
    public CommonResult<String> uploadFile(MultipartFile file) {
        // 1. 非空校验
        if (file == null || file.isEmpty()) {
            return CommonResult.failed(BatchErrorInfo.FILE_IS_EMPTY);
        }
        // 2. 生成服务器文件路径
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename(), BatchErrorInfo.FILE_NAME_INVALID.getMessage()));
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern(DateConstant.DATE_FORMAT_WITHOUT_TIME));
        try {
            Path dateDirPath = Paths.get(fileBasePath, date);
            if (Files.notExists(dateDirPath)) {
                log.info("dateDirPath mkdir [{}]", dateDirPath.toFile().mkdirs());
            }
            // 3. 上传文件
            Files.copy(file.getInputStream(), dateDirPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            // generate flag file
            String flagFileName = fileName.substring(0, fileName.lastIndexOf(DOT)) + BatchProperty.fileFlagSuffix;
            Files.createFile(Paths.get(fileBasePath, date, flagFileName));
            return CommonResult.ok();
        } catch (Exception e) {
            log.error(BatchErrorInfo.FILE_UPLOAD_FAILED.getMessage(), e);
        }
        return CommonResult.failed(BatchErrorInfo.FILE_UPLOAD_FAILED);
    }

    @Override
    public Resource downloadFile(String path) {
        // 1. 文件名非法校验
        if (org.apache.commons.lang3.StringUtils.isBlank(path) || path.endsWith(DOT)) {
            throw new CloudShieldException(BatchErrorInfo.FILE_NAME_INVALID);
        }
        // 2. 拼接文件路径并校验文件是否存在
        Path pathFile = Paths.get(path);
        if (!Files.exists(pathFile)) {
            throw new CloudShieldException(BatchErrorInfo.FILE_IS_EMPTY);
        }
        try {
            return new UrlResource(pathFile.toUri());
        } catch (IOException e) {
            log.error(BatchErrorInfo.FILE_DOWNLOAD_FAILED.getMessage(), e);
            throw new CloudShieldException(BatchErrorInfo.FILE_DOWNLOAD_FAILED);
        }
    }

}
