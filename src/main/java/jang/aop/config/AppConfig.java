package jang.aop.config;

import jang.aop.advice.LogAfterReturningAdvice;
import jang.aop.advice.LogAfterThrowingAdvice;
import jang.aop.advice.LogAroundAdvice;
import jang.aop.advice.LogBeforeAdvice;
import jang.aop.entity.Exam;
import jang.aop.entity.JangExam;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.NameMatchMethodPointcutAdvisor;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Exam jangExam() {
        return new JangExam(80, 100, 90, 100);
    }

    @Bean
    public ProxyFactoryBean jangExamProxy() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(jangExam());
        proxyFactoryBean.setInterceptorNames("logAroundAdvice", "classicPointCutAdvisor", "classicRegPointCutAdvisor", "logAfterThrowingAdvice");
        return proxyFactoryBean;
    }

    @Bean
    public LogAroundAdvice logAroundAdvice() {
        return new LogAroundAdvice();
    }

    @Bean
    public LogBeforeAdvice logBeforeAdvice() {
        return new LogBeforeAdvice();
    }

    @Bean
    public LogAfterReturningAdvice logAfterReturningAdvice() {
        return new LogAfterReturningAdvice();
    }

    @Bean
    public LogAfterThrowingAdvice logAfterThrowingAdvice() {
        return new LogAfterThrowingAdvice();
    }

    @Bean
    public NameMatchMethodPointcutAdvisor classicPointCutAdvisor() {
        NameMatchMethodPointcutAdvisor pointcutAdvisor = new NameMatchMethodPointcutAdvisor();
        pointcutAdvisor.setAdvice(logBeforeAdvice());
        pointcutAdvisor.setMappedNames("total", "avg");
        return pointcutAdvisor;
    }

    @Bean
    public RegexpMethodPointcutAdvisor classicRegPointCutAdvisor() {
        RegexpMethodPointcutAdvisor pointcutAdvisor = new RegexpMethodPointcutAdvisor();
        pointcutAdvisor.setAdvice(logAfterReturningAdvice());
        pointcutAdvisor.setPattern(".*to.*");
        return pointcutAdvisor;
    }

}
