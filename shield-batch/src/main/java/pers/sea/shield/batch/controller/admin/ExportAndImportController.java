package pers.sea.shield.batch.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.sea.shield.batch.common.enums.BatchErrorInfo;
import pers.sea.shield.batch.service.IExportAndImportService;
import pers.sea.shield.common.core.pojo.CommonResult;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 批量作业导入和导出处理
 *
 * @author moon on 2023/11/20
 */
// 使用该注解的类，会在接到SpringCloud配置中心配置刷新的时候，自动将新的配置更新到该类对应的字段中。
@RefreshScope
@RequestMapping("/file")
@RestController
@Slf4j
public class ExportAndImportController {

    final IExportAndImportService exportAndImportService;

    public ExportAndImportController(IExportAndImportService exportAndImportService) {
        this.exportAndImportService = exportAndImportService;
    }

    @GetMapping(value = "/download/{fileName:.+}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> downloadFile(@PathVariable String date,
                                                 @PathVariable String fileName) {
        Resource resource = exportAndImportService.downloadFile(date, fileName);
        Objects.requireNonNull(resource.getFilename(), BatchErrorInfo.FILE_IS_EMPTY.getMessage());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(resource.getFilename(), StandardCharsets.UTF_8).build().toString())
                .body(resource);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResult<String> uploadFile(@RequestParam MultipartFile file) {
        return exportAndImportService.uploadFile(file);
    }


}
