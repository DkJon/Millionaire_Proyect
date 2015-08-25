package proyect.com;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TimerJFrame extends JPanel {
	private static final long serialVersionUID = 1L;

	public TimerJFrame() {
		setSize(200, 200);
		JLabel label = new JLabel("<html><br>0 Min, 0 Seg", JLabel.CENTER);
		add(label);
		new TestTimer(label);
		setVisible(true);
	}

}
