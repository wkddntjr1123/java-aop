package jang.aop;

import jang.aop.entity.Exam;
import jang.aop.entity.JangExam;

import java.lang.reflect.Proxy;

public class Program {
    public static void main(String[] args) {
        Exam exam = new JangExam(80, 100, 90, 100);
        Exam examProxy = (Exam) Proxy.newProxyInstance(JangExam.class.getClassLoader(), new Class[]{Exam.class}, (proxy, method, args1) -> {
            long start = System.currentTimeMillis();

            Object result = method.invoke(exam, args1);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            long end = System.currentTimeMillis();
            String message = (end - start) + "ms 시간이 걸렸습니다.";
            System.out.println(message);

            return result;
        });

        System.out.println(examProxy.total());

    }
}