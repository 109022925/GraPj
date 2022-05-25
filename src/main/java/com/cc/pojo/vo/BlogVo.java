package com.cc.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class BlogVo {
    @TableId(value = "bid")
    private Integer bid;
    private String username;
    private String title;
    private String description;
    //    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private String time;
}
