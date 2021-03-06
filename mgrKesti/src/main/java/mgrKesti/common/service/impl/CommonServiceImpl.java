package mgrKesti.common.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mgrKesti.common.mapper.CommonMapper;
import mgrKesti.common.service.CommonService;
import mgrKesti.common.vo.CommonCodeVO;

@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService{

	private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

	/** commonMapper */
	@Resource(name = "commonMapper")
	private CommonMapper commonMapper;

	/**
	 * <pre>
	 * 프로젝트 유형 공통 조회
	 * </pre>
	 * @param 없음
	 * @return CommonCodeVO
	 * @throws Exception
	 * @author yis
	 * @since 21020. 04. 02.
	 */
	@Override
	public List<CommonCodeVO> selectCommonPgtTypeInfo() throws Exception{
		logger.debug("========= selectCommonPgtTypeInfo START ===========");
		logger.debug("========= selectCommonPgtTypeInfo END ===========");

		return commonMapper.selectCommonPgtTypeInfo();
	}
	
	/**
	 * <pre>
	 * 고객사 공통 조회
	 * </pre>
	 * @param 없음
	 * @return CommonCodeVO
	 * @throws Exception
	 * @author yis
	 * @since 21020. 04. 02.
	 */
	public List<CommonCodeVO> selectCommonCustomerInfo() throws Exception{
		logger.debug("========= selectCommonCustomerInfo START ===========");
		logger.debug("========= selectCommonCustomerInfo END ===========");

		return commonMapper.selectCommonCustomerInfo();
	}

	/**
	 * <pre>
	 * 수행사 공통 조회
	 * </pre>
	 * @param 없음
	 * @return CommonCodeVO
	 * @throws Exception
	 * @author yis
	 * @since 21020. 04. 02.
	 */
	public List<CommonCodeVO> selectCommonAgentInfo() throws Exception{
		logger.debug("========= selectCommonAgentInfo START ===========");
		logger.debug("========= selectCommonAgentInfo END ===========");

		return commonMapper.selectCommonAgentInfo();
	}

	/**
	 * <pre>
	 * 제안현황 공통 조회
	 * </pre>
	 * @param 없음
	 * @return CommonCodeVO
	 * @throws Exception
	 * @author yis
	 * @since 21020. 04. 02.
	 */
	public List<CommonCodeVO> selectCommonSgtTypeInfo() throws Exception{
		logger.debug("========= selectCommonSgtTypeInfo START ===========");
		logger.debug("========= selectCommonSgtTypeInfo END ===========");

		return commonMapper.selectCommonSgtTypeInfo();
	}
	
	/**
	 * <pre>
	 * 프로젝트 사용자 역할 공통 조회
	 * </pre>
	 * @param 없음
	 * @return CommonCodeVO
	 * @throws Exception
	 * @author yis
	 * @since 21020. 04. 02.
	 */
	public List<CommonCodeVO> selectCommonUserRoleInfo() throws Exception{
		logger.debug("========= selectCommonUserRoleInfo START ===========");
		logger.debug("========= selectCommonUserRoleInfo END ===========");

		return commonMapper.selectCommonUserRoleInfo();
	}
}