ping++ Java 版本 server sdk 示例程序。

该示例演示如何获取 charge 以及如何接收和验证 webhooks

基于 maven 构建。
使用 struts2 框架，内置 jetty 容器。

#### 运行方式

##### 生成 war 包

    执行  mvn clean install 可以生产 war 包。

##### 运行war

jdk 命令运行 
   
    可以使用 jdk 命令 执行：java -jar sunkai-standalone.war
    然后浏览器打开 http://127.0.0.1:8090 
    
tomcat 运行

    把 war 包放到 tomcat 所在路径的 webapp 目录下即可。
    
