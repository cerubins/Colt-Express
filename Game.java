
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


	//"src/images/BG1.png" - correct image pathing minus the ending
public class Game extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	Timer gameLoopTimer;
	private Character p1, p2, p3, p4;
	private Image bg;
	public int bgx = 0;
	public Game() throws IOException{
		setFocusable(true);
		
		gameLoopTimer = new Timer(10, this);
		gameLoopTimer.start();
		
		bg = ImageIO.read(new File("src/images/BG1.png"));
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		
		if(bgx<=-1920)bgx = 0;
		g2d.drawImage(bg,  bgx--, 0, 0, getHeight(),  this);
		g2d.drawString("TEST STRING", 400, 500);
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		String key = e.getActionCommand();
		
		if(key == "1"){
			
		}
		repaint();
	}
}