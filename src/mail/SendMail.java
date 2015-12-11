package mail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import mail.Email;

public class SendMail {
	
	int result;
	
	public int sendMail(Email email) throws Exception {
		try {
			String smtpServer = "mail.ilredian.xyz";
			String sender = "ilredian";

			Socket socket = new Socket(smtpServer, 25);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
			// System.out.println("서버에 연결되었습니다.");

			String line = br.readLine();
			// System.out.println("응답:" + line);
			if (!line.startsWith("220"))
				throw new Exception("SMTP서버가 아닙니다:" + line);

			// System.out.println("HELO 명령을 전송합니다.");
			pw.println("HELO mail.ilredian.xyz");
			line = br.readLine();
			// System.out.println("응답:" + line);
			if (!line.startsWith("250"))
				throw new Exception("HELO 실패했습니다:" + line);

			// System.out.println("MAIL FROM 명령을 전송합니다.");
			pw.println("MAIL FROM:" + sender);
			line = br.readLine();
			// System.out.println("응답:" + line);
			if (!line.startsWith("250"))
				throw new Exception("MAIL FROM 에서 실패했습니다:" + line);

			// System.out.println("RCPT 명령을 전송합니다.");
			pw.println("RCPT TO:" + email.getReceiver());
			line = br.readLine();
			// System.out.println("응답:" + line);
			if (!line.startsWith("250"))
				throw new Exception("RCPT TO 에서 실패했습니다:" + line);

			// System.out.println("DATA 명령을 전송합니다.");
			pw.println("DATA");
			line = br.readLine();
			// System.out.println("응답:" + line);
			if (!line.startsWith("354"))
				throw new Exception("DATA 에서 실패했습니다:" + line);

			// System.out.println("본문을 전송합니다.");
			pw.println("SUBJECT:" + email.getSubject());
			pw.println(email.getContent());
			pw.println(".");
			line = br.readLine();
			// System.out.println("응답:" + line);
			if (!line.startsWith("250"))
				throw new Exception("내용전송에서 실패했습니다:" + line);

			// System.out.println("접속 종료합니다.");
			pw.println("quit");

			br.close();
			pw.close();
			socket.close();
			result = 1;
		} catch (Exception e) {
			e.printStackTrace();
			result = 0;
		}
		
		return result;
	}
}
