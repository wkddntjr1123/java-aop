## java-aop, spring-aop

1. 메소드에 core-concern, cross-cutting-concern 함께 구현
2. java.reflection.Proxy를 이용해 cross-cutting-concern 분리
3. spring을 이용하여 AOP 구현
    - around, before, afterReturning, afterThrowing
    - target의 point-cut, join-point 설정
    - advisor와 point-cut을 통합한 설정 간소화 (NameMatchMethodPointcutAdvisor)
    - 정규식을 통한 pointcut 설정 (RegexpMethodPointcutAdvisor)