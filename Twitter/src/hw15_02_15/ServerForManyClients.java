package hw15_02_15;

import java.io.*;
import java.net.*;

public class ServerForManyClients {

  public static void main(String[] args) throws IOException, InterruptedException {
    System.out.println("Welcome to Server side");


    ServerSocket servers = null;
    Socket       fromclient = null;

    // create server socket
    try {
      servers = new ServerSocket(8086);
    } catch (IOException e) {
      System.out.println("Couldn't listen to port 8086");
      System.exit(-1);
    }
    
    
    try {
      System.out.print("Waiting for a client...");
      boolean isStopped = false;
      while(!isStopped){
          fromclient = servers.accept();
          
          Thread mThread = new Thread(new NewConnect(fromclient));	//Создание потока "myThready"
          mThread.start();          //Запуск потока
          mThread.join();
      }    
  
      System.out.println("Client connected");
    } catch (IOException e) {
      System.out.println("Can't accept");
      System.exit(-1);
    }
    
    servers.close();    

  }
   
}

class NewConnect implements Runnable		
{
    BufferedReader in = null;
    PrintWriter    out= null;
    Socket fromclient;
    NewConnect(Socket fromclient){
    	this.fromclient = fromclient;
    }
    public void run()		
    {
        try {
			out = new PrintWriter(fromclient.getOutputStream(),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        out.println("Welcome to our telnet server!!!");
        try {
			in  = new BufferedReader(new 
			InputStreamReader(fromclient.getInputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String   input, output;

        System.out.println("Wait for messages");
        try {
			while ((input = in.readLine()) != null) {
			 if (input.equalsIgnoreCase("exit")) break;
			 out.println("S ::: "+input);
			 System.out.println(input);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        out.close();
        try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			fromclient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
    }
}
