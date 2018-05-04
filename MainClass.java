import java.io.IOException;

import javax.swing.JFrame;

public class MainClass {

	
	public static void main(String args[]) throws IOException{
		
		JFrame frame = new JFrame();
		frame.pack();
		frame.setSize(1920, 1080);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Game());
		frame.setVisible(true);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	}
}