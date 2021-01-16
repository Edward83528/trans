<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>台中市警察局轉置資料</title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<!-- ajax送表單指令用到 -->
<script type="text/javascript" src="js/jquery.form.js" /></script>
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script>
<style type="text/css">
.container {
	background-color: #FFD9EC;
}

.importent {
	color: #FF0000;
}
</style>
</head>
<script type="text/javascript">
	function getClassdata() {
		var $classTable = $("#classTable");
		var options = {
			url : "jsonClass.jsp?type=A",
			type : "POST",
			success : function(msg) {
				ary_msg = msg.split("||");
				var json = JSON.parse(ary_msg[1]);
				var num = document.getElementById("classTable").rows.length;
				for (i = 0; i < num - 1; i++) {
					document.getElementById("classTable").deleteRow(-1);
				}
				for (var i = 0; i < json.classSerno_old.length; i++) {
					$classTable.append("<tr>" + "<td data-th=\"舊類別代碼\">"
							+ json.classSerno_old[i] + "<td data-th=\"舊類別名稱\">"
							+ json.className_old[i] + "</td>" + "</tr>");
				}

			}
		};
		$('#mform').ajaxSubmit(options);

	}
	function getClassdata2() {
		var $classTable2 = $("#classTable2");
		var options = {
			url : "jsonClass.jsp?type=B",
			type : "POST",
			success : function(msg) {
				ary_msg = msg.split("||");
				var json = JSON.parse(ary_msg[1]);
				var num = document.getElementById("classTable2").rows.length;
				for (i = 0; i < num - 1; i++) {
					document.getElementById("classTable2").deleteRow(-1);
				}
				for (var i = 0; i < json.classSerno_new.length; i++) {
					$classTable2.append("<tr>" + "<td data-th=\"新類別代碼\">"
							+ json.classSerno_new[i] + "<td data-th=\"新類別名稱\">"
							+ json.className_new[i] + "</td>"
							+ "<td data-th=\"系統名稱\">" + json.DSID[i] + "</td>"
							+ "</tr>");
				}

			}
		};
		$('#mform').ajaxSubmit(options);

	}
</script>
<body>

	<br>
	<form action="do.jsp" name="mform" id="mform">
		<div class="container">
			<div class="row col-md-6 col-md-offset-3">
				<h3>台中市警察局轉置資料(請跟著步驟做)</h3>
			</div>
			<div class="row">
				<div class="col-sm-6">
					<label class="control-label ">Step1(要匯哪個單元):</label> <select name="unit" id="unit" onchange="getClassdata()">
						<%-- 訊息 --%>
						<option value="1">警政新聞</option>
						<option value="2">活動訊息</option>
						<option value="3">忠愛園地</option>
						<option value="4">人事公告</option>
						<option value="5">招標資訊</option>
						<%-- 便民 --%>
						<option value="20">線上申辦</option>
						<option value="7">表單下載</option>
						<option value="6">常見問答</option>
						<option value="19">法規查詢</option>
						<%-- 宣導--%>
						<option value="8">春節治安</option>
						<option value="9">預防犯罪</option>
						<option value="10">交通宣導</option>
						<option value="11">少年安全</option>
						<option value="12">婦幼安全</option>
						<option value="13">社區治安</option>
						<option value="14">性別主流化</option>
						<option value="15">政令宣導(分局)</option>
						<option value="22">政令宣導(總局)</option>
						<option value="16">志工專區</option>
						<option value="17">人事服務</option>
						<%-- 資訊公開--%>
						<option value="21">施政計畫</option>

					</select> <span style="font-size: 10px;">&nbsp;&nbsp;註:選擇舊網站的單元</span>
				</div>

				<div class="col-sm-6">
					<label class="control-label ">Step2(是要匯哪一個網站): </label><select name="web" id="web" onchange="getClassdata2()">
						<option value="1">台中市警察局(總局網站)</option>
						<option value="22">台中市警察局(運動網站)</option>
						<option value="23">台中市警察局(兒童版)</option>
						<option value="2">第一分局網站</option>
						<option value="3">第二分局網站</option>
						<option value="4">第三分局網站</option>
						<option value="5">第四分局網站</option>
						<option value="6">第五分局網站</option>
						<option value="7">第六分局網站</option>
						<option value="8">豐原分局網站</option>
						<option value="9">霧峰分局網站</option>
						<option value="10">烏日分局網站</option>
						<option value="11">清水分局網站</option>
						<option value="12">大甲分局網站</option>
						<option value="13">太平分局網站</option>
						<option value="14">東勢分局網站</option>
						<option value="15">和平分局網站</option>
						<option value="16">保安警察大隊網站</option>
						<option value="17">刑事警察大隊網站</option>
						<option value="18">交通警察大隊網站</option>
						<option value="19">少年警察隊網站</option>
						<option value="20">少年輔導委員會網站</option>
						<option value="21">婦幼警察隊網站</option>
					</select> <span style="font-size: 10px;">&nbsp;&nbsp;註:選擇要匯哪個網站</span>
				</div>
			</div>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<h5 class="importent">註:Step1和Step2的組合選擇只是去撈類別資料以便做對應,所以請注意 &nbsp;&nbsp; "要匯哪個單元是看Step1的選擇與Step3 節點的值搭配做決定"</h5>
			<hr>
			<div class="row">
				<div class="col-sm-4">
					<label class="control-label ">Step3(節點):</label> <input class="form-control" type="text" name="node" /><span style="font-size: 10px;">&nbsp;&nbsp;註:要去現行警局URL的ctNode 查值</span>
				</div>

				<div class="col-sm-4">
					<label class="control-label ">Step4(系統名稱)(可參考新類別資訊對照表的DSID值):</label> <input class="form-control" type="text" name="dsid" />
				</div>

				<div class="col-sm-4">
					<label class="control-label ">Step5(語系):</label> <input type="text" class="form-control" name="languageType" /><span style="font-size: 10px;">&nbsp;&nbsp;註:chinese或english或其他語系</span>
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col-sm-12">
					<label class="control-label ">Step6(填入多個類別-舊的代碼): </label><input class="form-control" type="text" name="serno_old" size="130" /> <br> <span style="font-size: 10px;">&nbsp;&nbsp;註:類別間用逗號分開,並要與新類別做對應</span>
				</div>
			</div>
			<hr>
			<div class="row">
				<div class="col-sm-12">
					<label class="control-label ">Step7(填入多個類別-新的代碼):</label> <input class="form-control" type="text" name="serno" size="130" /> <br> <span style="font-size: 10px;">&nbsp;&nbsp;註:類別間用逗號分開,並要與舊類別做對應</span>
				</div>
			</div>
			<hr>

			<div class="row col-md-2 col-md-offset-5">
				<div class="col-sm-12">
					<input class="btn btn-danger btn-lg" type="submit" value="轉置">
				</div>
			</div>
			<br> <br>
			<hr>
			<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;註:Step1和Step2一定都要變動一次類別資料才會正確

			<div class="row">
				<div class="col-sm-6">
					<fieldset>
						<legend>
							<label class="control-label ">舊類別資訊對照表</label>
						</legend>
						<table width="300" border="1" id="classTable" class="table table-hover">
							<tr>
								<th class="info">舊類別代碼</th>
								<th class="warning">舊類別名稱</th>
							</tr>
						</table>
					</fieldset>
				</div>
				<div class="col-sm-6">
					<fieldset>
						<legend>
							<label class="control-label ">新類別資訊對照表</label>
						</legend>

						<table width="300" border="1" id="classTable2" class="table table-hover">
							<tr>
								<th class="warning">新類別代碼</th>
								<th class="info">新類別名稱</th>
								<th class="success">系統名稱(DSID)</th>
							</tr>

						</table>
					</fieldset>
				</div>
			</div>
		</div>
	</form>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
</body>
</html>