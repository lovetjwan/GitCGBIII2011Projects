package com.cy.java.api.net;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class MainClient {//客户端(类似浏览器)

    public static void main(String[] args) throws IOException {
        //浏览器，向指定服务建立连接
        Socket socket = new Socket("127.0.0.1",9999);
        //向服务端写一句话
        ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
        os.writeUTF("hello server");
        os.flush();
        socket.close();
    }
}
