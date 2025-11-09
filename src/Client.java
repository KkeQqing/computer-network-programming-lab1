import java.io.*;
import java.net.*;


class Client extends Thread {
    int port;
    Socket socket;
    DataInputStream inStream = null;
    DataOutputStream outStream = null;

    public Client(int port) {
        try {
            this.port = port;
            socket = new Socket("127.0.0.1", port); // 连接本地
            inStream = new DataInputStream(socket.getInputStream());
            outStream = new DataOutputStream(socket.getOutputStream());
            System.out.println("Client is ok, please continue!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); // 从键盘读取
            while (true) {
                System.out.print("Enter message: ");
                String str = reader.readLine();
                if (str == null || str.equals("exit")) break;

                outStream.writeUTF(str);
                String response = inStream.readUTF();
                System.out.println("The client receive String: " + response);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}