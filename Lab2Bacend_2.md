# Note for Lab2_2 （2020.3.16）By Tianyizhang
### 1. 目前对于该项目的模块化认识
* 目前我认为和后端的开发可以分为三个模块：
    * AuthService的编写实现Java控制regsiter和login的具体功能，后续实现更复杂的功能可能需要在AuthController中加入更多的请求接受功能  
        * 需要编写： AuthService.java, AuthController.java
    * Spring Security的相关问题，包括JWT需要用来维持login用户的连接状态以及返回数据的json转写，同时JwtRequestFilter还要配合Securityconfig完成安全验证的相关问题
        * 需要编写： JwtRequestFilter.java, SecurityConfig.java, JwtUserDetailsService.java
    * CorsConfig相关问题，这个目前还不是很清楚，跨域问题涉及到在服务器上部署时前后端的结合，如果实现复杂交互功能可能需要其他代码一起配合
        * 需要编写： CorsConfig.java 
* 该项目的数据库暂时使用的是轻量化的H2内存数据库，已经添加在dependency中，数据库模块的api已经给出
  

### 2. 目前我已经编写的部分
* AuthService.java 中register和login的基本功能已经实现（有较为详细的注释），后续增加功能需要修改（如达不到要求时输入框变红），login目前只写了密码的核对校验，后续还需和jwt结合维护login状态
* JwtRequestFilter.java 我经过查阅资料后给出了一个实验版本因为其他部分还没有补全目前还无法判断可行性，目前处于注释状态
* exception中编写了几种新的exception用于处理判断问题
* 我修改了config中index.js的api target指向Spring运行的ip和端口号，同样可以在Application.properties中修改Spring运行端口号
  
### 3. 目前使用的一些约定规范
* 版本情况
    * jdk 1.8.0
    * spring-boot-starter-parent 2.2.5
    * maven 3.6 及以上
* 登录信息逻辑
    * 用户名： 6-20个个字符，由大写字母，小写字母和数字构成
    * 密码： 8-20个字符，至少一个大写字母，一个小写字母和一个数字且只有这三种字符
    * 全名（fullname）： 英文fullName 2-4个单词，中间空格
    * 判断逻辑： 先判断用户名时候否合法，再判断密码是否合法，再判断全名是否合法，全部合法则开始注册，出现问题抛出异常
* 目前我的测试版本在我的github：<https://github.com/SakuraX99/SE_Lab2Test>