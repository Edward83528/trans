<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="transfer.tcpolice.*"%>
<%@ page import="transfer.entity.Tcpolice"%>
<%@ page import="java.util.*"%>
<%@ page import="org.json.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>台中市警察局轉置資料</title>
</head>
<body>

	<%
		//判斷哪個網站
		String web = (String) request.getParameter("web");
		//判斷單元名稱
		String unit = (String) request.getParameter("unit");
		//判斷新舊類別哪個改變
		String type = (String) request.getParameter("type");

		String WebSiteDN = "";
		String codeMetaId = "";
		String classname = "";
		String dsid = "";

		switch (web) {
		//總局
		case "1":
			WebSiteDN = "ou=chtcpolice,ou=ap_root,o=tcpolice,c=tw";
			break;
		//第一分局網站
		case "2":
			WebSiteDN = "ou=chpa,ou=ap_root,o=tcpolice,c=tw";
			break;
		//第二分局網站
		case "3":
			WebSiteDN = "ou=chpb,ou=ap_root,o=tcpolice,c=tw";
			break;
		//第三分局網站
		case "4":
			WebSiteDN = "ou=chpc,ou=ap_root,o=tcpolice,c=tw";
			break;
		//第四分局網站
		case "5":
			WebSiteDN = "ou=chpd,ou=ap_root,o=tcpolice,c=tw";
			break;
		//第五分局網站
		case "6":
			WebSiteDN = "ou=chpe,ou=ap_root,o=tcpolice,c=tw";
			break;
		//第六分局網站
		case "7":
			WebSiteDN = "ou=chpf,ou=ap_root,o=tcpolice,c=tw";
			break;
		//豐原分局網站
		case "8":
			WebSiteDN = "ou=chpg,ou=ap_root,o=tcpolice,c=tw";
			break;
		//霧峰分局網站
		case "9":
			WebSiteDN = "ou=chph,ou=ap_root,o=tcpolice,c=tw";
			break;
		//烏日分局網站
		case "10":
			WebSiteDN = "ou=chpi,ou=ap_root,o=tcpolice,c=tw";
			break;
		//清水分局網站
		case "11":
			WebSiteDN = "ou=chpj,ou=ap_root,o=tcpolice,c=tw";
			break;
		//大甲分局網站
		case "12":
			WebSiteDN = "ou=chpk,ou=ap_root,o=tcpolice,c=tw";
			break;
		//太平分局網站
		case "13":
			WebSiteDN = "ou=chpl,ou=ap_root,o=tcpolice,c=tw";
			break;
		//東勢分局網站
		case "14":
			WebSiteDN = "ou=chpm,ou=ap_root,o=tcpolice,c=tw";
			break;
		//和平分局網站
		case "15":
			WebSiteDN = "ou=chpn,ou=ap_root,o=tcpolice,c=tw";
			break;
		//保安警察大隊網站
		case "16":
			WebSiteDN = "ou=chta,ou=ap_root,o=tcpolice,c=tw";
			break;
		//刑事警察大隊網站
		case "17":
			WebSiteDN = "ou=chtb,ou=ap_root,o=tcpolice,c=tw";
			break;
		//交通警察大隊網站
		case "18":
			WebSiteDN = "ou=chtc,ou=ap_root,o=tcpolice,c=tw";
			break;
		//少年警察隊網站
		case "19":
			WebSiteDN = "ou=chtd,ou=ap_root,o=tcpolice,c=tw";
			break;
		//少年輔導委員會網站
		case "20":
			WebSiteDN = "ou=chtg,ou=ap_root,o=tcpolice,c=tw";
			break;
		//婦幼警察隊網站
		case "21":
			WebSiteDN = "ou=chte,ou=ap_root,o=tcpolice,c=tw";
			break;
		//台中市警察局(運動網站)
		case "22":
			WebSiteDN = "ou=chtf,ou=ap_root,o=tcpolice,c=tw";
			break;
		//台中市警察局(兒童版)
		case "23":
			WebSiteDN = "ou=kids,ou=ap_root,o=tcpolice,c=tw";
			break;
		}

		switch (unit) {
		//警政新聞
		case "1":
			codeMetaId = "newsCatPolice";
			classname = "NewsClass";
			dsid = "news";
			break;
		//活動訊息
		case "2":
			codeMetaId = "newsCatActivity";
			classname = "ActivityClass";
			dsid = "activity";
			break;
		//忠愛園地
		case "3":
			codeMetaId = "newsCatGood";
			classname = "LoyaltyClass";
			dsid = "loyalty";
			break;
		//人事公告
		case "4":
			codeMetaId = "PersonnelMatters";
			classname = "MultisClass";
			dsid = "bulletin";
			break;
		//招標資訊
		case "5":
			codeMetaId = "newsCatBid";
			classname = "MultisClass";
			dsid = "tender";
			break;
		//常見問答
		case "6":
			codeMetaId = "QandA";
			classname = "FAQClass";
			dsid = "chfaq";
			break;
		//表單下載
		case "7":
			codeMetaId = "serviceDownloadMain";
			classname = "DownloadClass";
			dsid = "downlod";
			break;
		//春節治安
		case "8":
			codeMetaId = "";
			classname = "PublicizeClass";
			dsid = "newyear";
			break;
		//預防犯罪
		case "9":
			codeMetaId = "CrimePrevention";
			classname = "PublicizeClass";
			dsid = "prevent";
			break;
		//交通宣導
		case "10":
			codeMetaId = "TrafficSafety";
			classname = "PublicizeClass";
			dsid = "traffic";
			break;
		//少年安全
		case "11":
			codeMetaId = "YouthSafety";
			classname = "PublicizeClass";
			dsid = "juvenile";
			break;
		//婦幼安全
		case "12":
			codeMetaId = "GirlsSafety";
			classname = "PublicizeClass";
			dsid = "womchild";
			break;
		//社區治安
		case "13":
			codeMetaId = "SocialPolice";
			classname = "PublicizeClass";
			dsid = "community";
			break;
		//性別主流化
		case "14":
			codeMetaId = "";
			classname = "InfoPublicClass";
			dsid = "sexmains";
			break;

		//政令宣導(分局)
		case "15":
			codeMetaId = "d decree";
			classname = "PublicizeClass";
			dsid = "decree";
			break;
		//志工專區
		case "16":
			codeMetaId = "";
			classname = "PublicizeClass";
			dsid = "volunteer";
			break;
		//人事服務
		case "17":
			codeMetaId = "";
			classname = "MultisClass";
			dsid = "personnel";
			break;
		//護生法集**
		case "18":
			codeMetaId = "";
			classname = "ProStudLawClass";
			dsid = "protect";
			break;
		//法規查詢
		case "19":
			codeMetaId = "lawsearch";
			classname = "MultisClass";
			dsid = "law";
			break;
		//線上申辦
		case "20":
			codeMetaId = "OnlineApply";
			classname = "MultisClass";
			dsid = "bid";
			break;
		//施政計畫
		case "21":
			codeMetaId = "AdministrationPlan";
			classname = "InfoPublicClass";
			dsid = "policy";
			break;
		//政令宣導(總局)
		case "22":
			codeMetaId = "decree";
			classname = "PublicizeClass";
			dsid = "decree";
			break;
		}

		JSONArray array = new JSONArray();

		JsonClass j = new JsonClass();
		j.setClasstablename(classname);

		JSONObject ob = new JSONObject();
		if (type.equals("A")) {
			ArrayList<Tcpolice> items2 = j.selectClass_old(codeMetaId);
			for (int i = 0; i < items2.size(); i++) {
				Tcpolice item = items2.get(i);
				Tcpolice item2 = items2.get(i);
				ob.append("classSerno_old", item2.getSerno());
				ob.append("className_old", item2.getClassName());
			}

		} else if (type.equals("B")) {
			ArrayList<Tcpolice> items = j.selectClass_new(WebSiteDN, dsid);
			for (int i = 0; i < items.size(); i++) {
				Tcpolice item = items.get(i);
				ob.append("classSerno_new", item.getSerno());
				ob.append("className_new", item.getClassName());
				ob.append("DSID", item.getDSID());
			}

		}

		response.setContentType("text/plain; charset=UTF-8");
		response.getWriter().print("||" + ob.toString() + "||");
		response.getWriter().flush();
		response.getWriter().close();
	%>
</body>
</html>