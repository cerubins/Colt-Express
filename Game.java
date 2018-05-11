

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

class Game extends JPanel implements KeyListener {
    private char c = 'e';
    private Image bg, trainImage, characterSelectImage;
    private boolean characterSelectionMenu = true;
    
    public Game() throws IOException {
        this.setPreferredSize(new Dimension(500, 500));
        addKeyListener(this);
        
		bg = ImageIO.read(new File("src/images/BG1.png"));
		trainImage = ImageIO.read(new File("src/images/trainImage.png"));
		characterSelectImage = ImageIO.read(new File("src/images/characterSelectLinupImage.png"));
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public void paintComponent(Graphics g) {
    	g.drawImage(bg, 0, 0, this);
    	
    	if(characterSelectionMenu){
    		g.drawImage(characterSelectImage, 0, bg.getHeight(this) - 570, bg.getWidth(this), bg.getHeight(this)/2, this);
    		g.drawOval(320, 400, 200, 200);
    	}
    	else {
    		g.drawImage(trainImage, 0, bg.getHeight(this) - 470, this);
    	}
    }

    public void keyPressed(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) {
        c = e.getKeyChar();
        repaint();
    }

    public static void main(String[] s) throws IOException {
        JFrame f = new JFrame();
        f.getContentPane().add(new Game());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setPreferredSize(new Dimension(1920, 1080));
        f.pack();
        f.setVisible(true);
        
    }
}
