Socket 类（客户端）
作用：
代表客户端套接字，用于与服务器建立连接并进行数据通信。
常用方法：
方法	说明
getInputStream()	获取输入流，用于从服务器读取数据
getOutputStream()	获取输出流，用于向服务器发送数据
close()	关闭套接字连接
isClosed()	判断是否已关闭
getInetAddress() / getLocalAddress()	获取远程/本地 IP 地址

ServerSocket 类（服务器端）
作用：
监听指定端口，等待客户端连接请求。每当有客户端连接时，会创建一个新的 Socket 对象用于与该客户端通信。
常用方法：
方法	说明
accept()	阻塞方法，等待并接受一个客户端连接，返回一个 Socket 对象
close()	关闭服务器套接字，停止监听
isClosed()	判断是否已关闭
getLocalPort()	获取监听的本地端口号