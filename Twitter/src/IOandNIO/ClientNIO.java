package IOandNIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.concurrent.Future;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;
import java.net.InetSocketAddress;

public class ClientNIO {

    public static void main (String [] args)
            throws Exception {
	
        new ClientNIO().go();
    }

    private void go()
            throws IOException, InterruptedException, ExecutionException {
	
        AsynchronousSocketChannel client = AsynchronousSocketChannel.open();
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8086);
        Future<Void> future = client.connect(hostAddress);
        future.get(); // returns null

        System.out.println("Client is started: " + client.isOpen());
        System.out.println("Sending messages to server: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
			String in = br.readLine();
			if (in.equalsIgnoreCase("exit")) {break;}
            byte [] message = in.getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            Future<Integer> result = client.write(buffer);
		
            while (! result.isDone()) {
                System.out.println("Sending... ");
            }
            buffer.clear();
            result = client.read(buffer); 
            while (! result.isDone()) {
                System.out.println("Waiting answer... ");
            }
            buffer.flip();
            System.out.println(new String(buffer.array()).trim()); 
           // buffer.clear();
 //           Thread.sleep(3000);
		}
 		
		client.close();
 		
	}
}