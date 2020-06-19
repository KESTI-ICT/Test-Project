/**
 *	Class Name : KctcExpTxtPaginationRender.java
 *	Description : View페이지(JSP)에서 페이징 처리할 수 있도록 하기 위한 유틸
 *	Modification Information
 *
 *	   수정일		   수정자					 수정내용
 *	 -------	--------	---------------------------
 *
 *
 *	@author 황태연
 *	@since 2012. 07. 31
 *	@version 1.0
 *	@see Copyright (C) by KCTC All right reserved.
 */
package mgrKesti.common.util;

import org.springframework.stereotype.Service;

@Service("pagenationService")
public class HomePageUtil {
	private int pageIndex;				//현재페이지 번호
	private int totalCount;				//전체레코드 개수
	private int pageSize;				//페이지당 레코드 사이즈
	private int pageBlockSize;			//페이지블럭 사이즈
	private int totalPage;				//전체페이지
	private int startNum;				//페이지 시작번호

	private StringBuilder sbPagination;

	/**
	 *
	 */
	public HomePageUtil() {}

   /**
	*
	* Description : 페이지 번호를 반환
	* @author 황태연
	* @since 2012. 11. 23.
	* @param
	* @return int
	*/
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 *
	 * Description : 페이지 번호를 설정
	 * @author 황태연
	 * @since 2012. 11. 23.
	 * @param
	 * @return void
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageBlockSize
	 */
	public int getPageBlockSize() {
		return pageBlockSize;
	}

	/**
	 * @param pageBlockSize the pageBlockSize to set
	 */
	public void setPageBlockSize(int pageBlockSize) {
		this.pageBlockSize = pageBlockSize;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 *
	 * Description : 사용자 입력부분
	 * @author 황태연
	 * @since 2012. 11. 23.
	 * @param
	 * @return int
	 */
	public int getStartNum() {
		if( pageIndex == 1){
			startNum = totalCount;
		}else{
			startNum = totalCount - ((pageIndex - 1) * pageSize);
		}

		return startNum;
	}

	/**
	 *
	 * Description : 페이지정보를 Html로 변환
	 * @author 황태연
	 * @since 2012. 11. 8.
	 * @param
	 * @return StringBuilder
	 */
	public StringBuilder renderHtmlOut() {
		sbPagination = new StringBuilder();

		sbPagination.append("<table class=\"naviTable\" align=\"center\">");
		sbPagination.append("<tr>");

		int tmpNum = ((pageIndex - 1) / pageBlockSize) * pageBlockSize + 1;

		if( tmpNum == 1){
			sbPagination.append("<th><a href=\"javascript:fn_searchPage('1');\"><img src=\"/mgrKesti/resources/images/icon_navi_01.gif\"></a></th>\n");
			sbPagination.append("<th><a href=\"javascript:fn_searchPage('1');\"><img src=\"/mgrKesti/resources/images/icon_navi_02.gif\"></a></th>\n");
		}else{
			sbPagination.append("<th><a href=\"javascript:fn_searchPage('1');\"><img src=\"/mgrKesti/resources/images/icon_navi_01.gif\"></a></th>\n");
			sbPagination.append("<th><a href=\"javascript:fn_searchPage('"+(tmpNum-pageBlockSize)+"');\"><img src=\"/mgrKesti/resources/images/icon_navi_02.gif\"></a></th>\n");
		}


		for(int i=1; i <= pageBlockSize; i++){
			if( tmpNum == pageIndex){
				sbPagination.append("<td class=\"F\">"+tmpNum+"</td>");
			}else{
				sbPagination.append("<td><a href=\"javascript:fn_searchPage('"+tmpNum+"');\">"+tmpNum+"</a></td>");
			}

			tmpNum += 1;

			if(tmpNum > totalPage) break;
		}

		if( pageIndex <= totalPage){
			if( tmpNum > totalPage){
				sbPagination.append("<th><a href=\"javascript:fn_searchPage('"+totalPage+"');\"><img src=\"/mgrKesti/resources/images/icon_navi_03.gif\"></a></th>\n");
				sbPagination.append("<th><a href=\"javascript:fn_searchPage('"+totalPage+"');\"><img src=\"/mgrKesti/resources/images/icon_navi_04.gif\"></a></th>\n");

			}else{
				sbPagination.append("<th><a href=\"javascript:fn_searchPage('"+tmpNum+"');\"><img src=\"/mgrKesti/resources/images/icon_navi_03.gif\"></a></th>\n");
				sbPagination.append("<th><a href=\"javascript:fn_searchPage('"+totalPage+"');\"><img src=\"/mgrKesti/resources/images/icon_navi_04.gif\"></a></th>\n");
			}
		}

		sbPagination.append("</tr>");
		sbPagination.append("</table>");

		return sbPagination;
	}

	/**
	 * 페이지 계산 메소드
	 */
	public void caluratePage(){
//		입력된 전체 열의 수를 통해 전체 페이지 수를 계산한다
		totalPage	 = (int) Math.ceil((double)totalCount / (double)pageSize);

//		현재 페이지가 전체 페이지수보다 클경우 전체 페이지수로 강제로 설정
		if( pageIndex > totalPage){
			pageIndex = totalPage;
		}

//		전체 레코드 카운트가 1보다 작으면 pageIndex=1로 설정
		if(this.totalCount < 1){
			pageIndex=1;
		}

	}

	/**
	 * 메인메소드
	 * @param args
	 */
	public static void main(String[] args){
		HomePageUtil page = new HomePageUtil();
		page.setPageIndex(5);
		page.setPageSize(10);
		page.setPageBlockSize(10);

		page.setTotalCount(47);
		page.caluratePage();

		System.out.println("TOTAL PAGE : " + page.getTotalPage());
		System.out.println("page size : " + page.getPageSize());
		System.out.println("page index : " + page.getPageIndex());

		int startNum = 0;

		if( page.getPageIndex() == 1){
			startNum = page.getTotalCount();

		}else{
			startNum = page.getTotalCount() - ((page.getPageIndex() - 1) * page.getPageSize());
		}

		System.out.println("시작번호 : " + startNum);
	}
}