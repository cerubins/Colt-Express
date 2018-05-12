import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

class Game extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	private char selectChar = 'e', secondarySelectChar = 'e';
    private Image bg, trainImage, characterSelectImage, blurredBg;
    private boolean characterSelectionMenu = true, char1Selected = false, secondarySelect = false;
    private Font font = new Font("sansserif", Font.PLAIN, 40);
    
    public Game() throws IOException {
        this.setPreferredSize(new Dimension(500, 500));
        addKeyListener(this);
        
		bg = ImageIO.read(new File("src/images/background.png"));
		blurredBg = ImageIO.read(new File("src/images/blurredBackground.png"));
		trainImage = ImageIO.read(new File("src/images/train.png"));
		characterSelectImage = ImageIO.read(new File("src/images/characterSelectMainImage.png"));
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public void paintComponent(Graphics g) {
    	String it = selectChar + "";
    	g.drawString(it, 0, 0);
    	if(characterSelectionMenu){
    		
    		//BEGIN CHARACTER SELECTOR
    		g.drawImage(blurredBg, 0, 0, this);
    		g.drawImage(characterSelectImage, 460, bg.getHeight(this) - 560, this);
    		
    		g.setFont(font);
    		
    		//NUMBERS ABOVE HEADS
    		g.drawString("1", 610, bg.getHeight(this) - 560);
    		g.drawString("2", 760, bg.getHeight(this) - 560);
    		g.drawString("3", 910, bg.getHeight(this) - 560);
    		g.drawString("4", 1060, bg.getHeight(this) - 560);
    		g.drawString("5", 1200, bg.getHeight(this) - 560);
    		g.drawString("6", 1310, bg.getHeight(this) - 560);
    		
    		if(selectChar == '1' && char1Selected == false) {
    			//set player 1
    			g.drawString("Would you like to be in the Caboose(4) or the car in front of the Caboose(3)?", 460, 300);
    			
    			if(secondarySelectChar == '4') {
    				System.out.println("CABOOSE SELECTED FOR CHAR 1");
    				char1Selected = true;
    			}
    			else if(secondarySelectChar == '3') {
    				System.out.println(" FRONT OF CABOOSE SELECTED FOR CHAR 1");
    				char1Selected = true;
    			}
    			
    			secondarySelect = true;
    		}
    		else if(selectChar == '2') {
    			//set player 2
    		}
    		else if(selectChar == '3') {
    			//set player 3
    		}
    		else if(selectChar == '4') {
    			//set player 4
    		}
    		else if(selectChar == '5') {
    			//set player 5
    		}
    		else if(selectChar == '6') {
    			//set player 6
    		}
    	
    	}
    	else {
    		g.drawImage(bg, 0, 0, this);
    		g.drawImage(trainImage, 0, bg.getHeight(this) - 470, this);
    	}
    }

    public void keyPressed(KeyEvent e) { }
    public void keyReleased(KeyEvent e) { }
    public void keyTyped(KeyEvent e) {
    	if(characterSelectionMenu && !secondarySelect) {
    		selectChar = e.getKeyChar();
    	}
    	else if(secondarySelect) {
        secondarySelectChar = e.getKeyChar();
    	}
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
