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
package mgrKesti.logInfo.mapper;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mgrKesti.common.CommonDao;
import mgrKesti.logInfo.vo.LoginInfoSerachVO;

@Component
public class LoginMapper extends CommonDao{

	private static final Logger logger = LoggerFactory.getLogger(LoginMapper.class);
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;	
	
	public List<LoginInfoSerachVO> getLoginInfo(String userId) throws Exception{
		logger.debug("========= loginMapper START ===========");
		
		logger.debug("========= loginMapper END ===========");
		return sqlSessionTemplate.selectList("mgrKesti_logInfo.getLoginInfo", userId);
		
	}
}