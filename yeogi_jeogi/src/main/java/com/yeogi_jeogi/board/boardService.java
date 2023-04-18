package com.yeogi_jeogi.board;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import java.util.concurrent.Future;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class boardService {
	private final boardDAOImpl bDao;
	private final boardAsyncDAOImpl baDao;

	@Value("${yeogi_jeogi.imgs}")
	String fdir;
	
	public boardDAO boardNService() {
		return bDao;
	}
	@Async
	public Future<boardAsyncDAO> boardAsyncService() {
		return new AsyncResult<boardAsyncDAO>(baDao);
	}
	public void imageUpload(HttpServletRequest request) throws Exception {
		byte[] fileSize = new byte[Integer.parseInt(request.getHeader("file-size"))];
		String fileType = request.getHeader("file-Type");
		String fileName = fdir + UUID.randomUUID().toString() + "." +fileType.substring(fileType.indexOf("/") +1);
		File file = new File(fileName);
		InputStream is = request.getInputStream();
		OutputStream os = new FileOutputStream(file);
		int b;
		while ((b = is.read(fileSize)) != -1)
			os.write(fileSize);
		if (is != null)
			is.close();
		os.flush();
		os.close();
	}
}
