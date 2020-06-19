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
package mgrKesti.logInfo.service;

import java.util.List;

import mgrKesti.logInfo.vo.LoginInfoSerachVO;


/**
 * <pre>
 * ClassName : LoginService
 * Description : LoginService Interface
 * Modification Information
 *
 * 수정일       수정자        수정내용
 * ---------    ---------   -------------------------------
 * 2020. 03. 30.   yis      최초생성
 *
 * Copyright (C) by Korea Environmental Science All right reserved.
 * </pre>
 * @author 
 * @since 2020. 03. 30.
 * @version 1.0
 *
 */
public interface LoginService {
	
	List<LoginInfoSerachVO> getLoginInfo(String userId) throws Exception;

}
