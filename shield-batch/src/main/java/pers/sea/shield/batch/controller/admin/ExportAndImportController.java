package pers.sea.shield.batch.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pers.sea.shield.batch.common.enums.BatchErrorInfo;
import pers.sea.shield.batch.pojo.entity.Job;
import pers.sea.shield.batch.service.IExportAndImportService;
import pers.sea.shield.batch.service.IJobService;
import pers.sea.shield.common.core.exception.CloudShieldException;
import pers.sea.shield.common.core.pojo.CommonResult;

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
    final IJobService jobService;

    public ExportAndImportController(IExportAndImportService exportAndImportService,
                                     IJobService jobService) {
        this.exportAndImportService = exportAndImportService;
        this.jobService = jobService;
    }

    @GetMapping(value = "/download/{jobId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource downloadFile(@PathVariable String jobId) {
        Job job = jobService.getById(jobId);
        if (job == null) {
            throw new CloudShieldException(BatchErrorInfo.JOB_NOT_FOUND);
        }
        return exportAndImportService.downloadFile(job.getResultFile());
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public CommonResult<String> uploadFile(@RequestParam MultipartFile file) {
        return exportAndImportService.uploadFile(file);
    }


}
