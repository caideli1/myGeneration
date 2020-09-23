package com.beyondsoft.studysharding.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_udict")
public class Dict {
    private Long dictid;
    private String ustatus;
    private String uvalue;
}
