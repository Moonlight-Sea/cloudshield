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
 * 字典表
 * </p>
 *
 * @author moon
 * @since 2023-12-12
 */
@Getter
@Setter
@TableName("dispatch_dictionary")
public class Dictionary {

    @TableId(value = "uuid", type = IdType.ASSIGN_UUID)
    private String uuid;

    /**
     * 字典代码
     */
    @TableField("code")
    private String code;

    /**
     * 字典值
     */
    @TableField("value")
    private String value;

    /**
     * 字典中文
     */
    @TableField("name_cn")
    private String nameCn;

    /**
     * 字典中文描述
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
