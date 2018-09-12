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
		info1 = new JLabel("����:              ������:      �Ļغ�");
		this.setLayout(new FlowLayout());
		
		Font font = new Font("����", Font.BOLD,48);
		this.setFont(font);
		this.add(info1);

		this.setPreferredSize(new Dimension(0,30));
	}
	
	public void changeInfo(int taking,int turns) {
		String player = null,turn;
		switch(taking) {
		case 1: player = "�ڷ�";break;
		case 0: player = "�׷�";break;
		}
		if(turns == taking) {
			turn = "��";
		}else {
			turn = "�Է�";
		}
		info1.setText("����:"+player+" ������:"+turn+"�Ļغ�");
	}
	
	
}
