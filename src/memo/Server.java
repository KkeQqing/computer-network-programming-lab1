package memo;

import java.net.*;

public class Server {
    public static void main(String[] args) {
        MemoController memoController = new MemoController();
        try {
            ServerSocket ss = new ServerSocket(1999);
            System.out.println("Memo Server started on port 1999...");
            while (true) {
                Socket s = ss.accept();
                try {
                    UserThread t = new UserThread(s, memoController);
                    t.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}