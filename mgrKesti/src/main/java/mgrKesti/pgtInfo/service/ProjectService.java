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
package mgrKesti.pgtInfo.service;

import java.util.HashMap;
import java.util.List;

import mgrKesti.pgtInfo.vo.ProjectInfoVO;


/**
 * <pre>
 * ClassName : LoginService
 * Description : LoginService Interface
 * Modification Information
 *
 * ������       ������        ��������
 * ---------    ---------   -------------------------------
 * 2020. 03. 30.   yis      ���ʻ���
 *
 * Copyright (C) by Korea Environmental Science All right reserved.
 * </pre>
 * @author 
 * @since 2020. 03. 30.
 * @version 1.0
 *
 */
public interface ProjectService{
	
	List<ProjectInfoVO> selectProjectListInfo(HashMap<String, Object> map) throws Exception;
	
	HashMap<String, Object> selectProjectInfo(HashMap<String, Object> map) throws Exception;

	void insertProjectInfo(ProjectInfoVO vo) throws Exception;
	
	void deleteProjectInfo(Integer pmsSeq) throws Exception;
	
	void updateProjectInfo(ProjectInfoVO vo) throws Exception;
}