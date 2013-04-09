package org.goldratio.services.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.rmi.ServerException;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.goldratio.core.ZenTaskConstants;
import org.goldratio.services.AttachmentService;
import org.goldratio.util.ImageResizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/** 
 * ClassName: AttachmentServiceImpl <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 4, 2013 3:11:34 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */

@Service("attachmentService")
public class AttachmentServiceImpl implements AttachmentService{
	private static final int AVATAR_IMAGE_WIDTH = 10;
	private static final int AVATAR_IMAGE_HEIGHT = 10;
	
	
	@Autowired
	private Properties configProperties;
	
	@Override
	public String uploadImage(MultipartFile file) throws IOException,
			ServerException {
		if(file != null) {
			//String fileName = file.getOriginalFilename();
			String avararPath = configProperties.getProperty(ZenTaskConstants.FILE_UPLOAD_PATH);
			String fileName = UUID.randomUUID().toString();
			File savefile = new File(avararPath + fileName);
			FileUtils.writeByteArrayToFile(savefile, file.getBytes());
			return fileName;
		}
		return null;
	}

	@Override
	public String uploadAvatar(MultipartFile file) throws IOException,
			ServerException {
		if(file != null) {
			//String fileName = file.getOriginalFilename();
			String avararPath = configProperties.getProperty(ZenTaskConstants.FILE_UPLOAD_PATH);
			String fileName = UUID.randomUUID().toString();
			File savefile = new File(avararPath + fileName);
			InputStream in = null ;
			ByteArrayOutputStream baos = null;
			try{
				in = new ByteArrayInputStream(file.getBytes());
				baos = new ByteArrayOutputStream();
				ImageResizer.resizeImage(in, baos, AVATAR_IMAGE_WIDTH, AVATAR_IMAGE_HEIGHT);
				FileUtils.writeByteArrayToFile(savefile, baos.toByteArray());
			}
			finally {
				if(in != null)
					in.close();
				if(baos != null)
					baos.close();
			}
			return fileName;
		}
		return null;
	}
	@Override
	public void download(String path, OutputStream outputStream)
			throws IOException, ServerException {
		String avararPath = configProperties.getProperty(ZenTaskConstants.FILE_UPLOAD_PATH);
		File readFile = new File(avararPath + path);
		if(readFile.exists()) {
			InputStream in = null;
			try {
				in = new FileInputStream(readFile);
				IOUtils.copy(in, outputStream);
			}
			finally {
				if(in != null)
					in.close();
			}
		}
		
	}

}
