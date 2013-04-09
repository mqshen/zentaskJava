package org.goldratio.util;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/** 
 * ClassName: ImageResizer <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 4, 2013 6:28:03 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

public class ImageResizer {

	public static void resizeImage(InputStream ins, ByteArrayOutputStream baos, int maxWidth, int maxHeight) throws IOException {
		BufferedImage originalImage = ImageIO.read(ins);
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();
		
		if (width <= maxWidth && height <= maxHeight) {
			ImageIO.write( originalImage, "jpg", baos );
		}
		else {
			int scaledWidth = width;
			int scaledHeight = height;
			double ratio = width/height;
			if (scaledWidth > maxWidth){
		        scaledWidth = maxWidth;
		        scaledHeight = (int) (scaledWidth/ratio);
		    }
			if (scaledHeight > maxHeight){
		        scaledHeight = maxHeight;
		        scaledWidth = (int) (scaledHeight*ratio);
			}
			BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight,  BufferedImage.TYPE_INT_RGB);
			Graphics2D g = scaledBI.createGraphics();
			g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
			g.dispose();
			ImageIO.write(scaledBI, "jpg", baos );
		}
		baos.flush();
	}
}
