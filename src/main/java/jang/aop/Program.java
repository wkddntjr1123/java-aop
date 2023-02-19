package jang.aop;

import jang.aop.config.AppConfig;
import jang.aop.entity.Exam;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Program {
    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        Exam exam = (Exam) applicationContext.getBean("jangExamProxy");
        System.out.println(exam.total());
        System.out.println(exam.avg());
    }
}