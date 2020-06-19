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
package mgrKesti.pgtInfo.mapper;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import mgrKesti.common.CommonDao;
import mgrKesti.pgtInfo.vo.ProjectInfoVO;

@Component
public class ProjectMapper extends CommonDao{
	private static final Logger logger = LoggerFactory.getLogger(ProjectMapper.class);
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;	
	
	public List<ProjectInfoVO> selectProjectListInfo(HashMap<String, Object> map) throws Exception{
		logger.debug("========= selectProjectListInfo START ===========");
		logger.debug("========= selectProjectListInfo END ===========");
		return sqlSessionTemplate.selectList("mgrKesti_projectInfo.selectProjectListInfo", map);
	}
	
	public HashMap<String, Object> selectProjectInfo(HashMap<String, Object> map) throws Exception{
		logger.debug("========= selectProjectInfo START ===========");
		logger.debug("========= selectProjectInfo END ===========");
		return sqlSessionTemplate.selectOne("mgrKesti_projectInfo.selectProjectInfo", map);
	}
	
	public void insertProjectInfo(ProjectInfoVO vo) throws Exception{
		logger.debug("========= insertProjectInfo START ===========");
		
		logger.debug("========= insertProjectInfo END ===========");
		sqlSessionTemplate.insert("mgrKesti_projectInfo.insertProjectInfo", vo);
	}
	
	public void deleteProjectInfo(Integer pmsSeq) throws Exception{
		logger.debug("========= deleteProjectInfo START ===========");
		
		logger.debug("========= deleteProjectInfo END ===========");
		sqlSessionTemplate.delete("mgrKesti_projectInfo.deleteProjectInfo", pmsSeq);
	}
	
	public void updateProjectInfo(ProjectInfoVO vo) throws Exception{
		logger.debug("========= updateProjectInfo START ===========");
		
		logger.debug("========= updateProjectInfo END ===========");
		sqlSessionTemplate.delete("mgrKesti_projectInfo.updateProjectInfo", vo);
	}
}