package top.shanbing;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RuntimeTest {

    public static void main(String[] args) {
        int count = 1000;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        ExecutorService executorService = Executors.newFixedThreadPool(count);
        long now = System.currentTimeMillis();

        for (int i = 0; i < count; i++){
            Thread t = new Thread(new Task(cyclicBarrier));
            t.setName("线程"+i);
            executorService.execute(t);
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("All is finished!---------"+(end-now));
    }


}
