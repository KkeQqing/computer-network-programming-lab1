public class ServerClient {
    public ServerClient(int port) {  // 创建服务器, port 为服务器端口
        Server server = new Server(port);
        server.start();

        // 等待服务器启动（简单延迟）
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Client client = new Client(port);
        client.start();
    }

    public static void main(String[] args) {
        new ServerClient(7777);
    }
}