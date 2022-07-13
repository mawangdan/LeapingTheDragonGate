package com.mawang.qianrushu;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("fish")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fish {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String userName;

    private Integer waterDegree;
    private Integer feed;
    private Integer fishSize;
    private Byte light;
    private LocalDateTime gmtCreate;

    public static void main(String[] args) {
        String userJson = JSON.toJSONString(new Fish(1L,"2",333,3,12,(byte)1, null));
        JSONObject user = JSONObject.parseObject(userJson);
        System.out.println(user.getInteger("waterDegree"));
        System.out.println(user.getInteger("feed"));
        System.out.println(user.getInteger("fishSize"));
        System.out.println(user.getByte("light"));
    }
}
