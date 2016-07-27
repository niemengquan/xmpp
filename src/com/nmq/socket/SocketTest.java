package com.nmq.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by niemengquan on 2016/7/27.
 */
public class SocketTest {
    public static void main(String[] args) {
        try {
            Socket socket=new Socket("127.0.0.1",8888);
            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(outputStream));
            bw.write("howly");
            bw.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
