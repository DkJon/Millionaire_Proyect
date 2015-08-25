package proyect.com;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class TestTimer {
	private JLabel label;
    Timer countdownTimer;
    int seg = 0;
    int min=0;

    public TestTimer(JLabel passedLabel) {
       countdownTimer = new Timer(1000, new CountdownTimerListener());
       this.label = passedLabel;
       countdownTimer.start();
    }

     class CountdownTimerListener implements ActionListener {
         public void actionPerformed(ActionEvent e) {
        	++seg;
        	if (seg==60){
        		++min;
        		seg=0;
        	}
            label.setText(String.valueOf(min)+" Min, "+String.valueOf(seg)+" Seg");
         }
     }
}
