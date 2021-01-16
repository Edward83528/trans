package transfer.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import sysview.zhiren.function.SvString;
import transfer.entity.Tcpolice;

public class FileDeal {

	public static void main(String[] args) {

	}

	public Tcpolice deal(String filename, String dsid, int index) {
		String sourcepath = "";// 原本圖片路徑
		String targetpath = "";// 目標圖片路徑
		// 設定檔路徑
		String path = "D:\\webapps\\police\\trans.police.gov.tw\\src\\TransferData\\fileimage.properties";
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(path));
			sourcepath = properties.getProperty("sourcefilepath2");
			targetpath = properties.getProperty("targetfilepath2");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		String ndate = nowDate("yyyyMMddhhmmss");// 取系統日期時間(取到秒,讓之後檔案命名不會重複)
		// sourcepath = "D:\\picture\\";// 原本檔案路徑
		// targetpath = "D:\\newpicture\\";// 目標檔案路徑
		// System.out.println("sourcepath:" + sourcepath);
		// System.out.println("targetpath:" + targetpath);
		File oldfile1 = new File(sourcepath, filename);
		// 檔案如果存在
		if (oldfile1.exists()) {
			Tcpolice item = new Tcpolice();
			// 取出副檔名
			String subname = "." + SvString.lastRight(filename, ".");
			String sourcefile = "";
			String targetfile = "";
			String newserverfile = "";
			String fileflag = "";
			String msmallimg = "";
			// 如果是圖片
			if (subname.indexOf(".JPG") >= 0 || subname.indexOf(".jpg") >= 0 || subname.indexOf(".JPEG") >= 0
					|| subname.indexOf(".jpeg") >= 0 || subname.indexOf(".GIF") >= 0 || subname.indexOf(".gif") >= 0
					|| subname.toUpperCase().indexOf(".PNG") >= 0 || subname.indexOf(".png") >= 0
					|| subname.toUpperCase().indexOf(".BMP") >= 0 || subname.indexOf(".bmp") >= 0) {
				fileflag = "pic";
				String targetpath1 = targetpath + "pic\\" + dsid + "\\";// 目標資料夾路徑
				// 目標資料夾不存在，建立資料夾目錄
				File f = new File(targetpath1);
				if (!f.exists()) {
					f.mkdirs();
				}
				// newserverfile為 現在時間+ 第幾個檔案 + subname(.副檔名);
				newserverfile = ndate + String.valueOf(index) + subname;
				sourcefile = sourcepath + filename;
				targetfile = targetpath1 + newserverfile;
			} else {
				fileflag = "dos";
				String targetpath1 = targetpath + "dos\\" + dsid + "\\";// 目標資料夾路徑
				// 目標資料夾不存在，建立資料夾目錄
				File f = new File(targetpath1);
				if (!f.exists()) {
					f.mkdirs();
				}
				// newserverfile為 現在時間+ 第幾個檔案 + subname(.副檔名);
				newserverfile = ndate + String.valueOf(index) + subname;
				sourcefile = sourcepath + filename;
				targetfile = targetpath1 + newserverfile;

			}
			// System.out.println("sourcefile1=" + sourcefile);
			// System.out.println("targetfile1=" + targetfile);
			// 複製檔案
			try {
				FileInputStream fis = new FileInputStream(sourcefile);
				FileOutputStream fos = new FileOutputStream(targetfile);
				byte[] buf = new byte[1024];
				int k = 0;
				while ((k = fis.read(buf)) != -1) {
					fos.write(buf, 0, k);
				}
				fis.close();
				fos.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			// 複製檔案END

			// 自動縮圖
			if (subname.indexOf(".JPG") >= 0 || subname.indexOf(".jpg") >= 0 || subname.indexOf(".JPEG") >= 0
					|| subname.indexOf(".jpeg") >= 0 || subname.indexOf(".GIF") >= 0 || subname.indexOf(".gif") >= 0
					|| subname.toUpperCase().indexOf(".PNG") >= 0 || subname.indexOf(".png") >= 0
					|| subname.toUpperCase().indexOf(".BMP") >= 0 || subname.indexOf(".bmp") >= 0) {
				String autoresize = "N";
				String resizename = SvString.left(newserverfile, ".") + "_small" + subname;
				String resizeupload = targetpath + "\\pic\\" + dsid + "\\" + resizename;
				String sourceupload = targetpath + "\\pic\\" + dsid + "\\" + newserverfile;
				ImageMagick checkimageW = new ImageMagick();
				try {
					// System.out.println("sourceupload:" + sourceupload);
					// System.out.println("resizeupload:" + resizeupload);

					boolean smallimage = checkimageW.checkResizeByWidth(sourceupload, 220);

					if (smallimage) {
						ImageMagick image = new ImageMagick();
						image.converImage(220, sourceupload, resizeupload);
						autoresize = "Y";
					}
					// 如果有縮圖
					if (autoresize.equals("Y")) {
						msmallimg = resizename;
					} else {
						msmallimg = newserverfile;
					}
					ImageMagick sourceCompress = new ImageMagick();
					sourceCompress.CompressImage(sourceupload);

				} catch (Exception e) {
					System.out.println(e.toString());
				}

			}
			// 自動縮圖END
			// 存入資訊
			item.setImageMagick(msmallimg);
			item.setServerFile(newserverfile);
			return item;
		} else {
			System.out.println("檔案不存在");
			return null;
		}

	}

	// 取系統日期，傳入欲呈現的格式，如yyyyMMdd
	public static String nowDate(String formatstyle) {
		Format formatter = new SimpleDateFormat(formatstyle);
		Date todayDate = new Date();
		return formatter.format(todayDate);
	}

}
