package com.walker.producer;

import com.alibaba.fastjson.JSON;
import com.walker.beans.Message;
import com.walker.common.threadUtils.ProducerThread;
import com.walker.common.utils.ResultInfo;
import com.walker.common.utils.ReturnCodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * kafka生产者
 *
 * @author Walker
 * @date 2019/3/11 下午8:15
 */
@RestController
@RequestMapping("/kafka")
public class KafkaProducer {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final KafkaTemplate kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * 发送消息
     *
     * @param msg
     * @return
     */
    @PostMapping("send")
    public ResultInfo send(@RequestBody Message msg) {
        ResultInfo resultInfo = new ResultInfo();
        if (msg == null) {
            resultInfo.setMsg(ReturnCodeUtil.ReturnCode.EMPTY_PARAMS.getMsg());
            resultInfo.setStatus(ReturnCodeUtil.ReturnCode.EMPTY_PARAMS.getStatus());
            return resultInfo;
        }
        logger.info("生产者生产消息：" + msg.toString());
        String jsonMsg = JSON.toJSONString(msg);
        System.out.println(jsonMsg);
        //kafka服务配置了五个分区，会对每个topic生效
        kafkaTemplate.send("topic1", 1, "walker", jsonMsg);
        return resultInfo;
    }

    /**
     * 发送字符串
     *
     * @param msg
     * @return
     */
    @RequestMapping("sendStr")
    public ResultInfo sendStr(String msg) {
        ResultInfo resultInfo = new ResultInfo();
        if (StringUtils.isEmpty(msg)) {
            resultInfo.setMsg(ReturnCodeUtil.ReturnCode.EMPTY_PARAMS.getMsg());
            resultInfo.setStatus(ReturnCodeUtil.ReturnCode.EMPTY_PARAMS.getStatus());
            return resultInfo;
        }
        logger.info("生产者生产消息：" + msg);
        kafkaTemplate.send("topic1", "walker", msg);
        return resultInfo;
    }

    /**
     * 通过多线程发送消息
     *
     * @return
     */
    @RequestMapping("sendMsg")
    public ResultInfo sendMsg() {
        ResultInfo resultInfo = new ResultInfo();

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ProducerThread producerThread = new ProducerThread(kafkaTemplate, "topic1");
        executorService.execute(producerThread);
        //关闭线程池
        //executorService.shutdown();
        return resultInfo;
    }
}
