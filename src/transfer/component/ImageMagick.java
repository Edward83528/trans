
package transfer.component;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageMagick {
	public static void main(String[] args) {
	}

	public ImageMagick() {
		System.setProperty("jmagick.systemclassloader", "no");
	}

	private String errorMsg = null;

	public String getErrorMsg() {
		return errorMsg;
	}

	/*** 判斷原圖片寬是否比要縮圖的寬 ***/
	public boolean checkResizeByWidth(String srcImage, int width) throws IOException {
		try {
			BufferedImage bf = ImageIO.read(new File(srcImage));
			int oriWidth = bf.getWidth();
			if (oriWidth > width) {
				return true;
			} else {
				return false;
			}

		} catch (Exception ex) {
			System.out.println("error=" + ex);
		}
		return false;
	}

	/*** 依寬等比縮圖 ***/
	public void converImage(int imageresizeW, String sSourceFile, String sDestFile) throws IOException {

		if (System.getProperty("jmagick.systemclassloader") == null) {
			System.setProperty("jmagick.systemclassloader", "no");
		}

		try {
			BufferedImage bf = ImageIO.read(new File(sSourceFile));

			int imageW = bf.getWidth();
			int imageH = bf.getHeight();
			int imageresizeH = (imageresizeW * imageH) / imageW;

			ImageMagickResize imageresize = new ImageMagickResize();
			imageresize.resizeImage(sSourceFile, sDestFile, imageresizeW, imageresizeH);

		} catch (Exception ex) {
			errorMsg = ex.toString();
			System.out.println("error=" + ex);
		}

	}

	public void CompressImage(String sSourceFile) {
		try {
			ImageMagickResize imagecompress = new ImageMagickResize();
			imagecompress.Compress(sSourceFile);

		} catch (Exception ex) {
			errorMsg = ex.toString();
			System.out.println("error=" + ex);
		}
	}

	// 判斷是直式圖還是橫式圖
	public boolean CheckWidthHeight(String srcImage) {
		try {
			BufferedImage bf = ImageIO.read(new File(srcImage));
			int oriWidth = bf.getWidth();
			int oriHeight = bf.getHeight();
			if (oriHeight > oriWidth) {
				return true;
			}
		} catch (Exception ex) {
			System.out.println("error=" + ex);
		}
		return false;
	}

	/*** 固定寬跟高縮圖 ***/
	public void converImageFixed(int imageresizeW, int imageresizeH, String sSourceFile, String sDestFile)
			throws IOException {

		if (System.getProperty("jmagick.systemclassloader") == null) {
			System.setProperty("jmagick.systemclassloader", "no");
		}

		try {

			ImageMagickResize imageresize = new ImageMagickResize();
			imageresize.resizeImage(sSourceFile, sDestFile, imageresizeW, imageresizeH);

		} catch (Exception ex) {
			errorMsg = ex.toString();
			System.out.println("error=" + ex);
		}

	}

}
