# management

本项目为图书管理系统的面试笔试项目，开发框架为基础ssh框架
可于src/main/resources目录下的log4j.properties文件修改日志输出路径

项目使用角色进行权限控制，通过添加@AuthMethod以及@AuthClass进行权限控制

可通过controller包中添加方法并加注解@RequestMapping(value="")的方式暴露需要的Restful接口

