
package transfer.component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;

public class ImageMagickResize {
	public static void main(String[] args) {
	}

	public ImageMagickResize() {
		System.setProperty("jmagick.systemclassloader", "no");
	}

	// 壓縮圖的程式路徑
	String convertpath = "C:\\Program Files (x86)\\ImageMagick-6.3.9-Q16\\convert.exe";
	String pngquantpath = "D:\\Program Files\\pngquant\\pngquant.exe";

	public int resizeImage(String source, String To, int width, int height) {
		try {
			if (System.getProperty("jmagick.systemclassloader") == null) {
				System.setProperty("jmagick.systemclassloader", "no");
			}
			ImageInfo info = new ImageInfo(source);
			MagickImage image = new MagickImage(info);

			MagickImage scaleImg = image.scaleImage(width, height);

			scaleImg.setFileName(To);
			scaleImg.writeImage(info);

			Compress(To);

			return 1;
		} catch (MagickException t) {
			t.printStackTrace();
			return -1;
		}

	}

	@SuppressWarnings("unused")
	public void Compress(String To) throws MagickException {
		try {
			String subname = To.substring(To.lastIndexOf("."));

			if (subname.toUpperCase().equals(".PNG")) {
				ImagePNGCompress pngquant = new ImagePNGCompress(pngquantpath);
				int ret = pngquant.execute(To);

				pngquant.destroy();
			} else {
				String[] cmd = new String[6];
				cmd[0] = convertpath;

				cmd[1] = "-strip";
				cmd[2] = "-quality";
				cmd[3] = "80%";
				cmd[4] = To;
				cmd[5] = To;

				Runtime rt = Runtime.getRuntime();

				Process proc = rt.exec(cmd);
				// any error message?
				StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERROR");

				// any output?
				StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "OUTPUT");

				// kick them off
				errorGobbler.start();
				outputGobbler.start();

				int exitVal = proc.waitFor();
				System.out.println("ExitValue: " + exitVal);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	class StreamGobbler extends Thread {
		InputStream is;
		String type;

		StreamGobbler(InputStream is, String type) {
			this.is = is;
			this.type = type;
		}

		public void run() {
			InputStreamReader isr = null;
			BufferedReader br = null;
			try {
				isr = new InputStreamReader(is);
				br = new BufferedReader(isr);
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println(type + ">" + line);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				if (isr != null) {
					try {
						isr.close();
					} catch (Exception e2) {
						e2.printStackTrace();

					}
				}
			}
		}
	}

}
