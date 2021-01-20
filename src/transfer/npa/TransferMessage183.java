package transfer.npa;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FilenameUtils;

import transfer.component.DownloadImage;
import transfer.component.Extraction;
import transfer.component.Snowflake;
import transfer.entity.Message;
import transfer.entity.Link;

// 3.警政報馬仔wg183
public class TransferMessage183 extends Thread {
	// MySQL資料庫連線資訊
	private String driver = "com.mysql.jdbc.Driver";
	private static String databaseName_mysql = "pbs";
	private String url = "jdbc:mysql://localhost:3306/" + databaseName_mysql;
	private String user = "u0151051";
	private String password = "zxcvbnm0351";
	// MSSQL資料庫連線資訊
	private static String databaseName_ms = "NpaWebDB";
	private String connectionUrl = "jdbc:sqlserver://10.40.201.54:1433;" + "user=systex;password=systex97311466;"
			+ "databaseName=" + databaseName_ms + ";useUnicode=true;characterEncoding=utf-8;";
	// 連結URL取代為空的片段
	private String urlReplace = "https://www.pbs.gov.tw/upload/cht/attachment/";
	// 錯誤訊息
	private String errorMsg = null;
	// Ms資料表名稱
	private String datatablename = null;
	private String filetablename = null;
	private String resourcetablename = null;
	private String postertablename = null;
	// 樹節點
	private String NodeId = "";
	// dsid
	private String dsid = "wg183";
	// 轉檔後檔案輸出路徑
	private String filePath = "D:\\transferFile\\" + dsid;
	// 轉檔後檔案輸出路徑(ck)
	private String filePath_ck = "D:\\transferCkFile\\" + dsid;
	// 語言
	private String LanguageType = null;
	// 類別序號
	private ArrayList<String> classSerno = new ArrayList<String>();
	// 類別名稱
	private ArrayList<String> className = new ArrayList<String>();

	public static void main(String[] args) {

		TransferMessage183 transfer = new TransferMessage183();
		// 設置資料表參數
		transfer.setDatatablename("[" + databaseName_ms + "].[dbo].[ODDT_MESSAGES]");
		transfer.setPostertablename("[" + databaseName_ms + "].[dbo].[ODDT_MESSAGEPOSTER]");
		transfer.setResourcetablename("[" + databaseName_ms + "].[dbo].[ODDT_DATARESOURCES]");
		transfer.setFiletablename("[" + databaseName_ms + "].[dbo].[ODDT_DATAFILES]");
		// 讀進
		ArrayList<Message> in = transfer.transform_in();
		if (in != null && in.size() > 0) {
			System.out.println("讀進資料共:" + in.size() + "筆");
		}
		// 讀出並寫入
		// System.out.println("轉檔是否成功:" + transfer.transform_out(in));

	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getDatatablename() {
		return datatablename;
	}

	public void setDatatablename(String datatablename) {
		this.datatablename = datatablename;
	}

	public String getFiletablename() {
		return filetablename;
	}

	public void setFiletablename(String filetablename) {
		this.filetablename = filetablename;
	}

	public String getResourcetablename() {
		return resourcetablename;
	}

	public void setResourcetablename(String resourcetablename) {
		this.resourcetablename = resourcetablename;
	}

	public String getPostertablename() {
		return postertablename;
	}

	public void setPostertablename(String postertablename) {
		this.postertablename = postertablename;
	}

	public String getNodeId() {
		return NodeId;
	}

	public void setNodeId(String nodeId) {
		NodeId = nodeId;
	}

	public ArrayList<String> getClassSerno() {
		return classSerno;
	}

	public void setClassSerno(ArrayList<String> classSerno) {
		this.classSerno = classSerno;
	}

	public ArrayList<String> getClassName() {
		return className;
	}

	public void setClassName(ArrayList<String> className) {
		this.className = className;
	}

	public String getDsid() {
		return dsid;
	}

	public void setDsid(String dsid) {
		this.dsid = dsid;
	}

	public String getLanguageType() {
		return LanguageType;
	}

	public void setLanguageType(String languageType) {
		LanguageType = languageType;
	}

	// 查詢原Mysql資料
	public ArrayList<Message> transform_in() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			if (conn != null && !conn.isClosed()) {
				System.out.println("讀進mysql資料庫連線測試成功！");
				// 建立statement
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				StringBuilder sql = new StringBuilder();
				sql.append("select * from " + databaseName_mysql + ".km_article where category_id=143 ");
				// System.out.println(sql.toString());
				ResultSet rs = stmt.executeQuery(sql.toString());

				ArrayList<Message> lists = new ArrayList<Message>();

				for (int i = 0; rs.next(); i++) {
					Message list = new Message();

					String serno = "3A2M" + String.valueOf(rs.getInt("ID"));
					list.setOD_SERNO(serno.replaceAll(" ", "").trim());
					list.setOD_PUBUNITDN(MappingDept.mappingNPAPubUnitDN(String.valueOf(rs.getInt("group_ID"))));
					list.setOD_SUBJECT(rs.getString("title"));
					list.setOD_DETAILCONTENT("<div class=\"ed_model01 clearfix\"><div class=\"ed_txt\">"
							+ rs.getString("content") + "</div></div>");

					// 下載內容的圖片
					Extraction.getImgMatchString(rs.getString("content"), true, filePath_ck);

					list.setOD_POSTERDATE(parse(timestampToDate(rs.getLong("date_start"))));

					list.setOD_CLOSEDATE("0".equals(rs.getString("noexpire")) ? parse("2020-09-03 00:00:00") : null);
					list.setOD_STARTUSING("0".equals(rs.getString("noexpire")) ? false : true);

					list.setOD_LIAISONPER("");

					list.setOD_LIAISONTEL("");

					list.setOD_LIAISONFAX("");

					list.setOD_LIAISONEMAIL("");

					list.setOD_READNUMBER(Long.parseLong(rs.getString("hit")));

					list.setOD_SUBJECTID("16");

					list.setOD_SUBJECTCLASS("[170]警政");

					list.setOD_ADMINISTATIONID("32");

					list.setOD_ADMINISTATIONCLASS("[140]警政");

					list.setOD_SERVICEID("94");

					list.setOD_SERVICECLASS("[E10]人身及財物安全");

					list.setOD_DSID(dsid);

					list.setOD_LANGUAGETYPE("chinese");

					list.setOD_METAKEYWORD(rs.getString("title"));

					list.setOD_VIEWUID("systex");

					list.setOD_VIEWNAME("6LOH5paZ6L2J5YWl");

					list.setOD_VIEWDATE(parse(timestampToDate(rs.getLong("date_start"))));

					list.setOD_ISEXAMINE(false);

					list.setOD_EXAMINEDATE(null);

					list.setOD_EXAMINENAME("");

					list.setOD_CREATEUID("systex");

					list.setOD_CREATENAME("6LOH5paZ6L2J5YWl");

					list.setOD_CREATEDATE(parse(timestampToDate(rs.getLong("date_start"))));

					list.setOD_POSTUID("systex");

					list.setOD_POSTNAME("6LOH5paZ6L2J5YWl");

					list.setOD_POSTERDATE(parse(timestampToDate(rs.getLong("date_start"))));

					list.setLinks(findFile(conn, String.valueOf(rs.getInt("ID")), rs.getString("href"),
							rs.getString("title")));

					lists.add(list);
				}

				System.out.println("查詢成功！共:" + (lists != null && lists.size() > 0 ? lists.size() : 0) + "筆");
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				conn.close();
				return lists;
			} else {
				System.out.println("讀進mysql資料庫連線測試失敗！");
			}

		} catch (Exception e) {
			System.out.println("e.toString():" + e.toString());
			this.errorMsg = e.toString();
		}
		return null;
	}

	public ArrayList<Link> findFile(Connection conn, String id, String href, String title) {
		try {

			if (conn != null && !conn.isClosed()) {
				// System.out.println("資料庫連線測試成功！");
				// 建立statement
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				StringBuilder sql = new StringBuilder();
				sql.append("select * from " + databaseName_mysql + ".km_attachment where article_id=" + id);
				// System.out.println(sql.toString());
				ResultSet rs = stmt.executeQuery(sql.toString());

				ArrayList<Link> lists = new ArrayList<Link>();

				for (int i = 0; rs.next(); i++) {
					Link link = new Link();
					link.setName(rs.getString("real_name"));
					link.setUrl(rs.getString("file_name").replace("..", "https://www.pbs.gov.tw"));
					lists.add(link);
				}
				if (href != null && !href.isEmpty()) {
					Link link3 = new Link();
					link3.setName(title);
					link3.setUrl(href.replace("..", "https://www.pbs.gov.tw"));
					lists.add(link3);
				}

				System.out.println("查詢成功！共:" + (lists != null && lists.size() > 0 ? lists.size() : 0) + "筆");
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				// conn.close();
				return lists;
			} else {
				System.out.println("資料庫連線測試失敗！");
			}

		} catch (Exception e) {
			this.errorMsg = e.toString();
		}
		return null;
	}

	// 新增到Mssql資料表
	public boolean transform_out(ArrayList<Message> lists) {

		boolean check = false;
		// MSSql
		Connection conn = null;
		try {
			// 連結資料庫
			conn = DriverManager.getConnection(connectionUrl);
			Class.forName(driver);

		} catch (Exception e) {
			this.errorMsg = e.toString();
			return check;
		}

		PreparedStatement stmts = null;
		ResultSet rs = null;
		PreparedStatement stmts2 = null;
		ResultSet rs2 = null;
		PreparedStatement stmts3 = null;
		ResultSet rs3 = null;
		PreparedStatement stmts4 = null;
		ResultSet rs4 = null;
		PreparedStatement stmts5 = null;
		ResultSet rs5 = null;
		PreparedStatement stmts6 = null;
		ResultSet rs6 = null;
		PreparedStatement stmts7 = null;
		ResultSet rs7 = null;
		try {
			for (int i = 0; i < lists.size(); i++) {
				Message data = (Message) lists.get(i);

				String sSql = String.format("INSERT INTO %s (OD_SERNO, OD_PUBUNITDN, OD_SUBJECT, OD_DETAILCONTENT, "
						+ "OD_POSTERDATE, OD_CLOSEDATE, OD_STARTUSING, OD_LIAISONPER, OD_LIAISONTEL, OD_LIAISONFAX, "
						+ "OD_LIAISONEMAIL, OD_ISEXAMINE, OD_EXAMINEDATE, OD_EXAMINENAME, OD_READNUMBER, OD_SUBJECTID, "
						+ "OD_SUBJECTCLASS, OD_ADMINISTATIONID, OD_ADMINISTATIONCLASS, OD_SERVICEID, OD_SERVICECLASS, "
						+ "OD_CREATEUID, OD_CREATENAME, OD_CREATEDATE, OD_POSTUID, OD_POSTNAME, OD_UPDATEDATE, "
						+ "OD_DSID, OD_LANGUAGETYPE, OD_METAKEYWORD, OD_VIEWUID, OD_VIEWNAME, OD_VIEWDATE) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, "
						+ "?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)", datatablename);
				// System.out.println(sSql.toString());
				stmts = conn.prepareStatement(sSql);
				stmts.clearParameters();
				stmts.setString(1, data.getOD_SERNO());// serno
				stmts.setString(2, data.getOD_PUBUNITDN());// pubUnitDN
				stmts.setString(3, data.getOD_SUBJECT());// subject
				stmts.setString(4, data.getOD_DETAILCONTENT());// detailContent
				stmts.setString(5, decompose(data.getOD_POSTERDATE()));// posterDate
				stmts.setString(6, data.getOD_CLOSEDATE() != null ? decompose(data.getOD_CLOSEDATE()) : null);// closeDate\
				stmts.setString(7, data.getOD_STARTUSING() ? "1" : "0");// startUsing
				stmts.setString(8, data.getOD_LIAISONPER());// liaisonPer
				stmts.setString(9, data.getOD_LIAISONTEL());// liaisonTel
				stmts.setString(10, data.getOD_LIAISONFAX());// liaisonFax
				stmts.setString(11, data.getOD_LIAISONEMAIL());// liaisonEmail
				stmts.setString(12, "1");// isExamine
				stmts.setString(13, null);// examineDate
				stmts.setString(14, data.getOD_EXAMINENAME());// examineName
				stmts.setInt(15, data.getOD_READNUMBER().intValue());// readNumber
				stmts.setString(16, data.getOD_SUBJECTID());// subjectID
				stmts.setString(17, data.getOD_SUBJECTCLASS());// subjectClass
				stmts.setString(18, data.getOD_ADMINISTATIONID());// administationID
				stmts.setString(19, data.getOD_ADMINISTATIONCLASS());// administationClass
				stmts.setString(20, data.getOD_SERVICEID());// serviceID
				stmts.setString(21, data.getOD_SERVICECLASS());// serviceClass
				stmts.setString(22, data.getOD_CREATEUID());// createUid
				stmts.setString(23, data.getOD_CREATENAME());// createName
				stmts.setString(24, decompose(data.getOD_CREATEDATE()));// SYSDATETIME
				stmts.setString(25, data.getOD_POSTUID());// postUid
				stmts.setString(26, data.getOD_POSTNAME());// postName
				stmts.setString(27, decompose(data.getOD_CREATEDATE()));// SYSDATETIME
				stmts.setString(28, data.getOD_DSID());// DSID
				stmts.setString(29, data.getOD_LANGUAGETYPE());// languageType
				stmts.setString(30, data.getOD_METAKEYWORD());// metaKeyword
				stmts.setString(31, data.getOD_VIEWUID());// viewUID
				stmts.setString(32, data.getOD_VIEWNAME());// viewName
				stmts.setString(33, null);// viewDate
				stmts.executeUpdate();

				// 多網發布站台
				String sSql2 = String.format(
						"INSERT INTO %s (OD_SERNO, OD_DETAILNO, OD_MSERNO, OD_WEBSITEDN, OD_WEBSITENAME, OD_WEBSITEEXAM,OD_LANGUAGEURL, OD_ISTOP, OD_TOPDATE, OD_DATASORT,OD_CREATEUID, OD_CREATENAME, OD_CREATEDATE, OD_POSTUID, OD_POSTNAME, OD_UPDATEDATE) "
								+ "VALUES (?, ?, ?, ?, ?, ?, ?,?,?,?, " + "?, ?, ?, ?, ?, ?)",
						postertablename);
				// System.out.println(sSql2.toString());
				Snowflake s = new Snowflake();
				stmts2 = conn.prepareStatement(sSql2);
				stmts2.clearParameters();

				stmts2.setString(1, data.getOD_SERNO());// serno
				stmts2.setString(2, String.valueOf(s.nextId()));// detailno
				stmts2.setString(3, "86e69beb-c13c-4c6b-9952-d0c278b7219b");// mserno
				stmts2.setString(4, "ou=chpbs,ou=websitelist,ou=ap_root,o=npa,c=tw");// websitedn
				stmts2.setString(5, "警察廣播電臺中文網");// websitename
				stmts2.setString(6, "Y");// websiteexam
				stmts2.setString(7, "");// languageurl
				stmts2.setString(8, "0");// istop
				stmts2.setString(9, null);// topdate
				stmts2.setInt(10, 9999999);// dataSort
				stmts2.setString(11, data.getOD_CREATEUID());// createUid
				stmts2.setString(12, data.getOD_CREATENAME());// createName
				stmts2.setString(13, decompose(data.getOD_CREATEDATE()));// SYSDATETIME
				stmts2.setString(14, data.getOD_POSTUID());// postUid
				stmts2.setString(15, data.getOD_POSTNAME());// postName
				stmts2.setString(16, decompose(data.getOD_CREATEDATE()));// SYSDATETIME
				stmts2.executeUpdate();

				// 相關連結
				ArrayList<Link> urlList = data.getLinks();
				if (urlList != null && urlList.size() > 0) {
					for (int k = 0; k < urlList.size(); k++) {
						Link item = (Link) urlList.get(k);
						if (item.getUrl().indexOf("mp3") != -1) {
							String sSql4 = String.format(
									"INSERT INTO %s (OD_SERNO, OD_DETAILNO, OD_RELATEURL, OD_RELATENAME, "
											+ "OD_TARGET, OD_READNUMBER, OD_DATASORT, OD_CREATEUID, "
											+ "OD_CREATENAME, OD_CREATEDATE, OD_POSTUID, OD_POSTNAME, OD_UPDATEDATE, "
											+ "OD_DSID) " + "VALUES (?, ?, ?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?, ?, ?)",
									resourcetablename);
							stmts4 = conn.prepareStatement(sSql4);
							stmts4.clearParameters();
							Snowflake s1 = new Snowflake();
							stmts4.setString(1, data.getOD_SERNO());// serno
							stmts4.setString(2, String.valueOf(s1.nextId()));// detailNo
							stmts4.setString(3, item.getUrl());// relateURL
							stmts4.setString(4, item.getName());// relateName
							stmts4.setString(5, "1");// target
							stmts4.setInt(6, 0);// readNumber
							stmts4.setInt(7, 9999999);// dataSort
							stmts4.setString(8, data.getOD_CREATEUID());// createUid
							stmts4.setString(9, data.getOD_CREATENAME());// createName
							stmts4.setString(10, decompose(data.getOD_CREATEDATE()));// SYSDATETIME
							stmts4.setString(11, data.getOD_POSTUID());// postUid
							stmts4.setString(12, data.getOD_POSTNAME());// postName
							stmts4.setString(13, decompose(data.getOD_CREATEDATE()));// SYSDATETIME
							stmts4.setString(14, data.getOD_DSID());// dsid
							stmts4.executeUpdate();
						}

					}
				}
				// 相關附件
				DownloadImage downloadImage = new DownloadImage();
				ArrayList<Link> urlList2 = data.getLinks();
				if (urlList2 != null && urlList2.size() > 0) {
					for (int k = 0; k < urlList2.size(); k++) {
						Link item = (Link) urlList2.get(k);

						if (item.getUrl() != null && !item.getUrl().isEmpty() && item.getUrl().indexOf("mp3") == -1) {

							String sSql5 = String.format(
									"INSERT INTO %s (OD_SERNO, OD_DETAILNO, OD_FLAG, OD_CLIENTFILE, "
											+ "OD_SERVERFILE, OD_EXPFILE, OD_IMAGEMAGICK, OD_SERVERFILEPDF, OD_PDFFLAG, OD_SERVERFILEODF, "
											+ "OD_ODFFLAG, OD_VIDEOURL, OD_VIDEOSOURCE, OD_FILESIZE, OD_READNUMBER, OD_DATASORT, OD_CREATEUID, "
											+ "OD_CREATENAME, OD_CREATEDATE, OD_POSTUID, OD_POSTNAME, OD_UPDATEDATE, "
											+ "OD_DSID) "
											+ "VALUES (?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?,?, ?)",
									filetablename);

							stmts5 = conn.prepareStatement(sSql5);
							stmts5.clearParameters();
							Snowflake s1 = new Snowflake();
							stmts5.setString(1, data.getOD_SERNO());// serno
							stmts5.setString(2, String.valueOf(s1.nextId()));// detailNo

							String urlLink = item.getUrl();
							String img_client = urlLink.replace(urlReplace, "");

							String ext = FilenameUtils.getExtension(img_client);
							String flag = "pic";
							if (img_client.indexOf("doc") != -1 || img_client.indexOf("docx") != -1
									|| img_client.indexOf("pdf") != -1 || img_client.indexOf("xls") != -1) {
								flag = "doc";
							}
							String img_server = s1.nextId() + "." + ext;

							stmts5.setString(3, flag);// OD_FLAG

							downloadImage.download(urlLink, img_server, filePath);

							stmts5.setString(4, img_client);// OD_CLIENTFILE
							stmts5.setString(5, img_server);// OD_SERVERFILE
							stmts5.setString(6, item.getName());// OD_EXPFILE
							stmts5.setString(7, "");// OD_IMAGEMAGICK
							stmts5.setString(8, "");// OD_SERVERFILEPDF
							stmts5.setString(9, "0");// OD_PDFFLAG
							stmts5.setString(10, "");// OD_SERVERFILEODF
							stmts5.setString(11, "0");// OD_ODFFLAG
							stmts5.setString(12, "");// OD_VIDEOURL
							stmts5.setString(13, "");// OD_VIDEOSOURCE
							stmts5.setString(14, "163840");// OD_FILESIZE
							stmts5.setInt(15, 56);// readNumber
							stmts5.setInt(16, 9999999);// dataSort
							stmts5.setString(17, data.getOD_CREATEUID());// createUid
							stmts5.setString(18, data.getOD_CREATENAME());// createName
							stmts5.setString(19, decompose(data.getOD_CREATEDATE()));// SYSDATETIME
							stmts5.setString(20, data.getOD_POSTUID());// postUid
							stmts5.setString(21, data.getOD_POSTNAME());// postName
							stmts5.setString(22, decompose(data.getOD_CREATEDATE()));// SYSDATETIME
							stmts5.setString(23, data.getOD_DSID());// dsid
							stmts5.executeUpdate();

						}

					}
				}

			}
			check = true;
			return check;
		} catch (Exception e) {
			System.out.println("error:" + e.toString());
			errorMsg = "find from table error: " + e.toString();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmts != null)
					stmts.close();
				if (rs2 != null)
					rs2.close();
				if (stmts2 != null)
					stmts2.close();
				if (rs3 != null)
					rs3.close();
				if (stmts3 != null)
					stmts3.close();
				if (rs4 != null)
					rs4.close();
				if (stmts4 != null)
					stmts4.close();
				if (rs5 != null)
					rs5.close();
				if (stmts5 != null)
					stmts5.close();
				if (rs6 != null)
					rs6.close();
				if (stmts6 != null)
					stmts6.close();
				if (rs7 != null)
					rs7.close();
				if (stmts7 != null)
					stmts7.close();
				conn.close();
			} catch (SQLException se) {
				System.out.println("error:" + se.toString());
				errorMsg = "close ResultSet or Statment or connection error: " + se.toString();
			}

		}
		return check;
	}

	// 取系統日期，傳入欲呈現的格式，如yyyyMMdd
	public static String nowDate(String formatstyle) {
		Format formatter = new SimpleDateFormat(formatstyle);
		Date todayDate = new Date();
		return formatter.format(todayDate);
	}

	public static LocalDateTime parse(String timestamp) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.parse(timestamp, formatter);
	}

	public static String decompose(LocalDateTime localDateTime) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return localDateTime.format(dateFormatter);
	}

	public static String timestampToDate(long time) {
		// 10位的秒級別的時間戳
		String result1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time * 1000));
		return result1;
	}

}
