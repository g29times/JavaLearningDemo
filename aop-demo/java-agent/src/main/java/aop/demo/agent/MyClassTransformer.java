package aop.demo.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author: chenyin
 * @date: 2019-11-05 16:51
 */
public class MyClassTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!className.equals("aop/demo/service/AopDemoServiceWithoutInterface")) {
            return "DEFAULT".getBytes();
        }
        try {
            System.out.println("MyClassTransformer，当前类名:" + className);
            // TODO: 2019-11-05 搜索JVM的同路径下的class
            ClassPool classPool = ClassPool.getDefault();
            CtClass ctClass = classPool.get("aop.demo.service.AopDemoServiceWithoutInterface");

            CtMethod ctMethod = ctClass.getDeclaredMethod("sayHelloFinal");
            ctMethod.insertBefore("{ System.out.println(\"start\");}");
            ctMethod.insertAfter("{ System.out.println(\"end\"); }");

            byte[] bytes = ctClass.toBytecode();
            ctClass.defrost();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
