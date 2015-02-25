package IOandNIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerNIOSelector {

	public static void main(String[] args) {
		System.out.println("Server start! ");

		try {
			ServerSocketChannel ssc = ServerSocketChannel.open();
			ssc.configureBlocking(false);

			// Get the Socket connected to this channel, and bind it
			// to the listening port
			ServerSocket ss = ssc.socket();
			InetSocketAddress isa = new InetSocketAddress(8086);
			ss.bind(isa);

			Selector selector = Selector.open(); // Create a new Selector for
													// selecting
			ssc.register(selector, SelectionKey.OP_ACCEPT); // Register the
															// ServerSocketChannel,
															// so we can listen
															// for incoming
															// connections
			while (true) {

				int num = selector.select();
				if (num == 0) {
					continue; // If we don't have any activity, loop around and
								// wait again
				}

				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> it = keys.iterator();

				while (it.hasNext()) {
					SelectionKey key = (SelectionKey) it.next();

					// What kind of activity is it?
					if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {

						System.out.println("Accept new connection!"); // It's an incoming connection. Register this socket with Selector.

						Socket s = ss.accept();
						System.out.println("Got connection from " + s);

						SocketChannel sc = s.getChannel(); // Make sure to make
															// it non-blocking,
															// so we can use a
															// selector on it.

						sc.configureBlocking(false);

						sc.register(selector, SelectionKey.OP_READ); // Register it with  the selector, for reading
					} else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {

						SocketChannel sc = null;

						try {

							// It's incoming data on a connection, so process it
							sc = (SocketChannel) key.channel();
							boolean ok = processInput(sc);

							// If the connection is dead, then remove it from
							// the selector and close it
							if (!ok) {
								key.cancel();

								Socket s = null;
								try {
									s = sc.socket();
									s.close();
								} catch (IOException ie) {
									System.err.println("Error closing socket "
											+ s + ": " + ie);
								}
							}

						} catch (IOException ie) {

							// On exception, remove this channel from the
							// selector
							key.cancel();
							sc.close();
							System.out.println("Closed " + sc);
						}

					}

					keys.clear(); // We remove the selected keys, because we've
									// dealt with them.
				}
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static boolean processInput(SocketChannel sc) throws IOException {
		ByteBuffer buffer = ByteBuffer.allocate(16384);
		buffer.clear();
		int res = -1;
		res = sc.read(buffer);
	
		
//		
//		while (res !=-1) {
//			
//		}
		
		String str = new String(buffer.array());
		System.out.println(str);
		
		buffer.flip();

		buffer.wrap(("Data is read: "+str).getBytes());
		
//		buffer.flip();
		sc.write(buffer);

		System.out.println("Processed " + buffer.limit() + " from " + sc);

		return true;
	}
}
