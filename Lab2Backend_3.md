# Note for Lab2_3 （2020.3.18）By TianyiZhang
### 1. 开发进度更新说明
* Service层面的最基本功能已经初步实现，可以进行register和login操作（还没有经过大量样例的检测）
* 相比之前（3.16 Version）register 过程中加入了对于密码的BCrypt加密，密码以加密形式存储在H2数据库中  
login加入了jwt令牌的生成，并且补充了JwtRequestFilter中未编写的部分(用于解析后续请求中的token)
* 由于需要处理Spring Security的相关问题，对于UserDetails，AuthenticationManager等抽象接口进行了实现
* 由于需要生成Security验证所需要的类并调用方法，增加了AuthService的private属性
* 区分了不同Exception的序列号和Exception类型并增加了合法用户名不存在的Exception
* CorsConfig中增加了跨域设置规定了允许的请求方法和端口等信息

### 2. 初步规范说明
* 用户名密码等规范见Lab2Backend_2
* register返回值
    * 如果用户名等信息均符合规范会返回一个关于User对象的json字串
    * 如果出现任意异常会返回异常对应的相关提示字串
* login返回值
    * 如果login失败会返回相应的提示字串（此处如果先行判断username不合法的情况还没有完全统一，后续会修改）
    * 如果login成功会返回login的相关信息以及此时系统生成的token令牌
    * token令牌约定：
        * validity持续时长目前暂定为1800000ms,即30min
        * secret秘钥暂定为"FudanSE2020"
        * 目前写在login返回值的最后

### 3. 后续开发任务
* 完善令牌的提取核验机制为后续功能实现做准备
* 完善注册登录机制应对更多样例
* 增强代码统一性，在必要的地方增加注释
* 进一步搞清楚Spring运行的原理和机制
* 开发文档的编写