package Model.Panel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


public class ButtonPanel extends JPanel {
	private JButton stopGame = new JButton("��ͣ����");
	private JButton admit = new JButton("����");
//	private JButton regret = new JButton("����");
	private JButton giveUp = new JButton("����");
	private TimePanel timer = new TimePanel(); 
	
	private JButton[] jb = {stopGame,admit,giveUp};
	public ButtonPanel(ActionListener actionListener) {
		
		this.setPreferredSize(new Dimension(190,0));
		this.setLayout(new GridLayout(5,1,20,20)); 
			
		add(timer);
		add(admit);
		admit.setActionCommand("admit");
		add(stopGame);
		giveUp.setActionCommand("surrender");
		add(giveUp);
		
		for(JButton button:jb) {
			button.addActionListener(actionListener);
		}
	
	}

}
