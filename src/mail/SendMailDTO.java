package mail;

public class SendMailDTO {

	String name; // name : 보낼 사람의 이름
	String from; // from : 보낼사람주소
	String to; // to : 받는 사람주소 receiver;
	String title; // title : 제목 subject;
	String content; // content : 내용
	String tar; // tar : text or html
	String filename;// filename ; 첨부파일 절대 경로(파일명 포함)

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTar() {
		return tar;
	}

	public void setTar(String tar) {
		this.tar = tar;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}