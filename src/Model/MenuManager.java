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
	
	private JMenu gameMenu = new JMenu("游戏"),    //主菜单栏选项
			aboutMenu = new JMenu("关于"); 
	private JMenu[] Menu = {gameMenu,aboutMenu};
	
	
	private JMenuItem restartGame = new JMenuItem(),
			jmiGiveUp = new JMenuItem(),
			jmiExit = new JMenuItem(), 
			jmiStop = new JMenuItem();
	
	 private JMenuItem[] gameMenuItem = { restartGame,jmiStop,jmiGiveUp,jmiExit}; 
	private String[] gameItemName = {"重新开始","暂停比赛","认输","|","退出"}; 
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

	
	//为顶级容器添加主菜单
	public void addMenu(JFrame mainFrame) {
		mainFrame.setJMenuBar(mb);
		for(JMenu m:Menu){
			mb.add(m);
		}
	}
	
	
	//初始化菜单选项 参数： 选项实体  选项名 选项快捷键 添加的监听器  
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
	
	
	//为主菜单栏中的菜单添加菜单选项
	public void addMenuItem(JMenu menu,JMenuItem item,String itemName,int Mnemonic,ActionListener mainFrame){
		item.setText(itemName);
		menu.add( item );
		item.setAccelerator(KeyStroke.getKeyStroke(Mnemonic,ActionEvent.CTRL_MASK));
		item.addActionListener(mainFrame);
	}
	
	
}
