package transfer.tcpolice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import transfer.entity.Tcpolice;

//會有漏掉幾個單位,其餘手動補
public class MappingDept {
	// MySQL資料庫連線資訊
	String url = "jdbc:mysql://localhost:3306/jip";
	String user = "root";
	String password = "zxcvbnm0351";
	// MsSQL資料庫連線資訊
	String connectionUrl = "jdbc:sqlserver://localhost:1433;" + "user=harry;password=sa;"
			+ "databaseName=TXGPoliceWeb;useUnicode=true;characterEncoding=utf-8;";

	public static void main(String[] args) {
		MappingDept data = new MappingDept();
		data.mapping_in();
	}

	// 查詢原Mysql資料
	public ArrayList<Tcpolice> mapping_out() {
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
				sql.append("select * from jip.dept");
				// System.out.println(sql.toString());
				ResultSet rs = stmt.executeQuery(sql.toString());
				ArrayList<Tcpolice> lists = new ArrayList<Tcpolice>();
				for (int i = 0; rs.next(); i++) {
					Tcpolice list = new Tcpolice();
					list.setPubUnitDN(rs.getString("deptId"));
					if (rs.getString("deptId").length() > 3) {
						if (rs.getString("deptId").substring(0, 3).equals("001")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("總局", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("001")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("總局", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("002")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("第一分局", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("003")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("第二分局", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("004")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("第三分局", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("005")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("第四分局", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("006")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("第五分局", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("007")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("第六分局", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("008")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("豐原分局", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("009")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("烏日分局", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("010")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("大甲分局", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("011")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("清水分局", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("012")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("和平分局", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("013")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("霧峰分局", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("014")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("東勢分局", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("015")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("太平分局", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("016")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("刑事警察大隊", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("017")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("交通警察大隊", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("018")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("婦幼警察隊", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("019")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("保安警察大隊", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("020")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("少年警察隊", ""));
						} else if (rs.getString("deptId").substring(0, 3).equals("021")) {
							list.setPubUnitName(rs.getString("deptName").replaceAll("少年輔導委員會", ""));
						} else {
							list.setPubUnitName(rs.getString("deptName"));
						}
					} else {
						list.setPubUnitName(rs.getString("deptName"));
					}

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
			System.out.println(e.toString());
		}
		return null;
	}

	// 更新到Mssql資料表
	public boolean mapping_in() {
		boolean check = false;
		Connection conn = null;
		try {
			// 連結資料庫
			conn = DriverManager.getConnection(connectionUrl);
		} catch (Exception e) {
			System.out.println(e.toString());
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
		try {
			ArrayList<Tcpolice> lists = mapping_out();
			if (lists != null) {
				for (int i = 0; i < lists.size(); i++) {
					Tcpolice list = (Tcpolice) lists.get(i);
					// System.out.println("挖" + list.getPubUnitDN());
					// System.out.println("挖" + list.getPubUnitName());
					StringBuffer sSql = new StringBuffer();
					String s = "0";
					if (list.getPubUnitDN().length() >= 3) {
						s = list.getPubUnitDN().substring(0, 3);
					}
					if (!s.equals("001")) {
						sSql.append("update [TXGPoliceWeb].[dbo].[Department] set UnitDNOld=? ");
						sSql.append(" where ChineseTitle  = ?  ");
						sSql.append(" and OU like ?  ");
					} else {
						sSql.append("update [TXGPoliceWeb].[dbo].[Department] set UnitDNOld=? ");
						sSql.append(" where ChineseTitle  = ?  ");
						sSql.append(" and UnitDN like ?  ");

					}
					System.out.println(sSql.toString());
					stmts = conn.prepareStatement(sSql.toString());
					stmts.clearParameters();
					stmts.setString(1, list.getPubUnitDN());
					stmts.setString(2, list.getPubUnitName());
					stmts.setString(3, "%" + MappingPubUnitDN(list.getPubUnitDN()) + "%");
					int a = stmts.executeUpdate();
					System.out.println("影響:" + a + "筆" + "PubUnitDN:" + list.getPubUnitDN() + "PubUnitName:"
							+ list.getPubUnitName() + "MappingPubUnitDN:" + MappingPubUnitDN(list.getPubUnitDN()));
				}
			}

			check = true;
			return check;

		} catch (Exception e) {
			System.out.println(e.toString());
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
				conn.close();
			} catch (SQLException se) {
				System.out.println(se.toString());
			}

		}
		return check;
	}

	// 發布單位Mapping
	public String MappingPubUnitDN(String deptId) {
		String PubUnitDN3 = "";
		int dept = 001;
		if (deptId != null && !deptId.equals("") && deptId.length() >= 3) {
			dept = Integer.valueOf(deptId.substring(0, 3));
		} else {
			dept = 0;
		}
		if (dept == 1) {
			PubUnitDN3 = PubUnitDN3 + "tcpolice01";
		} else if (dept == 2) {
			PubUnitDN3 = PubUnitDN3 + "pa";
		} else if (dept == 3) {
			PubUnitDN3 = PubUnitDN3 + "pb";
		} else if (dept == 4) {
			PubUnitDN3 = PubUnitDN3 + "pc";
		} else if (dept == 5) {
			PubUnitDN3 = PubUnitDN3 + "pd";
		} else if (dept == 6) {
			PubUnitDN3 = PubUnitDN3 + "pe";
		} else if (dept == 7) {
			PubUnitDN3 = PubUnitDN3 + "pf";
		} else if (dept == 8) {
			PubUnitDN3 = PubUnitDN3 + "pg";
		} else if (dept == 9) {
			PubUnitDN3 = PubUnitDN3 + "pi";
		} else if (dept == 10) {
			PubUnitDN3 = PubUnitDN3 + "pk";
		} else if (dept == 11) {
			PubUnitDN3 = PubUnitDN3 + "pj";
		} else if (dept == 12) {
			PubUnitDN3 = PubUnitDN3 + "pn";
		} else if (dept == 13) {
			PubUnitDN3 = PubUnitDN3 + "ph";
		} else if (dept == 14) {
			PubUnitDN3 = PubUnitDN3 + "pm";
		} else if (dept == 15) {
			PubUnitDN3 = PubUnitDN3 + "pl";
		} else if (dept == 16) {
			PubUnitDN3 = PubUnitDN3 + "tb";
		} else if (dept == 17) {
			PubUnitDN3 = PubUnitDN3 + "tc";
		} else if (dept == 18) {
			PubUnitDN3 = PubUnitDN3 + "te";
		} else if (dept == 19) {
			PubUnitDN3 = PubUnitDN3 + "ta";
		} else if (dept == 20) {
			PubUnitDN3 = PubUnitDN3 + "td";
		} else {
			PubUnitDN3 = PubUnitDN3 + "organization";
		}
		return PubUnitDN3;

	}
}