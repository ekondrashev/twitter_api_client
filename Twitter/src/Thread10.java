
public abstract class Thread10 implements Runnable {

	public static int count=0;
	

	public static void main(String[] args) throws InterruptedException  {
		for (int i = 0; i < 10; i++) {
			
			Thread myThready = new Thread(new Runnable() {

				public void run() // Этот метод будет выполняться в побочном потоке
				{
					System.out.println(count);
					for (int i =0; i<1000000; i++) {
						count++;
					}
				
				}
			});
			myThready.start(); 
			myThready.join();
		}
		System.out.println(count);

	}

}
