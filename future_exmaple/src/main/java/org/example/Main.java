package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(10);
        //Why we dont use Future Objcets
        //1.so we can not complete it manually
        //future object will block the main thread for 1 min -unitl the execution not complete
        //we don't have any method to complete the execution forcefully;
        Future<List<Integer>> future1 = service.submit(() -> {
            System.out.println("Thread :" + Thread.currentThread().getName());
            //delay(1);
            return Arrays.asList(1, 2, 3);
        });
        //4. exception can not be handle in future
        Future<List<Integer>> future2 = service.submit(() -> {
            System.out.println("Thread :" + Thread.currentThread().getName());
            //System.out.println(10/0);
            return Arrays.asList(1, 2, 3);
        });

        List<Integer> op = future2.get();
        System.out.println(op);

        Future<List<Integer>> future3 = service.submit(() -> {
            System.out.println("Thread :" + Thread.currentThread().getName());
            //delay(1);
            return Arrays.asList(1, 2, 3);
        });

        //2. multiple futures cannot be chained together - we cannot pass this list to another thread for execution.

        List<Integer> list = future1.get();
        System.out.println(list);

        //3. we cannot combine multiple future together
        //we can run it separately  but we can't combine them
         // future1.get() + future2.get() + future3.get();



        ////***********************************////////
        //completeble Future
        CompletableFuture completableFuture = new CompletableFuture();

        delay(1);
        completableFuture.complete("execution complete");

        //get method will still block the thread until complete the execution
        //but we can use complete method to forcfully complete the execution.
        Object cf = completableFuture.get();
        System.out.println(cf);
    }

    private static void delay(int min){
        try {
            TimeUnit.MINUTES.sleep(min);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}