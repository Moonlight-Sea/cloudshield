package pers.sea.shield.common.core.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 行业类别
 *
 * @author moon on 2024/5/15 by idea
 */
@Setter
@Getter
public class IndustryDto {
    
    private String category;
    private String largeCategory;
    private String middleCategory;
    private String smallCategory;
    private String name;
    private String description;
    

    public String toString(){
        String enumName = this.category;
        String parent = null;
        if (this.largeCategory != null) {
            enumName = this.largeCategory;
            parent = this.category;
        }
        if (this.middleCategory != null) {
            enumName = this.middleCategory;
            parent = this.largeCategory;
        }
        if (this.smallCategory != null) {
            enumName = this.smallCategory;
            parent = this.middleCategory;
        }
        return String.format("%s%s(\"%s\", \"%s\", \"%s\", \"%s\")",
                this.category,
                enumName,
                enumName,
                parent,
                this.name,
                this.description);
    }
}
