package transfer.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Retrospect {

	private String OD_SERNO;

	private String OD_PUBUNITDN; // 發佈單位DN

	private String OD_SUBJECT; // 節目名稱

	private String OD_STATIONSERNO; // 收聽地區代碼

	private LocalDateTime OD_SHOWDATE; // 節目日期

	private String OD_SHOWTIME; // 播出時段

	private String OD_SECSUBJECT; // 主持人

	private String OD_VIDEOTITLE; // 影音標題

	private String OD_DETAILCONTENT; // 影音內容

	// 共通

	private LocalDateTime OD_POSTERDATE; // 發布日期

	private LocalDateTime OD_CLOSEDATE; // 發布截止日期

	private Boolean OD_STARTUSING; // 啟用

	private String OD_LIAISONPER; // 聯絡人姓名

	private String OD_LIAISONTEL; // 聯絡人電話

	private String OD_LIAISONFAX; // 聯絡人傳真

	private String OD_LIAISONEMAIL; // 聯絡人 email

	private Long OD_READNUMBER; // 點閱數

	private String OD_SUBJECTID; // 主題分類代碼

	private String OD_SUBJECTCLASS; // 主題分類名稱

	private String OD_ADMINISTATIONID; // 施政分類代碼

	private String OD_ADMINISTATIONCLASS; // 施政分類名稱

	private String OD_SERVICEID; // 服務分類代碼

	private String OD_SERVICECLASS; // 服務分類名稱

	private String OD_DSID; // 資料代碼

	private String OD_LANGUAGETYPE; // 語系

	private String OD_METAKEYWORD; // 關鍵字

	private String OD_VIEWUID; // 最後檢視者代碼

	private String OD_VIEWNAME; // 最後檢視者名稱

	private String OD_MSERNO; // 最後檢視日期

	private LocalDateTime OD_VIEWDATE; // 最後檢視日期

	private Boolean OD_ISEXAMINE; // 審核狀態

	private LocalDateTime OD_EXAMINEDATE; // 審核日期

	private String OD_EXAMINENAME; // 審核者

	private String OD_CREATEUID; // 建檔人員帳號

	private String OD_CREATENAME; // 建檔人員姓名

	private LocalDateTime OD_CREATEDATE; // 建檔日期

	private String OD_POSTUID; // 最後更新人員帳號

	private String OD_POSTNAME; // 最後更新人員姓名

	private LocalDateTime OD_UPDATEDATE; // 最後更新日期

	private ArrayList<Link> links;

	public String getOD_SERNO() {
		return OD_SERNO;
	}

	public void setOD_SERNO(String oD_SERNO) {
		OD_SERNO = oD_SERNO;
	}

	public String getOD_PUBUNITDN() {
		return OD_PUBUNITDN;
	}

	public void setOD_PUBUNITDN(String oD_PUBUNITDN) {
		OD_PUBUNITDN = oD_PUBUNITDN;
	}

	public String getOD_SUBJECT() {
		return OD_SUBJECT;
	}

	public void setOD_SUBJECT(String oD_SUBJECT) {
		OD_SUBJECT = oD_SUBJECT;
	}

	public String getOD_STATIONSERNO() {
		return OD_STATIONSERNO;
	}

	public void setOD_STATIONSERNO(String oD_STATIONSERNO) {
		OD_STATIONSERNO = oD_STATIONSERNO;
	}

	public LocalDateTime getOD_SHOWDATE() {
		return OD_SHOWDATE;
	}

	public void setOD_SHOWDATE(LocalDateTime oD_SHOWDATE) {
		OD_SHOWDATE = oD_SHOWDATE;
	}

	public String getOD_SHOWTIME() {
		return OD_SHOWTIME;
	}

	public void setOD_SHOWTIME(String oD_SHOWTIME) {
		OD_SHOWTIME = oD_SHOWTIME;
	}

	public String getOD_SECSUBJECT() {
		return OD_SECSUBJECT;
	}

	public void setOD_SECSUBJECT(String oD_SECSUBJECT) {
		OD_SECSUBJECT = oD_SECSUBJECT;
	}

	public String getOD_DETAILCONTENT() {
		return OD_DETAILCONTENT;
	}

	public void setOD_DETAILCONTENT(String oD_DETAILCONTENT) {
		OD_DETAILCONTENT = oD_DETAILCONTENT;
	}

	public LocalDateTime getOD_POSTERDATE() {
		return OD_POSTERDATE;
	}

	public void setOD_POSTERDATE(LocalDateTime oD_POSTERDATE) {
		OD_POSTERDATE = oD_POSTERDATE;
	}

	public LocalDateTime getOD_CLOSEDATE() {
		return OD_CLOSEDATE;
	}

	public void setOD_CLOSEDATE(LocalDateTime oD_CLOSEDATE) {
		OD_CLOSEDATE = oD_CLOSEDATE;
	}

	public Boolean getOD_STARTUSING() {
		return OD_STARTUSING;
	}

	public void setOD_STARTUSING(Boolean oD_STARTUSING) {
		OD_STARTUSING = oD_STARTUSING;
	}

	public String getOD_LIAISONPER() {
		return OD_LIAISONPER;
	}

	public void setOD_LIAISONPER(String oD_LIAISONPER) {
		OD_LIAISONPER = oD_LIAISONPER;
	}

	public String getOD_LIAISONTEL() {
		return OD_LIAISONTEL;
	}

	public void setOD_LIAISONTEL(String oD_LIAISONTEL) {
		OD_LIAISONTEL = oD_LIAISONTEL;
	}

	public String getOD_LIAISONFAX() {
		return OD_LIAISONFAX;
	}

	public void setOD_LIAISONFAX(String oD_LIAISONFAX) {
		OD_LIAISONFAX = oD_LIAISONFAX;
	}

	public String getOD_LIAISONEMAIL() {
		return OD_LIAISONEMAIL;
	}

	public void setOD_LIAISONEMAIL(String oD_LIAISONEMAIL) {
		OD_LIAISONEMAIL = oD_LIAISONEMAIL;
	}

	public Long getOD_READNUMBER() {
		return OD_READNUMBER;
	}

	public void setOD_READNUMBER(Long oD_READNUMBER) {
		OD_READNUMBER = oD_READNUMBER;
	}

	public String getOD_SUBJECTID() {
		return OD_SUBJECTID;
	}

	public void setOD_SUBJECTID(String oD_SUBJECTID) {
		OD_SUBJECTID = oD_SUBJECTID;
	}

	public String getOD_SUBJECTCLASS() {
		return OD_SUBJECTCLASS;
	}

	public void setOD_SUBJECTCLASS(String oD_SUBJECTCLASS) {
		OD_SUBJECTCLASS = oD_SUBJECTCLASS;
	}

	public String getOD_ADMINISTATIONID() {
		return OD_ADMINISTATIONID;
	}

	public void setOD_ADMINISTATIONID(String oD_ADMINISTATIONID) {
		OD_ADMINISTATIONID = oD_ADMINISTATIONID;
	}

	public String getOD_ADMINISTATIONCLASS() {
		return OD_ADMINISTATIONCLASS;
	}

	public void setOD_ADMINISTATIONCLASS(String oD_ADMINISTATIONCLASS) {
		OD_ADMINISTATIONCLASS = oD_ADMINISTATIONCLASS;
	}

	public String getOD_SERVICEID() {
		return OD_SERVICEID;
	}

	public void setOD_SERVICEID(String oD_SERVICEID) {
		OD_SERVICEID = oD_SERVICEID;
	}

	public String getOD_SERVICECLASS() {
		return OD_SERVICECLASS;
	}

	public void setOD_SERVICECLASS(String oD_SERVICECLASS) {
		OD_SERVICECLASS = oD_SERVICECLASS;
	}

	public String getOD_DSID() {
		return OD_DSID;
	}

	public void setOD_DSID(String oD_DSID) {
		OD_DSID = oD_DSID;
	}

	public String getOD_LANGUAGETYPE() {
		return OD_LANGUAGETYPE;
	}

	public void setOD_LANGUAGETYPE(String oD_LANGUAGETYPE) {
		OD_LANGUAGETYPE = oD_LANGUAGETYPE;
	}

	public String getOD_METAKEYWORD() {
		return OD_METAKEYWORD;
	}

	public void setOD_METAKEYWORD(String oD_METAKEYWORD) {
		OD_METAKEYWORD = oD_METAKEYWORD;
	}

	public String getOD_VIEWUID() {
		return OD_VIEWUID;
	}

	public void setOD_VIEWUID(String oD_VIEWUID) {
		OD_VIEWUID = oD_VIEWUID;
	}

	public String getOD_VIEWNAME() {
		return OD_VIEWNAME;
	}

	public void setOD_VIEWNAME(String oD_VIEWNAME) {
		OD_VIEWNAME = oD_VIEWNAME;
	}

	public String getOD_MSERNO() {
		return OD_MSERNO;
	}

	public void setOD_MSERNO(String oD_MSERNO) {
		OD_MSERNO = oD_MSERNO;
	}

	public LocalDateTime getOD_VIEWDATE() {
		return OD_VIEWDATE;
	}

	public void setOD_VIEWDATE(LocalDateTime oD_VIEWDATE) {
		OD_VIEWDATE = oD_VIEWDATE;
	}

	public Boolean getOD_ISEXAMINE() {
		return OD_ISEXAMINE;
	}

	public void setOD_ISEXAMINE(Boolean oD_ISEXAMINE) {
		OD_ISEXAMINE = oD_ISEXAMINE;
	}

	public LocalDateTime getOD_EXAMINEDATE() {
		return OD_EXAMINEDATE;
	}

	public void setOD_EXAMINEDATE(LocalDateTime oD_EXAMINEDATE) {
		OD_EXAMINEDATE = oD_EXAMINEDATE;
	}

	public String getOD_EXAMINENAME() {
		return OD_EXAMINENAME;
	}

	public void setOD_EXAMINENAME(String oD_EXAMINENAME) {
		OD_EXAMINENAME = oD_EXAMINENAME;
	}

	public String getOD_CREATEUID() {
		return OD_CREATEUID;
	}

	public void setOD_CREATEUID(String oD_CREATEUID) {
		OD_CREATEUID = oD_CREATEUID;
	}

	public String getOD_CREATENAME() {
		return OD_CREATENAME;
	}

	public void setOD_CREATENAME(String oD_CREATENAME) {
		OD_CREATENAME = oD_CREATENAME;
	}

	public LocalDateTime getOD_CREATEDATE() {
		return OD_CREATEDATE;
	}

	public void setOD_CREATEDATE(LocalDateTime oD_CREATEDATE) {
		OD_CREATEDATE = oD_CREATEDATE;
	}

	public String getOD_POSTUID() {
		return OD_POSTUID;
	}

	public void setOD_POSTUID(String oD_POSTUID) {
		OD_POSTUID = oD_POSTUID;
	}

	public String getOD_POSTNAME() {
		return OD_POSTNAME;
	}

	public void setOD_POSTNAME(String oD_POSTNAME) {
		OD_POSTNAME = oD_POSTNAME;
	}

	public LocalDateTime getOD_UPDATEDATE() {
		return OD_UPDATEDATE;
	}

	public void setOD_UPDATEDATE(LocalDateTime oD_UPDATEDATE) {
		OD_UPDATEDATE = oD_UPDATEDATE;
	}

	public ArrayList<Link> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<Link> links) {
		this.links = links;
	}

	public String getOD_VIDEOTITLE() {
		return OD_VIDEOTITLE;
	}

	public void setOD_VIDEOTITLE(String oD_VIDEOTITLE) {
		OD_VIDEOTITLE = oD_VIDEOTITLE;
	}

}
