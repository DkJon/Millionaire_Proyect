package proyect.com;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class CallJframe extends JPanel {
	private static final long serialVersionUID = 2;

	public CallJframe() {	
		JLabel label = new JLabel("<html><br>30 Seg", JLabel.CENTER);
		JLabel labelRes=new JLabel("",JLabel.CENTER);
		add(label);
		add(labelRes);
		new TimerCall(label,labelRes);
		setVisible(true);
	}

}

