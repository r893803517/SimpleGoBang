package Net;

import java.io.Serializable;

public class MesCarrier implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private short mesType;
	private short statu;
	private String info;
	
	
	public MesCarrier() {}
	
	
	public MesCarrier(short mesType, short statu, String info) {
		super();
		this.mesType = mesType;
		this.statu = statu;
		this.info = info;
	}


	public short getMesType() {
		return mesType;
	}
	public void setMesType(short mesType) {
		this.mesType = mesType;
	}
	public short getStatu() {
		return statu;
	}
	public void setStatu(short statu) {
		this.statu = statu;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
	
	
	
}
