import java.util.ArrayList;


public abstract class Tread10 {
	public static int count=0;

	public void run() {
		while (count <= 1000000) {
			count++;
			System.out.println(count);
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException  {
		for (int i = 0; i < 10; i++) {
			 
			Thread myThready1 = new Thread();
			myThready1.start(); // Запуск потока
			myThready1.join();
		}
		

	}

}
