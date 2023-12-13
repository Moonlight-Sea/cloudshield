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
 * 请求参数表
 * </p>
 *
 * @author moon
 * @since 2023-12-12
 */
@Getter
@Setter
@TableName("dispatch_param_info")
public class ParamInfo {

    /**
     * 唯一编码
     */
    @TableId(value = "uuid", type = IdType.ASSIGN_UUID)
    private String uuid;

    /**
     * 接口唯一编码:00-通用
     */
    @TableField("api_uuid")
    private String apiUuid;

    /**
     * 进件参数
     */
    @TableField("code")
    private String code;

    /**
     * 中文名称
     */
    @TableField("name_cn")
    private String nameCn;

    /**
     * 映射参数
     */
    @TableField("mapping_code")
    private String mappingCode;

    /**
     * 数据类型00-string;01-int;02-double;03-array;
     */
    @TableField("data_type")
    private String dataType;

    /**
     * 必输校验0-非必输;1-必输
     */
    @TableField("must_input_check")
    private Integer mustInputCheck;

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
