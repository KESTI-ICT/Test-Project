package mgrKesti.common.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mgrKesti.common.service.CommonService;
import mgrKesti.common.vo.CommonCodeVO;
import mgrKesti.pgtInfo.service.ProjectService;

/**
* <pre>
* ClassName : CommonController.java
* Description : CommonController Class
* Modification Information
*
* 수정일       수정자        수정내용
* ---------    ---------   -------------------------------
* 2020. 03. 24.   yis      최초생성
*
* Copyright (C) by KR All right reserved.
* </pre>
* @author KESTI 공수 관리 시스템
* @since 2020. 03. 24.
* @version 1.0
*
*/

@Controller
@RequestMapping("/common/*")
public class CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	/** commonService */
	@Resource(name = "commonService")
	CommonService commonService;
	
	/**
     * <pre>
     * 프로젝트정보 화면 접속.
     * </pre>
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     * @author yis
     * @since 2020. 03. 24.
     */
	@RequestMapping(value = "/commonListLookup")
	public @ResponseBody Map<String, Object> commonListLookup(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		logger.debug("========= commonListLookup START2 ===========");
		
		Map<String, Object> message = new HashMap<String, Object>();
		
		//List<CommonCodeVO> commonPgtTypeInfo = commonService.selectCommonPgtTypeInfo();
		
		logger.debug("========= commonListLookup END2 ===========");
		return message;
	}
}