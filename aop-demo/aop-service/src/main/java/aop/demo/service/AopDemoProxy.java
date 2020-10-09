package aop.demo.service;

/**
 * @author: chenyin
 * @date: 2019-11-06 11:58
 */
public class AopDemoProxy implements AopDemoService {

    private AopDemoService targetService = new AopDemoServiceImpl();

    @Override
    public String sayHello() {
        StringBuilder builder = new StringBuilder();
        builder.append("start");
        System.out.println("start");

        builder.append(targetService.sayHello());

        builder.append("end");
        System.out.println("end");

        return builder.toString();
    }
}
