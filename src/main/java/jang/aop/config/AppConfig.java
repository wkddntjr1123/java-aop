package jang.aop.config;

import jang.aop.advice.LogAfterReturningAdvice;
import jang.aop.advice.LogAfterThrowingAdvice;
import jang.aop.advice.LogAroundAdvice;
import jang.aop.advice.LogBeforeAdvice;
import jang.aop.entity.Exam;
import jang.aop.entity.JangExam;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
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
        proxyFactoryBean.setInterceptorNames("logAroundAdvice", "classicBeforeAdvisor", "logAfterReturningAdvice", "logAfterThrowingAdvice");
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
    public NameMatchMethodPointcut classicPointCut() {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("total");
        return pointcut;
    }
    
    @Bean
    public DefaultPointcutAdvisor classicBeforeAdvisor() {
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(classicPointCut());
        advisor.setAdvice(logBeforeAdvice());
        return advisor;
    }

}
