package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import DTO.SmartEditorDTO;

@Controller
public class SmartEditorController {

	@RequestMapping("")
	public void submit(HttpServletRequest request) {
		System.out.println("에디터 컨텐츠값:" + request.getParameter("editor"));
	}

	// 단일파일업로드
	@RequestMapping("/photoUpload.go")
	public String photoUpload(HttpServletRequest request, SmartEditorDTO smartEditorDTO) {
		System.out.println("단일파일업로드");
		String callback = smartEditorDTO.getCallback();
		String callback_func = smartEditorDTO.getCallback_func();
		String file_result = "";
		try {
			if (smartEditorDTO.getFiledata() != null && smartEditorDTO.getFiledata().getOriginalFilename() != null
					&& !smartEditorDTO.getFiledata().getOriginalFilename().equals("")) {

				// 파일이 존재하면
				String original_name = smartEditorDTO.getFiledata().getOriginalFilename();
				String ext = original_name.substring(original_name.lastIndexOf(".") + 1);
				ext = ext.toLowerCase();

				// 확장자 검사
				String[] allow_file = { "jpg", "png", "bmp", "gif" };
				int cnt = 0;

				for (int i = 0; i < allow_file.length; i++) {
					if (ext.equals(allow_file[i])) {
						cnt++;
					}
				}
				if (cnt == 0) {
					file_result = "&errstr=" + original_name;
				} else {

					// 파일 기본경로
					String defaultPath = request.getSession().getServletContext().getRealPath("/");

					// 파일 기본경로 _ 상세경로
					String path = defaultPath + "editor" + File.separator + "upload" + File.separator;

					File file = new File(path);
					System.out.println("path : " + path);

					// 디렉토리 존재하지 않을경우 디렉토리 생성
					if (!file.exists()) {
						file.mkdirs();
					}

					// 서버에 업로드 할 파일명(한글문제로 인해 원본파일은 올리지 않는것이 좋음)
					String realname = "";
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
					String today = formatter.format(new java.util.Date());
					realname = today + UUID.randomUUID().toString() + "." + ext;

					// 서버에 파일쓰기
					smartEditorDTO.getFiledata().transferTo(new File(path + realname));

					file_result += "&bNewLine=true&sFileName=" + original_name + "&sFileURL=/editor/upload/" + realname;
					System.out.println(file_result);
				}
			} else {
				file_result += "&errstr=error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:" + callback + "?callback_func=" + callback_func + file_result;
	}

	// 다중파일업로드
	@RequestMapping("/multiplePhotoUpload.go")
	public void multiplePhotoUpload(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("다중파일업로드");
		try {
			// 파일정보
			String sFileInfo = "";
			// 파일명을 받는다 - 일반 원본파일명
			String filename = request.getHeader("file-name");
			// 파일 확장자
			String filename_ext = filename.substring(filename.lastIndexOf(".") + 1);
			// 확장자를소문자로 변경
			filename_ext = filename_ext.toLowerCase();

			// 이미지 검증 배열 변수
			String[] allow_file = { "jpg", "png", "bmp", "gif" };

			// 돌리면서 확장자가 이미지인지
			int cnt = 0;
			for (int i = 0; i < allow_file.length; i++) {
				if (filename_ext.equals(allow_file[i])) {
					cnt++;
				}
			}

			//이미지가 아님
			if (cnt == 0) {
				System.out.println("NOTALLOW_" + filename);
			} else {
				// 파일 기본경로
				String dftFilePath = request.getSession().getServletContext().getRealPath("/");
				
				// 파일 기본경로 _ 상세경로
				System.out.println("파일 기본경로 _ 상세경로");
				System.out.println(dftFilePath);
				String filePath = dftFilePath + "editor" + File.separator + "multiupload" + File.separator;
				System.out.println(filePath);
				
				File file = new File(filePath);
				if (!file.exists()) {
					file.mkdirs();
				}
				
				String realFileNm = "";
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
				String today = formatter.format(new java.util.Date());
				realFileNm = today + UUID.randomUUID().toString() + filename_ext;
				String rlFileNm = filePath + realFileNm;
				
				//서버에 파일쓰기
				InputStream is = request.getInputStream();
				OutputStream os = new FileOutputStream(rlFileNm);
				int numRead;
				byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
				while ((numRead = is.read(b, 0, b.length)) != -1) {
					os.write(b, 0, numRead);
				}
				if (is != null) {
					is.close();
				}
				os.flush();
				os.close();
				
				// 정보 출력
				sFileInfo += "&bNewLine=true";
				// img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
				sFileInfo += "&sFileName=" + filename;
				;
				sFileInfo += "&sFileURL=" + "/editor/multiupload/" + realFileNm;
				PrintWriter print = response.getWriter();
				print.print(sFileInfo);
				print.flush();
				print.close();
				System.out.println(sFileInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
