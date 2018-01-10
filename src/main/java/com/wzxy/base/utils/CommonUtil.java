package com.wzxy.base.utils;

import cn.assist.easydao.util.PojoHelper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {

	public static String getRandomNumber() {
		Random rand = new Random();
		char[] letters = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
				'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'r', '0', '1', '2', '3', '4',
				'5', '6', '7', '8', '9' };
		String str = "";
		int index;
		boolean[] flags = new boolean[letters.length];// 默认为false
		for (int i = 0; i < 5; i++) {
			do {
				index = rand.nextInt(letters.length);
			} while (flags[index] == true);
			char c = letters[index];
			str += c;
			flags[index] = true;
		}
		return str;
	}
	
	/**
	 * sql like时防止注入
	 * @param srcStr
	 * @return
	 */
	public static String queryLike(String srcStr) {
		//适用于mysql
		srcStr = StringUtils.replace(srcStr, "\\", "\\\\");
		srcStr = StringUtils.replace(srcStr, "'", "\\'");
		srcStr = StringUtils.replace(srcStr, "_", "\\_");
		srcStr = StringUtils.replace(srcStr, "%", "\\%");		
		return "%" + srcStr + "%";
	}
	
	/**
	 * 将数组转换成sql的in查询字符串
	 * @return '1','2',''		没有数据时返回''
	 */
	public static String arrayToSqlIn(Object[] array){
		StringBuffer sqlIn = new StringBuffer("'");
		for(int i=0; i < array.length; i++){
			sqlIn.append(array[i]+"','");
		}
		sqlIn.append("'");
		return sqlIn.toString();
	}

	/**
	 * 将时间数组，转时间戳数组，然后返回以，分割的字符串
	 * @return '444444','444444' 没有数据时返回''
	 */
	public static String arrayTimeToString(String[] times){
		StringBuffer stamp = new StringBuffer("");
		for(int i = 0; i < times.length; i++){
			long time = DateUtil.StringToDate(times[i],"yyyy-MM-dd").getTime();//获取时间戳 转为秒
			stamp.append(time+",");//获取时间
		}
		return stamp.deleteCharAt(stamp.length()-1).toString();
	}


	/**
	 * 将时间数组，转时间戳数组，然后返回以，分割的字符串
	 * @return '444444','444444' 没有数据时返回''
	 */
	public static String arrayTimeToString(String times){
		if (times == null) {
			return "";
		}
		String[] array = times.split(",");
		StringBuffer stamp = new StringBuffer("");
		for(int i = 0; i < array.length; i++){
			long time = DateUtil.StringToDate(array[i],"yyyy-MM-dd").getTime();//获取时间戳 转为秒
			stamp.append(time+",");//获取时间
		}
		return stamp.deleteCharAt(stamp.length()-1).toString();
	}

	/**
	 * 将时间戳数组，转日期数组，然后返回以，分割的字符串
	 * @return '444444','444444' 没有数据时返回''
	 */
	public static String arrayStampToString(String stamp){
		if (StringUtils.isBlank(stamp)) {
			return "";
		}
		String[] array = stamp.split(",");
		StringBuffer times = new StringBuffer("");
		for(int i = 0; i < array.length; i++){
			String time = DateUtil.stampToDate(array[i],"yyyy-MM-dd");//获取时间戳 转为秒
			times.append(time+",");//获取时间
		}
		return times.deleteCharAt(times.length()-1).toString();
	}
	
	/**
	 * 生成订单ID
	 * @return
	 */
	public static synchronized String generOrderId(){
		StringBuffer sb = new StringBuffer("OF-");// 添加前缀（3）
		sb.append(new SimpleDateFormat("yyMMdd-HHmmssSSS").format(new Date())).append("-");// 添加日期（17）
		sb.append((Math.random()+"").substring(2, 7));// 5位随机数
		return sb.toString();
	}
	
	public static String nullToBlank(Object obj) {
		return null == obj ? "" : obj.toString();
	}

	//设置默认值
	public static String blankToDefault(Object obj) {
		return obj.equals("") ? "  " : obj.toString();
	}
	/**
	 * 生成id
	 *
	 * @param code 业务码 会加在id最前面
	 *
	 * @return
	 */
	public static String getId(String code){
		String extra = getStringRandom(6);
		String time = getNumberRandom(6);
		return code + "-" + time + "-" + extra;
	}

	//生成随机数字和字母
	public static String getStringRandom(int length) {
		String base = UUID.randomUUID().toString().replaceAll("-", "");
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString().toUpperCase();
	}

	//获取uuid
	public static String getUUId() {
		return UUID.randomUUID().toString();
	}

	//生成随机数字
	public static String getNumberRandom(int length) {
		char[] chars = {'1','2','3','4','5','6','7','8','9','0'};
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < length; i++){
			sb.append(chars[new Random().nextInt(chars.length)]);
		}
		return sb.toString();
	}

	//生成随机数字
	public static String getCharsRandom(int length) {
		char[] chars = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','p','q','r','s','t','u','v','w','x','y','z'};
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < length; i++){
			sb.append(chars[new Random().nextInt(chars.length)]);
		}
		return sb.toString();
	}
	
	public static String getContentFromJSON(Object json,String name){
		if (json == null) {
			return "";
		}
		JSONObject jsonObject = JSONObject.parseObject(json.toString());
		if (jsonObject == null) {
			return "";
		}
		return jsonObject.get(name).toString();
	}

	public static void main(String[] args) throws IllegalAccessException {
		System.out.println(isPhoneNumber("17620027932"));
//		Option option = new Option();
//		option.setId("4444");
//		option.setName("444");
//		System.out.println(ObjectUtils.objectToMap(option));
//		System.out.println(arrayStampToString(new String[]{"1504281600","1504368000"}));
//		System.out.println(getId("PHW"));
//		Product product = new Product();
//		product.setStock(44444);
//		product.setRemark("44444");
//		product.setProductGroupType(3);
//		product.setImageUrl("");
//		product.setInfo("4444444444444444444444444");
//		List<Object> paramList = new ArrayList<Object>();
//		String sql = insertDuplicate(product,paramList);
//		System.out.println(sql+"============"+ JSON.toJSON(paramList));
	}
//    public static void main(String[] args) {
//    	for (int i = 0; i < 100; i++) {
//    		System.out.println(getNumberRandom(4));
//		}
//    	
//	}

	public static String insertDuplicate(Object t,List<Object> newParamList){
		List<Object> paramList = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer("insert into ");
		StringBuffer insertFields = new StringBuffer();
		PojoHelper pojoHelper = new PojoHelper(t);
		sql.append(pojoHelper.getTableName());
		StringBuffer insertValues = new StringBuffer();
		Map<String, Object> validDatas = pojoHelper.validDataList();
		Iterator<String> iterator = validDatas.keySet().iterator();
		for(int flag = 0; iterator.hasNext(); ++flag) {
			String fieldName = (String)iterator.next();
			if(flag > 0) {
				insertFields.append(", ");
				insertValues.append(", ");
			}

			insertFields.append("`" + fieldName + "`");
			insertValues.append("?");
			paramList.add(validDatas.get(fieldName));
		}

		sql.append("(" + insertFields + ") ");
		sql.append("values(" + insertValues + ") ");
		sql.append("ON DUPLICATE KEY ");
		sql.append("UPDATE ");
		System.out.println(JSON.toJSON(validDatas));
		Iterator<String> iterators = validDatas.keySet().iterator();
		for(int flag = 0; iterators.hasNext(); ++flag) {
			String fieldName = (String)iterators.next();
			if(flag > 0) {
				sql.append(", ");
			}
			sql.append("`" + fieldName + "` = ?");
			paramList.add(validDatas.get(fieldName));
		}
		newParamList.addAll(paramList);
		return sql.toString();
	}


	public static <T> List<T> removeNullFromList(List<T> list) {
		List<T> list1 = new ArrayList<T>();
		for (T t:list) {
			if (t == null) {
				continue;
			}
			list1.add(t);
		}
		return list1;
	}
    public static boolean isPhoneNumber(String number){
		String regExp = "^(13[0-9]|15[012356789]|17[013678]|18[0-9]|14[57])[0-9]{8}$";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(number);
		return m.find();//boolean
	}

	public List<Object> clearNuLLObject(List<Object> list) {
    	for (int i = 0; i < list.size(); i++) {
    		if (list.get(i) == null) {
    			list.remove(i);
    			continue;
			}
		}
		return list;
	}

	/**
	 * 获取用户的ip地址
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if(index != -1){
				return ip.substring(0,index);
			}else{
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
			return ip;
		}
		return request.getRemoteAddr();
	}
}
