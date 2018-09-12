package Model.Panel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Game.Game;

public class DisplayPanel extends JPanel {
	
	private JLabel info1;
	private Game game;
	
	public void setGame(Game game) {
		this.game = game;
	}

	public DisplayPanel() {
		info1 = new JLabel("你是:              现在是:      的回合");
		this.setLayout(new FlowLayout());
		
		Font font = new Font("隶书", Font.BOLD,48);
		this.setFont(font);
		this.add(info1);

		this.setPreferredSize(new Dimension(0,30));
	}
	
	public void changeInfo(int taking,int turns) {
		String player = null,turn;
		switch(taking) {
		case 1: player = "黑方";break;
		case 0: player = "白方";break;
		}
		if(turns == taking) {
			turn = "你";
		}else {
			turn = "对方";
		}
		info1.setText("你是:"+player+" 现在是:"+turn+"的回合");
	}
	
	
}
