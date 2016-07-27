package com.nmq.socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

/**
 * Created by niemengquan on 2016/7/27.
 */
public class ServerSocketTest {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket=new ServerSocket(8888);
            System.out.println("serversocket is running");
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            BufferedInputStream buf=new BufferedInputStream(inputStream);
            byte[] b=new byte[4096];
            int buff;
            while ((buff=buf.read(b))>0){
                System.out.println(new String(b,0,b.length));
            }
            buf.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
