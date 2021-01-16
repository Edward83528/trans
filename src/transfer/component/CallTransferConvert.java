package transfer.component;

import java.io.File;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

//轉檔程式
//JODConverter主要的功能是用來做各種檔案的轉換因為JODConverter是透過OpenOffice來做轉換, 所以使用前需要先安裝OpenOffice, 並且將OpenOffice的Service啟動, 才可以使用
//Step1: 安裝OpenOffice
//Step2: 啟動OpenOffice Service ->cd C:\Program Files (x86)\OpenOffice 4\program
//soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard
//Step3:將JODConverter的Jar檔放進專案中的Library
//Step4:執行本隻java程式
//Step5:執行完終止OpenOffice Service  ->  tskill soffice
public class CallTransferConvert {
	String errorMessage = null;
	String ServerFilePDF = null;
	String ServerFileODF = null;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getServerFilePDF() {
		return ServerFilePDF;
	}

	public void setServerFilePDF(String serverFilePDF) {
		ServerFilePDF = serverFilePDF;
	}

	public String getServerFileODF() {
		return ServerFileODF;
	}

	public void setServerFileODF(String serverFileODF) {
		ServerFileODF = serverFileODF;
	}

	public static void main(String[] args) {
		// CallTransferConvert call = new CallTransferConvert();
		// boolean check = call.convert("D:\\a.docx", "D:\\a.pdf", "D:\\a.odt",99);
		// if (check == true) {
		// System.out.println("轉換成功");
		// } else {
		// System.out.println("轉換失敗,失敗原因:" + call.getErrorMessage());
		// }
	}

	// 連結OpenOffice程式轉檔(doc轉pdf與doc轉odf)
	public boolean convert(String sourcefile, String targetpdffile, String targetodffile, String index) {
		String ndate = nowDate("yyyyMMddhhmmss");// 取系統日期
		File sourcefile2 = new File(sourcefile);// 原檔DOC檔
		File targetpdffile2 = new File(targetpdffile);// 目標PDF檔
		File targetodffile2 = new File(targetodffile);// 目標ODT檔

		OpenOfficeConnection connection = new SocketOpenOfficeConnection("127.0.0.1", 8100);
		try {
			if (sourcefile2.exists()) {
				connection.connect();
				DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
				converter.convert(sourcefile2, targetpdffile2);
				converter.convert(sourcefile2, targetodffile2);
				this.setServerFilePDF(ndate + String.valueOf(index) + ".pdf");
				this.setServerFileODF(ndate + String.valueOf(index) + ".odt");
				return true;
			} else {
				this.errorMessage = "檔案不存在";
				return false;
				// System.out.println("檔案不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
			this.errorMessage = e.toString();
			// this.errorMsg=e.toString();
		} finally {
			try {
				if (connection != null) {
					connection.disconnect();
					connection = null;
				}
			} catch (Exception e) {
				this.errorMessage = e.toString();
				// this.errorMsg=e.toString();
			}
		}

		return false;
	}

	// 取系統日期，傳入欲呈現的格式，如yyyyMMdd
	public static String nowDate(String formatstyle) {
		Format formatter = new SimpleDateFormat(formatstyle);
		Date todayDate = new Date();
		return formatter.format(todayDate);
	}

}
