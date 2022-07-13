package com.mawang.qianrushu.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mawang.qianrushu.Fish;
import com.mawang.qianrushu.FishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Component
public class AcceptTopicHandler {

    @Autowired
    private FishService fishService;

    public void paseJson(String jsonString){
        Fish fish=new Fish();
        JSONObject user = JSONObject.parseObject(jsonString);
        fish.setFishSize(user.getInteger("fishSize"));
        fish.setFeed(user.getInteger("feed"));
        fish.setLight(user.getByte("light"));
        fish.setWaterDegree(user.getInteger("waterDegree"));
        fishService.save(fish);
    }

}
