package Controller;

import Net.MesCarrier;

public interface MesHandler {
	void onConnect();
	void onClose();
	void handleMes(MesCarrier mes);
}
