package mgrKesti.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* <pre>
* ClassName : CommonController.java
* Description : CommonController Class
* Modification Information
*
* ������       ������        ��������
* ---------    ---------   -------------------------------
* 2020. 03. 24.   yis      ���ʻ���
*
* Copyright (C) by KR All right reserved.
* </pre>
* @author KESTI ���� ���� �ý���
* @since 2020. 03. 24.
* @version 1.0
*
*/

@Controller
@RequestMapping("/common/*")
public class PopupController {
	
	private static final Logger logger = LoggerFactory.getLogger(PopupController.class);
	
	@RequestMapping
	public void insertProjectPopForm(){
	}
}