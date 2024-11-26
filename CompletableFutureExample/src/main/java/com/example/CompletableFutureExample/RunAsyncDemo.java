package com.example.CompletableFutureExample;

import com.example.CompletableFutureExample.dto.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class RunAsyncDemo {
    public Void saveEmployees(File jsonFile) throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        CompletableFuture<Void> runAsyncCompletableFuture = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Employee> employees = mapper.
                            readValue(jsonFile, new TypeReference<List<Employee>>() {
                            });
                    //to save employee to database (print the empolyee)
                    System.out.println("Thread :" + Thread.currentThread().getName());
                    employees.stream().forEach(System.out::println);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return runAsyncCompletableFuture.get();

    }

    //with lambda
    public Void saveEmployeesWithCustomExecutor(File jsonFile) throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        Executor executor = Executors.newFixedThreadPool(5);
        CompletableFuture<Void> runAsyncCompletableFuture = CompletableFuture.runAsync(()->
            {
                try {
                    List<Employee> employees = mapper.
                            readValue(jsonFile, new TypeReference<List<Employee>>() {
                            });
                    //to save employee to database (print the empolyee)
                    System.out.println("Thread :" + Thread.currentThread().getName());

                    System.out.println(employees.size());
                  //  employees.stream().forEach(System.out::println);
                } catch (IOException e) {
                    throw new RuntimeException(e);

            }
        }, executor);

        return runAsyncCompletableFuture.get();

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RunAsyncDemo runAsyncDemo = new RunAsyncDemo();
        runAsyncDemo.saveEmployees(new File("employees.json"));
        System.out.println("**************************");
        runAsyncDemo.saveEmployeesWithCustomExecutor(new File("employees.json"));

    }
}
