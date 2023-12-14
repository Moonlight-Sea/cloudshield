package pers.sea.shield.dispatch.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 接口表
 * </p>
 *
 * @author moon
 * @since 2023-12-12
 */
@Getter
@Setter
@TableName("dispatch_api_info")
public class ApiInfo {

    /**
     * 唯一编码
     */
    @TableId(value = "uuid", type = IdType.ASSIGN_UUID)
    private String uuid;

    /**
     * 接口编码 unique
     * 6位代码: B00001; 前两位: 业务部门; 后四位: 自增
     */
    @TableField("item_code")
    private String itemCode;

    /**
     * 名称
     */
    @TableField("name")
    private String name;

    /**
     * 接口地址
     */
    @TableField("api_url")
    private String apiUrl;

    /**
     * 接口方法:get,post,put,delete
     */
    @TableField("api_method")
    private String apiMethod;

    /**
     * 参数模板
     */
    @TableField("api_template")
    private String apiTemplate;

    /**
     * 返回类型:00-JSON;01-XML
     */
    @TableField("result_type")
    private String resultType;

    /**
     * 返回模板
     */
    @TableField("result_template")
    private String resultTemplate;

    /**
     * 返回地址
     */
    @TableField("result_url")
    private String resultUrl;

    /**
     * 描述信息
     */
    @TableField("description")
    private String description;

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
