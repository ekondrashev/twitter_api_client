package hw15_02_15;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class CopyFileNIO {
	
	public static void main(String[] args) throws IOException {
		try(RandomAccessFile aFile = new RandomAccessFile("C:\\Error Log.txt", "rw");
		RandomAccessFile bFile = new RandomAccessFile("C:\\Error Log2.txt", "rw");){
		FileChannel inChannel = aFile.getChannel();
		FileChannel outChannel = bFile.getChannel();
		ByteBuffer buf = ByteBuffer.allocate(48);
		int bytesRead = inChannel.read(buf);
		while (bytesRead != -1) {
//		System.out.println("Read " + bytesRead);
		      	buf.flip();
			
		      	while(buf.hasRemaining()){
		      		outChannel.write(buf);
//		          		System.out.print((char) buf.get());
		      	}
			
		     	buf.clear();
		      	bytesRead = inChannel.read(buf);
		    }

	}
	}
}
