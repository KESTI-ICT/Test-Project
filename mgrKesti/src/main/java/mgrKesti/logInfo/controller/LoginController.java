package mgrKesti.logInfo.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import mgrKesti.common.service.CommonService;
import mgrKesti.common.util.HomePageUtil;
import mgrKesti.common.vo.CommonCodeVO;
import mgrKesti.logInfo.service.LoginService;
import mgrKesti.logInfo.vo.LoginInfoSerachVO;
import mgrKesti.pgtInfo.service.ProjectService;
import mgrKesti.pgtInfo.vo.ProjectInfoVO;
import mgrKesti.predictMmInfo.service.PredictMmService;
import mgrKesti.predictMmInfo.vo.PredictMmInfoVO;
import mgrKesti.utils.StringUtil;

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
@RequestMapping("/logInfo/*")
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/**
	 * 페이지를 처리 하는 속성
	 */
	@Resource(name="pagenationService")
	private HomePageUtil pageRender;
	
	/** commonService */
	@Resource(name = "commonService")
	CommonService commonService;
	
	/** loginService */
	@Resource(name = "loginService")
	LoginService loginService;
	
	/** projectService */
	@Resource(name = "projectService")
	ProjectService projectService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}*/
	
	/**
     * <pre>
     * 로그인 화면 접속.
     * </pre>
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     * @author yis
     * @since 2020. 03. 24.
     */
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		logger.debug("========= login(Controller) START ===========");
		logger.debug("========= login(Controller) END ===========");
		return "/loginInfo/login";
	}
	
	/**
     * <pre>
     * 프로젝트리스트 화면 접속.
     * </pre>
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     * @author yis
     * @since 2020. 03. 24.
     */
	@RequestMapping(value = "/adminLogin")
	public String adminLogin(HttpServletRequest req, HttpServletResponse res, Model model, PredictMmInfoVO predictMmInfoVO) throws Exception {
		logger.debug("========= adminLogin(Controller) START ===========");
		
		//사용자 입력값
		String userIdInput = StringUtil.nvl(req.getParameter("userId"));
		String userPwInput = StringUtil.nvl(req.getParameter("userPw"));
		
		LoginInfoSerachVO loginInfoSerachvo = new LoginInfoSerachVO();
		
		loginInfoSerachvo.setUserId(userIdInput.trim());
		//loginInfoSerachvo.setUserPw(userpw);
		
		List<LoginInfoSerachVO> loginInfo = loginService.getLoginInfo(loginInfoSerachvo.getUserId());
		logger.debug("size : " +loginInfo.size());
		//list 사이즈 체크
		if(loginInfo.size() != 0){
			
			//DB 저장된 user 정보
			String userPwOutput = StringUtil.nvl(loginInfo.get(0).getUserPw());
			
			logger.debug("userPwInput  : " + userPwInput);
			logger.debug("userPwOutput : " + userPwOutput);
			//사용자입력값과 DB저장값 비교(패스워드 맞는지 체크)
			if(userPwInput.equals(userPwOutput)){
				
				//로그인정보 세션관리
				HttpSession httpSession = req.getSession();
				
				httpSession.setAttribute("userId", loginInfo.get(0).getUserId());
				httpSession.setAttribute("userPw", loginInfo.get(0).getUserPw());
				httpSession.setAttribute("authId", loginInfo.get(0).getAuthId());
				
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("pageIndex",  predictMmInfoVO.getPageIndex());
				
				List<CommonCodeVO> commonPgtTypeInfoList  = commonService.selectCommonPgtTypeInfo();	//프로젝트 유형 공통코드 가져오기.
				List<CommonCodeVO> commonCustomerInfoList = commonService.selectCommonCustomerInfo();	//고객사 공통코드 가져오기.
				List<CommonCodeVO> commonAgentInfoList    = commonService.selectCommonAgentInfo();		//수행사 공통코드 가져오기.
				List<CommonCodeVO> commonSgtTypeInfoList  = commonService.selectCommonSgtTypeInfo();	//제안현황 공통코드 가져오기.
				
				List<ProjectInfoVO> projectInfoList       = projectService.selectProjectListInfo(map);
				
				model.addAttribute("projectInfoList", projectInfoList);
				
				logger.debug("commonPgtTypeInfoList =========>" + commonPgtTypeInfoList);
				
				model.addAttribute("commonPgtTypeInfoList", commonPgtTypeInfoList);
				model.addAttribute("commonCustomerInfoList", commonCustomerInfoList);
				model.addAttribute("commonAgentInfoList", commonAgentInfoList);
				model.addAttribute("commonSgtTypeInfoList", commonSgtTypeInfoList);
				
				model.addAttribute("userId", loginInfo.get(0).getUserId());
				model.addAttribute("userPw", loginInfo.get(0).getUserPw());
				model.addAttribute("authId", loginInfo.get(0).getAuthId());
				
				model.addAttribute("passFlag", "0");
				
				pageRender.setPageBlockSize(predictMmInfoVO.getPageBlockSize());
				pageRender.setPageIndex(predictMmInfoVO.getPageIndex());
				
				pageRender.setTotalCount(projectInfoList.get(0).getTotalCount());
				pageRender.caluratePage();
				
				model.addAttribute("totalCount", projectInfoList.get(0).getTotalCount());
				model.addAttribute("pageIndex", projectInfoList.get(0).getPageIndex());
				model.addAttribute("totalPage", pageRender.getTotalPage());
				model.addAttribute("pagenation", pageRender.renderHtmlOut().toString());
				
			
				
				logger.debug("========= adminLogin(Controller) END ===========");
				
				return "/pgtInfo/project";
			}else{
				model.addAttribute("passFlag", "1");
				return "/loginInfo/login";
			}
		}else{
			model.addAttribute("passFlag", "2");
			return "/loginInfo/login";
		}
	}
}