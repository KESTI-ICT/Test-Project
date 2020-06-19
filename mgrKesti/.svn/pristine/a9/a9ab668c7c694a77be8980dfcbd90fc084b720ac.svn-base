package mgrKesti.logInfo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mgrKesti.logInfo.mapper.LoginMapper;
import mgrKesti.logInfo.service.LoginService;
import mgrKesti.logInfo.vo.LoginInfoSerachVO;

@Service("loginService")
@Transactional
public class LoginServiceImpl implements LoginService{
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	/** loginDAO */
	@Resource(name = "loginMapper")
	private LoginMapper loginMapper;

	@Override
	public List<LoginInfoSerachVO> getLoginInfo(String userId) throws Exception{
		logger.debug("========= getLoginInfoImpl START ===========");
		
		return loginMapper.getLoginInfo(userId);
	}
}