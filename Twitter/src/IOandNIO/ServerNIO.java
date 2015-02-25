package IOandNIO;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
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
			final AsynchronousServerSocketChannel server =
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
	
    private static void handle(AsynchronousSocketChannel clientChannel) {
       

        System.out.println("Messages from client: ");

        if ((clientChannel != null) && (clientChannel.isOpen())) {
        
            ByteBuffer buffer = ByteBuffer.allocate(1000);
            
            Future<Integer> result = clientChannel.read(buffer);
 //           while (true) {
            int bytesRead = -1;
			try {
				bytesRead = result.get(10,TimeUnit.SECONDS);
				System.out.println(bytesRead);
			} catch (InterruptedException | ExecutionException | TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //read into buffer.
            while (bytesRead != -1) {


//                while (! result.isDone()) {
//                    // do nothing
//                }
            	buffer.flip();
                String clientString = new String(buffer.array()).trim();
                System.out.println(clientString); 
                
                buffer.clear();
                buffer = ByteBuffer.wrap(("Serv: "+clientString).getBytes());
                result = clientChannel.write(buffer);
 //               System.out.println(new String(buffer.array()).trim()); 
//                while (! result.isDone()) {
//                    // do nothing
//                }    
//                
      //          buffer.clear(); //make buffer ready for writing
                
                try {
					bytesRead = clientChannel.read(buffer).get();
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}


            } // while()


        } // end-if
  	
    }

	private static String String(byte[] array) {
		// TODO Auto-generated method stub
		return null;
	}
}