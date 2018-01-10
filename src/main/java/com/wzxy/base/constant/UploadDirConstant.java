package com.wzxy.base.constant;

/**
 * 
* @ClassName: OrderActivityStatusConstant
* @Description: TODO(好物订单状态)
* @author Administrator
* @date 2017年7月19日
*
 */
public enum UploadDirConstant {

	GOODS(1,"好物","yue-product/goods/"),ACTIVITY(2,"活动","yue-product/activity/"),TRAVEL(3,"旅游","yue-product/travel/"),ACTIVITY_DISPALY(4,"活动足迹图片","yue-user/img/"),CUSTOM_FILE(5,"定制旅游附件","yue-user/file/");

	private Integer type;
	private String name;
	private String dir;

	UploadDirConstant(Integer type, String name, String dir) {
		this.type = type;
		this.name = name;
		this.dir = dir;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public static String getUploadDir(Integer type){
		UploadDirConstant[] statusConstants = UploadDirConstant.values();
		for (UploadDirConstant s:statusConstants) {
			if (s.getType() == type) {
				return s.getDir();
			}
		}
		return "";
	}
}
