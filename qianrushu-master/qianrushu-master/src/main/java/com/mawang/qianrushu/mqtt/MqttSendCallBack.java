package com.mawang.qianrushu.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Component
public class MqttSendCallBack implements MqttCallbackExtended {
    /**
     * 链接EMQ服务器后触发
     * @param reconnect
     * @param serverURI
     */
    @Override
    public void connectComplete(boolean reconnect, String serverURI) {
        System.out.println("————————————————-ClientID:{}——————————————"+"链接成功");
    }

    /**
     * 客户端连接断开后触发
     * 这里可以做重新链接操作
     */
    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("【MQTT-发送端】链接断开！");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("【MQTT-发送端】接收消息主题 : " + topic);
        System.out.println("【MQTT-发送端】接收消息Qos : " + message.getQos());
        System.out.println("【MQTT-发送端】接收消息内容 : " + new String(message.getPayload()));
    }

    /**
     * 发送消息回调
     * @param token
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {

        String[] topics = token.getTopics();

        if (topics!=null && topics.length>0){
            for (String topic : topics) {
                System.out.println("【MQTT-发送端】向主题：" + topic + "发送消息成功！");
            }
        }
        try {
            MqttMessage message = token.getMessage();
            byte[] payload = message.getPayload();
            String s = new String(payload, "UTF-8");
            System.out.println("【MQTT-发送端】消息的内容是：" + s);
        } catch (MqttException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
