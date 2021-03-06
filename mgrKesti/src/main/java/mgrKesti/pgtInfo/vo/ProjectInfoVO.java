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
package mgrKesti.pgtInfo.vo;

import java.sql.Date;

import mgrKesti.common.vo.CommonPagingVO;

public class ProjectInfoVO extends CommonPagingVO{
	/**
	*	일련번호
	*/
	private int pmsSeq = 0;
	/**
	*	프로젝트명
	*/
	private String projectName = "";
	/**
	*	시작일자
	*/
	private Date startDate;
	/**
	*	종료일자
	*/
	private Date endDate;
	/**
	*	프로젝트유형
	*/
	private String projectType = "";
	/**
	*	프로젝트유형코드
	*/
	private String projectTypeCode = "";
	/**
	*	고객사
	*/
	private String customerName = "";
	/**
	*	고객사코드
	*/
	private String customerId = "";
	/**
	*	수행사
	*/
	private String agentName = "";
	/**
	*	수행사코드
	*/
	private String agentId = "";
	/**
	*	제안현황
	*/
	private String suggestionType = "";
	/**
	*	제안현황코드
	*/
	private String suggestionTypeCode = "";
	/**
	*	생성일자
	*/
	private String createDate = "";
	/**
	*	수정일자
	*/
	private String modifyDate = "";
	/**
	*	사용여부
	*/
	private String useYn = "";
	/**
	*	예상공수
	*/
	private Double predictMm;
	/**
	*	산정공수
	*/
	private String mmTotal = "";
	/**
	*	생성자
	*/
	private String createId = "";
	/**
	*	수정자
	*/
	private String modifyId = "";
	
	public int getPmsSeq() {
		return pmsSeq;
	}
	public void setPmsSeq(int pmsSeq) {
		this.pmsSeq = pmsSeq;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getSuggestionType() {
		return suggestionType;
	}
	public void setSuggestionType(String suggestionType) {
		this.suggestionType = suggestionType;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getMmTotal() {
		return mmTotal;
	}
	public void setMmTotal(String mmTotal) {
		this.mmTotal = mmTotal;
	}
	public String getProjectTypeCode() {
		return projectTypeCode;
	}
	public void setProjectTypeCode(String projectTypeCode) {
		this.projectTypeCode = projectTypeCode;
	}
	public String getAgentId() {
		return agentId;
	}
	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	public String getSuggestionTypeCode() {
		return suggestionTypeCode;
	}
	public void setSuggestionTypeCode(String suggestionTypeCode) {
		this.suggestionTypeCode = suggestionTypeCode;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public String getModifyId() {
		return modifyId;
	}
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}
	public Double getPredictMm() {
		return predictMm;
	}
	public void setPredictMm(Double predictMm) {
		this.predictMm = predictMm;
	}
}