package com.cy.java.api.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {//Server(服务器)，类似tomcat

    public static void main(String[] args) throws IOException {
        //tomcat启动的简易过程
        //创建ServerSocket对象，并在9999端口进行监听
        ServerSocket serverSocket = new ServerSocket(9999);
        //等待客户端的连接,没有客户端连接过来，这语句的下面内容不会执行，相当于阻塞
        System.out.println("server start on 9999 port");
        while (true){//不断等待客户端的连接
            Socket socket = serverSocket.accept();
            //客户端连接到服务端以后会继续向下执行
            System.out.println("client 来了");
            //通过流读取客户端向服务端发送的请求数据
            String threadName = Thread.currentThread().getName();
            System.out.println("threadName="+threadName);
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            String content = in.readUTF();
            System.out.println("client say:"+content);
        }
    }
}
