package com.mawang.qianrushu;

import com.alibaba.fastjson.JSON;
import com.mawang.qianrushu.mqtt.MqttSendClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", produces = "application/json;charset=UTF-8")
public class FishController {
    @Autowired
    private FishService fishService;

    @GetMapping("/fish")
    public Object foo() {
        return JSON.toJSONString(fishService.list());
    }
}
