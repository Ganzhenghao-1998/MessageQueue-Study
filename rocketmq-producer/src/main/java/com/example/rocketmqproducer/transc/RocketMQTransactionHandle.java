package com.example.rocketmqproducer.transc;

import cn.hutool.core.util.RandomUtil;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.messaging.Message;

/**
 * @author Ganzhenghao
 * @version 1.0
 * @date 2022/1/7 15:04
 */
@RocketMQTransactionListener
// 个人猜测 @RocketMQTransactionListener 里的属性 String rocketMQTemplateBeanName() default "rocketMQTemplate"; 是指定对哪个RocketMQTempalate进行事务控制
// @ExtRocketMQTemplateConfiguration 可以创建新的RocketMQTemplate 具体看属性
public class RocketMQTransactionHandle implements RocketMQLocalTransactionListener {

    RocketMQLocalTransactionState[] states = new RocketMQLocalTransactionState[]{RocketMQLocalTransactionState.COMMIT, RocketMQLocalTransactionState.ROLLBACK, RocketMQLocalTransactionState.UNKNOWN};

    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        //执行本地事务


        // 打印参数
        System.out.println("传给事务的参数 ==> " + arg);
        //返回随机状态
        int randomInt = RandomUtil.randomInt(0, 3);

        System.out.println("执行本地事务方法,返回的状态为: " + states[randomInt]);
        return states[randomInt];
    }

    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message msg) {

        /*        RocketMQLocalTransactionState.COMMIT,RocketMQLocalTransactionState.ROLLBACK,RocketMQLocalTransactionState.UNKNOWN*/

        //返回随机状态
        int randomInt = RandomUtil.randomInt(0, 3);
        System.out.println("本地事务检查方法,返回的状态为: " + states[randomInt]);

        return states[randomInt];
    }
}
