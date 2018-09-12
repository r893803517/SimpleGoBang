package Model.Panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimePanel extends JPanel{
	static int remainTime= 25;

    JLabel label = new JLabel("00:25");//实现25秒倒计时
    
    public Timer getMyTimer() {
		return myTimer;
	}

	public void setMyTimer(Timer myTimer) {
		this.myTimer = myTimer;
	}

	Timer myTimer = new Timer(1000, new ActionListener() {

          @Override

          public void actionPerformed(ActionEvent e) {
                if(remainTime>0 &&remainTime<=10){
                       label.setForeground(Color.red);
                }
 
                else if(remainTime<=0){
                       myTimer.stop();
                }
		            remainTime -= 1;
		            label.setText(secToTime(remainTime));
                }
    });
    
    public static String secToTime(int time) {
        String timeStr = null;
		int hour = 0;
		int minute = 0;
		int second = 0;
		
		if (time<= 0)
		
		return"00:00";
				
			else {
			minute = time / 60;
			if (minute< 60) {
			second = time % 60;
			timeStr = unitFormat(minute) + ":" + unitFormat(second);
           } else {
				hour = minute / 60;
				if (hour> 99)
				return"99:59:59";
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
           }
       }
	return timeStr;

}

	public static String unitFormat(int i) {
	
	    String retStr = null;
		if (i>= 0 &&i< 10)
		retStr = "0" + Integer.toString(i);
		else
		retStr = "" + i;
		return retStr;
	}

    
    public TimePanel() {
    	Font font = new Font("隶书", Font.BOLD, 48);
    	label.setFont(font);
    	this.add(label);
    	
    }
    
   
}
