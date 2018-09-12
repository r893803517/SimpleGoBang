package Model.Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controller.Controller;
import Controller.MesHandler;
import Game.Game;
import Game.Player;
import Game.PlayerWithBlack;
import Game.PlayerWithWhite;
import Net.AsynManager;
import Net.Client;
import Net.Communicator;
import Net.MesCarrier;
import Net.Server;



public class StartFrame  extends JFrame implements ActionListener,MesHandler{
		private JButton newGame = new JButton("�����Ծ�");
		private JButton joinGame = new JButton("������Ϸ");
		private JTextField ipText = new JTextField("127.0.0.1",15);
		private JButton exit = new JButton("�˳�");
		private JLabel matching = new JLabel("",JLabel.CENTER);
		private JButton cancel = new JButton("ȡ��");	
		private JButton jb[] = {newGame,joinGame,exit,cancel}; 

		
		private AsynManager nioManager;
		private Communicator communicator;
		private Player player;
		public StartFrame(){
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(6,1,20,20));
			panel.setSize(new Dimension(200,300));
			newGame.setActionCommand("newGame");
			panel.add(newGame);
			panel.add(ipText);
			joinGame.setActionCommand("joinGame");
			panel.add(joinGame);

			panel.add(matching);
			cancel.setActionCommand("cancel");
			panel.add(cancel);
			cancel.setVisible(false);
			
			for(JButton button:jb) {
				button.addActionListener(this);
				
			}
			
			this.add(panel,BorderLayout.CENTER);
			((JPanel)this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
			this.setTitle("gobang");
			this.setSize(300, 400);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//this.setVisible(true);
		}
		
		
		public void setNioManager(AsynManager nioManager) {
			this.nioManager = nioManager;
		}
		
		
		public boolean validIP() {
			String ip = ipText.getText();
			if(!ip.matches("[0-9.]{7,15}")) {
				return false;
			}
			return true;
			
		}
		
		
		

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(e.getActionCommand()) {
			case "newGame": 
				invalid();
				matching.setText("�ȴ��û�����Ծ�......");
				cancel.setVisible(true);
				communicator = new Server();
				nioManager = new AsynManager(communicator,this);
				player = new PlayerWithBlack();
				nioManager.connectNIO();
				
				break;
				
			case "joinGame":
				if(!validIP()) {
					matching.setText("IP��ʽ����ȷ");
					break;
				}
				invalid();
				matching.setText("���ڼ���Ծ�......");
				cancel.setVisible(true);
				communicator = new Client(ipText.getText());
				nioManager = new AsynManager(communicator,this);
				player = new PlayerWithWhite();
				nioManager.connectNIO();
				
			case "exit": break;
			case "cancel": 
				cancel.setVisible(false);
				matching.setText("");
				nioManager.close();
				valid();
				break;
			}
		}
		
		public void invalid() {
			newGame.setEnabled(false);
			joinGame.setEnabled(false);
		}
		
		public void valid() {
			newGame.setEnabled(true);
			joinGame.setEnabled(true);
		}
		
		public void startGame() {
			
		}


		@Override
		public void onConnect() {
			matching.setText("������Ϸ");
			Game game = new Game();
			Controller controller = new Controller();
			nioManager.setMesHandler(controller);
			controller.setG(game).setPlayer(player).setNioManager(nioManager);
			MainFrame mainFrame = new MainFrame(controller);
			mainFrame.boundGame(controller);
			controller.setMainFrame(mainFrame);
			controller.startGame();
			this.setVisible(false);
			controller.showGamePanel();
			
		}


		@Override
		public void onClose() {
			
		}


		@Override
		public void handleMes(MesCarrier mes) {
			
		}
		
		
}
