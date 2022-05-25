package com.cc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("blog")
public class Blog implements Serializable {
    @TableId(value = "bid", type = IdType.AUTO)
    private Integer bid;
    private Integer uid;
    private String title;
    private String description;
    private String content;
    //    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private String time;
}
