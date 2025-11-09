package memo;

import java.io.*;
import java.net.*;

public class UserThread extends Thread {
    private Socket socket;
    private MemoController memo;

    public UserThread(Socket socket, MemoController memo) {
        this.socket = socket;
        this.memo = memo;
    }

    @Override
    public void run() {
        try (
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()))
        ) {
            String req = br.readLine();
            if ("plain".equals(req)) {
                pw.println("hello");
            } else {
                memo.append(req);
                pw.println("OK");
            }
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}