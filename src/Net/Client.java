package Net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client implements Communicator{
	
	private Socket clientSocket;
	private String ip;

	@Override
	public MesCarrier receive() {
		 try {  
	        	ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(clientSocket.getInputStream()));  
	            Object obj = is.readObject();  
	            MesCarrier message = (MesCarrier)obj;  
	            return message;
	       
	        }catch (IOException ex) {  
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
			  
        	ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream());  
     
            os.writeObject(message);  
            os.flush();  

           
        } catch(IOException ex) {  
            ex.printStackTrace();
        } finally {  
        	
        }  
	}

	public Client(String ip) {
		this.ip = ip;
	}
	
	@Override
	public boolean connect() {
		try {
			clientSocket = new Socket(ip,5209);
			System.out.println("客户端连接成功");
			return true;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void close() {
		try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
