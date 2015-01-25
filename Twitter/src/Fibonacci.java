import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class Fibonacci extends RecursiveTask<Integer> {
   final int n;
   static final ForkJoinPool mainPool = new ForkJoinPool();

   Fibonacci(int n) { this.n = n; }
   protected Integer compute() {
     if (n <= 1)
        return n;
     Fibonacci f1 = new Fibonacci(n - 1);
     f1.fork();
     Fibonacci f2 = new Fibonacci(n - 2);
     f2.fork();
     return f2.join() + f1.join();
   }
   
   
   public static void main(String[] args){
	   long start = System.currentTimeMillis();
	   System.out.println(mainPool.invoke(new Fibonacci(45)));
	   long end = System.currentTimeMillis();
	   System.out.println(end-start);
   }
   
}

