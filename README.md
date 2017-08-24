## spring boot one (out of the box)

* 使用 thymeleaf 模板
* 使用 [程序猿DD spring-boot-starter-swagger](https://github.com/dyc87112/spring-boot-starter-swagger) 作为接口文档。*(localhost:8080/swagger-ui.html)*
* 使用 spring data jpa 操作db（mysql）(底层使用hibernate，较简洁轻量)
* 封装redisClient，方便使用redis，也可以直接使用spring redisTemplate，已配置好
* 自封装@cacheable注解， 方便自动过期式缓存使用，也可以直接使用spring @cacheable注解，已配置好

### todo
* 全局异常处理，自定义异常处理
* mongodb(spring data mongodb挺轻量，但mongo的官方插件morphia也不错)
* mq