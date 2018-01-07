# ssm(springmvc + spring + mybatis) 环境搭建
#### maven 相关jar的引入
-  主要相关spring的包，6个主要的相关包
   Spring-core、spring-context、spring-web、spring-beans、spring-tx、spring-test、spring-context-support
-  springmvc包
   spring-webmvc
- spring整合数据库需要的包
  spring-mybatis,spring-jdbc,druid(连接池)
- mysql相关的包
  mysql-connector-java, mybatis
- 格式化对象
  fastjson, jackson-annotations, jackson-databind, jackson-core
- freemarker 的模板语言
  freemarker
- shiro相关的包
 shiro-core shiro-web shiro-spring
#### web.xml编写
前端控制器(DispatcherServlet)，用于拦截所有的请求，改变默认加载springmvc.xml
#### springmvc.xml 
# sso（single sign on）单点登录系统
# apache shiro 权限管理
# captcha(japtcha and kaptcha) 验证码
# 中文乱码（get 和 post）解决
