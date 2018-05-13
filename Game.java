import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.*;

class Game extends JPanel implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	
	private char selectChar = 'e', secondarySelectChar = 'e';
	
    private Image bg, trainImage, characterSelectImage, blurredBg, belleImg, tucoImg, djangoImg, cheyenneImg, docImg, ghostImg;
    
    private boolean characterSelectionMenu = true, char1Selected = false, secondarySelect = false, char2Selected = false, 
    		char3Selected = false, char4Selected = false, char5Selected = false, char6Selected = false;
    
    private Font font = new Font("sansserif", Font.PLAIN, 40);
    
    private Train mainTrain = new Train();
	
	private static ArrayList<Character> finalchar = new ArrayList<Character>();
	
	private static TreeMap <Character, ArrayList <ActionCard>> hands = new TreeMap <Character, ArrayList <ActionCard>> ();
	
	private static TreeMap <Character, ArrayList <ActionCard>> bulletCards = new TreeMap <Character, ArrayList <ActionCard>> ();
		
	private static TreeMap <Character, ArrayList <ActionCard>> discard = new TreeMap <Character, ArrayList <ActionCard>> ();
	
	private static TreeMap <Character, ArrayList <ActionCard>> draw = new TreeMap <Character, ArrayList <ActionCard>> ();
	
	private int trainStartX = 329, trainCarLength = 314, trainCarHeightFromGround = 114, trainCarHeightCalc, characterSelectedCount = 0;
    
    public Game() throws IOException {
        this.setPreferredSize(new Dimension(500, 500));
        addKeyListener(this);
        
		bg = ImageIO.read(new File("src/images/background.png"));
		blurredBg = ImageIO.read(new File("src/images/blurredBackground.png"));
		trainImage = ImageIO.read(new File("src/images/train.png"));
		characterSelectImage = ImageIO.read(new File("src/images/characterSelectMainImage.png"));
		
		belleImg = ImageIO.read(new File("src/images/belleImg.png"));
		tucoImg = ImageIO.read(new File("src/images/tucoImg.png"));
		djangoImg = ImageIO.read(new File("src/images/djangoImg.png"));
		ghostImg = ImageIO.read(new File("src/images/ghostImg.png"));
		docImg = ImageIO.read(new File("src/images/docImg.png"));
		cheyenneImg = ImageIO.read(new File("src/images/cheyenneImg.png"));
    }

    public void addNotify() {
        super.addNotify();
        requestFocus();
    }

    public void paintComponent(Graphics g) {
    	trainCarHeightCalc = (bg.getHeight(this) - trainCarHeightFromGround - 157);
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
    		
    		if(selectChar == '1' && char1Selected == true) JOptionPane.showMessageDialog(null, "INVALID CHOICE: CHARACTER ALREADY CHOSEN");
    		else if(selectChar == '2' && char2Selected == true) JOptionPane.showMessageDialog(null, "INVALID CHOICE: CHARACTER ALREADY CHOSEN");
    		else if(selectChar == '3' && char3Selected == true) JOptionPane.showMessageDialog(null, "INVALID CHOICE: CHARACTER ALREADY CHOSEN");
    		else if(selectChar == '4' && char4Selected == true) JOptionPane.showMessageDialog(null, "INVALID CHOICE: CHARACTER ALREADY CHOSEN");
    		else if(selectChar == '5' && char5Selected == true) JOptionPane.showMessageDialog(null, "INVALID CHOICE: CHARACTER ALREADY CHOSEN");
    		else if(selectChar == '6' && char6Selected == true) JOptionPane.showMessageDialog(null, "INVALID CHOICE: CHARACTER ALREADY CHOSEN");
    		
    		if(selectChar == '1' && char1Selected == false) {
    			//set player 1
    			
    			if(secondarySelectChar == '4') {
    				System.out.println("CABOOSE SELECTED FOR CHAR 1");
    				char1Selected = true;
    				secondarySelect = false;
    				characterSelectedCount++;
    				
    				Character created = new Character ("belle", 4, 0, (trainStartX + trainCarLength * 4) + mainTrain.getTrainCar(4).getPlatform(0).getCharacterList().size() * 62, trainCarHeightCalc);
    				created.setImage(belleImg);
    				finalchar.add(created);
    				
    				mainTrain.getTrainCar(4).getPlatform(0).addPlayer(created);
    			}
    			else if(secondarySelectChar == '3') {
    				System.out.println(" FRONT OF CABOOSE SELECTED FOR CHAR 1");
    				char1Selected = true;
    				secondarySelect = false;
    				characterSelectedCount++;
    				
    				Character created = new Character ("belle", 3, 0, (trainStartX + trainCarLength * 3) + mainTrain.getTrainCar(3).getPlatform(0).getCharacterList().size() * 62, trainCarHeightCalc);
    				created.setImage(belleImg);
    				finalchar.add(created);
    				
    				mainTrain.getTrainCar(3).getPlatform(0).addPlayer(created);
    			}
    			else {
    				g.drawString("Would you like to be in the Caboose(4) or the car in front of the Caboose(3)?", 0, 400);
    				secondarySelect = true;
    			}
    			
    		}
    		else if(selectChar == '2' && char2Selected == false) {
    			//set player 2
    			
    			if(secondarySelectChar == '4') {
    				System.out.println("CABOOSE SELECTED FOR CHAR 2");
    				char2Selected = true;
    				secondarySelect = false;
    				characterSelectedCount++;
    				
    				Character created = new Character ("belle", 4, 0, (trainStartX + trainCarLength * 4) + mainTrain.getTrainCar(4).getPlatform(0).getCharacterList().size() * 62, trainCarHeightCalc);
    				created.setImage(tucoImg);
    				finalchar.add(created);
    				
    				mainTrain.getTrainCar(4).getPlatform(0).addPlayer(created);
    			}
    			else if(secondarySelectChar == '3') {
    				System.out.println(" FRONT OF CABOOSE SELECTED FOR CHAR 2");
    				char2Selected = true;
    				secondarySelect = false;
    				characterSelectedCount++;
    				
    				Character created = new Character ("belle", 3, 0, (trainStartX + trainCarLength * 3) + mainTrain.getTrainCar(3).getPlatform(0).getCharacterList().size() * 62, trainCarHeightCalc);
    				created.setImage(tucoImg);
    				finalchar.add(created);
    				
    				mainTrain.getTrainCar(3).getPlatform(0).addPlayer(created);
    			}
    			else {
    				g.drawString("Would you like to be in the Caboose(4) or the car in front of the Caboose(3)?", 0, 400);
    				secondarySelect = true;
    			}
    			
    		}
    		else if(selectChar == '3' && char3Selected == false) {
    			//set player 3
    			
    			if(secondarySelectChar == '4') {
    				System.out.println("CABOOSE SELECTED FOR CHAR 3");
    				char3Selected = true;
    				secondarySelect = false;
    				characterSelectedCount++;
    				
    				Character created = new Character ("belle", 4, 0, (trainStartX + trainCarLength * 4) + mainTrain.getTrainCar(4).getPlatform(0).getCharacterList().size() * 62, trainCarHeightCalc);
    				created.setImage(djangoImg);
    				finalchar.add(created);
    				
    				mainTrain.getTrainCar(4).getPlatform(0).addPlayer(created);
    			}
    			else if(secondarySelectChar == '3') {
    				System.out.println(" FRONT OF CABOOSE SELECTED FOR CHAR 3");
    				char3Selected = true;
    				secondarySelect = false;
    				characterSelectedCount++;
    				
    				Character created = new Character ("belle", 3, 0, (trainStartX + trainCarLength * 3) + mainTrain.getTrainCar(3).getPlatform(0).getCharacterList().size() * 62, trainCarHeightCalc);
    				created.setImage(djangoImg);
    				finalchar.add(created);
    				
    				mainTrain.getTrainCar(3).getPlatform(0).addPlayer(created);
    			}
    			else {
    				g.drawString("Would you like to be in the Caboose(4) or the car in front of the Caboose(3)?", 0, 400);
    				secondarySelect = true;
    			}
    		}
    		else if(selectChar == '4' && char4Selected == false) {
    			//set player 4

    			if(secondarySelectChar == '4') {
    				System.out.println("CABOOSE SELECTED FOR CHAR 4");
    				char4Selected = true;
    				secondarySelect = false;
    				characterSelectedCount++;
    				
    				Character created = new Character ("belle", 4, 0, (trainStartX + trainCarLength * 4) + mainTrain.getTrainCar(4).getPlatform(0).getCharacterList().size() * 62, trainCarHeightCalc);
    				created.setImage(ghostImg);
    				finalchar.add(created);
    				
    				mainTrain.getTrainCar(4).getPlatform(0).addPlayer(created);
    			}
    			else if(secondarySelectChar == '3') {
    				System.out.println(" FRONT OF CABOOSE SELECTED FOR CHAR 4");
    				char4Selected = true;
    				secondarySelect = false;
    				characterSelectedCount++;
    				
    				Character created = new Character ("belle", 3, 0, (trainStartX + trainCarLength * 3) + mainTrain.getTrainCar(3).getPlatform(0).getCharacterList().size() * 62, trainCarHeightCalc);
    				created.setImage(ghostImg);
    				finalchar.add(created);
    				
    				mainTrain.getTrainCar(3).getPlatform(0).addPlayer(created);
    			}
    			else {
    				g.drawString("Would you like to be in the Caboose(4) or the car in front of the Caboose(3)?", 0, 400);
    				secondarySelect = true;
    			}
    		}
    		else if(selectChar == '5' && char5Selected == false) {
    			//set player 5

    			if(secondarySelectChar == '4') {
    				System.out.println("CABOOSE SELECTED FOR CHAR 5");
    				char5Selected = true;
    				secondarySelect = false;
    				characterSelectedCount++;
    				
    				Character created = new Character ("belle", 4, 0, (trainStartX + trainCarLength * 4) + mainTrain.getTrainCar(4).getPlatform(0).getCharacterList().size() * 62, trainCarHeightCalc);
    				created.setImage(docImg);
    				finalchar.add(created);
    				
    				mainTrain.getTrainCar(4).getPlatform(0).addPlayer(created);
    			}
    			else if(secondarySelectChar == '3') {
    				System.out.println(" FRONT OF CABOOSE SELECTED FOR CHAR 5");
    				char5Selected = true;
    				secondarySelect = false;
    				characterSelectedCount++;
    				
    				Character created = new Character ("belle", 3, 0, (trainStartX + trainCarLength * 3) + mainTrain.getTrainCar(3).getPlatform(0).getCharacterList().size() * 62, trainCarHeightCalc);
    				created.setImage(docImg);
    				finalchar.add(created);
    				
    				mainTrain.getTrainCar(3).getPlatform(0).addPlayer(created);
    			}
    			else {
    				g.drawString("Would you like to be in the Caboose(4) or the car in front of the Caboose(3)?", 0, 400);
    				secondarySelect = true;
    			}
    		}
    		else if(selectChar == '6' && char6Selected == false) {
    			//set player 6

    			if(secondarySelectChar == '4') {
    				System.out.println("CABOOSE SELECTED FOR CHAR 6");
    				char6Selected = true;
    				secondarySelect = false;
    				characterSelectedCount++;
    				
    				Character created = new Character ("belle", 4, 0, (trainStartX + trainCarLength * 4) + mainTrain.getTrainCar(4).getPlatform(0).getCharacterList().size() * 62, trainCarHeightCalc);
    				created.setImage(cheyenneImg);
    				finalchar.add(created);
    				
    				mainTrain.getTrainCar(4).getPlatform(0).addPlayer(created);
    			}
    			else if(secondarySelectChar == '3') {
    				System.out.println(" FRONT OF CABOOSE SELECTED FOR CHAR 6");
    				char6Selected = true;
    				secondarySelect = false;
    				characterSelectedCount++;
    				
    				Character created = new Character ("belle", 3, 0, (trainStartX + trainCarLength * 3) + mainTrain.getTrainCar(3).getPlatform(0).getCharacterList().size() * 62, trainCarHeightCalc);
    				created.setImage(cheyenneImg);
    				finalchar.add(created);
    				
    				mainTrain.getTrainCar(3).getPlatform(0).addPlayer(created);
    			}
    			else {
    				g.drawString("Would you like to be in the Caboose(4) or the car in front of the Caboose(3)?", 0, 400);
    				secondarySelect = true;
    			}
    		}
    		secondarySelectChar = 'e';
    		
    		if(characterSelectedCount == 4) {
    			characterSelectionMenu = false;
    			repaint();
    		}
    	}
    	else {
    		g.drawImage(bg, 0, 0, this);
    		g.drawImage(trainImage, 0, bg.getHeight(this) - 470, this);
    		
    		for(int x = 0; x < finalchar.size(); x++) {
    			Character tempChar = finalchar.get(x);
    			g.drawImage(tempChar.getImage(), tempChar.getX(), tempChar.getY(), this);
    		}
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
