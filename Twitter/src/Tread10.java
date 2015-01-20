
public abstract class Tread10 implements Runnable {

	public static int count=0;

	@Override
	public void run() {
		while (count <= 1000000) {
			count++;
			System.out.println(count);
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException  {
		for (int i = 0; i < 10; i++) {
			 
			Thread myThread1 = new Thread();
			myThread1.start(); // Запуск потока
			myThread1.join();
			
			System.out.println("oK");
		}
		

	}

}
