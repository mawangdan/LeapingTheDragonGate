import time
import random

if __name__ == '__main__':
    i=0
    while True:
        #10s更新一次信息
        #这里通过IO读入信息
        # topic,msg

        print("topic: " +"waterHeight  "+str(random.choice([25.1, 25.2, 25.3, 25.4, 25.5,25.6, 25.7, 25.8, 25.9])))
        print("topic: " +"foodLast  "+str(random.choice([100])))

        print("topic: " +"waterTemp  "+str(random.choice([23.1, 23.2, 23.3, 23.4, 23.5, 23.6, 23.7, 23.8, 23.9, 23.0])))
        print("topic: " +"grassLight  "+str(random.choice(["off"])))
        time.sleep(20)
        i+=1
        if(i%2==0):
            print("topic: " + "FishSize  " + str(random.choice([249494,249496,249499,249493])))
