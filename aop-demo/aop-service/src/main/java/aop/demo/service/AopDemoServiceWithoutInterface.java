package aop.demo.service;

import org.springframework.stereotype.Service;

/**
 * @author: chenyin
 * @date: 2019-10-29 15:05
 */
@Service
public class AopDemoServiceWithoutInterface {

    public String sayHello() {
        String ret = "hello(normal method)";
        System.out.println(ret);
        return ret;
    }

    public final String sayHelloFinal() {
        String ret = "hello(final method)";
        System.out.println(ret);
        return ret;
    }

}
