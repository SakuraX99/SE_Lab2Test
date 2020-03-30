# Lab2 个人项目文档
### Name: Tianyi Zhang   &nbsp;   ID: 17307110223

## 1. 在项目中负责的内容
* 编写项目的需求规划（Devcloud）
* Web后端代码模板的研究（认知过程）
* 根据项目需求编写后端代码
* 部分与前端的对接工作
* 后端功能的部署功能测试
---
## 2. 项目开发的基本逻辑流程
* ***对于Spring框架的理解和研究，由于之前没有接触过这方面的内容，前两天对于Spring框架的运作模式进行了探索，详见附件Lab2_Backend相关开发文档***
* ***本项目使用的是MVC设计模式，经过学习了解，MVC设计模式是使用MVC（Model View Controller 模型-视图-控制器）设计创建Web应用程序的模式***
    * Model（模型）是应用程序中用于处理应用程序数据逻辑的部分，通常模型对象负责在数据库中存取数据
    * View（视图）是应用程序中处理数据显示的部分，通常视图是依据模型数据创建的
    * Controller（控制器）是应用程序中处理用户交互的部分，通常控制器负责从视图读取数据，控制用户输入，并向模型发送数据
    * 本Web项目由于前后端分离，主要包含了Model和Controller的组件
* ***项目的开发需求分析，要实现的部分：***
    * 编写与需求相关的User、Authority、Meeting对象的类和请求接收request类，使Request能在IOC容器中获得前端发来的数据并顺利封装
    * 编写Register、Login、Holdmeeting方法
    * 编写在上述方法中需要用到的抛出异常
    * 编写Json Web Token的令牌过滤检测过程，并在Login中颁布限时令牌
    * 编写Spring Security中的安全配置以及相关配合JwtFilter的实现
* ***基于项目给出的SpringMVC模板进行开发编写***
---
## 3.项目开发具体内容
* ***本项目的Spring框架运行流程***<img src="User Guide.png">
* ***方法实现细节***
    * 拦截过滤器（Filter）
        * 修改Security配置中对于不同访问路径的限制，暂时对/register和/login不作限制，其他诸如/holdmeeting的请求需要经过JwtFilter的过滤检查操作
        * 每次login中使用JwtTokenUtil中提供的方法使用User实例生成Token令牌写入Response中
        * 在JwtRequestFilter（Spring流程进行自动实例化）的doFilterInternal中先提取Token利用相关方法提取Token中信息比对判断令牌是否有效并对有效用户予以放行
        * 每次生成的Token对象会被封装进Json格式的response对象中
    * 控制器（Controller）
        * 在AuthController中编写register、login、holdmeeting方法使用JPA注解获取IOC容器中封装好的Request对象并导入相应的处理流程
        * 在package service中增加LoginService、RegisterService、HoldmeetingService对象封装相应请求的处理方法
            * RegisterService中包含了对于RegisterRegister对象中数据合法性的校验（InformationCheck）、程序与数据库的交互以及生成完毕的User对象的返回（Register）
            * LoginService的login方法中包含了login流程的密码转码校验（调用SampleManager的authenticate方法并查询H2数据库）和token生成，最后会返回验证成功的相应response对象（附带token）
            * HoldmeetingService中的holdmeeting完成了会议名简称的重复性校验和meeting对象的构建并封装进response对象进行回复
        * 扩充Exception中的异常类型使之能处理Service中可能出现的各种异常
    * 跨域设置（CorsConfig），目前把跨域设置设置为"*",使公网域名可以访问
    * 数据格式规范参见小组统一的"前后端接口参考文档"该部分以及前后端协调主要由宋子阳同学负责

## 4.项目开发的经验总结和收获
* 了解学习不熟悉的开发框架需要阅读官方文档配合QuickStart等项目进行快速上手，最好尽可能多的了解框架的运行原理
* 代码编写需要定义清晰模块化逻辑缜密
* 了解了MVC设计模式，认识到了设计模式在软件开发中扮演的角色
* 对于关系型数据库中的一对多、多对多的关系有了初步认识
* 了解了Json Web Token的令牌细节以及保存状态的方式
* 团队配合沟通需要简洁高效最好通过文档
  
## 5.项目开发的反思
* 代码能力需要加强，方法的传参方式需要更加考究，条件判断方式需要使用代价更小的方式
* 异常抛出需要进行统一规划，Service返回值需要进行统一
* 对于数据库了解过少，需要更多学习数据库相关理论知识
* JPA注解在开发中能发挥重要作用，需要进一步学习了解
* 团队开发的相关规范要尽早确定避免后期麻烦
* 团队开发需要多相互学习请教
   