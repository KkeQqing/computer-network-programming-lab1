package memo;

import java.io.FileWriter;
import java.io.IOException;

public class MemoController {
    private final Object lock = new Object();// 锁对象

    public void append(String line) throws IOException {
        System.out.println("当前工作目录: " + System.getProperty("user.dir")); // 👈 添加这行
        synchronized (lock) { // 同一时间只有一个线程能进入该代码块，防止并发写入导致文件内容错乱或覆盖。
            try (FileWriter writer = new FileWriter("Memo.txt", true)) {
                writer.write(line);
                writer.write(System.lineSeparator());  // 换行
            }
        }
    }
}