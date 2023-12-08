package pers.sea.shield.batch.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 任务表
 * </p>
 *
 * @author moon
 * @since 2023-12-08
 */
@Getter
@Setter
@TableName("batch_job")
public class Job {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 任务配置id
     */
    @TableField("config_id")
    private Integer configId;

    /**
     * 任务名称
     */
    @TableField("job_name")
    private String jobName;

    /**
     * 任务状态00-未执行;01-执行中;02-执行成功;03-执行失败;04-推送中;05-推送失败;06-已取消
     */
    @TableField("job_status")
    private String jobStatus;

    /**
     * 监听文件: 绝对路径
     */
    @TableField("monitor_file")
    private String monitorFile;

    /**
     * 实际加载文件: 绝对路径
     */
    @TableField("load_file")
    private String loadFile;

    /**
     * 执行器ip
     */
    @TableField("allow_ip")
    private String allowIp;

    /**
     * 实际开始时间
     */
    @TableField("start_time")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;

    /**
     * 总数
     */
    @TableField("total_count")
    private Integer totalCount;

    /**
     * 成功数
     */
    @TableField("success_count")
    private Integer successCount;

    /**
     * 失败数
     */
    @TableField("fail_count")
    private Integer failCount;

    /**
     * 完成数
     */
    @TableField("done_count")
    private Integer doneCount;

    /**
     * 结果数
     */
    @TableField("result_count")
    private Integer resultCount;

    /**
     * 结果文件: 绝对路径
     */
    @TableField("result_file")
    private String resultFile;

    /**
     * 结果标记文件: 绝对路径
     */
    @TableField("result_flag_file")
    private String resultFlagFile;

    /**
     * 描述信息
     */
    @TableField("description")
    private String description;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private LocalDateTime modifyTime;

    /**
     * 完成时间
     */
    @TableField("finish_time")
    private LocalDateTime finishTime;
}
