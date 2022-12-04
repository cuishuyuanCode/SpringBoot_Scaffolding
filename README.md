# SpringBoot 项目脚手架



提供SpringBoot + maven脚手架，可快速拉取到本地进行项目搭建；

*如果你已经有项目基础，请忽略该文章*


## 配置项目

### 1 插件配置
当使用idea时，下载Lombok插件并应用。否则@Data注解无法被IDEA识别

### 2 Spring扫描注入
如果想要修改文件目录或名称，需调整以下部分
```
@ComponentScan(basePackages = {"com.csy.demo.service.*","com.csy.demo.web"})
@MapperScan(basePackages = {"com.csy.demo.mapper"})
```
将目录调整为你所修改的目录即可

### 3 mysql配置
本文基于笔者本地mysql版本所配置，url含有&usSSL=false；在低版本的mysql中不需要配置该参数

### 4 服务端口
可在application.yaml中进行配置



