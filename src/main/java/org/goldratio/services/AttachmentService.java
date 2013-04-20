package org.goldratio.services;

import java.io.IOException;
import java.io.OutputStream;
import java.rmi.ServerException;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/** 
 * ClassName: AttachmentService <br/> 
 * Function: <br/> 
 * Reason: <br/> 
 * date: Apr 4, 2013 3:07:15 PM <br/> 
 * 
 * @author GoldRatio 
 * @version 1.0
 */
@Transactional
public interface AttachmentService {
	/**
	 * 上传图片avatar
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws ServerException
	 */
	public String uploadAvatar(MultipartFile file) throws IOException, ServerException;
	
	public String uploadImage(MultipartFile file) throws IOException, ServerException;
	
	public String uploadAttachment(MultipartFile file) throws IOException, ServerException;
	
	public void download(String path, OutputStream outputStream) throws IOException, ServerException;
	
	public void downloadAttachment(String path, OutputStream outputStream) throws IOException, ServerException;
}