package mail;

import java.util.ArrayList;
import java.util.List;

public class ReceiveMailDTO {

	private List<String> from = new ArrayList<String>();
	private List<String> to = new ArrayList<String>();
	private String title;
	private List<String> content = new ArrayList<String>();
	private String date;
	private int size;
	private String fileName;
	private String fileLocation;
	private String html;
	
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
}
