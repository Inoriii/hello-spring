# hello-spring

1.多模块拆分

2.引入依赖

3.引入mysql+mybais

4.日志

5.全局异常处理

6.引入redis

7.mybais多数据源,使用德鲁伊数据连接池,动态数据源切换
遗留问题
5.数据库配置主从

8.使用maven-resources-plugin插件将其他模块的配置文件打包到任意模块下；
使用maven-assembly-plugin插件将bin目录、jar包、yml打包至根目录；
运行 jar 包读取外部配置文件(默认：相同目录下的配置文件优先级要高于jar包中的配置文件。)