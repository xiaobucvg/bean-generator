# javaBean自动生成工具
使用方法：
1. 在resources目录下创建配置文件，命名为 beanconfig.properties
2. 在你的项目中执行 BeanUtil.generate() 等待自动生成javaBean

配置文件配置项：
1. （必须）jdbc.user 数据库用户名
2. （必须）jdbc.password 数据库密码
3. （必须）jdbc.url 数据库路径
4. （必须）jdbc.driver 数据库驱动的全路径名
5. （必须）bean.target 生成的javabean要存放在哪个目录（输入相对于项目路径的目录）
6. （必须）bean.package 生成的javabean的package信息（对应与target，用 ‘.’ 分割）
7.  bean.isSerializable 生成的javabean是否实现序列化接口

一个完整的配置文件实例：

```
jdbc.user=root
jdbc.password=199711
jdbc.url=jdbc:mysql://192.168.56.99:3306/java_qq
jdbc.driver=com.mysql.jdbc.Driver

bean.target=src/main/java/aa/bb/cc
bean.package=aa.bb.cc
bean.isSerializable=false
```