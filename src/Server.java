import java.io.*;
import java.net.*;

class Server extends Thread {
    int port; // 服务器端口
    ServerSocket server; // 服务器套接字
    Socket socket;  // 客户端套接字
    DataOutputStream outStream = null;  // 服务器输出流
    DataInputStream inStream = null;    // 服务器输入流

    public Server(int port) {
        try {
            this.port = port;
            server = new ServerSocket(port);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void run() {
        try {
            System.out.println("Server waiting for client on port " + port + "...");
            socket = server.accept(); // 等待客户端连接
            outStream = new DataOutputStream(socket.getOutputStream());
            inStream = new DataInputStream(socket.getInputStream());
            System.out.println("Server is ok, please continue!");

            while (true) {
                String str = inStream.readUTF();
                System.out.println("The server receive String: " + str);
                outStream.writeUTF(str); // Echo back
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (socket != null) socket.close();
                if (server != null) server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}