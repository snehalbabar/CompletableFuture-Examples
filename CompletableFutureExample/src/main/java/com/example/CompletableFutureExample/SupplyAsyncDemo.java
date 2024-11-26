package com.example.CompletableFutureExample;

import com.example.CompletableFutureExample.database.EmployeeDatabase;
import com.example.CompletableFutureExample.dto.Employee;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SupplyAsyncDemo {

    public List<Employee> getEmployee() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newCachedThreadPool();
        CompletableFuture<List<Employee>> listCompletableFuture = CompletableFuture
                .supplyAsync(() ->
                {
                    System.out.println("executed by thread" + Thread.currentThread().getName());
                  return   EmployeeDatabase.fetchEmployees();
                },executor);
        return listCompletableFuture.get();
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        SupplyAsyncDemo supplyAsyncDemo = new SupplyAsyncDemo();
        List<Employee> employee = supplyAsyncDemo.getEmployee();
        employee.stream().forEach(System.out::println);
    }
}
