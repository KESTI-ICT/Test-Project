package mgrKesti.predictMmInfo.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mgrKesti.predictMmInfo.mapper.PredictMmMapper;
import mgrKesti.predictMmInfo.service.PredictMmService;
import mgrKesti.predictMmInfo.vo.PredictMmInfoVO;

@Service("predictMmService")
@Transactional
public class PredictMmServiceImpl implements PredictMmService{
	private static final Logger logger = LoggerFactory.getLogger(PredictMmServiceImpl.class);
	/** loginDAO */
	@Resource(name = "predictMmMapper")
	private PredictMmMapper predictMmMapper;
	
	/**
     * <pre>
     * 예상공수정보 리스트 조회.
     * </pre>
     * @param 
     * @throws Exception
     * @author yis
     * @since 2020. 04. 06.
     */
	@Override
	public List<PredictMmInfoVO> selectPredictMmListInfo(HashMap<String, Object> map) throws Exception{
		logger.debug("========= selectPredictMmListInfo START ===========");
		logger.debug("========= selectPredictMmListInfo END ===========");
		return predictMmMapper.selectPredictMmListInfo(map);
	}
	
	/**
     * <pre>
     * 예상공수정보 단건 조회.
     * </pre>
     * @param 
     * @throws Exception
     * @author yis
     * @since 2020. 04. 07.
     */
	public HashMap<String, Object> selectPredictMmInfo(HashMap<String, Object> map) throws Exception{
		logger.debug("========= selectPredictMmInfo START ===========");
		logger.debug("========= selectPredictMmInfo END ===========");
		return predictMmMapper.selectPredictMmInfo(map);
	}
	
	/**
     * <pre>
     * 예상공수정보상세 리스트 조회.
     * </pre>
     * @param 
     * @throws Exception
     * @author yis
     * @since 2020. 04. 06.
     */
	@Override
	public List<PredictMmInfoVO> selectPredictMmDetailListInfo(HashMap<String, Object> map) throws Exception{
		logger.debug("========= selectPredictMmDetailListInfo START ===========");
		logger.debug("========= selectPredictMmDetailListInfo END ===========");
		return predictMmMapper.selectPredictMmDetailListInfo(map);
	}
	
	/**
     * <pre>
     * 예상공수정보 등록.
     * </pre>
     * @param pmsSeqId
     * @throws Exception
     * @author yis
     * @since 2020. 04. 06.
     */
	public void insertPredictMmInfo(HashMap<String, Object> map) throws Exception{
		logger.debug("========= insertPredictMmInfo START ===========");
		logger.debug("========= insertPredictMmInfo END ===========");
		
		predictMmMapper.insertPredictMmInfo(map);
	}
	
	/**
     * <pre>
     * 예상공수정보상세 등록.
     * </pre>
     * @param pmsSeqId
     * @throws Exception
     * @author yis
     * @since 2020. 04. 06.
     */
	public void insertPredictMmDetail(HashMap<String, Object> map) throws Exception{
		logger.debug("========= insertPredictMmDetail START ===========");
		logger.debug("========= insertPredictMmDetail END ===========");
		
		predictMmMapper.insertPredictMmDetail(map);
	}
	
	/**
     * <pre>
     * 예상공수정보 수정.
     * </pre>
     * @param pmsSeqId
     * @throws Exception
     * @author yis
     * @since 2020. 04. 09.
     */
	public void updatePredictMmInfo(HashMap<String, Object> map) throws Exception{
		logger.debug("========= updatePredictMmInfo START ===========");
		logger.debug("========= updatePredictMmInfo END ===========");
		
		predictMmMapper.updatePredictMmInfo(map);
	}
	
	/**
     * <pre>
     * 예상공수정보 삭제.
     * </pre>
     * @param pmsSeqId
     * @throws Exception
     * @author yis
     * @since 2020. 04. 06.
     */
	public void deletePredictMmInfo(HashMap<String, Object> map) throws Exception{
		logger.debug("========= deletePredictMmInfo START ===========");
		logger.debug("========= deletePredictMmInfo END ===========");
		
		predictMmMapper.deletePredictMmInfo(map);
	}
}