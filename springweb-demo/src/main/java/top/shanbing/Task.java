package top.shanbing;

import top.shanbing.util.http.async.HttpAsyncUtil;

import java.util.concurrent.CyclicBarrier;

public class Task implements Runnable {
    private CyclicBarrier cyclicBarrier;

    public Task() {
    }
    public Task(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            System.out.println("【"+Thread.currentThread().getName()+"】等待所有任务准备就绪");
            cyclicBarrier.await();
            System.out.println("【"+Thread.currentThread().getName()+"】开始执行");
            // 测试内容
            String content=HttpAsyncUtil.get("http://localhost:8080/user");
            //String content=HttpAsyncUtil.syncGet("http://localhost:8080/user");
            //String content=HttpAsyncUtil.get("http://localhost:8080/user/list");
            System.out.println("【"+Thread.currentThread().getName()+"】" +content);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
