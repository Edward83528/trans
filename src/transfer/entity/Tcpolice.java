package transfer.entity;

public class Tcpolice {

	// 新版
	private String Serno = null;
	private String PubUnitDN = null;
	private String PubUnitName = null;
	private String Subject = null;
	private String SecSubject = null;
	private String DetailContent = null;
	private String PosterDate = null;
	private String CloseDate = null;
	private String StartUsing = null;
	private String LiaisonPer = null;
	private String LiaisonTel = null;
	private String LiaisonFax = null;
	private String LiaisonEmail = null;
	private String IsExamine = null;
	private String ExamineDate = null;
	private String ExamineName = null;
	private int ReadNumber = 0;
	private String Audience = null;
	private String SubjectID = null;
	private String SubjectClass = null;
	private String AdministationID = null;
	private String AdministationClass = null;
	private String ServiceID = null;
	private String ServiceClass = null;
	private String CreateUid = null;
	private String CreateName = null;
	private String CreateDate = null;
	private String PostUid = null;
	private String PostName = null;
	private String UpdateDate = null;
	private String DSID = null;
	private String LanguageType = null;
	private String MetaKeyword = null;
	private String SubjectTTSID = null;
	private String SubjectTTSFile = null;
	private String DetailContentTTSID = null;
	private String DetailContentTTSFile = null;
	private String APINumber = null;
	private String TableName = null;
	private String OU = null;
	// 新版發布單位
	private String DetailNo = null;
	private String Mserno = null;
	private String WebSiteDN = null;
	private String WebSiteName = null;
	private String WebSiteExam = null;
	private String WebSiteExamDate = null;
	private String WebSiteExamName = null;
	private String IsTop = null;
	private String TopDate = null;
	private String DataSort = null;
	// 新版分類
	private String GroupID = null;
	private String ClassName = null;
	// 新版相關連結
	private String RelateUrl = null;
	private String RelateName = null;
	private String Target = null;
	// 新版附件
	private String Flag = null;
	private String ClientFile = null;
	private String ServerFile = null;
	private String ExpFile = null;
	private String ImageMagick = null;
	private String ServerFilePDF = null;
	private String PDFFlag = null;
	private String ServerFileODF = null;
	private String ODFFlag = null;
	private String CMediaFile = null;
	private String SMediaFile = null;
	private String ExpMediaFile = null;
	private String FlvFile = null;
	private int FileSize = 0;

	// 舊的
	private String icuitem = null;
	private String ibaseDsd = null;
	private String ictunit = null;// 單元ID
	private String stitle = null;
	private String idept = null;// 發布單位
	private String xbody = null;
	private String fctupublic = null;
	private String avBegin = null;
	private String avEnd = null;
	private String ieditor = null;
	private String icreator = null;
	private String deditDate = null;
	private String topCat = null;// 資料所屬小分類
	private String xkeyword = null;
	private String ximportant = null;
	private String xurl = null;
	private String xnewWindow = null;
	private String xpostDate = null;
	private String xpostDateEnd = null;
	private String createdDate = null;
	private String fileDownLoad = null;
	private String refId = null;
	private String showType = null;
	private String ximgFile = null;
	private String showInCalendar = null;

	public String getSerno() {
		return Serno;
	}

	public void setSerno(String serno) {
		Serno = serno;
	}

	public String getPubUnitDN() {
		return PubUnitDN;
	}

	public void setPubUnitDN(String pubUnitDN) {
		PubUnitDN = pubUnitDN;
	}

	public String getSubject() {
		return Subject;
	}

	public void setSubject(String subject) {
		Subject = subject;
	}

	public String getSecSubject() {
		return SecSubject;
	}

	public void setSecSubject(String secSubject) {
		SecSubject = secSubject;
	}

	public String getDetailContent() {
		return DetailContent;
	}

	public void setDetailContent(String detailContent) {
		DetailContent = detailContent;
	}

	public String getPosterDate() {
		return PosterDate;
	}

	public void setPosterDate(String posterDate) {
		PosterDate = posterDate;
	}

	public String getCloseDate() {
		return CloseDate;
	}

	public void setCloseDate(String closeDate) {
		CloseDate = closeDate;
	}

	public String getStartUsing() {
		return StartUsing;
	}

	public void setStartUsing(String startUsing) {
		StartUsing = startUsing;
	}

	public String getLiaisonPer() {
		return LiaisonPer;
	}

	public void setLiaisonPer(String liaisonPer) {
		LiaisonPer = liaisonPer;
	}

	public String getLiaisonTel() {
		return LiaisonTel;
	}

	public void setLiaisonTel(String liaisonTel) {
		LiaisonTel = liaisonTel;
	}

	public String getLiaisonFax() {
		return LiaisonFax;
	}

	public void setLiaisonFax(String liaisonFax) {
		LiaisonFax = liaisonFax;
	}

	public String getLiaisonEmail() {
		return LiaisonEmail;
	}

	public void setLiaisonEmail(String liaisonEmail) {
		LiaisonEmail = liaisonEmail;
	}

	public String getIsExamine() {
		return IsExamine;
	}

	public void setIsExamine(String isExamine) {
		IsExamine = isExamine;
	}

	public String getExamineDate() {
		return ExamineDate;
	}

	public void setExamineDate(String examineDate) {
		ExamineDate = examineDate;
	}

	public String getExamineName() {
		return ExamineName;
	}

	public void setExamineName(String examineName) {
		ExamineName = examineName;
	}

	public int getReadNumber() {
		return ReadNumber;
	}

	public void setReadNumber(int readNumber) {
		ReadNumber = readNumber;
	}

	public String getAudience() {
		return Audience;
	}

	public void setAudience(String audience) {
		Audience = audience;
	}

	public String getSubjectID() {
		return SubjectID;
	}

	public void setSubjectID(String subjectID) {
		SubjectID = subjectID;
	}

	public String getSubjectClass() {
		return SubjectClass;
	}

	public void setSubjectClass(String subjectClass) {
		SubjectClass = subjectClass;
	}

	public String getAdministationID() {
		return AdministationID;
	}

	public void setAdministationID(String administationID) {
		AdministationID = administationID;
	}

	public String getAdministationClass() {
		return AdministationClass;
	}

	public void setAdministationClass(String administationClass) {
		AdministationClass = administationClass;
	}

	public String getServiceID() {
		return ServiceID;
	}

	public void setServiceID(String serviceID) {
		ServiceID = serviceID;
	}

	public String getServiceClass() {
		return ServiceClass;
	}

	public void setServiceClass(String serviceClass) {
		ServiceClass = serviceClass;
	}

	public String getCreateUid() {
		return CreateUid;
	}

	public void setCreateUid(String createUid) {
		CreateUid = createUid;
	}

	public String getCreateName() {
		return CreateName;
	}

	public void setCreateName(String createName) {
		CreateName = createName;
	}

	public String getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(String createDate) {
		CreateDate = createDate;
	}

	public String getPostUid() {
		return PostUid;
	}

	public void setPostUid(String postUid) {
		PostUid = postUid;
	}

	public String getPostName() {
		return PostName;
	}

	public void setPostName(String postName) {
		PostName = postName;
	}

	public String getUpdateDate() {
		return UpdateDate;
	}

	public void setUpdateDate(String updateDate) {
		UpdateDate = updateDate;
	}

	public String getDSID() {
		return DSID;
	}

	public void setDSID(String dSID) {
		DSID = dSID;
	}

	public String getLanguageType() {
		return LanguageType;
	}

	public void setLanguageType(String languageType) {
		LanguageType = languageType;
	}

	public String getMetaKeyword() {
		return MetaKeyword;
	}

	public void setMetaKeyword(String metaKeyword) {
		MetaKeyword = metaKeyword;
	}

	public String getSubjectTTSID() {
		return SubjectTTSID;
	}

	public void setSubjectTTSID(String subjectTTSID) {
		SubjectTTSID = subjectTTSID;
	}

	public String getSubjectTTSFile() {
		return SubjectTTSFile;
	}

	public void setSubjectTTSFile(String subjectTTSFile) {
		SubjectTTSFile = subjectTTSFile;
	}

	public String getDetailContentTTSID() {
		return DetailContentTTSID;
	}

	public void setDetailContentTTSID(String detailContentTTSID) {
		DetailContentTTSID = detailContentTTSID;
	}

	public String getDetailContentTTSFile() {
		return DetailContentTTSFile;
	}

	public void setDetailContentTTSFile(String detailContentTTSFile) {
		DetailContentTTSFile = detailContentTTSFile;
	}

	public String getAPINumber() {
		return APINumber;
	}

	public void setAPINumber(String aPINumber) {
		APINumber = aPINumber;
	}

	public String getTableName() {
		return TableName;
	}

	public void setTableName(String tableName) {
		TableName = tableName;
	}

	public String getIcuitem() {
		return icuitem;
	}

	public void setIcuitem(String icuitem) {
		this.icuitem = icuitem;
	}

	public String getIbaseDsd() {
		return ibaseDsd;
	}

	public void setIbaseDsd(String ibaseDsd) {
		this.ibaseDsd = ibaseDsd;
	}

	public String getIctunit() {
		return ictunit;
	}

	public void setIctunit(String ictunit) {
		this.ictunit = ictunit;
	}

	public String getStitle() {
		return stitle;
	}

	public void setStitle(String stitle) {
		this.stitle = stitle;
	}

	public String getIdept() {
		return idept;
	}

	public void setIdept(String idept) {
		this.idept = idept;
	}

	public String getXbody() {
		return xbody;
	}

	public void setXbody(String xbody) {
		this.xbody = xbody;
	}

	public String getFctupublic() {
		return fctupublic;
	}

	public void setFctupublic(String fctupublic) {
		this.fctupublic = fctupublic;
	}

	public String getAvBegin() {
		return avBegin;
	}

	public void setAvBegin(String avBegin) {
		this.avBegin = avBegin;
	}

	public String getAvEnd() {
		return avEnd;
	}

	public void setAvEnd(String avEnd) {
		this.avEnd = avEnd;
	}

	public String getIeditor() {
		return ieditor;
	}

	public void setIeditor(String ieditor) {
		this.ieditor = ieditor;
	}

	public String getIcreator() {
		return icreator;
	}

	public void setIcreator(String icreator) {
		this.icreator = icreator;
	}

	public String getDeditDate() {
		return deditDate;
	}

	public void setDeditDate(String deditDate) {
		this.deditDate = deditDate;
	}

	public String getTopCat() {
		return topCat;
	}

	public void setTopCat(String topCat) {
		this.topCat = topCat;
	}

	public String getXkeyword() {
		return xkeyword;
	}

	public void setXkeyword(String xkeyword) {
		this.xkeyword = xkeyword;
	}

	public String getXimportant() {
		return ximportant;
	}

	public void setXimportant(String ximportant) {
		this.ximportant = ximportant;
	}

	public String getXurl() {
		return xurl;
	}

	public void setXurl(String xurl) {
		this.xurl = xurl;
	}

	public String getXnewWindow() {
		return xnewWindow;
	}

	public void setXnewWindow(String xnewWindow) {
		this.xnewWindow = xnewWindow;
	}

	public String getXpostDate() {
		return xpostDate;
	}

	public void setXpostDate(String xpostDate) {
		this.xpostDate = xpostDate;
	}

	public String getXpostDateEnd() {
		return xpostDateEnd;
	}

	public void setXpostDateEnd(String xpostDateEnd) {
		this.xpostDateEnd = xpostDateEnd;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getFileDownLoad() {
		return fileDownLoad;
	}

	public void setFileDownLoad(String fileDownLoad) {
		this.fileDownLoad = fileDownLoad;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getXimgFile() {
		return ximgFile;
	}

	public void setXimgFile(String ximgFile) {
		this.ximgFile = ximgFile;
	}

	public String getShowInCalendar() {
		return showInCalendar;
	}

	public void setShowInCalendar(String showInCalendar) {
		this.showInCalendar = showInCalendar;
	}

	public String getDetailNo() {
		return DetailNo;
	}

	public void setDetailNo(String detailNo) {
		DetailNo = detailNo;
	}

	public String getMserno() {
		return Mserno;
	}

	public void setMserno(String mserno) {
		Mserno = mserno;
	}

	public String getWebSiteDN() {
		return WebSiteDN;
	}

	public void setWebSiteDN(String webSiteDN) {
		WebSiteDN = webSiteDN;
	}

	public String getWebSiteName() {
		return WebSiteName;
	}

	public void setWebSiteName(String webSiteName) {
		WebSiteName = webSiteName;
	}

	public String getWebSiteExam() {
		return WebSiteExam;
	}

	public void setWebSiteExam(String webSiteExam) {
		WebSiteExam = webSiteExam;
	}

	public String getWebSiteExamDate() {
		return WebSiteExamDate;
	}

	public void setWebSiteExamDate(String webSiteExamDate) {
		WebSiteExamDate = webSiteExamDate;
	}

	public String getWebSiteExamName() {
		return WebSiteExamName;
	}

	public void setWebSiteExamName(String webSiteExamName) {
		WebSiteExamName = webSiteExamName;
	}

	public String getIsTop() {
		return IsTop;
	}

	public void setIsTop(String isTop) {
		IsTop = isTop;
	}

	public String getTopDate() {
		return TopDate;
	}

	public void setTopDate(String topDate) {
		TopDate = topDate;
	}

	public String getDataSort() {
		return DataSort;
	}

	public void setDataSort(String dataSort) {
		DataSort = dataSort;
	}

	public String getGroupID() {
		return GroupID;
	}

	public void setGroupID(String groupID) {
		GroupID = groupID;
	}

	public String getClassName() {
		return ClassName;
	}

	public void setClassName(String className) {
		ClassName = className;
	}

	public String getRelateUrl() {
		return RelateUrl;
	}

	public void setRelateUrl(String relateUrl) {
		RelateUrl = relateUrl;
	}

	public String getRelateName() {
		return RelateName;
	}

	public void setRelateName(String relateName) {
		RelateName = relateName;
	}

	public String getTarget() {
		return Target;
	}

	public void setTarget(String target) {
		Target = target;
	}

	public String getFlag() {
		return Flag;
	}

	public void setFlag(String flag) {
		Flag = flag;
	}

	public String getClientFile() {
		return ClientFile;
	}

	public void setClientFile(String clientFile) {
		ClientFile = clientFile;
	}

	public String getServerFile() {
		return ServerFile;
	}

	public void setServerFile(String serverFile) {
		ServerFile = serverFile;
	}

	public String getExpFile() {
		return ExpFile;
	}

	public void setExpFile(String expFile) {
		ExpFile = expFile;
	}

	public String getImageMagick() {
		return ImageMagick;
	}

	public void setImageMagick(String imageMagick) {
		ImageMagick = imageMagick;
	}

	public String getServerFilePDF() {
		return ServerFilePDF;
	}

	public void setServerFilePDF(String serverFilePDF) {
		ServerFilePDF = serverFilePDF;
	}

	public String getPDFFlag() {
		return PDFFlag;
	}

	public void setPDFFlag(String pDFFlag) {
		PDFFlag = pDFFlag;
	}

	public String getServerFileODF() {
		return ServerFileODF;
	}

	public void setServerFileODF(String serverFileODF) {
		ServerFileODF = serverFileODF;
	}

	public String getODFFlag() {
		return ODFFlag;
	}

	public void setODFFlag(String oDFFlag) {
		ODFFlag = oDFFlag;
	}

	public String getCMediaFile() {
		return CMediaFile;
	}

	public void setCMediaFile(String cMediaFile) {
		CMediaFile = cMediaFile;
	}

	public String getSMediaFile() {
		return SMediaFile;
	}

	public void setSMediaFile(String sMediaFile) {
		SMediaFile = sMediaFile;
	}

	public String getExpMediaFile() {
		return ExpMediaFile;
	}

	public void setExpMediaFile(String expMediaFile) {
		ExpMediaFile = expMediaFile;
	}

	public String getFlvFile() {
		return FlvFile;
	}

	public void setFlvFile(String flvFile) {
		FlvFile = flvFile;
	}

	public int getFileSize() {
		return FileSize;
	}

	public void setFileSize(int fileSize) {
		FileSize = fileSize;
	}

	public String getPubUnitName() {
		return PubUnitName;
	}

	public void setPubUnitName(String pubUnitName) {
		PubUnitName = pubUnitName;
	}

	public String getOU() {
		return OU;
	}

	public void setOU(String oU) {
		OU = oU;
	}

}
