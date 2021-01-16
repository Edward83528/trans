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
import transfer.component.Snowflake;
import transfer.entity.ProgramList;
import transfer.entity.Link;
import transfer.entity.ProgramList;

// 7.全國暨地區網(含網路廣播)(只有圖檔)
public class TransferProgramList extends Thread {
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
	private String urlReplace = "https://www.pbs.gov.tw/upload/cht/show/background/";
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
	private String dsid = "programlist";
	// 轉檔後檔案輸出路徑
	private String filePath = "D:\\transferFile\\" + dsid;
	// 語言
	private String LanguageType = null;
	// 類別序號
	private ArrayList<String> classSerno = new ArrayList<String>();
	// 類別名稱
	private ArrayList<String> className = new ArrayList<String>();

	public static void main(String[] args) {

		TransferProgramList transfer = new TransferProgramList();
		// 設置資料表參數
		transfer.setDatatablename("[" + databaseName_ms + "].[dbo].[ODDT_PROGRAMLIST]");
		transfer.setPostertablename("[" + databaseName_ms + "].[dbo].[ODDT_PROGRAMLISTPOSTER]");
		transfer.setResourcetablename("[" + databaseName_ms + "].[dbo].[ODDT_DATARESOURCES]");
		transfer.setFiletablename("[" + databaseName_ms + "].[dbo].[ODDT_DATAFILES]");
		// 讀進
		ArrayList<ProgramList> in = transfer.transform_in();

		if (in != null && in.size() > 0) {
			System.out.println("讀進資料共:" + in.size() + "筆");
		}
		// 讀出並寫入
		System.out.println("轉檔是否成功:" + transfer.transform_out(in));

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
	public ArrayList<ProgramList> transform_in() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			if (conn != null && !conn.isClosed()) {
				System.out.println("讀進mysql資料庫連線測試成功！");
				// 建立statement
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				StringBuilder sql = new StringBuilder();
				sql.append("select * from " + databaseName_mysql + ".km_show ");
				// System.out.println(sql.toString());
				ResultSet rs = stmt.executeQuery(sql.toString());

				ArrayList<ProgramList> lists = new ArrayList<ProgramList>();

				for (int i = 0; rs.next(); i++) {
					ProgramList list = new ProgramList();

					String serno = "7A2M" + String.valueOf(rs.getInt("ID"));
					list.setOD_SERNO(serno.replaceAll(" ", "").trim());

					list.setOD_PUBUNITDN("ou=A2M00,ou=organization,o=npa,c=tw");
					list.setOD_SUBJECT(rs.getString("show_title")); // 節目名稱

					String local_id = rs.getString("local_id");
					String stationSerno = "5566";
					String programLink = "#";
					if ("9".equals(local_id)) {
						// 台南
						stationSerno = "7493b402-f78d-4303-947e-18fd43b9a30c";
						programLink = "http://www.pbs.gov.tw/live/tns.html";
					} else if ("1".equals(local_id)) {
						// 全國
						stationSerno = "7ab3f359-f469-4b3e-9ddd-c723e6d9e5f5";
						programLink = "http://www.pbs.gov.tw/live/pbs.html";
					} else if ("2".equals(local_id)) {
						// 台北
						stationSerno = "c042c8e5-32d2-4847-aded-18fc1fec09ab";
						programLink = "http://www.pbs.gov.tw//live/tps.html";
					} else if ("3".equals(local_id)) {
						// 台中
						stationSerno = "44dc0bc9-3f0e-4ef4-b6f3-cd47854802d4";
						programLink = "http://www.pbs.gov.tw/live/tcs.html";
					} else if ("4".equals(local_id)) {
						// 高雄
						stationSerno = "f3a14a9c-a2f1-4944-bba4-d33d1382ec6f";
						programLink = "http://www.pbs.gov.tw/live/kss.html";
					} else if ("5".equals(local_id)) {
						// 宜蘭
						stationSerno = "0d13e6bb-1105-4980-82da-be3564f47a32";
						programLink = "http://www.pbs.gov.tw/live/els.html";
					} else if ("6".equals(local_id)) {
						// 花蓮
						stationSerno = "26927d4b-381c-4d02-8e69-709781f02568";
						programLink = "http://www.pbs.gov.tw/live/hls.html";
					} else if ("7".equals(local_id)) {
						// 台東
						stationSerno = "9884fba1-7d2a-4af7-9f76-b60a859c7fae";
						programLink = "http://www.pbs.gov.tw/live/tts.html";
					} else if ("8".equals(local_id)) {
						// 新竹
						stationSerno = "fd1d014e-3af6-4bc2-b4ef-83fb5ecb1360";
						programLink = "http://www.pbs.gov.tw/live/scs.html";
					}
					list.setOD_STATIONSERNO(stationSerno); // 分臺代碼

					list.setOD_PROGRAMSERNO(""); // 聯播節目設定

					list.setOD_ADMINUID("administrator"); // 節目管理人代碼

					list.setAdminName("5bug5ZWG566h55CG6ICF"); // 節目管理人名稱

					list.setOD_PROGRAMlINK(programLink); // 節目內容轉址

					list.setOD_WEEK(rs.getString("week")); // 時間

					list.setOD_STARTTIME(rs.getString("begin_time")); // 節目開始時段

					list.setOD_ENDTIME(rs.getString("end_time")); // 節目結束時段

					list.setOD_HOSTNAME(rs.getString("host_name")); // 主持人名稱

					DownloadImage downloadImage = new DownloadImage();
					if (rs.getString("host_img") != null && !rs.getString("host_img").isEmpty()) {

						String host_img_client = rs.getString("host_img").replace("../upload/cht/show/host/", "");
						list.setOD_HOSTIMGCLIENTFILE(host_img_client); // 主持人封面圖原始檔名
						Snowflake s1 = new Snowflake();
						String ext = FilenameUtils.getExtension(host_img_client);
						String host_img_server = s1.nextId() + "." + ext;
						// System.out.println("==============================");
						// System.out.println("原始主播圖:" + host_img_client);
						// System.out.println("新的主播圖:" + host_img_server);
						// System.out.println("==============================");
						downloadImage.download(rs.getString("host_img").replace("..", "https://www.pbs.gov.tw"),
								host_img_server, filePath);
						list.setOD_HOSTIMGSERVERFILE(host_img_server); // 主持人封面圖主機檔名
					} else {
						list.setOD_HOSTIMGCLIENTFILE(""); // 主持人封面圖原始檔名
						list.setOD_HOSTIMGSERVERFILE(""); // 主持人封面圖主機檔名
					}

					list.setOD_HOSTFB(rs.getString("host_fb")); // 主持人FB

					list.setOD_HOSTFANGROUP(rs.getString("host_fb_fans")); // 主持人粉絲團

					String detailContent = rs.getString("host_content");
					if (detailContent != null && !detailContent.isEmpty()) {
						list.setOD_DETAILCONTENT("<div class=\"ed_model01 clearfix\"><div class=\"ed_txt\">"
								+ detailContent + "</div></div>"); // 主持人介紹(心情小語)
					} else {
						list.setOD_DETAILCONTENT("");
					}

					list.setOD_POSTERDATE(parse(rs.getString("setup_time").substring(0, 10) + " 00:00:00"));

					list.setOD_CLOSEDATE("0".equals(rs.getString("enable")) ? parse("2020-09-03 00:00:00") : null);
					list.setOD_STARTUSING("0".equals(rs.getString("enable")) ? false : true);

					list.setOD_LIAISONPER("");

					list.setOD_LIAISONTEL("");

					list.setOD_LIAISONFAX("");

					list.setOD_LIAISONEMAIL("");

					list.setOD_READNUMBER(rs.getLong("count"));

					list.setOD_SUBJECTID("16");

					list.setOD_SUBJECTCLASS("[170]警政");

					list.setOD_ADMINISTATIONID("32");

					list.setOD_ADMINISTATIONCLASS("[140]警政");

					list.setOD_SERVICEID("94");

					list.setOD_SERVICECLASS("[E10]人身及財物安全");

					list.setOD_DSID(dsid);

					list.setOD_LANGUAGETYPE("chinese");

					list.setOD_METAKEYWORD(rs.getString("show_title"));

					list.setOD_VIEWUID("systex");

					list.setOD_VIEWNAME("6LOH5paZ6L2J5YWl");

					list.setOD_VIEWDATE(parse(rs.getString("setup_time").substring(0, 10) + " 00:00:00"));

					list.setOD_ISEXAMINE(false);

					list.setOD_EXAMINEDATE(null);

					list.setOD_EXAMINENAME("");

					list.setOD_CREATEUID("systex");

					list.setOD_CREATENAME("6LOH5paZ6L2J5YWl");

					list.setOD_CREATEDATE(parse(rs.getString("setup_time").substring(0, 10) + " 00:00:00"));

					list.setOD_POSTUID("systex");

					list.setOD_POSTNAME("6LOH5paZ6L2J5YWl");

					list.setOD_POSTERDATE(parse(rs.getString("update_time").substring(0, 10) + " 00:00:00"));

					list.setLinks(findFile(rs.getString("host_name"), rs.getString("host_background")));

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

	public ArrayList<Link> findFile(String host_name, String host_background) {
		try {

			ArrayList<Link> lists = new ArrayList<Link>();

			if (host_background != null && !host_background.isEmpty()) {
				Link link = new Link();
				link.setName(host_name + "背景圖");
				link.setUrl(host_background.replace("..", "https://www.pbs.gov.tw"));
				lists.add(link);
			}
			return lists;
		} catch (Exception e) {
			this.errorMsg = e.toString();
		}
		return null;
	}

	// 新增到Mssql資料表
	public boolean transform_out(ArrayList<ProgramList> lists) {

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
				ProgramList data = (ProgramList) lists.get(i);

				String sSql = String.format("INSERT INTO %s (OD_SERNO, OD_PUBUNITDN, OD_SUBJECT, "
						+ "OD_STATIONSERNO, OD_PROGRAMSERNO, OD_ADMINUID, OD_ADMINNAME, OD_PROGRAMlINK, "
						+ "OD_WEEK, OD_STARTTIME, OD_ENDTIME, OD_HOSTNAME, OD_HOSTIMGCLIENTFILE, OD_HOSTIMGSERVERFILE, "
						+ "OD_HOSTFB, OD_HOSTFANGROUP, OD_DETAILCONTENT, "
						+ "OD_POSTERDATE, OD_CLOSEDATE, OD_STARTUSING, OD_LIAISONPER, OD_LIAISONTEL, OD_LIAISONFAX, "
						+ "OD_LIAISONEMAIL, OD_ISEXAMINE, OD_EXAMINEDATE, OD_EXAMINENAME, OD_READNUMBER, OD_SUBJECTID, "
						+ "OD_SUBJECTCLASS, OD_ADMINISTATIONID, OD_ADMINISTATIONCLASS, OD_SERVICEID, OD_SERVICECLASS, "
						+ "OD_CREATEUID, OD_CREATENAME, OD_CREATEDATE, OD_POSTUID, OD_POSTNAME, OD_UPDATEDATE, "
						+ "OD_DSID, OD_LANGUAGETYPE, OD_METAKEYWORD, OD_VIEWUID, OD_VIEWNAME, OD_VIEWDATE) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, "
						+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?, ?, ?)",
						datatablename);
				// System.out.println(sSql.toString());46
				stmts = conn.prepareStatement(sSql);
				stmts.clearParameters();

				stmts.setString(1, data.getOD_SERNO());// serno
				stmts.setString(2, data.getOD_PUBUNITDN());// pubUnitDN
				stmts.setString(3, data.getOD_SUBJECT());// subject
				stmts.setString(4, data.getOD_STATIONSERNO());
				stmts.setString(5, data.getOD_PROGRAMSERNO()); // OD_PROGRAMSERNO
				stmts.setString(6, data.getOD_ADMINUID()); // OD_ADMINUID
				stmts.setString(7, data.getAdminName()); // OD_ADMINNAME
				stmts.setString(8, data.getOD_PROGRAMlINK()); // OD_PROGRAMlINK
				stmts.setString(9, data.getOD_WEEK()); // OD_WEEK
				stmts.setString(10, data.getOD_STARTTIME()); // OD_STARTTIME
				stmts.setString(11, data.getOD_ENDTIME()); // OD_ENDTIME
				stmts.setString(12, data.getOD_HOSTNAME()); // OD_HOSTNAME
				stmts.setString(13, data.getOD_HOSTIMGCLIENTFILE()); // OD_HOSTIMGCLIENTFILE
				stmts.setString(14, data.getOD_HOSTIMGSERVERFILE()); // OD_HOSTIMGSERVERFILE
				stmts.setString(15, data.getOD_HOSTFB()); // OD_HOSTFB
				stmts.setString(16, data.getOD_HOSTFANGROUP()); // OD_HOSTFANGROUP
				stmts.setString(17, data.getOD_DETAILCONTENT());// detailContent
				stmts.setString(18, decompose(data.getOD_POSTERDATE()));// posterDate
				stmts.setString(19, data.getOD_CLOSEDATE() != null ? decompose(data.getOD_CLOSEDATE()) : null);// closeDate\
				stmts.setString(20, data.getOD_STARTUSING() ? "1" : "0");// startUsing
				stmts.setString(21, data.getOD_LIAISONPER());// liaisonPer
				stmts.setString(22, data.getOD_LIAISONTEL());// liaisonTel
				stmts.setString(23, data.getOD_LIAISONFAX());// liaisonFax
				stmts.setString(24, data.getOD_LIAISONEMAIL());// liaisonEmail
				stmts.setString(25, "1");// isExamine
				stmts.setString(26, null);// examineDate
				stmts.setString(27, data.getOD_EXAMINENAME());// examineName
				stmts.setInt(28, data.getOD_READNUMBER() != null ? data.getOD_READNUMBER().intValue() : 99);// readNumber
				stmts.setString(29, data.getOD_SUBJECTID());// subjectID
				stmts.setString(30, data.getOD_SUBJECTCLASS());// subjectClass
				stmts.setString(31, data.getOD_ADMINISTATIONID());// administationID
				stmts.setString(32, data.getOD_ADMINISTATIONCLASS());// administationClass
				stmts.setString(33, data.getOD_SERVICEID());// serviceID
				stmts.setString(34, data.getOD_SERVICECLASS());// serviceClass
				stmts.setString(35, data.getOD_CREATEUID());// createUid
				stmts.setString(36, data.getOD_CREATENAME());// createName
				stmts.setString(37, decompose(data.getOD_CREATEDATE()));// SYSDATETIME
				stmts.setString(38, data.getOD_POSTUID());// postUid
				stmts.setString(39, data.getOD_POSTNAME());// postName
				stmts.setString(40, decompose(data.getOD_CREATEDATE()));// SYSDATETIME
				stmts.setString(41, data.getOD_DSID());// DSID
				stmts.setString(42, data.getOD_LANGUAGETYPE());// languageType
				stmts.setString(43, data.getOD_METAKEYWORD());// metaKeyword
				stmts.setString(44, data.getOD_VIEWUID());// viewUID
				stmts.setString(45, data.getOD_VIEWNAME());// viewName
				stmts.setString(46, null);// viewDate
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
				stmts2.setString(3, data.getOD_STATIONSERNO());// mserno
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
				/*
				 * ArrayList<Link> urlList = data.getLinks(); if (urlList != null &&
				 * urlList.size() > 0) { for (int k = 0; k < urlList.size(); k++) { Link item =
				 * (Link) urlList.get(k); String sSql4 = String.format(
				 * "INSERT INTO %s (OD_SERNO, OD_DETAILNO, OD_RELATEURL, OD_RELATENAME, " +
				 * "OD_TARGET, OD_READNUMBER, OD_DATASORT, OD_CREATEUID, " +
				 * "OD_CREATENAME, OD_CREATEDATE, OD_POSTUID, OD_POSTNAME, OD_UPDATEDATE, " +
				 * "OD_DSID) " + "VALUES (?, ?, ?, ?, ?, ?, ?, " + "?, ?, ?, ?, ?, ?, ?)",
				 * resourcetablename); stmts4 = conn.prepareStatement(sSql4);
				 * stmts4.clearParameters(); Snowflake s1 = new Snowflake(); stmts4.setString(1,
				 * data.getOD_SERNO());// serno stmts4.setString(2,
				 * String.valueOf(s1.nextId()));// detailNo stmts4.setString(3,
				 * item.getUrl());// relateURL stmts4.setString(4, item.getName());// relateName
				 * stmts4.setString(5, "1");// target stmts4.setInt(6, 0);// readNumber
				 * stmts4.setInt(7, 9999999);// dataSort stmts4.setString(8,
				 * data.getOD_CREATEUID());// createUid stmts4.setString(9,
				 * data.getOD_CREATENAME());// createName stmts4.setString(10,
				 * decompose(data.getOD_CREATEDATE()));// SYSDATETIME stmts4.setString(11,
				 * data.getOD_POSTUID());// postUid stmts4.setString(12,
				 * data.getOD_POSTNAME());// postName stmts4.setString(13,
				 * decompose(data.getOD_CREATEDATE()));// SYSDATETIME stmts4.setString(14,
				 * data.getOD_DSID());// dsid stmts4.executeUpdate(); } }
				 */
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
							stmts5.setString(3, "pic");// OD_FLAG

							String urlLink = item.getUrl();
							String img_client = urlLink.replace(urlReplace, "");

							String ext = FilenameUtils.getExtension(img_client);
							String img_server = s1.nextId() + "." + ext;

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
