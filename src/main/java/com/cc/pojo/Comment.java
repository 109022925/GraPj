package com.cc.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("comment")
public class Comment implements Serializable {
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;
    private String content;
    private Date stamp;
    private Integer bid;
    private Integer uid;
}
