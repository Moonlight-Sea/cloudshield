
[mybatis plus 数据安全保护](https://baomidou.com/pages/e0a5ce/)

YML 配置：
```yaml
// 加密配置 mpw: 开头紧接加密内容（ 非数据库配置专用 YML 中其它配置也是可以使用的 ）
spring:
  datasource:
    url: mpw:qRhvCwF4GOqjessEB3G+a5okP+uXXr96wcucn2Pev6Bf1oEMZ1gVpPPhdDmjQqoM
    password: mpw:Hzy5iliJbwDHhjLs1L0j6w==
    username: mpw:Xb+EgsyuYRXw7U7sBJjBpA=
```

密钥加密：
```java
// 生成 16 位随机 AES 密钥
String randomKey = AES.generateRandomKey();

// 随机密钥加密
String result = AES.encrypt(data, randomKey);
```

如何使用：
```args
// Jar 启动参数（ idea 设置 Program arguments , 服务器可以设置为启动环境变量 ）
--mpw.key=d1104d7c3b616f0b
```