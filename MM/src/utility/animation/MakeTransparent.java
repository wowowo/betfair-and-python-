package utility.animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.*;

import javax.imageio.ImageIO;

/**
 * Same as http://stackoverflow.com/questions/665406/how-to-make-a-color-transparent-in-a-bufferedimage-and-save-as-png
 * @author User
 *
 */
public class MakeTransparent {
	
	private String od;

	public MakeTransparent(String path, String op) throws IOException {

		od = op;
		
		BufferedImage img = ImageIO.read(new File(path));

		int color = img.getRGB(0, 0);
		
		Image transp = remove(img, new Color(color));

		BufferedImage output = ImageToBufferedImage(transp, img.getWidth(), img.getHeight());

		ImageIO.write(output, "PNG", new File(op));

	}
	
	public String getNewImage() {
		
		return od;
		
	}

	private Image remove(BufferedImage img, final Color color) {

		ImageFilter filter = new RGBImageFilter() {

			public int markerRGB = color.getRGB() | 0xFF000000;

			public final int filterRGB(int x, int y, int rgb) {
				
				if ((rgb | 0xFF000000) == markerRGB)
					return 0x00FFFFFF & rgb;
				
				else 
					return rgb;
			}
		};

		ImageProducer ip = new FilteredImageSource(img.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}

	private BufferedImage ImageToBufferedImage(Image image, int width, int height) {

		BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2 = dest.createGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();

		return dest;
	}
	

}