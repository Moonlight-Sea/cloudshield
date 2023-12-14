package pers.sea.shield.dispatch.common.enums;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * ParamInfoMustInputCheckEnum
 *
 * @author moon on 12/14/2023
 */
public enum ParamInfoMustInputCheckEnum implements IEnum<Integer> {

    MUST_INPUT(1),
    MUST_NOT_INPUT(2);

    private final int value;

    ParamInfoMustInputCheckEnum(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
