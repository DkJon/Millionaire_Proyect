package proyect.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.Timer;


public class TimerCall {
	private JLabel label;
	private JLabel labelRes;
    Timer countdownTimer;
    int seg = 30;

    public TimerCall(JLabel passedLabel, JLabel labelR) {
       countdownTimer = new Timer(1000, new CountdownTimerListener());
       this.label = passedLabel;
       this.labelRes=labelR;
       countdownTimer.start();
    }

     class CountdownTimerListener implements ActionListener {
         public void actionPerformed(ActionEvent e) {

        	 if (seg==0){
        		 seg=0;		 
        	 }
        	 else{
        		seg=seg-1;
        		if(seg==10){
        			Random randomGenerator = new Random();
        			int x = randomGenerator.nextInt(4);
        			if (x == 1) {
        				labelRes.setText("<html><br>Creo que es la A");
        			}
        			if (x == 2) {
        				labelRes.setText("<html><br>Creo que es la B");
        			}
        			if (x == 3) {
        				labelRes.setText("<html><br>Creo que es la C");
        			}
        			if (x == 4) {
        				labelRes.setText("<html><br>Creo que es la D");
        			}
        			if (x == 0) {
        				labelRes.setText("<html><br>Perdon... No lo se!");
        			}
        		}
        	}
            label.setText("<html><br>"+String.valueOf(seg)+" Seg");
         }
     }
}

