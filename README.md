### Completebale future

## runAsync() and supplyAsync()

### runAsync
if we want to rrunn some background task asynchronously and **do not want to return** anything from that task, then use 
**CompletableFuture.runAsync()** method. it take a runnable objcet and returns CompletableFuture<Void>.

### supplyAync
if we want to run some background task asynchronously and **want to return** anything from that task, we should use
**CompletableFuture.supplyAsync** method. it takes a supplier<T> and returns CompletableFuture<T>
where T is type of thhe value obtained by calling the given supplier.


