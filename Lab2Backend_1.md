# Note for Lab2_1 （2020.3.16）By Tianyizhang
### 1. 初步理解的Spring服务器端工作流程
* AuthController作为Controller使用注释关联负责request请求的接收和数据的回复
* AuthController中在return语句中调用AuthService.login或者AuthService.register完成响应体的返回
* 认为应当在AuthService.login和AuthService.register中完成的工作：  
    * Register  
        * 验证request请求中的信息是否合法（不符合规范或者已被注册），若不合法返回null并在exception中提示注册信息不合法  
        * 如果合法，应当在数据库中添加相应数据并且返回该User对象  
    * Login  
        * 在数据库中查找若用户名不存在返回用户名不存在  
        * 在数据库中查找到相关用户核对password，若核准，返回登录成功  
* JwtUserDetailsService类用于将用户信息转化为json个是返回给用户  
  
###2. 目前的主要疑惑  
* 没有完全明确如何操作java reposity数据库
* jwt中的函数究竟需要实现哪些内容
* Spring框架的具体代码工作流程还没有完全搞清楚