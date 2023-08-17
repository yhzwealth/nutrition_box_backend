package com.wealth.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TargetQuestion{
    @TableId(type = IdType.INPUT)
    private Integer qId;

    private String content;
    private String AChoice;
    private String BChoice;
    private String CChoice;
    private String DChoice;
    private String EChoice;
    private String FChoice;
    private Integer IsMultipleChoice;
    private Integer belong;
}
