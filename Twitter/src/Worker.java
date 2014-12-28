import java.util.ArrayList;
import java.util.List;

public class Worker {
	
	public static List lala = new ArrayList();

	public static void main(String[] args) throws InterruptedException {
		// —оздание потока
		Thread myThready1 = new Thread(new Runnable() {

			public void run() // Ётот метод будет выполн€тьс€ в побочном потоке
			{
				for (int i = 0; i < 100; i++) {
					lala.add(i);
					
				}
				lala.add("stop");
		}
		});
		myThready1.start(); // «апуск потока
		
		Thread myThready2 = new Thread(new Runnable() {

			public void run() // Ётот метод будет выполн€тьс€ в побочном потоке
			{
					if (lala.size()>0){
					String res = (String) lala.get(lala.size());
					int resI = 0;
					if (res == "stop")
						System.out.println("¬ышли из 2-го потока");
					else
						resI = Integer.parseInt(res) ;
						System.out.println(resI);
						}
			
			}
		});
		myThready2.start(); // «апуск потока
			
		myThready1.join();
		myThready1.join();
		

		System.out.println("all...");
	}
}

