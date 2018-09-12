package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Game.Game;
import Game.Game.gameStatus;
import Game.Player;
import Model.Frame.MainFrame;
import Model.Frame.StartFrame;
import Net.AsynManager;
import Net.MesCarrier;



public class Controller  implements ActionListener,MesHandler{
	
	private AsynManager nioManager;
	private Player player;
	private Game g;
	private MainFrame mainFrame;
	private StartFrame startFrame;
	
	
	
	public void showGamePanel() {
		mainFrame.setVisible(true);
	}
	
	public void hideGamePanel() {
		mainFrame.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch(cmd) {

		case "stopGame":
			if(player.getTaking() != g.getTurns()) {
				JOptionPane.showMessageDialog(null,"还没轮到你呢");
				break;
			}
			break;

		case "admit":
			if(player.getTeam() != g.getTurns()) {
				System.out.println("error turns ");
				break;
			}
			if(!g.admit(player.getTaking())) {
				System.out.println("error taking");
				break;
			}			
			sendMes((short)EventType.ADMIT,(short) 0, g.pre_x+","+g.pre_y+","+player.getTeam()+","+player.getTaking());
			squareGame();
			mainFrame.refreshGame();
			break;
			
		case "surrender":
			if(g.getState() == gameStatus.MATCHING || g.getState() == gameStatus.STOP) {
				JOptionPane.showMessageDialog(null,"你确认要投降吗?");
				break;
			}

		case "return":
			if(g.getState() == gameStatus.MATCHING || g.getState() == gameStatus.STOP) {
				JOptionPane.showMessageDialog(null,"游戏正在运行");
				break;
			}
			break;

		case "exit":
			System.exit(0);break;
		}
			
	}
	

	public void handleMes(MesCarrier message) {
		try {
			System.out.println(message.getInfo());
			switch(message.getMesType()) {
			case 1:
				
				String info[] = message.getInfo().split(",");
				if(info.length != 4) {
					throw new Exception("数据格式不正确");
				}
				int param[] = new int[4];
				for(int i = 0; i < 4; i ++) {
					
					param[i] =  Integer.parseInt(info[i]);
				}
				if(param[2] != g.getTurns()) {
					break;
				}
				g.detec_x = param[0];
				g.detec_y = param[1];
				if(!g.admit(param[3])) {
					break;
				}			
				squareGame();
				mainFrame.refreshGame();
				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void sendMes(short type,short statu,String info) {
		MesCarrier message = new MesCarrier(type,statu,info);
		nioManager.sendNIO(message);
	}
	
	public void squareGame() {
		String winner = null;
		switch(g.isWin()) {
		case 0:winner = "白方"; g.setState(gameStatus.WHITEWIN); break;
		case 1:winner = "黑方"; g.setState(gameStatus.BLACKWIN);break;
		case -1:g.nextTurns();mainFrame.refreshDisplay(g);return;
		}
		String mString = String.format("%s     WIN!", winner);
		JOptionPane.showMessageDialog(mainFrame, mString);
	}

	public Controller setNioManager(AsynManager nioManager) {
		this.nioManager = nioManager;
		return this;
	}

	public Controller setG(Game g) {
		this.g = g;
		return this;
	}

	public Controller setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		return this;
	}
	
	public Controller setPlayer(Player player) {
		this.player = player;
		return this;
	}
	
	public Controller setStartFrame(StartFrame startFrame) {
		this.startFrame = startFrame;
		return this;
	}

	public Player getPlayer() {
		return player;
	}

	public Game getG() {
		return g;
	}

	@Override
	public void onConnect() {
		
	}

	@Override
	public void onClose() {
		
	}

	public void startGame() {
		nioManager.receiveNIO();
		
	}
	
	public void closeGame() {
		nioManager.stopReceive();
		nioManager.close();
	}

	
	
		
}
