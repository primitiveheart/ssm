# ssm(springmvc + spring + mybatis) 环境搭建
#### maven 相关jar的引入
-  主要相关spring的包，6个主要的相关包<br>
   Spring-core、spring-context、spring-web、spring-beans、spring-tx、spring-test、spring-context-support
-  springmvc包<br>
   spring-webmvc
- spring整合数据库需要的包<br>
  spring-mybatis,spring-jdbc,druid(连接池)
- mysql相关的包<br>
  mysql-connector-java, mybatis
- 格式化对象<br>
  fastjson, jackson-annotations, jackson-databind, jackson-core
- freemarker 的模板语言<br>
  freemarker
- shiro相关的包<br>
 shiro-core shiro-web shiro-spring
- kaptcha相关的包<br>
 kaptcha
#### web.xml编写
前端控制器(DispatcherServlet)，用于拦截所有的请求，改变默认加载springmvc.xml
#### springmvc.xml 
# js
#### jquery ui
#### jquery supersized
需要的supersized.js和supersized.css和自己编写supersized-init.js
1. 自动调整等比例图片并充满整个浏览器屏幕
2. 循环展示图片，支持滑动和淡出淡入等多种图片切换效果
3. 导航按钮，支持键盘方向键导航（未实现）
# css

# sso（single sign on）单点登录系统
# apache shiro 权限管理
# captcha(japtcha and kaptcha) 验证码
#### kaptcha生成过程
 1. 在xml在配置
# 中文乱码（get 和 post）解决
