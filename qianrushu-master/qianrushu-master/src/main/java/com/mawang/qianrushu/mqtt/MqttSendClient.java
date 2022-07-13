package com.mawang.qianrushu.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MqttSendClient {
    
    @Autowired
    private MqttSendCallBack mqttSendCallBack;

    @Autowired
    private MqttProperties mqttProperties;

    private static MqttClient mqttClient;

    private static MqttClient getClient() {


        return mqttClient;
    }

    private static void setClient(MqttClient client) {


        MqttSendClient.mqttClient = client;
    }

    /**
     * 客户端连接
     * @return
     */
    public void connect(){
        MqttClient client = null;

        try {


            //String uuid = UUID.randomUUID().toString().replaceAll("-",""); //设置每一个客户端的id
            client = new MqttClient(mqttProperties.getHostUrl(),mqttProperties.getClientId() , new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(mqttProperties.getUsername());
            options.setPassword(mqttProperties.getPassword().toCharArray());
            options.setConnectionTimeout(mqttProperties.getTimeout());
            options.setKeepAliveInterval(mqttProperties.getKeepAlive());
            options.setCleanSession(true);
            options.setAutomaticReconnect(false);

            MqttSendClient.setClient(client);
            try {


                // 设置回调
                client.setCallback(mqttSendCallBack);
                client.connect(options);
            } catch (Exception e) {

                e.printStackTrace();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    /**
     * 发布，默认qos为0，非持久化
     *
     * @param topic 主题名
     * @param pushMessage 消息
     */
    public void publish(String topic, String pushMessage) {
        publish(0, false, topic, pushMessage);
    }

    /**
     * 发布
     *
     * @param qos
     * @param retained
     * @param topic
     * @param pushMessage
     */
    public void publish(int qos, boolean retained, String topic, String pushMessage) {
        MqttMessage message = new MqttMessage();
        message.setQos(qos);
        message.setRetained(retained);
        message.setPayload(pushMessage.getBytes());
        MqttTopic mTopic = MqttSendClient.getClient().getTopic(topic);
        if (null == mTopic) {
            System.out.println("主题不存在: "+mTopic);
        }
        try {
            System.out.println();
            mTopic.publish(message);
            System.out.println("消息发送成功");
        } catch (Exception e) {
            System.out.println("mqtt发送消息异常:"+e);
        }
    }
}
