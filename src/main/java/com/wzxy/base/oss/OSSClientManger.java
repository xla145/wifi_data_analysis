package com.wzxy.base.oss;

import com.aliyun.oss.OSSClient;
import com.wzxy.base.utils.ConfigUtil;

public class OSSClientManger {
//	private static PropertiesLoader propertiesLoader = new PropertiesLoader("resources.properties");
	public static String endpoint = ConfigUtil.getValue("upload.img.endpoint");
    private static String accessKeyId = ConfigUtil.getValue("upload.img.accessKeyId");
    private static String accessKeySecret = ConfigUtil.getValue("upload.img.accessKeySecret");
    
    private static OSSClient ossClient;
    
    
    /**
     * 
    * 创建一个新的实例 OSSClientManger.
    * 初始化
     */
    public OSSClientManger (){
    	init();
    }
    
    /**
     * 初始化
     */
    public void init() {
       ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }
   
    /**
     * 销毁
     */
    public void destory() {
      ossClient.shutdown();
    }
    
    /**
     * 
    * @Title: getOSSClient
    * @Description: TODO(获取一个实例)
    * @param @return    参数
    * @return OSSClient    返回类型
    * @throws
     */
    public OSSClient getOSSClient(){
    	return ossClient;
    }
    
    public static void main(String[] args) {
    	System.out.println(endpoint+" "+accessKeyId+" "+accessKeySecret);
    	System.out.println(OSSClientManger.getInstance().getOSSClient());
	}
    
    private static class OSSClientHandle{
    	public static OSSClientManger manager = new OSSClientManger (); 
    }
    
    public static OSSClientManger getInstance(){
    	return OSSClientHandle.manager;
    }
}
