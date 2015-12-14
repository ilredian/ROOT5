package mail;

import java.util.ArrayList;
import java.util.List;

public class ReceiveMailDTO {

	private List<String> from = new ArrayList<String>();//보낸사람
	private List<String> to = new ArrayList<String>();//받는사람 - 고정 ilredian@ilredian.xyz
	private String title;//제목
	private List<String> content = new ArrayList<String>();//내용
	private String date;//날짜
	private double MailSize;//메일 크기
	private double FileSize;//첨부파일 크기
	private String fileName;//파일 이름
	private String fileLocation;//파일 위치
	private String html;//내용 - html 파일로 저장
	
	public double getMailSize() {
		return MailSize;
	}

	public void setMailSize(double mailSize) {
		MailSize = mailSize;
	}

	public double getFileSize() {
		return FileSize;
	}

	public void setFileSize(double fileSize) {
		FileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getContent() {
		return content.toString();
	}

	public void setContent(String content) {
		this.content.add(content);
	}

	public String getFrom() {
		return from.toString();
	}

	public void setFrom(String from) {
		this.from.add(from);
	}

	public String getTo() {
		return to.toString();
	}

	public void setTo(String to) {
		this.to.add(to);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
