package mgrKesti.pgtInfo.controller;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import mgrKesti.common.service.CommonService;
import mgrKesti.common.util.HomePageUtil;
import mgrKesti.common.vo.CommonCodeVO;
import mgrKesti.pgtInfo.service.ProjectService;
import mgrKesti.pgtInfo.vo.ProjectInfoVO;
import mgrKesti.predictMmInfo.service.PredictMmService;
import mgrKesti.predictMmInfo.vo.PredictMmInfoVO;
import mgrKesti.utils.StringUtil;

/**
* <pre>
* ClassName : PgtInfoController.java
* Description : PgtInfoController Class
* Modification Information
*
* 수정일		수정자		   수정내용
* ---------	   ---------   -------------------------------
* 2020. 03. 24.	  yis	   최초생성
*
* Copyright (C) by KR All right reserved.
* </pre>
* @author KESTI 공수 관리 시스템
* @since 2020. 03. 24.
* @version 1.0
*
*/

@Controller
@RequestMapping("/pgtInfo/*")
public class PgtInfoController {

	private static final Logger logger = LoggerFactory.getLogger(PgtInfoController.class);

	/**
	 * 페이지를 처리 하는 속성
	 */
	@Resource(name="pagenationService")
	private HomePageUtil pageRender;
	
	/** projectService */
	@Resource(name = "projectService")
	ProjectService projectService;
	
	/** PredictMmService */
	@Resource(name = "predictMmService")
	PredictMmService predictMmService;

	/** commonService */
	@Resource(name = "commonService")
	CommonService commonService;

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
	@RequestMapping(value = "/projectLookup")
	public String projectLookup(HttpServletRequest req, HttpServletResponse res, Model model, ProjectInfoVO projectInfoVO) throws Exception {
		logger.debug("========= projectLookup start ===========");

		//로그인정보
		HttpSession httpSession = req.getSession();
		String userId = (String)httpSession.getAttribute("userId");
		String authId = (String)httpSession.getAttribute("authId");

		logger.debug("userId : " + userId);
		logger.debug("authId : " + authId);
		
		//로그인 접속여부
		if(!"".equals(userId) && userId != null){		//null일떄 로그인화면으로 이동
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("pageIndex",  projectInfoVO.getPageIndex());
			
			List<CommonCodeVO> commonPgtTypeInfoList  = commonService.selectCommonPgtTypeInfo();		//프로젝트 유형 공통코드 가져오기.
			List<CommonCodeVO> commonCustomerInfoList = commonService.selectCommonCustomerInfo();		//고객사 공통코드 가져오기.
			List<CommonCodeVO> commonAgentInfoList    = commonService.selectCommonAgentInfo();			//수행사 공통코드 가져오기.
			List<CommonCodeVO> commonSgtTypeInfoList  = commonService.selectCommonSgtTypeInfo();		//제안현황 공통코드 가져오기.

			List<ProjectInfoVO> projectInfoList = projectService.selectProjectListInfo(map);

			model.addAttribute("projectInfoList", projectInfoList);
			
			model.addAttribute("authId", authId);
			model.addAttribute("commonPgtTypeInfoList", commonPgtTypeInfoList);
			model.addAttribute("commonCustomerInfoList", commonCustomerInfoList);
			model.addAttribute("commonAgentInfoList", commonAgentInfoList);
			model.addAttribute("commonSgtTypeInfoList", commonSgtTypeInfoList);
			
			pageRender.setPageSize(projectInfoVO.getPageSize());
			pageRender.setPageBlockSize(projectInfoVO.getPageBlockSize());
			pageRender.setPageIndex(projectInfoVO.getPageIndex());
			
			pageRender.setTotalCount(projectInfoList.get(0).getTotalCount());
			pageRender.caluratePage();
			
			model.addAttribute("totalCount", projectInfoList.get(0).getTotalCount());
			model.addAttribute("pageIndex", projectInfoList.get(0).getPageIndex());
			model.addAttribute("totalPage", pageRender.getTotalPage());
			model.addAttribute("pagenation", pageRender.renderHtmlOut().toString());
			
			return "/pgtInfo/project";
		}else{
			model.addAttribute("passFlag", "2");
			return "/loginInfo/login";
		}
	}
	/**
	 * <pre>
	 * 프로젝트리스트 조회  ajax 통신.
	 * </pre>
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * @author yis
	 * @since 2020. 03. 24.
	 */
	@RequestMapping(value = "/projectListLookup")
	public @ResponseBody Map<String, Object> projectListLookup(HttpServletRequest req, HttpServletResponse res) throws Exception {
		logger.debug("========= projectListLookup START2 ===========");
		Map<String, Object> message = new HashMap<String, Object>();

		//List<ProjectInfoVO> projectInfoList = projectService.selectProjectListInfo();

		//logger.debug("size : " + projectInfoList.size());

		//message.put("resultList", projectInfoList);
		logger.debug("========= projectListLookup END2 ===========");
		return message;
	}
	
	/**
	 * <pre>
	 * 프로젝트  단건 조회
	 * </pre>
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * @author yis
	 * @since 2020. 03. 24.
	 */
	@RequestMapping(value = "/selectProjectInfo")
	public @ResponseBody Map<String, Object> selectProjectInfo(HttpServletRequest req, HttpServletResponse res, ProjectInfoVO projectInfoVO) throws Exception{
		logger.debug("========= selectProjectInfo start ===========");
		Map<String, Object> message = new HashMap<String, Object>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("pmsSeq", projectInfoVO.getPmsSeq());	//시퀀스정보
	
		HashMap<String, Object> projectInfo = projectService.selectProjectInfo(map);
		
		message.put("projectInfo", projectInfo);
		logger.debug("========= selectProjectInfo end ===========");
		return message;
	}

	/**
	 * <pre>
	 * 프로젝트리스트 입력
	 * </pre>
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * @author yis
	 * @since 2020. 03. 24.
	 */
	@RequestMapping("/insertProjectInfo")
	public String insertProjectInfo(@ModelAttribute("projectInfoVO") ProjectInfoVO projectInfoVO, HttpServletRequest req) throws Exception{
		logger.debug("========= insertProjectInfo START ===========");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		//req.setCharacterEncoding("utf-8");

		try{
			String projectName	  = req.getParameter("projectName1");					//프로젝트명
			String projectType	  = req.getParameter("projectType1");					//프로젝트유형
			String customerName	  = req.getParameter("customerName1");					//고객사
			String agentName	  = req.getParameter("agentName1");						//수행사
			String suggestionType = req.getParameter("suggestionType1");				//제안유형
			Double predictMm	  = Double.parseDouble(req.getParameter("predictMm1"));	//예상공수
			String createId		  = req.getParameter("userId");							//생성자
			String modifyId		  = req.getParameter("userId");							//수정자

			Date startDate		  =  java.sql.Date.valueOf(req.getParameter("strDate1"));
			Date endDate   		  =  java.sql.Date.valueOf(req.getParameter("endDate1"));

			//파라미터 셋팅
			projectInfoVO.setStartDate(startDate);
			projectInfoVO.setEndDate(endDate);
			projectInfoVO.setProjectName(projectName);
			projectInfoVO.setProjectType(projectType);
			projectInfoVO.setCustomerName(customerName);
			projectInfoVO.setAgentName(agentName);
			projectInfoVO.setSuggestionType(suggestionType);
			projectInfoVO.setPredictMm(predictMm);
			projectInfoVO.setCreateId(createId);
			projectInfoVO.setModifyId(modifyId);

			projectService.insertProjectInfo(projectInfoVO);
			
			//예상공수 테이블 insert
			
			//올해 연도 가져오기
			int year = Calendar.getInstance().get(Calendar.YEAR);
			logger.debug("year====>" + year);
			
			map.put("managerId", createId);			//시용자명
			map.put("projectName", projectName);	//프로젝트명
			map.put("pmsUserRole", "");				//담당자 역할 싱세
			map.put("pmsUserRoleCode", "PR006");	//담당자 역할 
			map.put("mmYear", year);				//기준년도
			
			predictMmService.insertPredictMmInfo(map);
			
			logger.debug("========= insertProjectInfo END ===========");
		}catch (Exception e){
			e.printStackTrace();
		}
		return "redirect:/pgtInfo/projectLookup.do";
	}

	/**
	 * <pre>
	 * 프로젝트리스트 삭제
	 * </pre>
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * @author yis
	 * @since 2020. 03. 24.
	 */
	@RequestMapping("/deleteProjectInfo")
	public String deleteProjectInfo(HttpServletRequest req, HttpServletResponse res)throws Exception{
		Integer pmsSeq = Integer.parseInt(StringUtil.nvl(req.getParameter("pmsSeq"), "0"));
		logger.debug("pmsSeq========>" + pmsSeq);
		projectService.deleteProjectInfo(pmsSeq);

		return "redirect:/pgtInfo/projectLookup.do";
	}
	
	/**
	 * <pre>
	 * 프로젝트리스트 수정
	 * </pre>
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * @author yis
	 * @since 2020. 04. 06.
	 */
	@RequestMapping("/updateProjectInfo")
	public String updateProjectInfo(HttpServletRequest req, HttpServletResponse res)throws Exception{
		logger.debug("========= updateProjectInfo START ===========");
		
		int pmsSeq	  	  	  = Integer.parseInt(req.getParameter("pmsSeq"));			//시퀀스
		String projectName	  = req.getParameter("projectName2");						//프로젝트명
		String projectType	  = req.getParameter("projectType2");						//프로젝트유형
		String customerName	  = req.getParameter("customerName2");						//고객명
		String agentName	  = req.getParameter("agentName2");							//수행사
		String suggestionType = req.getParameter("suggestionType2");					//계약현황
		Double predictMm	  = Double.parseDouble(req.getParameter("predictMm2"));		//예상공수
		String modifyId		  = req.getParameter("userId");								//수정자
		
		Date startDate 		  =  java.sql.Date.valueOf(req.getParameter("strDate2"));	//프로젝트 시작기간
		Date endDate  		  =  java.sql.Date.valueOf(req.getParameter("endDate2"));	//프로젝트 마감기간
		
		ProjectInfoVO paramVO = new ProjectInfoVO();
		
		paramVO.setStartDate(startDate);
		paramVO.setEndDate(endDate);
		paramVO.setProjectName(projectName);
		paramVO.setProjectType(projectType);
		paramVO.setCustomerName(customerName);
		paramVO.setAgentName(agentName);
		paramVO.setSuggestionType(suggestionType);
		paramVO.setPredictMm(predictMm);
		paramVO.setModifyId(modifyId);
		paramVO.setPmsSeq(pmsSeq);
		
		projectService.updateProjectInfo(paramVO);
		logger.debug("========= updateProjectInfo END ===========");
		return "redirect:/pgtInfo/projectLookup.do";
	}
}