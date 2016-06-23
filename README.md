# 描述   

 这是一个基于netty的IM聊天项目  
 
### 编码方式   

  采用MessagePack进行编码  
  为什么采用MessagePack进行编码？  
  1. MessagePack比json序列化使用的字节流更少  
  2. MessagePack能支持基本的数据类型，支持list和map， 还支持自定义的数据类型， 序列化和反序列化一个javabean， 只要加上@MessagePackMessage的注解  
  3. 反序列化的时间要少于json  
  4. 比protocol buffers和thrift对程序的侵入更小，如果协议改变，无需执行任何脚本，直接改变协议体部分  
  
### 协议介绍  

  1. 字节流格式:  
  字节流格式采用Length-Value模式  
  Length固定占2字节，代表Value所占字节的长度  
  Value是MessagePack序列化后的值  
  
  2. 协议格式:  
  协议部分体现在MessagePack定义的内容。分为两部分:header(请求头)，body(请求体)。  
  header：请求头（int类型），用不同的数值来代表不同的业务类型，表示登陆，心跳，私信，群聊等。  
  body：请求体（不一定会存在的业务消息体），代表实际的消息内容，针对不同的业务，包内容也会不同，例如私信的时候包的内容是聊天的消息，pong响应就是一个空的请求体。  
  
  3. 请求头header数据字典  

|状态码| 代表的业务|
| --- |  ---    |
|     |         |
|     |         |
|     |         |
|     |         |
|     |         |
|     |         |
|     |         |
|     |         |
|     |         |

  
### 缺点  

现在只是单机版，以后要支持集群  
