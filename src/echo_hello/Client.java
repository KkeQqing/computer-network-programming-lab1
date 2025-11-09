package echo_hello;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要发送的消息: ");
        String message = scanner.nextLine();

        try (
                Socket socket = new Socket("localhost", 8888);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            // 发送请求
            out.println(message);

            // 尝试读取响应（最多等待几秒）
            if (socket.isClosed() || socket.isInputShutdown()) {
                System.out.println("⚠️ 连接已关闭，无响应。");
                return;
            }

            try {
                String response = in.readLine();
                if (response != null) {
                    System.out.println("✅ 收到服务器回复: " + response);
                } else {
                    System.out.println("📭 服务器未返回任何数据（连接已关闭）");
                }
            } catch (IOException e) {
                System.out.println("⏳ 等待响应超时，或服务器未回复。");
            }

        } catch (IOException e) {
            System.err.println("❌ 连接失败: " + e.getMessage());
        }
    }
}
