package transfer.tcpolice;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import transfer.component.CallTransferConvert;
import transfer.component.FileDeal;
import transfer.component.SysUtil;
import transfer.entity.Tcpolice;

public class TransferDownloadData extends Thread {
	// MySQL資料庫連線資訊
	String url = "jdbc:mysql://localhost:3306/jip";
	String user = "root";
	String password = "zxcvbnm0351";
	// MsSQL資料庫連線資訊
	String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "user=harry;password=sa;"
			+ "databaseName=TXGPoliceWeb;useUnicode=true;characterEncoding=utf-8;";
	// 錯誤訊息
	String errorMsg = null;
	// Ms資料表名稱
	private String datatablename = null;
	private String filetablename = null;
	private String resourcetablename = null;
	private String postertablename = null;
	// 樹節點
	private String NodeId = "";
	// dsid
	private String dsid = "";
	// 語言
	private String LanguageType = null;
	// 類別序號
	private ArrayList<String> classSerno = new ArrayList<String>();
	// 類別名稱
	private ArrayList<String> className = new ArrayList<String>();

	// 適用於表單下載(Download)
	public static void main(String[] args) {
		/*
		 * boolean check = false; TransferDownloadData data = new
		 * TransferDownloadData(); // 設置轉資料所需參數 // 資料表
		 * data.setDatatablename("Download"); data.setPostertablename("DownloadPoster");
		 * data.setFiletablename("DownloadFile");
		 * data.setResourcetablename("DownloadResource");
		 * 
		 * data.setNodeId("1757");// 節點 data.setDsid("downlod");// 系統名稱
		 * data.setLanguageType("chinese");// 語系 // 類別序號 ArrayList<String> classSerno =
		 * new ArrayList<String>(); // 類別名稱 ArrayList<String> className = new
		 * ArrayList<String>(); // 分類1 className.add("09");// 行政類(09)
		 * classSerno.add("201710280009"); // 分類2 className.add("02");// 保安類(02)
		 * classSerno.add("201710280010"); // 分類3 className.add("03");// 交通類(03)
		 * classSerno.add("201710280011"); // 分類4 className.add("04");// 外事類(04)
		 * classSerno.add("201710280011"); // 分類5 className.add("05");// 刑事類(05)
		 * classSerno.add("201710280011"); // 分類6 className.add("06");// 婦幼類(06)
		 * classSerno.add("201710280011"); // 分類7 className.add("07");// 保安民防類(07)
		 * classSerno.add("201710280011"); // 其他 classSerno.add("201710280012");
		 * 
		 * data.setClassName(className); data.setClassSerno(classSerno); // 設置轉資料所需參數
		 * END ArrayList<TransferItem> lists = data.transform_in(); if (lists != null &&
		 * lists.size() > 0) { check = data.transform_out(lists); } if (check == true) {
		 * System.out.println("新增成功!"); } else { System.out.println("新增失敗!"); }
		 */
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
	public ArrayList<Tcpolice> transform_in() {
		String driver = "com.mysql.jdbc.Driver";
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			if (conn != null && !conn.isClosed()) {
				// System.out.println("資料庫連線測試成功！");
				// 建立statement
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				StringBuilder sql = new StringBuilder();
				sql.append("select * from jip.cudtgeneric");
				sql.append(" where  ictunit in(SELECT ctUnitId FROM jip.cattreenode ");
				sql.append(" where ctNodeId in(" + this.NodeId + ") )   ");
				// System.out.println(sql.toString());and idept='00306'
				ResultSet rs = stmt.executeQuery(sql.toString());

				ArrayList<Tcpolice> lists = new ArrayList<Tcpolice>();

				for (int i = 0; rs.next(); i++) {
					Tcpolice list = new Tcpolice();
					// 找尋主題分類
					// ArrayList<TransferItem> Cakes = foundCake(rs.getString("icuitem"), conn);
					// if (Cakes != null && Cakes.size() > 0) {
					// TransferItem cake = (TransferItem) Cakes.get(0);
					// list.setSubjectID(cake.getSubjectID());
					// list.setSubjectClass("");
					// list.setAdministationID(cake.getAdministationID());
					// list.setAdministationClass("");
					// list.setServiceID(cake.getServiceID());
					// list.setServiceClass("");
					// } else {
					// list.setSubjectID("");
					// list.setSubjectClass("");
					// list.setAdministationID("");
					// list.setAdministationClass("");
					// list.setServiceID("");
					// list.setServiceClass("");
					//
					// }
					list.setSubjectID("1,16");
					list.setSubjectClass("[100]內政及國土,[170]警政");
					list.setAdministationID("32");
					list.setAdministationClass("[140]警政");
					list.setServiceID("18,117");
					list.setServiceClass("[I00]公共資訊,[IZ0]其他");
					// 找尋主題分類END

					list.setIcuitem(rs.getString("icuitem"));
					list.setWebSiteDN(foundWebDNs(rs.getString("ictunit"), conn).get(0).getWebSiteDN());// 多網DN
					list.setWebSiteName(foundWebDNs(rs.getString("ictunit"), conn).get(0).getWebSiteName());// 多網名稱
					list.setTopCat(rs.getString("topCat"));// 分類
					list.setSerno(rs.getString("icuitem"));
					list.setPubUnitDN(mapping_dn(rs.getString("idept")));// 發布單位
					list.setSubject(rs.getString("stitle"));
					list.setSecSubject("");
					list.setDetailContent(rs.getString("xbody"));
					list.setPosterDate(rs.getString("xpostDate"));// xpostDate:張貼日
					// list.setCloseDate(rs.getString("xpostDateEnd"));// xpostDateEnd:資料迄日
					list.setCloseDate(rs.getString("avEnd"));// 公開迄日
					list.setStartUsing(rs.getString("fctupublic").equals("Y") ? "1" : "0");// 啟用
					// list.setLiaisonPer("");// 聯絡人
					// list.setLiaisonTel("");
					// list.setLiaisonFax("");
					// list.setLiaisonEmail("");
					list.setIsExamine("Y");// 審核狀態
					// list.setExamineDate("");// 審核日期
					// list.setExamineName("");// 審核人員姓名
					list.setReadNumber(foundReadNumber(rs.getString("icuitem"), conn));
					list.setAudience("general,womenchild,police");
					list.setCreateUid("administrator");
					list.setCreateName("5pyA6auY566h55CG6ICF");
					// list.setCreateUid("");
					// list.setCreateName(rs.getString("icreator"));
					list.setCreateDate(rs.getString("createdDate"));// createdDate:製作日期
					list.setPostUid("administrator");
					list.setPostName("5pyA6auY566h55CG6ICF");
					list.setUpdateDate(rs.getString("deditDate"));// deditDate:編修日期
					list.setDSID(this.dsid);// 系統名稱
					list.setLanguageType(this.LanguageType);// 語系
					list.setMetaKeyword(rs.getString("xkeyword"));// 關鍵字
					// list.setSubjectTTSID("");
					// list.setSubjectTTSFile("");
					// list.setDetailContentTTSID("");
					// list.setDetailContentTTSFile("");
					// list.setAPINumber("");
					list.setTableName(this.getDatatablename());
					list.setXimgFile(rs.getString("ximgFile"));// 單一圖檔
					list.setFileDownLoad(rs.getString("fileDownLoad"));// 單一檔案
					list.setRefId(rs.getString("refId"));// 引用資料Id
					list.setXurl(rs.getString("xurl"));// 外連
					list.setXnewWindow(rs.getString("xnewWindow"));// 是否打開新視窗
					lists.add(list);
				}
				System.out.println("查詢成功！");
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				conn.close();
				return lists;
			}

		} catch (Exception e) {
			this.errorMsg = e.toString();
		}
		return null;
	}

	// 查詢發布單位
	public String mapping_dn(String UnitDNOld) {
		String UnitDn = null;
		// 設定檔路徑
		String path = "D:\\webapps\\police\\trans.police.gov.tw\\src\\TransferData\\department.properties";
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(path));
			UnitDn = properties.getProperty(UnitDNOld);
			// System.out.println("發布單位" + UnitDn);
			return UnitDn;
		} catch (Exception e) {
			this.errorMsg = e.toString();
		}
		return UnitDn;
	}

	// 查詢多網
	public ArrayList<Tcpolice> foundWebDNs(String ictunit, Connection conn) {
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			if (conn != null && !conn.isClosed()) {
				// 建立statement
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				StringBuilder sql = new StringBuilder();
				sql.append("select *  from  jip.cattreenode  where  ctUnitId='" + ictunit + "' and ctNodeId='"
						+ this.NodeId + "'  ");
				// System.out.println(sql.toString());
				ResultSet rs = stmt.executeQuery(sql.toString());
				rs.next();
				ArrayList<Tcpolice> lists = new ArrayList<Tcpolice>();
				if (rs.getString("ctRootId").equals("9")) {
					System.out.println("目前是匯臺中市政府警察局");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chtcpolice,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("臺中市政府警察局");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("14")) {
					System.out.println("目前是匯兒童版");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=kids,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("兒童版");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("15")) {
					System.out.println("目前是匯行動版");
					// 目前WebSiteUnit沒行動版DN值,所以先設為臺中市政府警察局DN值
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chtcpolice,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("行動版");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("16")) {
					System.out.println("目前是匯運動網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chtf,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("運動網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("17")) {
					System.out.println("目前是匯少年輔導委員會網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chtg,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("少年輔導委員會網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("18")) {
					System.out.println("目前是匯第一分局網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chpa,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("第一分局網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("19")) {
					System.out.println("目前是匯第二分局網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chpb,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("第二分局網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("20")) {
					System.out.println("目前是匯第三分局網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chpc,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("第三分局網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("21")) {
					System.out.println("目前是匯第四分局網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chpd,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("第四分局網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("22")) {
					System.out.println("目前是匯第五分局網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chpe,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("第五分局網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("23")) {
					System.out.println("目前是匯第六分局網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chpf,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("第六分局網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("24")) {
					System.out.println("目前是匯豐原分局網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chpg,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("豐原分局網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("25")) {
					System.out.println("目前是匯烏日分局網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chpi,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("烏日分局網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("26")) {
					System.out.println("目前是匯大甲分局網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chpk,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("大甲分局網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("27")) {
					System.out.println("目前是匯清水分局網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chpj,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("清水分局網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("28")) {
					System.out.println("目前是匯和平分局網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chpn,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("和平分局網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("29")) {
					System.out.println("目前是匯霧峰分局網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chph,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("霧峰分局網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("30")) {
					System.out.println("目前是匯東勢分局網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chpm,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("東勢分局網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("31")) {
					System.out.println("目前是匯太平分局網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chpl,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("太平分局網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("32")) {
					System.out.println("目前是匯刑事警察大隊網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chtb,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("刑事警察大隊網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("34")) {
					System.out.println("目前是匯婦幼警察隊網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chte,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("婦幼警察隊網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("35")) {
					System.out.println("目前是匯保安警察大隊網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chta,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("保安警察大隊網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("36")) {
					System.out.println("目前是匯少年警察隊網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chtd,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("少年警察隊網站");
					lists.add(list);
				} else if (rs.getString("ctRootId").equals("37")) {
					System.out.println("目前是匯交通警察大隊網站");
					Tcpolice list = new Tcpolice();
					list.setWebSiteDN("ou=chtc,ou=ap_root,o=tcpolice,c=tw");
					list.setWebSiteName("交通警察大隊網站");
					lists.add(list);
				}
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				return lists;
			}

		} catch (Exception e) {
			this.errorMsg = e.toString();
		}
		return null;
	}

	// 查詢相關連結
	public ArrayList<Tcpolice> foundUrl(String xiCuItem, Connection conn) {
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			if (conn != null && !conn.isClosed()) {
				// 建立statement
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				StringBuilder sql = new StringBuilder();
				sql.append("select *  from cudtpage  where  xiCuItem ='" + xiCuItem + "' ");
				ResultSet rs = stmt.executeQuery(sql.toString());
				ArrayList<Tcpolice> lists = new ArrayList<Tcpolice>();
				for (int i = 0; rs.next(); i++) {
					Tcpolice list = new Tcpolice();
					list.setRelateUrl(rs.getString("url"));
					list.setRelateName(rs.getString("atitle"));
					list.setTarget("Y");
					lists.add(list);
				}
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				return lists;
			}

		} catch (Exception e) {
			this.errorMsg = e.toString();
		}
		return null;
	}

	// 查詢相關附件
	public ArrayList<Tcpolice> foundFile(String xiCuItem, Connection conn) {
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			if (conn != null && !conn.isClosed()) {
				// 建立statement
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM cudtattach a,imagefile b where a.nFileName=b.newFileName and xiCuItem='"
						+ xiCuItem + "' ");
				ResultSet rs = stmt.executeQuery(sql.toString());

				ArrayList<Tcpolice> lists = new ArrayList<Tcpolice>();
				for (int i = 0; rs.next(); i++) {
					Tcpolice list = new Tcpolice();
					list.setExpFile(rs.getString("atitle"));
					list.setServerFile(rs.getString("newFileName"));
					list.setClientFile(rs.getString("oldFileName"));
					list.setFileSize(rs.getInt("size"));
					list.setReadNumber(rs.getInt("downloadCount"));// 下載次數
					list.setCreateName(SysUtil.encrypt(rs.getString("aeditor")));
					list.setCreateDate(rs.getString("aeditDate"));
					lists.add(list);
				}
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				// conn.close();
				return lists;
			}

		} catch (Exception e) {
			this.errorMsg = e.toString();
		}
		return null;
	}

	// 查詢單一檔案(圖檔或其他檔案)
	public ArrayList<Tcpolice> foundImgFile(String newFileName, Connection conn) {
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			if (conn != null && !conn.isClosed()) {
				// 建立statement
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM jip.imagefile where newFileName='" + newFileName + "' ");
				ResultSet rs = stmt.executeQuery(sql.toString());
				ArrayList<Tcpolice> lists = new ArrayList<Tcpolice>();
				for (int i = 0; rs.next(); i++) {
					Tcpolice list = new Tcpolice();
					list.setExpFile("");// ExpFilev欄位資料庫設計不能為null,固給空
					list.setServerFile(rs.getString("newFileName"));
					list.setClientFile(rs.getString("oldFileName"));
					list.setFileSize(rs.getInt("size"));
					list.setReadNumber(rs.getInt("downloadCount"));// 下載次數
					lists.add(list);
				}
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				// conn.close();
				return lists;
			}

		} catch (Exception e) {
			this.errorMsg = e.toString();
		}
		return null;
	}

	// 查詢主題分類
	public ArrayList<Tcpolice> foundCake(String gicuitem, Connection conn) {
		String driver = "com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
			if (conn != null && !conn.isClosed()) {
				// 建立statement
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				StringBuilder sql = new StringBuilder();
				sql.append("select *  from  cumeta4gcat  where  gicuitem ='" + gicuitem + "' ");
				ResultSet rs = stmt.executeQuery(sql.toString());
				ArrayList<Tcpolice> lists = new ArrayList<Tcpolice>();
				for (int i = 0; rs.next(); i++) {
					Tcpolice list = new Tcpolice();
					list.setSubjectID(rs.getString("GCat_Theme"));
					// list.setSubjectClass(rs.getString(""));
					list.setAdministationID(rs.getString("GCat_Cake"));
					// list.setAdministationClass(rs.getString(""));
					list.setServiceID(rs.getString("GCat_Service"));
					// list.setServiceClass(rs.getString(""));
					lists.add(list);
				}
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				// conn.close();
				return lists;
			}

		} catch (Exception e) {
			this.errorMsg = e.toString();
		}
		return null;
	}

	// 查詢多網發布次序
	public int webnumber(String Serno, Connection conn) {
		int s = 0;
		PreparedStatement stmts = null;
		ResultSet rs = null;

		try {
			StringBuffer sSql = new StringBuffer();
			sSql.append(" select *  from [TXGPoliceWeb].[dbo].[" + this.postertablename
					+ "]  where Serno=? order by DetailNo");
			// System.out.println(sSql.toString());
			stmts = conn.prepareStatement(sSql.toString());
			stmts.clearParameters();
			stmts.setString(1, Serno);
			rs = stmts.executeQuery();
			for (int i = 0; rs.next(); i++) {
				s = rs.getInt("DetailNo");
			}

			return s + 1;

		} catch (Exception e) {
			this.errorMsg = e.toString();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmts != null)
					stmts.close();
				// conn.close();
			} catch (SQLException se) {
				System.out.println(se.toString());
			}

		}
		return 1;
	}

	// 查詢檔案DetailNo
	public int selectDetailNo(String Serno, Connection conn, String Flag) {
		int s = 0;

		PreparedStatement stmts = null;
		ResultSet rs = null;

		try {
			StringBuffer sSql = new StringBuffer();
			sSql.append(" select *  from [TXGPoliceWeb].[dbo].[" + this.filetablename
					+ "]  where Serno=? and Flag=?  order by DetailNo");
			// System.out.println(sSql.toString());
			stmts = conn.prepareStatement(sSql.toString());
			stmts.clearParameters();
			stmts.setString(1, Serno);
			stmts.setString(2, Flag);
			rs = stmts.executeQuery();
			for (int i = 0; rs.next(); i++) {
				s = rs.getInt("DetailNo");
			}
			return s + 1;
		} catch (Exception e) {
			this.errorMsg = e.toString();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmts != null)
					stmts.close();
				// conn.close();
			} catch (SQLException se) {
				System.out.println(se.toString());
			}

		}
		return 1;
	}

	// 查詢相關連結DetailNo
	public int selectDetailNo_url(String Serno, Connection conn) {
		int s = 0;
		PreparedStatement stmts = null;
		ResultSet rs = null;

		try {
			StringBuffer sSql = new StringBuffer();
			sSql.append(" select *  from [TXGPoliceWeb].[dbo].[" + this.resourcetablename
					+ "]  where Serno=?   order by DetailNo");
			// System.out.println(sSql.toString());
			stmts = conn.prepareStatement(sSql.toString());
			stmts.clearParameters();
			stmts.setString(1, Serno);
			rs = stmts.executeQuery();
			for (int i = 0; rs.next(); i++) {
				s = rs.getInt("DetailNo");
			}
			return s + 1;
		} catch (Exception e) {
			this.errorMsg = e.toString();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmts != null)
					stmts.close();
				// conn.close();
			} catch (SQLException se) {
				System.out.println(se.toString());
			}

		}
		return 1;
	}

	// 查詢點擊率
	public int foundReadNumber(String docId, Connection conn) {
		String driver = "com.mysql.jdbc.Driver";
		int readNumber = 0;
		try {
			Class.forName(driver);
			if (conn != null && !conn.isClosed()) {
				// 建立statement
				Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM jip.ivahitcount where  docId='" + docId + "' ");
				ResultSet rs = stmt.executeQuery(sql.toString());
				for (int i = 0; rs.next(); i++) {
					Tcpolice list = new Tcpolice();
					readNumber = readNumber + Integer.valueOf(rs.getString("hitCount"));
				}
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				// conn.close();
				return readNumber;
			}

		} catch (Exception e) {
			this.errorMsg = e.toString();
		}
		return readNumber;
	}

	// 新增到Mssql資料表
	public boolean transform_out(ArrayList<Tcpolice> lists) {
		String sourcefilepath = "";// 原文件路徑
		String targetfilepath = "";// 目標文件路徑
		// 設定檔路徑
		String path = "D:\\webapps\\police\\trans.police.gov.tw\\src\\TransferData\\fileimage.properties";
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(path));
			sourcefilepath = properties.getProperty("sourcefilepath");
			targetfilepath = properties.getProperty("targetfilepath");
			// System.out.println("原文件路徑" + sourcefilepath);
		} catch (Exception e) {
			this.errorMsg = e.toString();
		}
		String ndate = nowDate("yyyyMMddhhmmss");// 取系統日期
		boolean check = false;
		// Mssql
		Connection conn = null;
		// Mysql
		String driver = "com.mysql.jdbc.Driver";
		Connection conn2 = null;
		try {
			// 連結資料庫
			conn = DriverManager.getConnection(connectionUrl);
			Class.forName(driver);
			conn2 = DriverManager.getConnection(url, user, password);
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
				Tcpolice data = (Tcpolice) lists.get(i);
				// 引用ID欄位如果為NULL值,這筆資料就是沒引用它筆資料
				if (data.getRefId() == null) {
					StringBuffer sSql = new StringBuffer();
					sSql.append("insert into [TXGPoliceWeb].[dbo].[" + this.datatablename + "]");
					sSql.append("(Serno,PubUnitDN,Subject,SecSubject,DetailContent,PosterDate,CloseDate,StartUsing"
							+ ",LiaisonPer,LiaisonTel,LiaisonFax,LiaisonEmail" + ",IsExamine,ExamineDate,ExamineName"
							+ ",ReadNumber,Audience,SubjectID,SubjectClass,AdministationID,AdministationClass"
							+ ",ServiceID,ServiceClass,CreateUid,CreateName,CreateDate,PostUid,PostName,UpdateDate,DSID,LanguageType,MetaKeyword,SubjectTTSID,SubjectTTSFile,DetailContentTTSID,DetailContentTTSFile,APINumber,TableName)");
					sSql.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					// System.out.println(sSql.toString());
					stmts = conn.prepareStatement(sSql.toString());
					stmts.clearParameters();
					stmts.setString(1, data.getIcuitem());// Serno
					stmts.setString(2, data.getPubUnitDN());// PubUnitDN
					stmts.setString(3, data.getSubject());// Subject
					stmts.setString(4, data.getSecSubject());// SecSubject
					stmts.setString(5, data.getDetailContent());// DetailContent
					stmts.setString(6, data.getPosterDate());// PosterDate
					stmts.setString(7, data.getCloseDate());// CloseDate
					stmts.setString(8, data.getStartUsing());// StartUsing
					stmts.setString(9, data.getLiaisonPer());// LiaisonPer
					stmts.setString(10, data.getLiaisonTel());// LiaisonTel
					stmts.setString(11, data.getLiaisonFax());// LiaisonFax
					stmts.setString(12, data.getLiaisonEmail());// LiaisonEmail
					stmts.setString(13, data.getIsExamine());// IsExamine
					stmts.setString(14, data.getExamineDate());// ExamineDate
					stmts.setString(15, data.getExamineName());// ExamineName
					stmts.setInt(16, data.getReadNumber());// ReadNumber
					stmts.setString(17, data.getAudience());// Audience
					stmts.setString(18, data.getSubjectID());// SubjectID
					stmts.setString(19, data.getSubjectClass());// SubjectClass
					stmts.setString(20, data.getAdministationID());// AdministationID
					stmts.setString(21, data.getAdministationClass());// AdministationClass
					stmts.setString(22, data.getServiceID());// ServiceID
					stmts.setString(23, data.getServiceClass());// ServiceClass
					stmts.setString(24, data.getCreateUid());// CreateUid
					stmts.setString(25, data.getCreateName());// CreateName
					stmts.setString(26, data.getCreateDate());// CreateDate
					stmts.setString(27, data.getPostUid());// PostUid
					stmts.setString(28, data.getPostName());// PostName
					stmts.setString(29, data.getUpdateDate());// UpdateDate
					stmts.setString(30, data.getDSID());// DSID
					stmts.setString(31, data.getLanguageType());// LanguageType
					stmts.setString(32, data.getMetaKeyword());// MetaKeyword
					stmts.setString(33, data.getSubjectTTSID());// SubjectTTSID
					stmts.setString(34, data.getSubjectTTSFile());// SubjectTTSFile
					stmts.setString(35, data.getDetailContentTTSID());// DetailContentTTSID
					stmts.setString(36, data.getDetailContentTTSFile());// DetailContentTTSFile
					stmts.setString(37, data.getAPINumber());// APINumber
					stmts.setString(38, data.getTableName());// TableName
					stmts.executeUpdate();
				}

				// 分類
				String mserno = "";
				if (this.className != null && this.className.size() > 0 && this.classSerno.size() > 0) {
					if (data.getTopCat() != null && !data.getTopCat().equals("")) {
						for (int q = 0; q < this.className.size(); q++) {
							if (data.getTopCat().equals(this.className.get(q))) {
								mserno = this.classSerno.get(q);
								break;
							} else {
								if (q == this.className.size() - 1) {
									mserno = this.classSerno.get(this.classSerno.size() - 1);
								}
							}
						}
					} else {
						mserno = this.classSerno.get(this.classSerno.size() - 1);
					}
				}
				// 分類END

				// 多網發布站台
				StringBuffer sSql2 = new StringBuffer();
				sSql2.append("insert into [TXGPoliceWeb].[dbo].[" + this.postertablename + "]");
				sSql2.append("(Serno,DetailNo,Mserno,WebSiteDN,WebSiteName,WebSiteExam,WebSiteExamDate,WebSiteExamName"
						+ ",IsTop,TopDate,DataSort,CreateUid,CreateName,CreateDate,PostUid,PostName,UpdateDate)");
				sSql2.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				// System.out.println(sSql2.toString());
				stmts2 = conn.prepareStatement(sSql2.toString());
				stmts2.clearParameters();
				// 引用ID欄位如果不為NULL值,這筆資料就是引用它筆資料
				if (data.getRefId() != null) {
					stmts2.setString(1, data.getRefId());// Serno為引用資料的Serno值
					stmts2.setInt(2, webnumber(data.getRefId(), conn));// DetailNo
				} else {
					stmts2.setString(1, data.getIcuitem());// 沒引用其他資料,Serno值為自己
					stmts2.setInt(2, webnumber(data.getIcuitem(), conn));// DetailNo
				}
				stmts2.setString(3, mserno);// Mserno
				stmts2.setString(4, data.getWebSiteDN());// WebSiteDN
				stmts2.setString(5, data.getWebSiteName());// WebSiteName
				stmts2.setString(6, "Y");// WebSiteExam
				stmts2.setString(7, data.getWebSiteExamDate());// WebSiteExamDate
				stmts2.setString(8, data.getWebSiteExamName());// WebSiteExamName
				stmts2.setString(9, "N");// IsTop
				stmts2.setString(10, data.getTopDate());// TopDate
				stmts2.setInt(11, 9999999);// DataSort
				stmts2.setString(12, data.getCreateUid());// CreateUid
				stmts2.setString(13, data.getCreateName());// CreateName
				stmts2.setString(14, data.getCreateDate());// CreateDate
				stmts2.setString(15, data.getPostUid());// PostUid
				stmts2.setString(16, data.getPostName());// PostName
				stmts2.setString(17, data.getUpdateDate());// UpdateDate
				stmts2.executeUpdate();

				// 本身為外連新增到相關連結
				if (data.getXurl() != null && !data.getXurl().equals("")) {

					StringBuffer sSql7 = new StringBuffer();
					sSql7.append("insert into [TXGPoliceWeb].[dbo].[" + this.resourcetablename + "]");
					sSql7.append(
							"(Serno,DetailNo,RelateUrl,RelateName,Target,ReadNumber,DataSort,CreateUid,CreateName,CreateDate,PostUid,PostName,UpdateDate)");
					sSql7.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
					stmts7 = conn.prepareStatement(sSql7.toString());
					stmts7.clearParameters();
					stmts7.setString(1, data.getIcuitem());// Serno
					stmts7.setInt(2, selectDetailNo_url(data.getIcuitem(), conn));// DetailNo
					stmts7.setString(3, data.getXurl());// RelateUrl
					stmts7.setString(4, data.getSubject());// RelateName
					stmts7.setString(5, data.getXnewWindow());// Target
					stmts7.setInt(6, data.getReadNumber());// ReadNumber
					stmts7.setInt(7, 9999999);// DataSort
					stmts7.setString(8, data.getCreateUid());// CreateUid
					stmts7.setString(9, data.getCreateName());// CreateName,
					stmts7.setString(10, data.getCreateDate());// CreateDate
					stmts7.setString(11, data.getPostUid());// PostUid
					stmts7.setString(12, data.getPostName());// PostName
					stmts7.setString(13, data.getUpdateDate());// UpdateDate
					stmts7.executeUpdate();
				}

				// 相關連結
				ArrayList<Tcpolice> urlList = foundUrl(data.getIcuitem(), conn2);
				if (urlList != null && urlList.size() > 0) {
					for (int k = 0; k < urlList.size(); k++) {
						Tcpolice item = (Tcpolice) urlList.get(k);
						StringBuffer sSql4 = new StringBuffer();
						sSql4.append("insert into [TXGPoliceWeb].[dbo].[" + this.resourcetablename + "]");
						sSql4.append(
								"(Serno,DetailNo,RelateUrl,RelateName,Target,ReadNumber,DataSort,CreateUid,CreateName,CreateDate,PostUid,PostName,UpdateDate)");
						sSql4.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
						stmts4 = conn.prepareStatement(sSql4.toString());
						stmts4.clearParameters();

						stmts4.setString(1, data.getIcuitem());// Serno
						stmts4.setInt(2, selectDetailNo_url(data.getIcuitem(), conn));// DetailNo
						stmts4.setString(3, item.getRelateUrl());// RelateUrl
						stmts4.setString(4, item.getRelateName());// RelateName
						stmts4.setString(5, item.getTarget());// Target
						stmts4.setInt(6, item.getReadNumber());// ReadNumber
						stmts4.setInt(7, 9999999);// DataSort
						stmts4.setString(8, data.getCreateUid());// CreateUid
						stmts4.setString(9, data.getCreateName());// CreateName,
						stmts4.setString(10, data.getCreateDate());// CreateDate
						stmts4.setString(11, data.getPostUid());// PostUid
						stmts4.setString(12, data.getPostName());// PostName
						stmts4.setString(13, data.getUpdateDate());// UpdateDate
						stmts4.executeUpdate();
					}
				}

				// 單一圖檔(單一圖檔不屬於附件,固要跟附件分開搜尋)
				if (data.getXimgFile() != null && !data.getXimgFile().equals("")) {
					ArrayList<Tcpolice> fileList2 = foundImgFile(data.getXimgFile(), conn2);
					if (fileList2 != null && fileList2.size() > 0) {
						for (int s = 0; s < 1; s++) {
							Tcpolice item = (Tcpolice) fileList2.get(s);
							// 處理檔案
							FileDeal f = new FileDeal();
							Tcpolice filedata = f.deal(item.getClientFile(), this.dsid, s);
							// 處理檔案END
							StringBuffer sSql3 = new StringBuffer();
							sSql3.append("insert into [TXGPoliceWeb].[dbo].[" + this.filetablename + "]");
							sSql3.append(
									"(Serno,DetailNo,Flag,ClientFile,ServerFile,ExpFile,ImageMagick,ServerFilePDF,PDFFlag,ServerFileODF,ODFFlag,CMediaFile,SMediaFile,ExpMediaFile,FlvFile,FileSize,ReadNumber,DataSort,CreateUid,CreateName,CreateDate,PostUid,PostName,UpdateDate)");
							sSql3.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
							stmts3 = conn.prepareStatement(sSql3.toString());
							stmts3.clearParameters();
							stmts3.setString(1, data.getIcuitem());// Serno
							if (item.getServerFile().indexOf("jpg") >= 0 || item.getServerFile().indexOf("JPG") >= 0
									|| item.getServerFile().indexOf("bmp") >= 0
									|| item.getServerFile().indexOf("BMP") >= 0
									|| item.getServerFile().indexOf("png") >= 0
									|| item.getServerFile().indexOf("PNG") >= 0
									|| item.getServerFile().indexOf("jpeg") >= 0
									|| item.getServerFile().indexOf("JPEG") >= 0
									|| item.getServerFile().indexOf("GIF") >= 0
									|| item.getServerFile().indexOf("gif") >= 0) {
								stmts3.setInt(2, selectDetailNo(data.getIcuitem(), conn, "pic"));// DetailNo
								stmts3.setString(3, "pic");// Flag
							} else {
								stmts3.setInt(2, selectDetailNo(data.getIcuitem(), conn, "dos"));// DetailNo
								stmts3.setString(3, "dos");// Flag
							}
							stmts3.setString(4, item.getClientFile());// ClientFile
							if (filedata != null && filedata.getServerFile() != null) {
								stmts3.setString(5, filedata.getServerFile());// ServerFile
							} else {
								stmts3.setString(5, item.getServerFile());// ServerFile
							}
							stmts3.setString(6, item.getExpFile());// ExpFile
							// 如果圖片有被處理(縮圖)過
							if (filedata != null && filedata.getImageMagick() != null) {
								stmts3.setString(7, filedata.getImageMagick());// ImageMagick
							} else {
								stmts3.setString(7, item.getImageMagick());// ImageMagick
							}
							stmts3.setString(8, item.getServerFilePDF());// ServerFilePDF
							stmts3.setString(9, item.getPDFFlag());// PDFFlag
							stmts3.setString(10, item.getServerFileODF());// ServerFileODF
							stmts3.setString(11, item.getODFFlag());// ODFFlag
							stmts3.setString(12, item.getCMediaFile());// CMediaFile
							stmts3.setString(13, item.getSMediaFile());// SMediaFile
							stmts3.setString(14, item.getExpMediaFile());// ExpMediaFile
							stmts3.setString(15, item.getFlvFile());// FlvFile
							stmts3.setInt(16, item.getFileSize());// FileSize
							stmts3.setInt(17, item.getReadNumber());// ReadNumber
							stmts3.setInt(18, 9999999);// DataSort
							stmts3.setString(19, item.getCreateUid());// CreateUid
							stmts3.setString(20, item.getCreateName());// CreateName
							stmts3.setString(21, item.getCreatedDate());// CreateDate
							stmts3.setString(22, "administrator");// PostUid
							stmts3.setString(23, "5pyA6auY566h55CG6ICF");// PostName
							stmts3.setString(24, data.getUpdateDate());// UpdateDate
							stmts3.executeUpdate();
						}
					}
				}

				// 單一檔案(單一檔案不屬於附件,固要跟附件分開搜尋)
				if (data.getFileDownLoad() != null && !data.getFileDownLoad().equals("")) {
					ArrayList<Tcpolice> fileList2 = foundImgFile(data.getFileDownLoad(), conn2);
					if (fileList2 != null && fileList2.size() > 0) {

						for (int s = 0; s < 1; s++) {
							Tcpolice item = (Tcpolice) fileList2.get(s);
							// 處理檔案
							FileDeal f = new FileDeal();
							Tcpolice filedata = f.deal(item.getClientFile(), this.dsid, s);
							// 處理檔案END
							StringBuffer sSql6 = new StringBuffer();
							sSql6.append("insert into [TXGPoliceWeb].[dbo].[" + this.filetablename + "]");
							sSql6.append(
									"(Serno,DetailNo,Flag,ClientFile,ServerFile,ExpFile,ImageMagick,ServerFilePDF,PDFFlag,ServerFileODF,ODFFlag,CMediaFile,SMediaFile,ExpMediaFile,FlvFile,FileSize,ReadNumber,DataSort,CreateUid,CreateName,CreateDate,PostUid,PostName,UpdateDate)");
							sSql6.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

							stmts6 = conn.prepareStatement(sSql6.toString());
							stmts6.clearParameters();
							stmts6.setString(1, data.getIcuitem());// Serno
							if (item.getServerFile().indexOf("jpg") >= 0 || item.getServerFile().indexOf("JPG") >= 0
									|| item.getServerFile().indexOf("bmp") >= 0
									|| item.getServerFile().indexOf("BMP") >= 0
									|| item.getServerFile().indexOf("png") >= 0
									|| item.getServerFile().indexOf("PNG") >= 0
									|| item.getServerFile().indexOf("jpeg") >= 0
									|| item.getServerFile().indexOf("JPEG") >= 0
									|| item.getServerFile().indexOf("GIF") >= 0
									|| item.getServerFile().indexOf("gif") >= 0) {
								stmts6.setInt(2, selectDetailNo(data.getIcuitem(), conn, "pic"));// DetailNo
								stmts6.setString(3, "pic");// Flag
							} else {
								stmts6.setInt(2, selectDetailNo(data.getIcuitem(), conn, "dos"));// DetailNo
								stmts6.setString(3, "dos");// Flag
							}
							stmts6.setString(4, item.getClientFile());// ClientFile
							if (filedata != null && filedata.getServerFile() != null) {
								stmts6.setString(5, filedata.getServerFile());// ServerFile
							} else {
								stmts6.setString(5, item.getServerFile());// ServerFile
							}
							stmts6.setString(6, item.getExpFile());// ExpFile
							// 如果圖片有被處理(縮圖)過
							if (filedata != null && filedata.getImageMagick() != null) {
								stmts6.setString(7, filedata.getImageMagick());// ImageMagick
							} else {
								stmts6.setString(7, item.getImageMagick());// ImageMagick
							}
							stmts6.setString(8, item.getServerFilePDF());// ServerFilePDF
							stmts6.setString(9, item.getPDFFlag());// PDFFlag
							stmts6.setString(10, item.getServerFileODF());// ServerFileODF
							stmts6.setString(11, item.getODFFlag());// ODFFlag
							stmts6.setString(12, item.getCMediaFile());// CMediaFile
							stmts6.setString(13, item.getSMediaFile());// SMediaFile
							stmts6.setString(14, item.getExpMediaFile());// ExpMediaFile
							stmts6.setString(15, item.getFlvFile());// FlvFile
							stmts6.setInt(16, item.getFileSize());// FileSize
							stmts6.setInt(17, item.getReadNumber());// ReadNumber
							stmts6.setInt(18, 9999999);// DataSort
							stmts6.setString(19, item.getCreateUid());// CreateUid
							stmts6.setString(20, item.getCreateName());// CreateName
							stmts6.setString(21, item.getCreatedDate());// CreateDate
							stmts6.setString(22, "administrator");// PostUid
							stmts6.setString(23, "5pyA6auY566h55CG6ICF");// PostName
							stmts6.setString(24, data.getUpdateDate());// UpdateDate
							stmts6.executeUpdate();
						}
					}
				}

				// 相關附件
				ArrayList<Tcpolice> fileList = foundFile(data.getIcuitem(), conn2);
				// System.out.println(data.getIcuitem()+":"+fileList.size());
				if (fileList != null && fileList.size() > 0) {
					for (int s = 0; s < fileList.size(); s++) {
						Tcpolice item = (Tcpolice) fileList.get(s);
						// 處理檔案
						FileDeal f = new FileDeal();
						Tcpolice filedata = f.deal(item.getClientFile(), this.dsid, s);
						// 處理檔案END
						StringBuffer sSql5 = new StringBuffer();
						sSql5.append("insert into [TXGPoliceWeb].[dbo].[" + this.filetablename + "]");
						sSql5.append(
								"(Serno,DetailNo,Flag,ClientFile,ServerFile,ExpFile,ImageMagick,ServerFilePDF,PDFFlag,ServerFileODF,ODFFlag,CMediaFile,SMediaFile,ExpMediaFile,FlvFile,FileSize,ReadNumber,DataSort,CreateUid,CreateName,CreateDate,PostUid,PostName,UpdateDate)");
						sSql5.append("values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
						stmts5 = conn.prepareStatement(sSql5.toString());
						stmts5.clearParameters();
						stmts5.setString(1, data.getIcuitem());// Serno
						if (item.getServerFile().indexOf("jpg") >= 0 || item.getServerFile().indexOf("JPG") >= 0
								|| item.getServerFile().indexOf("bmp") >= 0 || item.getServerFile().indexOf("BMP") >= 0
								|| item.getServerFile().indexOf("png") >= 0 || item.getServerFile().indexOf("PNG") >= 0
								|| item.getServerFile().indexOf("jpeg") >= 0
								|| item.getServerFile().indexOf("JPEG") >= 0 || item.getServerFile().indexOf("GIF") >= 0
								|| item.getServerFile().indexOf("gif") >= 0) {
							stmts5.setInt(2, selectDetailNo(data.getIcuitem(), conn, "pic"));// DetailNo
							stmts5.setString(3, "pic");// Flag
						} else {
							stmts5.setInt(2, selectDetailNo(data.getIcuitem(), conn, "dos"));// DetailNo
							stmts5.setString(3, "dos");// Flag
						}
						stmts5.setString(4, item.getClientFile());// ClientFile
						if (filedata != null && filedata.getServerFile() != null) {
							stmts5.setString(5, filedata.getServerFile());// ServerFile
						} else {
							stmts5.setString(5, item.getServerFile());// ServerFile
						}
						stmts5.setString(6, item.getExpFile());// ExpFile
						// 如果圖片有被處理(縮圖)過
						if (filedata != null && filedata.getImageMagick() != null) {
							stmts5.setString(7, filedata.getImageMagick());// ImageMagick
						} else {
							stmts5.setString(7, item.getImageMagick());// ImageMagick
						}
						// 假如檔案是doc將它轉檔
						if (item.getClientFile().indexOf("doc") >= 0) {
							CallTransferConvert callconvert = new CallTransferConvert();
							boolean check2 = callconvert.convert(sourcefilepath + item.getClientFile(),
									targetfilepath + ndate + String.valueOf(data.getIcuitem()) + ".pdf",
									targetfilepath + ndate + String.valueOf(data.getIcuitem()) + ".odt",
									data.getIcuitem());
							if (check2 == true) {
								stmts5.setString(8, callconvert.getServerFilePDF());// ServerFilePDF
								stmts5.setString(9, "Y");// PDFFlag
								stmts5.setString(10, callconvert.getServerFileODF());// ServerFileODF
								stmts5.setString(11, "Y");// ODFFlag
							} else {
								System.out.print(callconvert.getErrorMessage());
								stmts5.setString(8, item.getServerFilePDF());// ServerFilePDF
								stmts5.setString(9, item.getPDFFlag());// PDFFlag
								stmts5.setString(10, item.getServerFileODF());// ServerFileODF
								stmts5.setString(11, item.getODFFlag());// ODFFlag
							}
						} else {
							stmts5.setString(8, item.getServerFilePDF());// ServerFilePDF
							stmts5.setString(9, item.getPDFFlag());// PDFFlag
							stmts5.setString(10, item.getServerFileODF());// ServerFileODF
							stmts5.setString(11, item.getODFFlag());// ODFFlag
						}
						// 轉檔END
						stmts5.setString(12, item.getCMediaFile());// CMediaFile
						stmts5.setString(13, item.getSMediaFile());// SMediaFile
						stmts5.setString(14, item.getExpMediaFile());// ExpMediaFile
						stmts5.setString(15, item.getFlvFile());// FlvFile
						stmts5.setInt(16, item.getFileSize());// FileSize
						stmts5.setInt(17, item.getReadNumber());// ReadNumber
						stmts5.setInt(18, 9999999);// DataSort
						stmts5.setString(19, item.getCreateUid());// CreateUid
						stmts5.setString(20, item.getCreateName());// CreateName
						stmts5.setString(21, item.getCreatedDate());// CreateDate
						stmts5.setString(22, "administrator");// PostUid
						stmts5.setString(23, "5pyA6auY566h55CG6ICF");// PostName
						stmts5.setString(24, item.getCreateDate());// UpdateDate
						stmts5.executeUpdate();
					}
				}

			}
			check = true;
			return check;
		} catch (Exception e) {
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
				conn2.close();

			} catch (SQLException se) {
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

}
