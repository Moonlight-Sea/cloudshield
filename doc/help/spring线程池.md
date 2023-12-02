# SpringBootApplication 线程池

## 在 springBootApplication 中配置线程池

[Spring原理之@Async](https://blog.csdn.net/weixin_42272869/article/details/116117202)

**Note: @Async注解使用细节**
> 1. @Async注解一般用在方法上，如果用在类上，那么这个类所有的方法都是异步执行的；
> 2. @Async可以放在任何方法上，哪怕你是private的（若是同类调用，请务必注意注解失效的情况~~~）
> 3. 所使用的@Async注解方法的类对象应该是Spring容器管理的bean对象
> 4. @Async可以放在接口处（或者接口方法上）。但是只有使用的是JDK的动态代理时才有效，`CGLIB`会失效。因此建议：统一写在实现类的方法上
> 5. 需要注解@EnableAsync来开启异步注解的支持
> 6. 若你希望得到异步调用的返回值，请你的返回值用Future变量包装起来

```java
@SpringBootApplication
@EnableAsync
public class SpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

    @Bean
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(100);
    }
}