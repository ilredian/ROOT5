<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.util.UUID"%>
<%@page import="java.io.File"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page language="java" pageEncoding="UTF-8"%>
<%
	String sFileInfo = "";
	//파일명 - 싱글파일업로드와 다르게 멀티파일업로드는 HEADER로 넘어옴 
	String name = request.getHeader("file-name");
	String ext = name.substring(name.lastIndexOf(".") + 1);
	ext = ext.toLowerCase();

	//이미지 검증 배열변수
	String[] allow_file = { "jpg", "png", "bmp", "gif" };

	//돌리면서 확장자가 이미지인지 
	int cnt = 0;
	for (int i = 0; i < allow_file.length; i++) {
		if (ext.equals(allow_file[i])) {
			cnt++;
		}
	}

	//이미지가 아님
	if (cnt == 0) {
		out.println("NOTALLOW_" + name);
	} else {
		//파일 기본경로
		String defaultPath = request.getServletContext().getRealPath("/");
		//"C:\\Kosta106th\\Spring_M\\FinalLab\\ROOT\\WebContent\\upload\\";
		//파일 기본경로 _ 상세경로
		System.out.println("파일 기본경로 _ 상세경로");
		System.out.println(defaultPath);
		String path = defaultPath + "editor" + File.separator + "multiupload" + File.separator;
		System.out.println(path);
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String today= formatter.format(new java.util.Date());
		
		String realname = today + UUID.randomUUID().toString() + "." + ext;
		InputStream is = request.getInputStream();
		OutputStream os = new FileOutputStream(path + realname);
		int numRead;
		
		// 파일쓰기
		byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
		while ((numRead = is.read(b, 0, b.length)) != -1) {
			os.write(b, 0, numRead);
		}
		if (is != null) {
			is.close();
		}
		os.flush();
		os.close();
		
		//정보 출력
		sFileInfo += "&bNewLine=true&sFileName=" + name + "&sFileURL=" + "/editor/multiupload/"
				+ realname;
		out.println(sFileInfo);
		System.out.println(sFileInfo);
	}
%>