package Net;

import Controller.Controller;
import Controller.MesHandler;

public class AsynManager {
	
	
	private MesHandler controller;
	private Communicator communicator;
	private boolean circumReceive = true;

	public AsynManager(Communicator communicator,MesHandler controller) {
		this.controller = controller;
		this.communicator = communicator;
		
	}
	
	public void setMesHandler(MesHandler mesHandler) {
		this.controller = mesHandler;
	}
	
	public void connectNIO() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				if(communicator.connect()) {
					controller.onConnect();
				}
			}
			
		}).start();;
	}
	
	public void  receiveNIO() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				while(circumReceive) {
					controller.handleMes(communicator.receive());
				}
			}
			
		}).start();;
		
		
	}
	
	public void sendNIO(MesCarrier message) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				communicator.send(message);
			}
			
		}).start();
	}
	
	
	public void close() {
		communicator.close();
	}

	public void stopReceive() {
		circumReceive = false;
	}

}
