package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuManager {
	
	private JMenu gameMenu = new JMenu("��Ϸ"),    //���˵���ѡ��
			aboutMenu = new JMenu("����"); 
	private JMenu[] Menu = {gameMenu,aboutMenu};
	
	
	private JMenuItem restartGame = new JMenuItem(),
			jmiGiveUp = new JMenuItem(),
			jmiExit = new JMenuItem(), 
			jmiStop = new JMenuItem();
	
	 private JMenuItem[] gameMenuItem = { restartGame,jmiStop,jmiGiveUp,jmiExit}; 
	private String[] gameItemName = {"���¿�ʼ","��ͣ����","����","|","�˳�"}; 
	private int[] gameItemMnemonic = {KeyEvent.VK_N,KeyEvent.VK_O,KeyEvent.VK_S,KeyEvent.VK_E};
	
	
	
	private JMenuBar mb = new JMenuBar();
	
	public MenuManager(ActionListener actionlistener) {
	
		buildUpMenu(gameMenu,gameMenuItem,gameItemName,gameItemMnemonic,actionlistener);
		
		restartGame.setActionCommand("restart");
		jmiGiveUp.setActionCommand("giveup");
		jmiExit.setActionCommand("exit");
		jmiStop.setActionCommand("stopGame");
		//jmiDrawback.setActionCommand("drawback");
	}

	
	//Ϊ��������������˵�
	public void addMenu(JFrame mainFrame) {
		mainFrame.setJMenuBar(mb);
		for(JMenu m:Menu){
			mb.add(m);
		}
	}
	
	
	//��ʼ���˵�ѡ�� ������ ѡ��ʵ��  ѡ���� ѡ���ݼ� ��ӵļ�����  
	public void buildUpMenu(JMenu menu,JMenuItem[] item,String[] itemname,int[] Mnemonic,ActionListener mainFrame){
		for(int i =0,scount = 0; i<itemname.length; i++){
			if(itemname[i].equals("|")){
				menu.addSeparator();
				scount ++;
				continue;
			}
//			item[i-scount].
			addMenuItem(menu,item[i-scount],itemname[i],Mnemonic[i-scount],mainFrame);
		}
	}
	
	
	//Ϊ���˵����еĲ˵���Ӳ˵�ѡ��
	public void addMenuItem(JMenu menu,JMenuItem item,String itemName,int Mnemonic,ActionListener mainFrame){
		item.setText(itemName);
		menu.add( item );
		item.setAccelerator(KeyStroke.getKeyStroke(Mnemonic,ActionEvent.CTRL_MASK));
		item.addActionListener(mainFrame);
	}
	
	
}
