/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mgrKesti.predictMmInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mgrKesti.common.CommonDao;
import mgrKesti.predictMmInfo.vo.PredictMmInfoVO;

@Component
public class PredictMmMapper extends CommonDao{

	private static final Logger logger = LoggerFactory.getLogger(PredictMmMapper.class);
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public List<PredictMmInfoVO> selectPredictMmListInfo(HashMap<String, Object> map) throws Exception{
		logger.debug("========= selectPredictMmListInfo START ===========");
		logger.debug("========= selectPredictMmListInfo END ===========");
		return sqlSessionTemplate.selectList("mgrKesti_predictMmInfo.selectPredictMmListInfo", map);
	}
	
	public HashMap<String, Object> selectPredictMmInfo(HashMap<String, Object> map) throws Exception{
		logger.debug("========= selectPredictMmInfo START ===========");
		logger.debug("========= selectPredictMmInfo END ===========");
		return sqlSessionTemplate.selectOne("mgrKesti_predictMmInfo.selectPredictMmInfo", map);
	}
	
	public List<PredictMmInfoVO> selectPredictMmDetailListInfo(HashMap<String, Object> map) throws Exception{
		logger.debug("========= selectPredictMmDetailListInfo START ===========");
		logger.debug("========= selectPredictMmDetailListInfo END ===========");
		return sqlSessionTemplate.selectList("mgrKesti_predictMmInfo.selectPredictMmDetailListInfo", map);
	}
	
	public void insertPredictMmInfo(HashMap<String, Object> map) throws Exception{
		logger.debug("========= insertPredictMmInfo START ===========");
		logger.debug("========= insertPredictMmInfo END ===========");
		sqlSessionTemplate.insert("mgrKesti_predictMmInfo.insertPredictMmInfo", map);
	}
	
	public void insertPredictMmDetail(HashMap<String, Object> map) throws Exception{
		logger.debug("========= insertPredictMmDetail START ===========");
		logger.debug("========= insertPredictMmDetail END ===========");
		sqlSessionTemplate.insert("mgrKesti_predictMmInfo.insertPredictMmDetail", map);
	}
	
	public void updatePredictMmInfo(HashMap<String, Object> map) throws Exception{
		logger.debug("========= updatePredictMmInfo START ===========");
		logger.debug("========= updatePredictMmInfo END ===========");
		sqlSessionTemplate.update("mgrKesti_predictMmInfo.updatePredictMmInfo", map);
	}
	
	public void deletePredictMmInfo(HashMap<String, Object> map) throws Exception{
		logger.debug("========= deletePredictMmInfo START ===========");
		logger.debug("========= deletePredictMmInfo END ===========");
		sqlSessionTemplate.delete("mgrKesti_predictMmInfo.deletePredictMmInfo", map);
	}
}