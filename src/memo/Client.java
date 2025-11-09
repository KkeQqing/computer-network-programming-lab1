package memo;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 1999;

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("✅ 已连接到服务器 " + host + ":" + port);
            System.out.println("请输入要发送的消息（输入 'quit' 退出）：");

            while (true) {
                System.out.print(">>> ");
                String message = scanner.nextLine();

                if ("quit".equalsIgnoreCase(message)) {
                    break;
                }

                // 发送消息给服务器
                out.println(message);

                // 读取服务器响应
                String response = in.readLine();
                if (response != null) {
                    System.out.println("⬅️  服务器回复: " + response);
                } else {
                    System.out.println("⚠️  服务器已关闭连接");
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("❌ 连接失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}