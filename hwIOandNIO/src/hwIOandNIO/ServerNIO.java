package hwIOandNIO;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;



public class ServerNIO {
	private static int port = 8086;
	
	public ServerNIO() {
		
		startServer(port);
	}
	
	public static void main(String[] args) {
		if (args.length>0 && args[0]!=null){
			int tempPort = Integer.parseInt(args[0]);
			if(tempPort >0) port=tempPort;
		}
		
		startServer(port);

	}
	private static void startServer(int i) {
		try {
			AsynchronousChannelGroup group = AsynchronousChannelGroup.withThreadPool(Executors.newSingleThreadExecutor());
			AsynchronousServerSocketChannel server =
				    AsynchronousServerSocketChannel.open(group).bind(new InetSocketAddress(i));

	           System.out.println("Server listening on " + port);

	           server.accept("Attachment", new CompletionHandler<AsynchronousSocketChannel, String>() {
	               @Override
	               public void completed(AsynchronousSocketChannel result, String attachment) {
	                   System.out.println("Completed");
	                   server.accept(null, this);
	             
	                   handle(result);
	                   try {
	                       System.out.println("Accepted: " + result.getRemoteAddress());

	                   } catch (Exception e) {
	                       e.printStackTrace();
	                   }
	               }

	               @Override
	               public void failed(Throwable exc, String attachment) {
	                   System.out.println("Failed");
	               }
	           });
	            
			group.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		} 
		
	}
	
    private static void handle(AsynchronousSocketChannel result) {
        ByteBuffer bb = ByteBuffer.allocate(1024);
        try {
			
			int bytesRead = result.read(bb).get(10, TimeUnit.SECONDS);
			while (bytesRead != -1) {

		        System.out.println("Message from client: " + new String(bb.array()));
		 
				bb.flip();
				bb.put("Ok".getBytes());// this row has a problem
				bb.flip();
				bytesRead = result.read(bb).get(10, TimeUnit.SECONDS);;

		      	}


		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		};
        System.out.println(new String(bb.array()));
    }

}
