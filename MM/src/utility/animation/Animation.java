package utility.animation;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * An Animation class reads from the files of the Coords class;
 * Holds the coordinates of the frames of the animation on the Sprite Sheet;
 * Returns the next frame the animation;
 * Has helper class frame, that just stores the 4 coord values;
 * 
 * 
 * Used when all frames are in one file;
 * @author User
 *
 */
public class Animation {
	
	private ArrayList<Frame> frames;
	private BufferedImage sprite;
	private int currentFrame;
	private boolean played;
	
	public Animation(String s, BufferedImage img) {
		
		sprite = img;
		frames = new ArrayList<Frame>();
		currentFrame = 0;
		try {
			Scanner scnr = new Scanner(new File(s));
			while(scnr.hasNext())
				frames.add(new Frame(scnr.nextInt(), scnr.nextInt(), scnr.nextInt(), scnr.nextInt()));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		

		currentFrame = (currentFrame + 1) % frames.size() ;
		if(currentFrame == 0)
			played = true;
		
	}
	
	public void start() {
		
		currentFrame = 0;
				
	}
	
	public boolean hasPlayed() {
		return played;
	}
	
	public BufferedImage getFrame() {
		Frame fr = frames.get(currentFrame);
		return sprite.getSubimage(fr.x, fr.y, fr.width, fr.height);
	}
	
	
	private class Frame {
		
		int x, y, width, height;
		
		Frame(int x, int y, int w, int h) {
			this.x = x;
			this.y = y;
			this.width = w;
			this.height = h;
		}
		
	}

}

