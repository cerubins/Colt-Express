

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


	//"src/images/BG1.png" - correct image pathing minus the ending
public class Game extends JPanel implements ActionListener, KeyListener{
	
	private static final long serialVersionUID = 1L;
	
	Timer gameLoopTimer;
	private Character p1, p2, p3, p4;
	private Image bg, trainImage;
	private boolean choice1, choice2, choice3, choice4, choice5;
	public JFrame a;
	public JPanel pane;
	public int bgx = -960;
	public char choice = 'z';
	
	public Game(JFrame j) throws IOException{
		setFocusable(true);
		a=j;
		pane = new JPanel();
		
		a.add(pane);
		
		pane.addKeyListener(this);
		pane.addNotify();

		gameLoopTimer = new Timer(10, this);
		gameLoopTimer.start();
		
		
		
		bg = ImageIO.read(new File("src/images/BG1.png"));
		trainImage = ImageIO.read(new File("src/images/trainImage.png"));
	}
	
	public void addNotify(){
		super.addNotify();
		requestFocus();
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		
		//DRAWING BACKGROUND
		if(bgx>=0)bgx = -960;
		g2d.drawImage(bg, bgx+=4, 0, getWidth()*2, getHeight(), this); 
		
		//DRAWING TRAIN
		g2d.drawImage(trainImage, 0, getHeight()-470,trainImage.getWidth(this) , trainImage.getHeight(this), this);
		
		switch(choice){
		case 'e':
			System.out.println("KILL ME NOW");
		}
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e){

		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		choice = e.getKeyChar();
	}
}

class MyPanel extends JPanel implements KeyListener {
    private char c = 'e';

    public MyPanel() {
        this.setPreferredSize(new Dimension(500, 500));
        addKeyListener(this);
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public void paintComponent(Graphics g) {
        g.clearRect(0, 0, getWidth(), getHeight());
        g.drawString("the key that pressed is " + c, 250, 250);
    }

    public void keyPressed(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) {
        c = e.getKeyChar();
        repaint();
    }

    public static void main(String[] s) {
        JFrame f = new JFrame();
        f.getContentPane().add(new MyPanel());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
        f.setVisible(true);
    }
}
