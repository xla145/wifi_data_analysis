package com.wzxy.controller.guestFlow;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 *
 * 
 * @author caibin
 *
 */
@RequestMapping("analysis")
@Controller
public class AnalysisController {


	@RequestMapping(value = "/stopTime")
	public String stopTime(HttpServletRequest request, Model model){
		return "/modules/analysis/stopTime";
	}
	
	@RequestMapping(value = "/cycleTime")
	public String cycleTime(HttpServletRequest request, Model model){
		return "/modules/analysis/cycleTime";
	}


    /**
     * 客流量
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/guestFlowMonth")
    public String guestFlowMonth(HttpServletRequest request, Model model){
        return "/modules/analysis/guestFlowMonth";
    }

    @RequestMapping(value = "/guestFlowDay")
    public String guestFlowDay(HttpServletRequest request, Model model){
        return "/modules/analysis/guestFlowDay";
    }
	
	@RequestMapping(value = "/guestFlowYear")
	public String noPermission(HttpServletRequest request, Model model){
		return "/modules/analysis/guestFlowYear";
	}

    /**
     * 入店量
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/entryVolumeDay")
    public String entryVolumeDay(HttpServletRequest request, Model model){
        return "/modules/analysis/entryVolumeDay";
    }

    @RequestMapping(value = "/entryVolumeMonth")
    public String entryVolumeMonth(HttpServletRequest request, Model model){
        return "/modules/analysis/entryVolumeMonth";
    }

    @RequestMapping(value = "/entryVolumeYear")
    public String entryVolumeYear(HttpServletRequest request, Model model){
        return "/modules/analysis/entryVolumeYear";
    }


    /**
     * 数据比率
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/newAndOld")
    public String newAndOld(HttpServletRequest request, Model model){
        return "/modules/analysis/newAndOld";
    }

    @RequestMapping(value = "/guestActives")
    public String guestActives(HttpServletRequest request, Model model){
        return "/modules/analysis/guestActives";
    }

    @RequestMapping(value = "/entryVolume")
    public String entryVolume(HttpServletRequest request, Model model){
        return "/modules/analysis/entryVolume";
    }

    @RequestMapping(value = "/outEntryVolume")
    public String outEntryVolume(HttpServletRequest request, Model model){
        return "/modules/analysis/outEntryVolume";
    }

    @RequestMapping(value = "/inEntryVolume")
    public String inEntryVolume(HttpServletRequest request, Model model){
        return "/modules/analysis/inEntryVolume";
    }

}
