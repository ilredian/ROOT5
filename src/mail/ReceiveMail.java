package mail;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class ReceiveMail {
	
	private int attachnum = 1;
	
	private List<ReceiveMailDTO> list = new ArrayList<ReceiveMailDTO>();
	ReceiveMailDTO receiveMailDTO = new ReceiveMailDTO();

	public List<ReceiveMailDTO> reveiceMail() {
		
		try {
			String host = "mail.ilredian.xyz"; // 호스트
			String user = "ilredian"; // ID
			String password = "1004"; // PWD

			// 접속
			Session session = Session.getDefaultInstance(System.getProperties(), null);
			Store store = session.getStore("imap");
			store.connect(host, -1, user, password);

			// 폴더 열기
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);

			// 폴더에 있는 메세지수 취득
			int totalMessages = folder.getMessageCount();
			if (totalMessages == 0) {
				System.out.println("메일0건");
				folder.close(false);
				store.close();
				return null;
			}

			//
			Message[] messages = folder.getMessages();
			for (int i = 0; i < messages.length; i++) {
				receiveMailDTO = new ReceiveMailDTO();
				// 메세지 표시
				list.add(dumpPart(messages[i], receiveMailDTO));
			}

			// 폴더 닫기
			folder.close(false);
			store.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	private ReceiveMailDTO dumpPart(Part p, ReceiveMailDTO receiveMailDTO) throws Exception {
		
		String html = "";
		String content = "";
		boolean attachment = false;

		if (p instanceof Message) {
			showMessage((Message) p, receiveMailDTO);
		}

		if (p.isMimeType("text/plain")) { // 텍스트 내용일 경우
			System.out.println("내용：\n" + p.getContent());
			receiveMailDTO.setContent(p.getContent()+"\n");
		} else if (p.isMimeType("multipart/*")) { // 멀티파트일경우
			Multipart mp = (Multipart) p.getContent();
			int count = mp.getCount();
			for (int i = 0; i < count; i++) {
				dumpPart(mp.getBodyPart(i), receiveMailDTO);
			}
		} else if (p.isMimeType("message/rfc822")) { // 메세지 내용
			dumpPart((Part) p.getContent(), receiveMailDTO);
		} else if (p.isMimeType("text/html")) { // HTML의 경우
			html = ".html";
			attachment = true;
		} else { // 그외의 경우
			attachment = true;
		}

		/**
		 * 첨부파일 보관
		 */
		if (attachment) {
			String disp = p.getDisposition();
			
			// 첨부 파일
			if(disp !=null && disp.equalsIgnoreCase(Part.ATTACHMENT) == true){
				String filename = p.getFileName();
				filename = MimeUtility.decodeText(filename);
				
				try {
					File f = new File(filename);
					/*if (f.exists()) {
						throw new IOException("같은 파일이 존재합니다.");
					}*/
					OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
					InputStream is = p.getInputStream();
					int c;
					while ((c = is.read()) != -1) {
						os.write(c);
					}
					os.close();
					System.out.println(filename + "을 보관합니다.");
					receiveMailDTO.setFileName(filename);
					receiveMailDTO.setFileLocation("C:\\Kosta106th\\Spring_M\\utils\\sts-bundle\\sts-3.7.1.RELEASE");
				} catch (Exception e) {
					System.out.println("첨부파일 보관중 에러가 발생했습니다" + e);
				}
			}
			
			// HTML 내용
			if (disp == null || disp.equalsIgnoreCase(Part.ATTACHMENT)) {
				String filename = p.getFileName();
				if (filename != null) {
					filename = MimeUtility.decodeText(filename);
				} else {
					filename = "첨부파일" + attachnum++ + html;
				}
				try {
					File f = new File(filename);
					/*if (f.exists()) {
						throw new IOException("같은 파일이 존재합니다.");
					}*/
					OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
					InputStream is = p.getInputStream();
					int c;
					while ((c = is.read()) != -1) {
						os.write(c);
					}
					os.close();
					System.out.println(filename + "을 보관합니다.");
					receiveMailDTO.setHtml("C:\\Kosta106th\\Spring_M\\utils\\sts-bundle\\sts-3.7.1.RELEASE\\"+filename);
				} catch (Exception e) {
					System.out.println("첨부파일 보관중 에러가 발생했습니다" + e);
				}
			}
		}
		
		return receiveMailDTO;
	}

	/**
	 * 메일 내용을 표시
	 */
	private void showMessage(Message m, ReceiveMailDTO receiveMailDTO) throws Exception {
		Address[] a;
		// 보낸이
		if ((a = m.getFrom()) != null) {
			for (int j = 0; j < a.length; j++) {
				System.out.println("보낸이 : " + MimeUtility.decodeText(a[j].toString()));
				receiveMailDTO.setFrom(MimeUtility.decodeText(a[j].toString()));
			}
		}

		// 받는이
		if ((a = m.getRecipients(Message.RecipientType.TO)) != null) {
			for (int j = 0; j < a.length; j++) {
				System.out.println("받는이 : " + MimeUtility.decodeText(a[j].toString()));
				receiveMailDTO.setTo(MimeUtility.decodeText(a[j].toString()));
			}
		}
		// 제목
		System.out.println("제목 : " + m.getSubject());
		receiveMailDTO.setTitle(m.getSubject());

		// 날짜
		Date d = m.getSentDate();
		System.out.println("날짜 : " + (d!= null ? d.toString() : "불명"));
		receiveMailDTO.setDate((d != null ? d.toString() : "불명"));
		
		// 사이즈
		System.out.println("사이즈 : " + m.getSize());
		receiveMailDTO.setSize(m.getSize());
	}
}