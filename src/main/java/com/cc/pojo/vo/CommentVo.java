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
public class CommentVo {
    @TableId(value = "cid")
    private Integer cid;
    private String content;
    private String stamp;
    private String title;
    private String username;
}
