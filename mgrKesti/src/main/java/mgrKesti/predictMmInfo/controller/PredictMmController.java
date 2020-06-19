package mgrKesti.predictMmInfo.controller;

import java.math.BigDecimal;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mgrKesti.common.service.CommonService;
import mgrKesti.common.util.HomePageUtil;
import mgrKesti.common.vo.CommonCodeVO;
import mgrKesti.predictMmInfo.service.PredictMmService;
import mgrKesti.predictMmInfo.vo.PredictMmInfoVO;

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
@RequestMapping("/predictInfo/*")
public class PredictMmController {

	private static final Logger logger = LoggerFactory.getLogger(PredictMmController.class);

	/** PredictMmService */
	@Resource(name = "predictMmService")
	PredictMmService predictMmService;

	/** commonService */
	@Resource(name = "commonService")
	CommonService commonService;

	/**
	 * 페이지를 처리 하는 속성
	 */
	@Resource(name="pagenationService")
	private HomePageUtil pageRender;
	
	/**
	 * <pre>
	 * 프로젝트 공수관리 리스트 조회
	 * </pre>
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * @author yis
	 * @since 2020. 03. 24.
	 */
	@RequestMapping(value = "/predictMmListLookup")
	public String predictMmListLookup(HttpServletRequest req, HttpServletResponse res, Model model, PredictMmInfoVO predictMmInfoVO) throws Exception{
		logger.debug("========= predictMmListLookup start ===========");

		//로그인정보
		HttpSession httpSession = req.getSession();
		String userId = (String)httpSession.getAttribute("userId");
		String authId = (String)httpSession.getAttribute("authId");

		logger.debug("userId : " + userId);
		logger.debug("authId : " + authId);
		
		logger.debug("PageIndex() : " + predictMmInfoVO.getPageIndex());
		
		//로그인 접속여부
		if(!"".equals(userId) && userId != null){		//null일떄 로그인화면으로 이동
			List<CommonCodeVO> commonUserRoleInfoList = commonService.selectCommonUserRoleInfo();		//담당자역할코드 가져오기.
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("pageIndex",  predictMmInfoVO.getPageIndex());
			List<PredictMmInfoVO> predictMmInfoList = predictMmService.selectPredictMmListInfo(map);

			logger.debug("predictMmInfoList size : " + predictMmInfoList.size());
			
			model.addAttribute("userId", userId);
			model.addAttribute("authId", authId);
			
			model.addAttribute("commonUserRoleInfoList", commonUserRoleInfoList);
			model.addAttribute("predictMmInfoList", predictMmInfoList);
			
			pageRender.setPageSize(predictMmInfoVO.getPageSize());
			pageRender.setPageBlockSize(predictMmInfoVO.getPageBlockSize());
			pageRender.setPageIndex(predictMmInfoVO.getPageIndex());
			
			pageRender.setTotalCount(predictMmInfoList.get(0).getTotalCount());
			pageRender.caluratePage();
			
			logger.debug("imsu>>> getTotalCount--->"+ predictMmInfoList.get(0).getTotalCount());
			logger.debug("imsu>>> getPageIndex--->"+ pageRender.getPageIndex());
			logger.debug("imsu>>> getTotalPage--->"+ pageRender.getTotalPage());
			logger.debug("imsu>>> pagenation--->"+pageRender.renderHtmlOut().toString());
			
			model.addAttribute("totalCount", predictMmInfoList.get(0).getTotalCount());
			model.addAttribute("pageIndex", predictMmInfoList.get(0).getPageIndex());
			model.addAttribute("totalPage", pageRender.getTotalPage());
			model.addAttribute("pagenation", pageRender.renderHtmlOut().toString());
			
			
			return "/predictMmInfo/predictMm";
		}else{
			model.addAttribute("passFlag", "2");
			return "/loginInfo/login";
		}
	}
	
	/**
	 * <pre>
	 * 프로젝트 공수관리 단건 조회
	 * </pre>
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * @author yis
	 * @since 2020. 03. 24.
	 */
	@RequestMapping(value = "/predictMmLookup")
	public @ResponseBody Map<String, Object> predictMmLookup(HttpServletRequest req, HttpServletResponse res, Model model, PredictMmInfoVO predictMmInfoVO) throws Exception{
		logger.debug("========= predictMmLookup start ===========");
		Map<String, Object> message = new HashMap<String, Object>();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("pmsSeqId", predictMmInfoVO.getPmsSeqId());					//시퀀스정보
		map.put("managerId", predictMmInfoVO.getManagerId());				//담당자id
		map.put("pmsUserRoleCode", predictMmInfoVO.getPmsUserRoleCode());	//담당자 역할 상세
	
		HashMap<String, Object> predictMmInfo = predictMmService.selectPredictMmInfo(map);
		
		message.put("predictMmInfo", predictMmInfo);
		logger.debug("========= predictMmLookup end ===========");
		return message;
	}
	
	/**
	 * <pre>
	 * 프로젝트 공수관리 상세 리스트 조회
	 * </pre>
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * @author yis
	 * @since 2020. 03. 24.
	 */
	@RequestMapping(value = "/predictMmDetailListLookup")
	public String predictMmDetailListLookup(HttpServletRequest req, HttpServletResponse res, Model model, PredictMmInfoVO predictMmInfoVO) throws Exception{
		logger.debug("========= predictMmDetailListLookup start ===========");
		//로그인정보
		HttpSession httpSession = req.getSession();
		
		String userId = (String)httpSession.getAttribute("userId");
		String authId = (String)httpSession.getAttribute("authId");

		logger.debug("userId : " + userId);
		logger.debug("authId : " + authId);
		
		model.addAttribute("userId", userId);
		model.addAttribute("authId", authId);
		
		//로그인 접속여부
		if(!"".equals(userId) && userId != null){		//null일떄 로그인화면으로 이동
			HashMap<String, Object> map = new HashMap<String, Object>();
			
			map.put("pmsSeqId", predictMmInfoVO.getPmsSeqId());		//시퀀스정보
			map.put("pageIndex",  predictMmInfoVO.getPageIndex());	//페이지인덱스
			
			List<CommonCodeVO> commonUserRoleInfoList     = commonService.selectCommonUserRoleInfo();				//담당자역할코드 가져오기.
			List<PredictMmInfoVO> predictMmInfoDetailList = predictMmService.selectPredictMmDetailListInfo(map);	//리스트 가져오기
			
			if(predictMmInfoDetailList.size() > 0){
				model.addAttribute("projectNameRes", predictMmInfoDetailList.get(0).getProjectName());
			}
			
			model.addAttribute("commonUserRoleInfoList", commonUserRoleInfoList);
			model.addAttribute("predictMmInfoDetailList", predictMmInfoDetailList);
			
			pageRender.setPageSize(predictMmInfoVO.getPageSize());
			pageRender.setPageBlockSize(predictMmInfoVO.getPageBlockSize());
			pageRender.setPageIndex(predictMmInfoVO.getPageIndex());
			
			pageRender.setTotalCount(predictMmInfoDetailList.get(0).getTotalCount());
			pageRender.caluratePage();
			
			model.addAttribute("pmsSeqId", predictMmInfoDetailList.get(0).getPmsSeqId());
			
			model.addAttribute("totalCount", predictMmInfoDetailList.get(0).getTotalCount());
			model.addAttribute("pageIndex", predictMmInfoDetailList.get(0).getPageIndex());
			model.addAttribute("totalPage", pageRender.getTotalPage());
			model.addAttribute("pagenation", pageRender.renderHtmlOut().toString());
			
			logger.debug("========= predictMmDetailListLookup end ===========");
			
			return "/predictMmInfo/predictMmDetail";
		}else{
			model.addAttribute("passFlag", "2");
			return "/loginInfo/login";
		}
	}
	
	/**
	 * <pre>
	 * 예상공수 등록
	 * </pre>
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * @author yis
	 * @since 2020. 03. 24.
	 */
	@RequestMapping("/insertPredictMmInfo")
	public String insertPredictMmInfo(HttpServletRequest req, HttpServletResponse res, PredictMmInfoVO predictMmInfoVO)throws Exception{
		logger.debug("========= insertPredictMmInfo START ===========");
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("managerId", predictMmInfoVO.getManagerId());				//담당자id
		map.put("pmsUserRoleCode", predictMmInfoVO.getPmsUserRoleCode());	//담당자 역할코드
		map.put("projectName", predictMmInfoVO.getProjectName());			//프로젝트명
		map.put("pmsUserRole", predictMmInfoVO.getPmsUserRole());			//역할 상세
		
		BigDecimal a1  = new BigDecimal(predictMmInfoVO.getMmOne());
		BigDecimal a2  = new BigDecimal(predictMmInfoVO.getMmTwo());
		BigDecimal a3  = new BigDecimal(predictMmInfoVO.getMmThree());
		BigDecimal a4  = new BigDecimal(predictMmInfoVO.getMmFour());
		BigDecimal a5  = new BigDecimal(predictMmInfoVO.getMmFive());
		BigDecimal a6  = new BigDecimal(predictMmInfoVO.getMmSix());
		BigDecimal a7  = new BigDecimal(predictMmInfoVO.getMmSeven());
		BigDecimal a8  = new BigDecimal(predictMmInfoVO.getMmEight());
		BigDecimal a9  = new BigDecimal(predictMmInfoVO.getMmNine());
		BigDecimal a10 = new BigDecimal(predictMmInfoVO.getMmTen());
		BigDecimal a11 = new BigDecimal(predictMmInfoVO.getMmEleven());
		BigDecimal a12 = new BigDecimal(predictMmInfoVO.getMmTwelve());
		
		map.put("mmTotal", a1.add(a2).add(a3).add(a4).add(a5).add(a6).add(a7).add(a8).add(a9).add(a10).add(a11).add(a12));	//공수합계
		map.put("mmYear", predictMmInfoVO.getMmYear());						//기준년도
		map.put("mmOne", predictMmInfoVO.getMmOne());						//1월공수
		map.put("mmTwo", predictMmInfoVO.getMmTwo());						//2월공수
		map.put("mmThree", predictMmInfoVO.getMmThree());					//3월공수
		map.put("mmFour", predictMmInfoVO.getMmFour());						//4월공수
		map.put("mmFive", predictMmInfoVO.getMmFive());						//5월공수
		map.put("mmSix", predictMmInfoVO.getMmSix());						//6월공수
		map.put("mmSeven", predictMmInfoVO.getMmSeven());					//7월공수
		map.put("mmEight", predictMmInfoVO.getMmEight());					//8월공수
		map.put("mmNine", predictMmInfoVO.getMmNine());						//9월공수
		map.put("mmTen", predictMmInfoVO.getMmTen());						//10월공수
		map.put("mmEleven", predictMmInfoVO.getMmEleven());					//11월공수
		map.put("mmTwelve", predictMmInfoVO.getMmTwelve());					//12월공수
		
		predictMmService.insertPredictMmInfo(map);
		
		logger.debug("========= insertPredictMmInfo END ===========");
		return "redirect:/predictInfo/predictMmListLookup.do";
	}
	
	/**
	 * <pre>
	 * 예상공수 등록
	 * </pre>
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * @author yis
	 * @since 2020. 03. 24.
	 */
	@RequestMapping("/insertPredictMmDetail")
	public String insertPredictMmDetail(HttpServletRequest req, HttpServletResponse res, PredictMmInfoVO predictMmInfoVO)throws Exception{
		logger.debug("========= insertPredictMmDetail START ===========");
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		BigDecimal a1  = new BigDecimal(req.getParameter("mmOne1"));
		BigDecimal a2  = new BigDecimal(req.getParameter("mmTwo1"));
		BigDecimal a3  = new BigDecimal(req.getParameter("mmThree1"));
		BigDecimal a4  = new BigDecimal(req.getParameter("mmFour1"));
		BigDecimal a5  = new BigDecimal(req.getParameter("mmFive1"));
		BigDecimal a6  = new BigDecimal(req.getParameter("mmSix1"));
		BigDecimal a7  = new BigDecimal(req.getParameter("mmSeven1"));
		BigDecimal a8  = new BigDecimal(req.getParameter("mmEight1"));
		BigDecimal a9  = new BigDecimal(req.getParameter("mmNine1"));
		BigDecimal a10 = new BigDecimal(req.getParameter("mmTen1"));
		BigDecimal a11 = new BigDecimal(req.getParameter("mmEleven1"));
		BigDecimal a12 = new BigDecimal(req.getParameter("mmTwelve1"));
		
		map.put("mmTotal", a1.add(a2).add(a3).add(a4).add(a5).add(a6).add(a7).add(a8).add(a9).add(a10).add(a11).add(a12));	//공수합계
		
		map.put("projectName", predictMmInfoVO.getProjectName());				//프로젝트명
		map.put("pmsSeqId", predictMmInfoVO.getPmsSeqId());
		
		map.put("managerId", req.getParameter("managerId1"));					//기준년도
		map.put("pmsUserRoleCode", req.getParameter("pmsUserRoleCode1"));		//담당자 역할코드
		
		map.put("pmsUserRole", req.getParameter("pmsUserRole1"));				//역할 상세
		
		map.put("mmYear", predictMmInfoVO.getMmYear());							//기준년도
		map.put("mmOne", Double.parseDouble(req.getParameter("mmOne1")));		//1월공수
		map.put("mmTwo", Double.parseDouble(req.getParameter("mmTwo1")));		//2월공수
		map.put("mmThree", Double.parseDouble(req.getParameter("mmThree1")));	//3월공수
		map.put("mmFour", Double.parseDouble(req.getParameter("mmFour1")));		//4월공수
		map.put("mmFive", Double.parseDouble(req.getParameter("mmFive1")));		//5월공수
		map.put("mmSix", Double.parseDouble(req.getParameter("mmSix1")));		//6월공수
		map.put("mmSeven", Double.parseDouble(req.getParameter("mmSeven1")));	//7월공수
		map.put("mmEight", Double.parseDouble(req.getParameter("mmEight1")));	//8월공수
		map.put("mmNine", Double.parseDouble(req.getParameter("mmNine1")));		//9월공수
		map.put("mmTen", Double.parseDouble(req.getParameter("mmTen1")));		//10월공수
		map.put("mmEleven", Double.parseDouble(req.getParameter("mmEleven1")));	//11월공수
		map.put("mmTwelve", Double.parseDouble(req.getParameter("mmTwelve1")));	//12월공수
		
		predictMmService.insertPredictMmDetail(map);
		
		logger.debug("========= insertPredictMmDetail END ===========");
		//return "redirect:/predictInfo/predictMmListLookup.do";
		return "redirect:/predictInfo/predictMmDetailListLookup.do?pmsSeqId="+predictMmInfoVO.getPmsSeqId();
		
	}
	
	/**
	 * <pre>
	 * 예상공수 수정
	 * </pre>
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * @author yis
	 * @since 2020. 03. 24.
	 */
	@RequestMapping("/updatePredictMmInfo")
	public String updatePredictMmInfo(HttpServletRequest req, HttpServletResponse res, PredictMmInfoVO predictMmInfoVO)throws Exception{
		logger.debug("========= updatePredictMmInfo START ===========");
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("pmsSeqId", predictMmInfoVO.getPmsSeqId());					//시퀀스정보
		
		map.put("managerId", predictMmInfoVO.getManagerId());				//담당자id
		map.put("pmsUserRoleCode", predictMmInfoVO.getPmsUserRoleCode());	//담당자 역할코드
		map.put("pmsUserRole", predictMmInfoVO.getPmsUserRole());			//역할 상세
		
		BigDecimal a1  = new BigDecimal(predictMmInfoVO.getMmOne());
		BigDecimal a2  = new BigDecimal(predictMmInfoVO.getMmTwo());
		BigDecimal a3  = new BigDecimal(predictMmInfoVO.getMmThree());
		BigDecimal a4  = new BigDecimal(predictMmInfoVO.getMmFour());
		BigDecimal a5  = new BigDecimal(predictMmInfoVO.getMmFive());
		BigDecimal a6  = new BigDecimal(predictMmInfoVO.getMmSix());
		BigDecimal a7  = new BigDecimal(predictMmInfoVO.getMmSeven());
		BigDecimal a8  = new BigDecimal(predictMmInfoVO.getMmEight());
		BigDecimal a9  = new BigDecimal(predictMmInfoVO.getMmNine());
		BigDecimal a10 = new BigDecimal(predictMmInfoVO.getMmTen());
		BigDecimal a11 = new BigDecimal(predictMmInfoVO.getMmEleven());
		BigDecimal a12 = new BigDecimal(predictMmInfoVO.getMmTwelve());
		
		map.put("mmTotal", a1.add(a2).add(a3).add(a4).add(a5).add(a6).add(a7).add(a8).add(a9).add(a10).add(a11).add(a12));	//공수합계
		
		map.put("mmYear", predictMmInfoVO.getMmYear());						//프로젝트명
		map.put("mmOne", predictMmInfoVO.getMmOne());						//1월공수
		map.put("mmTwo", predictMmInfoVO.getMmTwo());						//2월공수
		map.put("mmThree", predictMmInfoVO.getMmThree());					//3월공수
		map.put("mmFour", predictMmInfoVO.getMmFour());						//4월공수
		map.put("mmFive", predictMmInfoVO.getMmFive());						//5월공수
		map.put("mmSix", predictMmInfoVO.getMmSix());						//6월공수
		map.put("mmSeven", predictMmInfoVO.getMmSeven());					//7월공수
		map.put("mmEight", predictMmInfoVO.getMmEight());					//8월공수
		map.put("mmNine", predictMmInfoVO.getMmNine());						//9월공수
		map.put("mmTen", predictMmInfoVO.getMmTen());						//10월공수
		map.put("mmEleven", predictMmInfoVO.getMmEleven());					//11월공수
		map.put("mmTwelve", predictMmInfoVO.getMmTwelve());					//12월공수
		
		predictMmService.updatePredictMmInfo(map);
		
		logger.debug("========= updatePredictMmInfo END ===========");
		return "redirect:/predictInfo/predictMmDetailListLookup.do?pmsSeqId="+predictMmInfoVO.getPmsSeqId();
	}
	
	/**
	 * <pre>
	 * 예상공수 삭제
	 * </pre>
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * @author yis
	 * @since 2020. 03. 24.
	 */
	@RequestMapping("/deletePredictMmInfo")
	public String deletePredictMmInfo(HttpServletRequest req, HttpServletResponse res, PredictMmInfoVO predictMmInfoVO)throws Exception{
		logger.debug("========= deletePredictMmInfo START ===========");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("pmsSeqId", predictMmInfoVO.getPmsSeqId());					//시퀀스정보
		map.put("managerId", predictMmInfoVO.getManagerId());				//담당자id
		map.put("pmsUserRoleCode", predictMmInfoVO.getPmsUserRoleCode());	//담당자 역할 상세
		
		predictMmService.deletePredictMmInfo(map);
		
		logger.debug("========= deletePredictMmInfo END ===========");
		return "redirect:/predictInfo/predictMmDetailListLookup.do?pmsSeqId="+predictMmInfoVO.getPmsSeqId();
	}
}