package pers.sea.shield.batch.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import pers.sea.shield.common.core.pojo.CommonResult;

/**
 * 批量作业导入和导出接口
 *
 * @author moon on 2023/11/20
 */
public interface IExportAndImportService {

    /**
     * upload file
     *
     * @param file user upload file
     * @return common result
     */
    CommonResult<String> uploadFile(MultipartFile file);

    /**
     * download file
     *
     * @param path absolute path
     * @return resource
     */
    Resource downloadFile(String path);
}
