package com.wzxy.base.oss;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.wzxy.base.utils.CommonUtil;
import com.wzxy.base.utils.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

/**
 * 上传文件内容
 *
 */
public class UploadFile {

	private static Logger logger = LoggerFactory.getLogger(UploadFile.class);
	private static String filedir = ConfigUtil.getValue("upload.img.filedir");
	private static String bucketName = ConfigUtil.getValue("upload.img.bucketName");
    private static OSSClient ossClient = null;
    /**
     * 下载文件内容
     * */
    public static void downloadContent(String name){
    	ossClient = OSSClientManger.getInstance().getOSSClient();
    	OSSObject ossObject = ossClient.getObject(bucketName, name);
    	InputStream content = ossObject.getObjectContent();
    	if (content != null) {
    	    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
    	    try {
    	    	while (true) {
    	    		String line = reader.readLine();
					if (line == null) break;
	    	        System.out.println("\n" + line);
    	    	}        		         	        
			} catch (Exception e) {
				System.err.println(e.getMessage());
			} finally {
				try {
					if(content != null) {
						content.close();
					}		
				} catch (IOException e) {
					e.printStackTrace();
				}
				ossClient.shutdown();
			} 	    
    	}
    }
    /**
     * 上传文件内容
     * */
	public static String uploadContent(MultipartFile file){
    	try{
    		String fileName = CommonUtil.getId("IMG")+"."+getFileType(file.getOriginalFilename());
    		ossClient = OSSClientManger.getInstance().getOSSClient();
    		InputStream inputStream = file.getInputStream();
			uploadFile2OSS(inputStream,fileName);
			String url = getUrl(filedir + fileName);
			return url;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

	/**
	 * 上传到OSS服务器  如果同名文件会覆盖服务器上的
	 *
	 * @param instream 文件流
	 * @param fileName 文件名称 包括后缀名
	 * @return 出错返回"" ,唯一MD5数字签名
	 */
	public static String uploadFile2OSS(InputStream instream, String fileName) {
		String ret = "";
		try {
			//创建上传Object的Metadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(instream.available());
			objectMetadata.setCacheControl("max-age=2592000");
			objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf(".") + 1)));
			objectMetadata.setContentDisposition("inline;filename=" + fileName);
			//上传文件
			PutObjectResult putResult = ossClient.putObject(bucketName, filedir + fileName, instream, objectMetadata);
			ret = putResult.getETag();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (instream != null) {
					instream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}
	/**
	 * 获得url链接
	 *
	 * @param key
	 * @return
	 */
	public static String getUrl(String key) {
		return  "http://"+bucketName+"."+OSSClientManger.endpoint+"/"+key;
	}
	/**
	 * Description: 判断OSS服务文件上传时文件的contentType
	 *
	 * @param FilenameExtension 文件后缀
	 * @return String
	 */
	public static String getcontentType(String FilenameExtension) {
		if (FilenameExtension.equalsIgnoreCase("bmp")) {
			return "image/bmp";
		}
		if (FilenameExtension.equalsIgnoreCase("gif")) {
			return "image/gif";
		}
		if (FilenameExtension.equalsIgnoreCase("jpeg") ||
				FilenameExtension.equalsIgnoreCase("jpg") ||
				FilenameExtension.equalsIgnoreCase("png")) {
			return "image/jpeg";
		}
		if (FilenameExtension.equalsIgnoreCase("pdf")){
			return "application/pdf";
		}
		if (FilenameExtension.equalsIgnoreCase("html")) {
			return "text/html";
		}
		if (FilenameExtension.equalsIgnoreCase("txt")) {
			return "text/plain";
		}
		if (FilenameExtension.equalsIgnoreCase("vsd")) {
			return "application/vnd.visio";
		}
		if (FilenameExtension.equalsIgnoreCase("pptx") ||
				FilenameExtension.equalsIgnoreCase("ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (FilenameExtension.equalsIgnoreCase("docx") ||
				FilenameExtension.equalsIgnoreCase("doc")) {
			return "application/msword";
		}
		if (FilenameExtension.equalsIgnoreCase("xml")) {
			return "text/xml";
		}
		return "image/jpeg";
	}

	public static String getFileType(String name) {
		return name.substring(name.lastIndexOf(".") + 1);
	}

	public static String getFileName(String name) {
		return name.substring(0,name.lastIndexOf("."));
	}

	public static void main(String[] args) {
		System.out.println(getFileType("IMG1545645456 (6).jpg"));
		System.out.println(getFileName("IMG1545645456 (6).jpg"));
	}
}
