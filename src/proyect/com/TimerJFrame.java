package proyect.com;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class TimerJFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public TimerJFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(200, 200);
		JLabel label = new JLabel("0 Min, 0 Seg", JLabel.CENTER);
		getContentPane().add(label);
		new TestTimer(label);
		setVisible(true);
	}

//	 public static void main(String[] args) {
//		 new TimerJFrame();
//	 }

}
