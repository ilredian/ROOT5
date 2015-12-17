package mail;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.io.*;

public class SendMail {
	
	int result = 0;

	public int sendMail(SendMailDTO sendMailDTO) {

		try{
		
		String host = "mail.ilredian.xyz";// 메일서버

		// 시스템 프로퍼티를 가져온다.
		Properties prop = System.getProperties();

		// 보낼 서버와 연결한 세션을 얻어온다.
		Session session = Session.getDefaultInstance(prop, null);

		// 보낼 메세지 객체 생성
		MimeMessage msg = new MimeMessage(session);

		// 보낼 사람의이름과 이메일 주소
		msg.setFrom(new InternetAddress(new String(sendMailDTO.getName().getBytes("KSC5601"), "8859_1") + "<" + sendMailDTO.getFrom() + ">"));

		// 받을 이메일주소
		// InternetAddress[] inet = InternetAddress.parse(to);
		msg.setRecipients(Message.RecipientType.TO, sendMailDTO.getTo());

		// 제목
		msg.setSubject(sendMailDTO.getTitle(), "KSC5601");

		// 내용
		MimeBodyPart mbp1 = new MimeBodyPart();
		if (sendMailDTO.getTar().equals("html")) {
			mbp1.setContent(sendMailDTO.getContent().replaceAll(" ", " "), "text/html;charset=UTF-8");
		} else {
			mbp1.setText(sendMailDTO.getContent().replaceAll(" ", " "), "KSC5601");
		}

		// 파일
		sendMailDTO.setFilename(fileSize(sendMailDTO.getFilename())); // 파일 사이즈를 구한다.
		MimeBodyPart mbp2 = new MimeBodyPart();
		FileDataSource fds = new FileDataSource(sendMailDTO.getFilename()); // J2EE API 중
		
		// activation패키지 참고
		mbp2.setDataHandler(new DataHandler(fds));
		
		// 다음에서 MimeUtility.encodeText는 한글이 포함된 파일명일 경우 글자깨짐을 막기 위함.
		mbp2.setFileName(MimeUtility.encodeText(fds.getName(), "KSC5601", "B"));

		// Multipart - 몸의 각부분(= MimeBodyPart)를 하나로 합친다. part부분의 대소문자 주의!!..
		// MimeMultipart(part - 소문자),MimeBodyPart(Part - 대문자)
		Multipart mp = new MimeMultipart();
		mp.addBodyPart(mbp1);
		if (!sendMailDTO.getFilename().equals("")) {
			mp.addBodyPart(mbp2);
		}

		msg.setContent(mp);

		// 메세지를 보낸다.
		Transport transport = session.getTransport("smtp");
		transport.connect(host, "ilredian", "1004");
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close();
		
		result = 1;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	private String fileSize(String filename1) {
		File file = new File(filename1);
		if (filename1.length() > (1024 * 1024 * 2.5)) {
			filename1 = "";
		}
		return filename1;
	}
}