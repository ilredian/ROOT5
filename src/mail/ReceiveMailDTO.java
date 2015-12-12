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
	private String file;
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getContent() {
		System.out.println("컨텐츠 : " + content.size());
		return content.toString();
	}

	public void setContent(String content) {
		this.content.add(content);
	}

	public String getFrom() {
		System.out.println("받는 사람 : " + from.size());
		return from.toString();
	}

	public void setFrom(String from) {
		this.from.add(from);
	}

	public String getTo() {
		System.out.println("보낸 사람 : " + to.size());
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
