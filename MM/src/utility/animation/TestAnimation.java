package utility.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A class to test the animation file of an image;
 * Click mouse to get next frame;
 * @author User
 *
 */
public class TestAnimation extends JPanel implements MouseListener {
	
	Animation animation;
	int width = 300, height = 300;
	
	public TestAnimation(String s, BufferedImage img) {
		
		this.animation = new Animation(s, img);
		
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus();
		addMouseListener(this);
		
	}
	
	public void paintComponent(Graphics g) {

		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, width, height);
		g.drawImage(animation.getFrame(), 0, 0, null);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		paintComponent(getGraphics());
		animation.update();

		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * test
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		JFrame frame = new JFrame();
		frame.add(new TestAnimation("an0",ImageIO.read(new File("res/util/RoadRunner.png"))));
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}

