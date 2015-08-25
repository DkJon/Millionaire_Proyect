package proyect.com;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image imagen = null;
	private int ancho = 0;
	private int alto = 0;

    public ImagePanel(String filename, int ancho, int alto) {
        this.imagen = new ImageIcon(filename).getImage();
        this.ancho=ancho;
        this.alto=alto;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, ancho, alto, null);
    }

}
