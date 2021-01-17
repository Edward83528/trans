package transfer.component;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Extraction {

	/**
	 * regex：获取<img src="">标签的正则（"<img.*?>"） string：提取图片标签的内容 isDistinguish：是否区分大小写
	 */
	public static List<String> getImgMatchString(String string, boolean isDistinguish, String filePath) {
		List<String> pics = new ArrayList<>();
		try {
			DownloadImage downloadImage = new DownloadImage();

			Pattern compile = null;
			if (isDistinguish) { // isDistinguish:是否区分大小写
				compile = Pattern.compile("<img.*?>"); // "<img.*?>" : 获取标签的正则
			} else {
				compile = Pattern.compile("<img.*?>", Pattern.CASE_INSENSITIVE);
			}
			Matcher matcher = compile.matcher(string);
			while (matcher.find()) {
				String img = matcher.group();
				// pics.add(img); // 如果只需要标签，那到这一步就可以了，如果直接需要图片URL，copy代码到最后
				/**
				 * reg_html_img_src_http: 获取src中 "" 图片地址的正则("\"http?(.*?)(\"|>|\\s+)")
				 * 
				 * 获取标签中src的正则表达式（"src\\s*=\\s*\"?(.*?)(\"|>|\\s+)"）
				 */

				Matcher m = Pattern.compile("\"http?(.*?)(\"|>|\\s+)").matcher(img);
				m.find();
				String group = m.group();
				String urlLink = group.substring(1, group.length() - 1);
				System.out.println(urlLink);
				String img_server = urlLink.replace("http://www.pbs.gov.tw/upload/ckfinder/images/", "");
				String decode_img_server = URLDecoder.decode(img_server, "utf-8");
				downloadImage.download(urlLink, decode_img_server, filePath);
				pics.add(urlLink);
			}
			return pics;
		} catch (Exception e) {
			System.out.println("match error" + e.toString());
			return pics;
		}

	}

}
