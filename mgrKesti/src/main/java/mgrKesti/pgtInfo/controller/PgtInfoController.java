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
* ������		������		   ��������
* ---------	   ---------   -------------------------------
* 2020. 03. 24.	  yis	   ���ʻ���
*
* Copyright (C) by KR All right reserved.
* </pre>
* @author KESTI ���� ���� �ý���
* @since 2020. 03. 24.
* @version 1.0
*
*/

@Controller
@RequestMapping("/pgtInfo/*")
public class PgtInfoController {

	private static final Logger logger = LoggerFactory.getLogger(PgtInfoController.class);

	/**
	 * �������� ó�� �ϴ� �Ӽ�
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
	 * �α��� ȭ�� ����.
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

		//�α�������
		HttpSession httpSession = req.getSession();
		String userId = (String)httpSession.getAttribute("userId");
		String authId = (String)httpSession.getAttribute("authId");

		logger.debug("userId : " + userId);
		logger.debug("authId : " + authId);
		
		//�α��� ���ӿ���
		if(!"".equals(userId) && userId != null){		//null�ϋ� �α���ȭ������ �̵�
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("pageIndex",  projectInfoVO.getPageIndex());
			
			List<CommonCodeVO> commonPgtTypeInfoList  = commonService.selectCommonPgtTypeInfo();		//������Ʈ ���� �����ڵ� ��������.
			List<CommonCodeVO> commonCustomerInfoList = commonService.selectCommonCustomerInfo();		//���� �����ڵ� ��������.
			List<CommonCodeVO> commonAgentInfoList    = commonService.selectCommonAgentInfo();			//����� �����ڵ� ��������.
			List<CommonCodeVO> commonSgtTypeInfoList  = commonService.selectCommonSgtTypeInfo();		//������Ȳ �����ڵ� ��������.

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
	 * ������Ʈ����Ʈ ��ȸ  ajax ���.
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
	 * ������Ʈ  �ܰ� ��ȸ
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
		
		map.put("pmsSeq", projectInfoVO.getPmsSeq());	//����������
	
		HashMap<String, Object> projectInfo = projectService.selectProjectInfo(map);
		
		message.put("projectInfo", projectInfo);
		logger.debug("========= selectProjectInfo end ===========");
		return message;
	}

	/**
	 * <pre>
	 * ������Ʈ����Ʈ �Է�
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
			String projectName	  = req.getParameter("projectName1");					//������Ʈ��
			String projectType	  = req.getParameter("projectType1");					//������Ʈ����
			String customerName	  = req.getParameter("customerName1");					//����
			String agentName	  = req.getParameter("agentName1");						//�����
			String suggestionType = req.getParameter("suggestionType1");				//��������
			Double predictMm	  = Double.parseDouble(req.getParameter("predictMm1"));	//�������
			String createId		  = req.getParameter("userId");							//������
			String modifyId		  = req.getParameter("userId");							//������

			Date startDate		  =  java.sql.Date.valueOf(req.getParameter("strDate1"));
			Date endDate   		  =  java.sql.Date.valueOf(req.getParameter("endDate1"));

			//�Ķ���� ����
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
			
			//������� ���̺� insert
			
			//���� ���� ��������
			int year = Calendar.getInstance().get(Calendar.YEAR);
			logger.debug("year====>" + year);
			
			map.put("managerId", createId);			//�ÿ��ڸ�
			map.put("projectName", projectName);	//������Ʈ��
			map.put("pmsUserRole", "");				//����� ���� �̼�
			map.put("pmsUserRoleCode", "PR006");	//����� ���� 
			map.put("mmYear", year);				//���س⵵
			
			predictMmService.insertPredictMmInfo(map);
			
			logger.debug("========= insertProjectInfo END ===========");
		}catch (Exception e){
			e.printStackTrace();
		}
		return "redirect:/pgtInfo/projectLookup.do";
	}

	/**
	 * <pre>
	 * ������Ʈ����Ʈ ����
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
	 * ������Ʈ����Ʈ ����
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
		
		int pmsSeq	  	  	  = Integer.parseInt(req.getParameter("pmsSeq"));			//������
		String projectName	  = req.getParameter("projectName2");						//������Ʈ��
		String projectType	  = req.getParameter("projectType2");						//������Ʈ����
		String customerName	  = req.getParameter("customerName2");						//����
		String agentName	  = req.getParameter("agentName2");							//�����
		String suggestionType = req.getParameter("suggestionType2");					//�����Ȳ
		Double predictMm	  = Double.parseDouble(req.getParameter("predictMm2"));		//�������
		String modifyId		  = req.getParameter("userId");								//������
		
		Date startDate 		  =  java.sql.Date.valueOf(req.getParameter("strDate2"));	//������Ʈ ���۱Ⱓ
		Date endDate  		  =  java.sql.Date.valueOf(req.getParameter("endDate2"));	//������Ʈ �����Ⱓ
		
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