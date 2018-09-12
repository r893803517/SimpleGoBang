package Model.Frame;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Controller;
import Game.Game;
import Model.MenuManager;
import Model.Panel.ButtonPanel;
import Model.Panel.ChessBoard;
import Model.Panel.DisplayPanel;


public class MainFrame extends JFrame implements GameViewer {
	private ButtonPanel buttonpanel;
	private DisplayPanel displaypanel;
	private ChessBoard chessboard;
	private MenuManager mManager;
	private Controller controller;
	
	
	public ChessBoard getChessboard() {
		return chessboard;
	}

	public void setChessboard(ChessBoard chessboard) {
		this.chessboard = chessboard;
	}
	

	
	
	public MainFrame(Controller controller) {
		this.controller = controller;
		chessboard = new ChessBoard();
		buttonpanel = new ButtonPanel(controller);
		displaypanel = new DisplayPanel();
		displaypanel.changeInfo(controller.getPlayer().getTaking(), controller.getG().getTurns());
		mManager = new MenuManager(controller);
		mManager.addMenu(this);
		
		add(chessboard,BorderLayout.LINE_START);
		add(buttonpanel,BorderLayout.LINE_END);
		add(displaypanel,BorderLayout.PAGE_END);
	
		setTitle("gobang");
		((JPanel)this.getContentPane()).setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setSize(840,690);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

	}
	
	public void refreshDisplay(Game game) {
		
		displaypanel.changeInfo(controller.getPlayer().getTaking(), controller.getG().getTurns());
	}
	


	@Override
	public void boundGame(Controller controller) {
		chessboard.setGame(controller.getG());
		chessboard.setPlayer(controller.getPlayer());
	}

	@Override
	public void refreshGame() {
		chessboard.repaint();
		
	}

	@Override
	public void refreshInfo() {
		
		
	}

}
