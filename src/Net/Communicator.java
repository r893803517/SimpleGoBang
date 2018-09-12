package Net;

public interface Communicator {
	MesCarrier receive();
	void send(MesCarrier mes);
	boolean connect();
	void close();
}
