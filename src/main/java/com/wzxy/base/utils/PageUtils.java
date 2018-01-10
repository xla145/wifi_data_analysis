package com.wzxy.base.utils;
import cn.assist.easydao.pojo.PagePojo;
import com.alibaba.fastjson.JSONObject;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 分页工具
 * 
 * @author caibin
 *
 */
public class PageUtils {

	static private Logger logger = LoggerFactory.getLogger(PageUtils.class);
	
	/**
	 * 
	 * 
	 * render 分页数据
	 * 
	 * 
	 * @param request
	 * @param dir		从WEB-INF目录开始， 例如：WEB-INF/view/modules/student/
	 * @param tempName	模板名称 相对于dir目录下的文件名 带后缀
	 * @param page		分页数据
	 * @return
	 */
	public static <T> JSONObject render(HttpServletRequest request, String dir, String tempName, PagePojo<T> page) {
		
		if(page == null || page.getPgaeData() == null || page.getPgaeData().size() < 1){
			return jsonResult.error("暂无数据！");
		}
		
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("dataList", page.getPgaeData());
		
		Writer out = null;
		try {
			Configuration conf = new Configuration();
			conf.setServletContextForTemplateLoading(request.getSession().getServletContext(), dir);
			conf.setDefaultEncoding("UTF-8");
			conf.setLocale(Locale.CHINESE);
			conf.setTemplateUpdateDelay(0);
			Template temp = conf.getTemplate(tempName);
			out = new StringWriter();
			temp.process(root, out);
			
			return new jsonResult().success(out.toString(), page.getPageNo(), page.getPageSize(), page.getTotal(), page.getPageTotal());
			
		} catch (Exception e) {
			logger.error("", e);
			e.printStackTrace();
		}finally{
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return new jsonResult().error("暂无数据！");
	}
	
	/**
	 * Writer html
	 * 
	 * @param request
	 * @param response
	 * @param dir		从WEB-INF目录开始， 例如：WEB-INF/view/modules/student/
	 * @param tempName	模板名称 相对于dir目录下的文件名 带后缀
	 */
	public static <T> void render(HttpServletRequest request, HttpServletResponse response, String dir, String tempName, Map<String, Object> datas) {
		Map<String, Object> root = new HashMap<String, Object>();
		if(datas != null){
			for (String key : datas.keySet()) {
				root.put(key, datas.get(key));
			}
		}
		try {
			Configuration conf = new Configuration();
			conf.setServletContextForTemplateLoading(request.getSession().getServletContext(), dir);
			conf.setDefaultEncoding("UTF-8");
			conf.setLocale(Locale.CHINESE);
			conf.setTemplateUpdateDelay(0);
			Template temp = conf.getTemplate(tempName);
			temp.process(root, response.getWriter());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e);
		}
	}
	
	/**
	 * Writer html
	 * 
	 * @param request
	 * @param response
	 * @param dir		从WEB-INF目录开始， 例如：WEB-INF/view/modules/student/
	 * @param tempName	模板名称 相对于dir目录下的文件名 带后缀
	 * @param data		分页数据
	 */
	public static <T> void render(HttpServletRequest request, HttpServletResponse response, String dir, String tempName, T data) {
		Map<String, Object> datas = new HashMap<String, Object>();
		datas.put("data", data);
		render(request, response, dir, tempName, datas);
	}
	
	static class jsonResult{
		public final int ERR = -1;
		public final int OK = 0;

		public static JSONObject error(String msg) {
			JSONObject json = new JSONObject();
			json.put("code", JsonBean.ERR);
			json.put("msg", msg);
			return json;
		}
		
		public  static JSONObject success(String html, int pageNo, int pageSize, int totle, int pageTotle) {
			JSONObject json = new JSONObject();
			json.put("code", JsonBean.OK);
			json.put("pageNo", pageNo);
			json.put("pageSize", pageSize);
			json.put("pageTotle", pageTotle);
			json.put("totle", totle);
			json.put("html", html);
			return json;
		}
	}
}
