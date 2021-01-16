<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="transfer.tcpolice.*"%>
<%@ page import="transfer.entity.Tcpolice"%>
<%@ page import="java.util.*"%>
<%@ page import="sysview.zhiren.function.SvString"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>台中市警察局轉置資料</title>
</head>
<body>
	<%
		//接收參數
		String unit = (String) request.getParameter("unit");//選擇的單元
		String node = (String) request.getParameter("node");//節點
		String dsid = (String) request.getParameter("dsid");//系統名稱
		String languageType = (String) request.getParameter("languageType");//語系
		//類別
		String serno_old = (String) request.getParameter("serno_old");
		String serno = (String) request.getParameter("serno");
		String[] serno_old2 = SvString.split(serno_old, ",");
		String[] serno2 = SvString.split(serno, ",");
		//轉換結果
		boolean check = false;
		//轉換結果訊息
		String result = "轉換失敗,原因不明";

		if (unit.equals("1")) {
			out.print("您將轉換的到警政新聞");
			TransferNewsData data = new TransferNewsData();

			// 設置轉資料所需參數
			// 資料表
			data.setDatatablename("News");
			data.setPostertablename("NewsPoster");
			data.setFiletablename("NewsFile");
			data.setResourcetablename("NewsResource");

			data.setNodeId(node);// 節點
			data.setDsid(dsid);// 系統名稱
			data.setLanguageType(languageType);// 語系
			// 類別序號
			ArrayList<String> classSerno = new ArrayList<String>();
			// 類別名稱
			ArrayList<String> className = new ArrayList<String>();
			// 分類
			for (int i = 0; i < serno_old2.length; i++) {
				className.add(serno_old2[i]);
				classSerno.add(serno2[i]);
			}

			data.setClassName(className);
			data.setClassSerno(classSerno);
			// 設置轉資料所需參數 END
			ArrayList<Tcpolice> lists = data.transform_in();
			if (lists != null && lists.size() > 0) {
				check = data.transform_out(lists);
			}
			if (check == true) {
				result = "新增成功!";
			} else {
				result = "新增失敗!失敗原因:" + data.getErrorMsg();
			}
		} else if (unit.equals("4")) {
			out.print("您將轉換到人事公告");
			TransferNewsData data = new TransferNewsData();

			// 設置轉資料所需參數
			// 資料表
			data.setDatatablename("Multis");
			data.setPostertablename("MultisPoster");
			data.setFiletablename("MultisFile");
			data.setResourcetablename("MultisResource");

			data.setNodeId(node);// 節點
			data.setDsid(dsid);// 系統名稱
			data.setLanguageType(languageType);// 語系
			// 類別序號
			ArrayList<String> classSerno = new ArrayList<String>();
			// 類別名稱
			ArrayList<String> className = new ArrayList<String>();
			// 分類
			for (int i = 0; i < serno_old2.length; i++) {
				className.add(serno_old2[i]);
				classSerno.add(serno2[i]);
			}

			data.setClassName(className);
			data.setClassSerno(classSerno);
			// 設置轉資料所需參數 END
			ArrayList<Tcpolice> lists = data.transform_in();
			if (lists != null && lists.size() > 0) {
				check = data.transform_out(lists);
			}
			if (check == true) {
				result = "新增成功!";
			} else {
				result = "新增失敗!失敗原因:" + data.getErrorMsg();
			}
		} else if (unit.equals("5")) {
			out.print("您將轉換到招標資訊");
			TransferNewsData data = new TransferNewsData();

			// 設置轉資料所需參數
			// 資料表
			data.setDatatablename("Multis");
			data.setPostertablename("MultisPoster");
			data.setFiletablename("MultisFile");
			data.setResourcetablename("MultisResource");

			data.setNodeId(node);// 節點
			data.setDsid(dsid);// 系統名稱
			data.setLanguageType(languageType);// 語系
			// 類別序號
			ArrayList<String> classSerno = new ArrayList<String>();
			// 類別名稱
			ArrayList<String> className = new ArrayList<String>();
			// 分類
			for (int i = 0; i < serno_old2.length; i++) {
				className.add(serno_old2[i]);
				classSerno.add(serno2[i]);
			}

			data.setClassName(className);
			data.setClassSerno(classSerno);
			// 設置轉資料所需參數 END
			ArrayList<Tcpolice> lists = data.transform_in();
			if (lists != null && lists.size() > 0) {
				check = data.transform_out(lists);
			}
			if (check == true) {
				result = "新增成功!";
			} else {
				result = "新增失敗!失敗原因:" + data.getErrorMsg();
			}
		} else if (unit.equals("2")) {
			out.print("您將轉換的到活動訊息");
			TransferActivityData data = new TransferActivityData();

			// 設置轉資料所需參數
			// 資料表
			data.setDatatablename("Activity");
			data.setPostertablename("ActivityPoster");
			data.setFiletablename("ActivityFile");
			data.setResourcetablename("ActivityResource");

			data.setNodeId(node);// 節點
			data.setDsid(dsid);// 系統名稱
			data.setLanguageType(languageType);// 語系

			// 類別序號
			ArrayList<String> classSerno = new ArrayList<String>();
			// 類別名稱
			ArrayList<String> className = new ArrayList<String>();
			// 分類
			for (int i = 0; i < serno_old2.length; i++) {
				className.add(serno_old2[i]);
				classSerno.add(serno2[i]);
			}

			data.setClassName(className);
			data.setClassSerno(classSerno);
			// 設置轉資料所需參數 END
			ArrayList<Tcpolice> lists = data.transform_in();
			if (lists != null && lists.size() > 0) {
				check = data.transform_out(lists);
			}
			if (check == true) {
				result = "新增成功!";
			} else {
				result = "新增失敗!失敗原因:" + data.getErrorMsg();
			}

		} else if (unit.equals("3")) {
			out.print("您將轉換的到忠愛園地");
			TransferLoyaltyData data = new TransferLoyaltyData();

			// 設置轉資料所需參數
			// 資料表
			data.setDatatablename("Loyalty");
			data.setPostertablename("LoyaltyPoster");
			data.setFiletablename("LoyaltyFile");
			data.setResourcetablename("LoyaltyResource");

			data.setNodeId(node);// 節點
			data.setDsid(dsid);// 系統名稱
			data.setLanguageType(languageType);// 語系

			// 類別序號
			ArrayList<String> classSerno = new ArrayList<String>();
			// 類別名稱
			ArrayList<String> className = new ArrayList<String>();
			// 分類
			for (int i = 0; i < serno_old2.length; i++) {
				className.add(serno_old2[i]);
				classSerno.add(serno2[i]);
			}

			data.setClassName(className);
			data.setClassSerno(classSerno);
			// 設置轉資料所需參數 END
			ArrayList<Tcpolice> lists = data.transform_in();
			if (lists != null && lists.size() > 0) {
				check = data.transform_out(lists);
			}
			if (check == true) {
				result = "新增成功!";
			} else {
				result = "新增失敗!失敗原因:" + data.getErrorMsg();
			}

		} else if (unit.equals("6")) {
			out.print("您將轉換的到常見問答");
			TransferFAQData data = new TransferFAQData();

			// 設置轉資料所需參數
			// 資料表
			data.setDatatablename("FAQ");
			data.setPostertablename("FAQPoster");
			data.setFiletablename("FAQFile");
			data.setResourcetablename("FAQResource");

			data.setNodeId(node);// 節點
			data.setDsid(dsid);// 系統名稱
			data.setLanguageType(languageType);// 語系

			// 類別序號
			ArrayList<String> classSerno = new ArrayList<String>();
			// 類別名稱
			ArrayList<String> className = new ArrayList<String>();
			// 分類
			for (int i = 0; i < serno_old2.length; i++) {
				className.add(serno_old2[i]);
				classSerno.add(serno2[i]);
			}

			data.setClassName(className);
			data.setClassSerno(classSerno);
			// 設置轉資料所需參數 END
			ArrayList<Tcpolice> lists = data.transform_in();
			if (lists != null && lists.size() > 0) {
				check = data.transform_out(lists);
			}
			if (check == true) {
				result = "新增成功!";
			} else {
				result = "新增失敗!失敗原因:" + data.getErrorMsg();
			}

		} else if (unit.equals("7")) {
			out.print("您將轉換的到表單下載");
			TransferDownloadData data = new TransferDownloadData();

			// 設置轉資料所需參數
			// 資料表
			data.setDatatablename("Download");
			data.setPostertablename("DownloadPoster");
			data.setFiletablename("DownloadFile");
			data.setResourcetablename("DownloadResource");

			data.setNodeId(node);// 節點
			data.setDsid(dsid);// 系統名稱
			data.setLanguageType(languageType);// 語系

			// 類別序號
			ArrayList<String> classSerno = new ArrayList<String>();
			// 類別名稱
			ArrayList<String> className = new ArrayList<String>();
			// 分類
			for (int i = 0; i < serno_old2.length; i++) {
				className.add(serno_old2[i]);
				classSerno.add(serno2[i]);
			}

			data.setClassName(className);
			data.setClassSerno(classSerno);
			// 設置轉資料所需參數 END
			ArrayList<Tcpolice> lists = data.transform_in();
			if (lists != null && lists.size() > 0) {
				check = data.transform_out(lists);
			}
			if (check == true) {
				result = "新增成功!";
			} else {
				result = "新增失敗!失敗原因:" + data.getErrorMsg();
			}

		} else if (unit.equals("8") || unit.equals("9") || unit.equals("10") || unit.equals("11")
				|| unit.equals("12") || unit.equals("13") || unit.equals("15") || unit.equals("16")
				|| unit.equals("22")) {
			out.print("您將轉換的到宣導專區中的某單元");
			TransferPublicizeData data = new TransferPublicizeData();

			// 設置轉資料所需參數
			// 資料表
			data.setDatatablename("Publicize");
			data.setPostertablename("PublicizePoster");
			data.setFiletablename("PublicizeFile");
			data.setResourcetablename("PublicizeResource");

			data.setNodeId(node);// 節點
			data.setDsid(dsid);// 系統名稱
			data.setLanguageType(languageType);// 語系

			// 類別序號
			ArrayList<String> classSerno = new ArrayList<String>();
			// 類別名稱
			ArrayList<String> className = new ArrayList<String>();
			// 分類
			for (int i = 0; i < serno_old2.length; i++) {
				className.add(serno_old2[i]);
				classSerno.add(serno2[i]);
			}

			data.setClassName(className);
			data.setClassSerno(classSerno);
			// 設置轉資料所需參數 END
			ArrayList<Tcpolice> lists = data.transform_in();
			if (lists != null && lists.size() > 0) {
				check = data.transform_out(lists);
			}
			if (check == true) {
				result = "新增成功!";
			} else {
				result = "新增失敗!失敗原因:" + data.getErrorMsg();
			}

		} else if (unit.equals("14")) {
			out.print("您將轉換到性別主流化");
			TransferNewsData data = new TransferNewsData();

			// 設置轉資料所需參數
			// 資料表
			data.setDatatablename("InfoPublic");
			data.setPostertablename("InfoPublicPoster");
			data.setFiletablename("InfoPublicFile");
			data.setResourcetablename("InfoPublicResource");

			data.setNodeId(node);// 節點
			data.setDsid(dsid);// 系統名稱
			data.setLanguageType(languageType);// 語系
			// 類別序號
			ArrayList<String> classSerno = new ArrayList<String>();
			// 類別名稱
			ArrayList<String> className = new ArrayList<String>();
			// 分類
			for (int i = 0; i < serno_old2.length; i++) {
				className.add(serno_old2[i]);
				classSerno.add(serno2[i]);
			}

			data.setClassName(className);
			data.setClassSerno(classSerno);
			// 設置轉資料所需參數 END
			ArrayList<Tcpolice> lists = data.transform_in();
			if (lists != null && lists.size() > 0) {
				check = data.transform_out(lists);
			}
			if (check == true) {
				result = "新增成功!";
			} else {
				result = "新增失敗!失敗原因:" + data.getErrorMsg();
			}
		} else if (unit.equals("17")) {
			out.print("您將轉換到人事服務");
			TransferNewsData data = new TransferNewsData();

			// 設置轉資料所需參數
			// 資料表
			data.setDatatablename("Multis");
			data.setPostertablename("MultisPoster");
			data.setFiletablename("MultisFile");
			data.setResourcetablename("MultisResource");

			data.setNodeId(node);// 節點
			data.setDsid(dsid);// 系統名稱
			data.setLanguageType(languageType);// 語系
			// 類別序號
			ArrayList<String> classSerno = new ArrayList<String>();
			// 類別名稱
			ArrayList<String> className = new ArrayList<String>();
			// 分類
			for (int i = 0; i < serno_old2.length; i++) {
				className.add(serno_old2[i]);
				classSerno.add(serno2[i]);
			}

			data.setClassName(className);
			data.setClassSerno(classSerno);
			// 設置轉資料所需參數 END
			ArrayList<Tcpolice> lists = data.transform_in();
			if (lists != null && lists.size() > 0) {
				check = data.transform_out(lists);
			}
			if (check == true) {
				result = "新增成功!";
			} else {
				result = "新增失敗!失敗原因:" + data.getErrorMsg();
			}
		} else if (unit.equals("19")) {
			out.print("您將轉換到法規查詢");
			TransferNewsData data = new TransferNewsData();

			// 設置轉資料所需參數
			// 資料表
			data.setDatatablename("Multis");
			data.setPostertablename("MultisPoster");
			data.setFiletablename("MultisFile");
			data.setResourcetablename("MultisResource");

			data.setNodeId(node);// 節點
			data.setDsid(dsid);// 系統名稱
			data.setLanguageType(languageType);// 語系
			// 類別序號
			ArrayList<String> classSerno = new ArrayList<String>();
			// 類別名稱
			ArrayList<String> className = new ArrayList<String>();
			// 分類
			for (int i = 0; i < serno_old2.length; i++) {
				className.add(serno_old2[i]);
				classSerno.add(serno2[i]);
			}

			data.setClassName(className);
			data.setClassSerno(classSerno);
			// 設置轉資料所需參數 END
			ArrayList<Tcpolice> lists = data.transform_in();
			if (lists != null && lists.size() > 0) {
				check = data.transform_out(lists);
			}
			if (check == true) {
				result = "新增成功!";
			} else {
				result = "新增失敗!失敗原因:" + data.getErrorMsg();
			}
		} else if (unit.equals("20")) {
			out.print("您將轉換到線上申辦");
			TransferNewsData data = new TransferNewsData();

			// 設置轉資料所需參數
			// 資料表
			data.setDatatablename("Multis");
			data.setPostertablename("MultisPoster");
			data.setFiletablename("MultisFile");
			data.setResourcetablename("MultisResource");

			data.setNodeId(node);// 節點
			data.setDsid(dsid);// 系統名稱
			data.setLanguageType(languageType);// 語系
			// 類別序號
			ArrayList<String> classSerno = new ArrayList<String>();
			// 類別名稱
			ArrayList<String> className = new ArrayList<String>();
			// 分類
			for (int i = 0; i < serno_old2.length; i++) {
				className.add(serno_old2[i]);
				classSerno.add(serno2[i]);
			}

			data.setClassName(className);
			data.setClassSerno(classSerno);
			// 設置轉資料所需參數 END
			ArrayList<Tcpolice> lists = data.transform_in();
			if (lists != null && lists.size() > 0) {
				check = data.transform_out(lists);
			}
			if (check == true) {
				result = "新增成功!";
			} else {
				result = "新增失敗!失敗原因:" + data.getErrorMsg();
			}
		} else if (unit.equals("21")) {
			out.print("您將轉換到施政計畫");
			TransferNewsData data = new TransferNewsData();

			// 設置轉資料所需參數
			// 資料表
			data.setDatatablename("InfoPublic");
			data.setPostertablename("InfoPublicPoster");
			data.setFiletablename("InfoPublicFile");
			data.setResourcetablename("InfoPublicResource");

			data.setNodeId(node);// 節點
			data.setDsid(dsid);// 系統名稱
			data.setLanguageType(languageType);// 語系
			// 類別序號
			ArrayList<String> classSerno = new ArrayList<String>();
			// 類別名稱
			ArrayList<String> className = new ArrayList<String>();
			// 分類
			for (int i = 0; i < serno_old2.length; i++) {
				className.add(serno_old2[i]);
				classSerno.add(serno2[i]);
			}

			data.setClassName(className);
			data.setClassSerno(classSerno);
			// 設置轉資料所需參數 END
			ArrayList<Tcpolice> lists = data.transform_in();
			if (lists != null && lists.size() > 0) {
				check = data.transform_out(lists);
			}
			if (check == true) {
				result = "新增成功!";
			} else {
				result = "新增失敗!失敗原因:" + data.getErrorMsg();
			}
		}
	%>
	<div><%=result%></div>
</body>
</html>