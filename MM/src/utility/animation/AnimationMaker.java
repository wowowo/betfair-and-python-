package utility.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Loads an image, tries to make it transparent and lets you
 * draw rectangles on it to specify the frames of an animation;
 * Left click in a rectangle to remove it;
 * Scroll click to save the rectangle coords to file and 
 * start a new animation;
 * Used to easily extract subImages from buffered image;
 * @author User
 * 
 */
public class AnimationMaker extends JPanel implements MouseListener {

	private int width, height;
	private BufferedImage img;
	private int rx, ry, rwidth = 1, rheight = 1;
	private boolean drawing;
	private ArrayList<Rectangle> rects;
	int anNum = 0;

	public AnimationMaker(String s, boolean transparent) throws IOException {

		if(transparent)
			img = ImageIO.read(new File(s));
		else 
			img = ImageIO.read(new File(new MakeTransparent(s, s).getNewImage()));
		
		width = img.getWidth();
		height = img.getHeight();
		rects = new ArrayList<Rectangle>();
		
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		requestFocus();
		addMouseListener(this);

	}

	public void paintComponent(Graphics g) {

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, width, height);
		g.drawImage(img, 0, 0, null);
		g.setColor(Color.BLACK);

		for(Rectangle e: rects)
			g.drawRect(e.x, e.y, e.width, e.height);

	}

	@Override
	public void mouseClicked(MouseEvent m) {
		
		if(m.getButton() == MouseEvent.BUTTON3) {
			
			int x1 = m.getX();
			int y1 = m.getY();
			
			for(int i = 0; i < rects.size(); i++) 
				if(x1 >= rects.get(i).x && x1 <= rects.get(i).width + rects.get(i).x &&
				   y1 >= rects.get(i).y && y1 <= rects.get(i).height + rects.get(i).y)
					rects.remove(i);
			
			paintComponent(getGraphics());
		}
		
		if(m.getButton() == MouseEvent.BUTTON2) {

			try {
				
				FileWriter writer = new FileWriter(new File("an" + anNum++));

				for(Rectangle e : rects)
					writer.write(e.x + " " + e.y + " " + e.width + " " + e.height + " ");
				
				rects = new ArrayList<Rectangle>();
				
				writer.close();
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
			}
		
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent m) {
		
		if (!drawing)
			if (m.getButton() == MouseEvent.BUTTON1) {
				drawing = true;
				rx = m.getX();
				ry = m.getY();
			}
	}

	@Override
	public void mouseReleased(MouseEvent m) {

		if(drawing) {
			
			drawing = false;
			
			if(m.getY() == ry && m.getX() == rx)
				return;
			
			rheight = m.getY() - ry;
			rwidth  = m.getX() - rx;
						
			if(rheight < 0) {
				rheight = -rheight;
				ry = m.getY();
			}
						
			if(rwidth < 0) {
				rwidth = -rwidth;
				rx = m.getX();
			}
				
			rects.add(new Rectangle(rx, ry, rwidth, rheight));
			paintComponent(getGraphics());
		}
		
		
		

	}

	/**
	 * test
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		JFrame frame = new JFrame();
		frame.add(new AnimationMaker("res/util/RoadRunner2.png", false));
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		

	}


}