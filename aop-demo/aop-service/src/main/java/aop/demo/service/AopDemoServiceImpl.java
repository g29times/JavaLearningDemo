package aop.demo.service;

/**
 * @author: chenyin
 * @date: 2019-10-29 15:05
 */
public class AopDemoServiceImpl implements AopDemoService {

    @Override
    public String sayHello() {
        String ret = "hello this is aop service";
        System.out.println(ret);
        return ret;
    }
}
