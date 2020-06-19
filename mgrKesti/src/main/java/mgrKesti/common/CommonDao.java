package mgrKesti.common;

import org.apache.ibatis.session.SqlSession;

public class CommonDao {
	
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
}
