package transfer.component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadImage {

	public void download(String urlString, String filename, String savePath) {
		try {
			// 構造URL
			URL url = new URL(urlString);
			// 開啟連線
			URLConnection con = url.openConnection();
			// 設定請求超時為5s
			con.setConnectTimeout(5 * 1000);
			// 輸入流
			InputStream is = con.getInputStream();

			// 1K的資料緩衝
			byte[] bs = new byte[1024];
			// 讀取到的資料長度
			int len;
			// 輸出的檔案流
			File sf = new File(savePath);
			if (!sf.exists()) {
				sf.mkdirs();
			}
			OutputStream os = new FileOutputStream(sf.getPath() + "\\" + filename);
			// 開始讀取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完畢，關閉所有連結
			os.close();
			is.close();
		} catch (Exception e) {
			System.out.println(urlString + "e.toString():" + e.toString());
		}
	}

}
