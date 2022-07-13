# coding=utf-8
import json
import random
import matplotlib.pyplot as plt
import numpy as np
from sklearn.linear_model import LinearRegression
from sklearn.preprocessing import PolynomialFeatures
import requests

from src.MqttClientSend import MyMqttSend
from src.MqttClientSub import MyMqttSub

tuijianwaterDegree=0
tuijianfeed=0
m1 = MyMqttSend()


def on_message_tuijianctrl(client, userdata, msg):
    print("主题:",msg.topic," 消息:")
    print(str(msg.payload.decode('utf-8')))
    msg = str(msg.payload.decode('utf-8'))
    if (msg == "0"):
        pass
    elif (msg == "1"):
        jsonFish = json.loads(requests.get("http://localhost:8090/fish").content)

        fishsize_data, feed_data, waterdegree = [], [], []

        for i in range(0, jsonFish.__len__()):
            fishsize_data.append(jsonFish[i]['fishSize'])
            feed_data.append(jsonFish[i]['feed'])
            waterdegree.append(jsonFish[i]['waterDegree'])
        global tuijianfeed,tuijianwaterDegree
        tuijianwaterDegree=getBestIndex(waterdegree, fishsize_data)
        tuijianfeed=getBestIndex(feed_data, fishsize_data)





def getBestIndex(myx,fishsize_data):
    # 特征构造
    pf = PolynomialFeatures(degree=2)
    # 创建线性模型
    linear_reg = LinearRegression()
    linear_reg.fit(pf.fit_transform(myx), fishsize_data)
    # 用特征构造数据进行预测
    xx = np.linspace(0, 5, 100)  # 生成密集点
    xx2 = pf.transform(xx[:, np.newaxis])  # 转换格式
    yy2 = linear_reg.predict(xx2)
    index = 0
    _max = 0
    for i in range(0, yy2.__len__()):
        if (_max < yy2[i]):
            index = i
            _max = yy2[i]
    # 此时的最佳值为xx[index]
    return xx[index]

def on_message_wdawd(client, userdata, msg):
    print("主题:",msg.topic," 消息:")
    print(str(msg.payload.decode('utf-8')))
    #这里写接到消息干嘛，主要是使逗鱼的电机开启
    msg = str(msg.payload.decode('utf-8'))
    global m1
    if (tuijianwaterDegree > int(msg)):
        m1.send("tmpctrl","1")
    elif (tuijianwaterDegree < int(msg)):
        m1.send("tmpctrl", "0")


if __name__ == '__main__':

    m2 = MyMqttSub("tuijianctrl", on_message_tuijianctrl)
    m2.start()

    m4 = MyMqttSub("waterTemp", on_message_wdawd)
    m4.start()



