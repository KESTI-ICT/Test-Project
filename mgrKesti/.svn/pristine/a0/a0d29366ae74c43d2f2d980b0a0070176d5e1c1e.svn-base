package mgrKesti.customerInfo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* <pre>
* ClassName : LoginController.java
* Description : LoginController Class
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
@RequestMapping("/customerInfo/*")
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	/**
     * <pre>
     * 고객사 화면 접속.
     * </pre>
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     * @author yis
     * @since 2020. 03. 24.
     */
	@RequestMapping(value = "/customer")
	public String login(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		logger.debug("========= login(Controller) START ===========");
		logger.debug("========= login(Controller) END ===========");
		return "/customerInfo/customer";
	}
}