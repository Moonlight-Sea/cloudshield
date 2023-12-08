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
 * 任务配置表
 * </p>
 *
 * @author moon
 * @since 2023-12-08
 */
@Getter
@Setter
@TableName("batch_job_config")
public class JobConfig {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 名称 
     */
    @TableField("name")
    private String name;

    /**
     * 描述信息
     */
    @TableField("description")
    private String description;

    /**
     * 监听文件: 监听的单个文件，会监听该文件变化，匹配监听目录
     */
    @TableField("monitor_file")
    private String monitorFile;

    /**
     * 加载文件: 加载的单个文件，会加载该文件内容，匹配监听目录
     */
    @TableField("load_file")
    private String loadFile;

    /**
     * 列标记: 列标记，用于匹配列
     */
    @TableField("column_mark")
    private String columnMark;

    /**
     * 列名称: 列名称，用于匹配列
     */
    @TableField("column_name")
    private String columnName;

    /**
     * 执行器ip: 任务执行的ip地址
     */
    @TableField("allow_ip")
    private String allowIp;

    /**
     * 允许最早执行时间: 格式: HH:mm:ss
     */
    @TableField("allow_time")
    private String allowTime;

    /**
     * 最大限制: 最大限制，当达到最大限制时，任务将不会再执行
     */
    @TableField("maximum_limit")
    private Integer maximumLimit;

    /**
     * 任务url: 调用的api地址，支持http和https
     */
    @TableField("api_url")
    private String apiUrl;

    /**
     * 任务参数: 调用api时传递的参数，支持json格式
     */
    @TableField("api_params")
    private String apiParams;

    /**
     * 格式化函数: 格式化函数，用于格式化返回的数据: 00-表达式;01-Jar包
     */
    @TableField("format_func")
    private String formatFunc;

    /**
     * jar包名称: 格式化函数为jar包时，需要指定jar包名称
     */
    @TableField("jar_name")
    private String jarName;

    /**
     * 类名: 格式化函数为jar包时，需要指定类名
     */
    @TableField("class_name")
    private String className;

    /**
     * 表达式: 格式化函数为表达式时，需要指定表达式
     */
    @TableField("express")
    private String express;

    /**
     * 结果文件: 结果文件，用于保存结果
     */
    @TableField("result_file")
    private String resultFile;

    /**
     * 结果标记: 结果标记，用于匹配结果
     */
    @TableField("result_flag")
    private String resultFlag;

    /**
     * 状态: 0-未启用;1-启用;
     */
    @TableField("status")
    private Integer status;

    /**
     * (optional)远程服务器ip
     */
    @TableField("remote_server_ip")
    private String remoteServerIp;

    /**
     * (optional)远程服务器端口
     */
    @TableField("remote_server_port")
    private Integer remoteServerPort;

    /**
     * (optional)远程服务器用户名
     */
    @TableField("remote_server_user")
    private String remoteServerUser;

    /**
     * (optional)远程服务器密码
     */
    @TableField("remote_server_password")
    private String remoteServerPassword;

    /**
     * (optional)远程服务器路径
     */
    @TableField("remote_server_path")
    private String remoteServerPath;

    /**
     * 创建用户
     */
    @TableField("create_uid")
    private String createUid;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改用户
     */
    @TableField("modify_uid")
    private String modifyUid;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    private LocalDateTime modifyTime;
}
