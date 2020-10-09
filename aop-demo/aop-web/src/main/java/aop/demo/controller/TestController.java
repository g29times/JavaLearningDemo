package aop.demo.controller;

import aop.demo.service.AopDemoServiceWithoutInterface;
import com.sun.tools.attach.VirtualMachine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author: chenyin
 * @date: 2019-10-24 10:45
 */
@RequestMapping("test")
@RestController
public class TestController {
    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @Resource
    private AopDemoServiceWithoutInterface aopDemoServiceWithoutInterface;

    /**
     * 硬编码实现
     *
     * @see aop.demo.agent.JavaAgent
     * @see aop.demo.agent.MyClassTransformer
     */
    @GetMapping("/javaAgentTest")
    public String javaAgentTest() {
        return aopDemoServiceWithoutInterface.sayHelloFinal();
    }

    @GetMapping("/attachAgentTest")
    public String attachAgentTest(String pid) throws Exception {
        if (StringUtils.isEmpty(pid)) {
            return "pid can not be empty";
        }
        VirtualMachine vm = VirtualMachine.attach(pid);
        String msg = "success";
        try {
            vm.loadAgent("D:\\WorkSpace\\Github\\JavaLearningDemo\\aop-demo\\java-agent-attach\\target\\java-agent-attach-0.0.1-SNAPSHOT.jar");
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        } finally {
            vm.detach();
        }
        return msg;
    }

}
