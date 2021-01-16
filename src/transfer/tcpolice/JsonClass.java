package transfer.tcpolice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import transfer.entity.Tcpolice;

public class JsonClass {
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
	private String classtablename = null;
	// 樹節點
	private String NodeId = "";
	// dsid
	private String dsid = "";
	// 語言
	private String LanguageType = null;

	public static void main(String[] args) {
		// JsonClass j = new JsonClass();
		// j.setClasstablename("NewsClass");
		// ArrayList<TransferItem> items =
		// j.selectClass_new("ou=chtcpolice,ou=ap_root,o=tcpolice,c=tw", "news");
		// if (items != null && items.size() > 0) {
		// for (int i = 0; i < items.size(); i++) {
		// TransferItem item = items.get(i);
		// System.out.println(item.getClassName());
		// }
		// }

	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getClasstablename() {
		return classtablename;
	}

	public void setClasstablename(String classtablename) {
		this.classtablename = classtablename;
	}

	// 查詢原舊類別資訊(Mysql)
	public ArrayList<Tcpolice> selectClass_old(String codeMetaId) {
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
				sql.append("select * from jip.codemain");
				sql.append(" where  codeMetaId ='" + codeMetaId + "'  order by msortValue");
				// System.out.println(sql.toString());
				ResultSet rs = stmt.executeQuery(sql.toString());

				ArrayList<Tcpolice> lists = new ArrayList<Tcpolice>();

				for (int i = 0; rs.next(); i++) {
					Tcpolice list = new Tcpolice();
					list.setSerno(rs.getString("mcode"));
					list.setClassName(rs.getString("mvalue"));
					lists.add(list);
				}
				// System.out.println("查詢成功！");
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

	// 查詢新類別資訊(Mssql)
	public ArrayList<Tcpolice> selectClass_new(String WebSiteDN, String DSID) {
		// Mssql
		Connection conn = null;
		try {
			// 連結資料庫
			conn = DriverManager.getConnection(connectionUrl);
		} catch (Exception e) {
			this.errorMsg = e.toString();
			return null;
		}

		PreparedStatement stmts = null;
		ResultSet rs = null;

		try {
			StringBuffer sSql = new StringBuffer();
			sSql.append(" select *  from [TXGPoliceWeb].[dbo].[" + this.classtablename
					+ "]  where WebSiteDN=? and DSID=?   order by  GroupID");
			// System.out.println(sSql.toString());
			stmts = conn.prepareStatement(sSql.toString());
			stmts.clearParameters();
			stmts.setString(1, WebSiteDN);
			stmts.setString(2, DSID);
			rs = stmts.executeQuery();
			ArrayList<Tcpolice> lists = new ArrayList<Tcpolice>();
			for (int i = 0; rs.next(); i++) {
				Tcpolice list = new Tcpolice();
				list.setSerno(rs.getString("Serno"));
				list.setClassName(rs.getString("ClassName"));
				list.setDSID(rs.getString("DSID"));
				lists.add(list);
			}
			return lists;
		} catch (Exception e) {
			this.errorMsg = e.toString();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmts != null)
					stmts.close();
				conn.close();
			} catch (SQLException se) {
				System.out.println(se.toString());
			}

		}
		return null;
	}

}
