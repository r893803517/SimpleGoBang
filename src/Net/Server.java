package Net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread implements Communicator{
	
	private ServerSocket server;
	private Socket socket;

	@Override
	public MesCarrier receive() {
		 try {  
         	ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));  
             Object obj = is.readObject();  
             MesCarrier message = (MesCarrier)obj;  
             return message;
             //is.close();
         } catch (IOException ex) {  
              ex.printStackTrace();
         } catch(ClassNotFoundException ex) {  
            ex.printStackTrace();
         } finally {  
             
         }  
         return null;
	}

	@Override
	public void send(MesCarrier message) {
		
		  try {  
			  
	        	ObjectOutputStream  os = new ObjectOutputStream(socket.getOutputStream());  
	     
	            os.writeObject(message);  
	            os.flush();  
	              
	     
	        } catch(IOException ex) {  
	            ex.printStackTrace();
	        } finally {  
	        	
	        }  
	}

	@Override
	public boolean connect() {
		try {
			server.setSoTimeout(1000*30);
			socket = server.accept();
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	public  Server() {
           try{
               server=new ServerSocket(5209);
               System.out.println("服务器启动成功");
          
           }catch(Exception e) {
                   System.out.println("没有启动监听："+e);
           }
	}
	
	public void run() {
		this.connect();
	}
	
	public void close() {
		try {
			server.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
