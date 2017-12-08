package com.zdy.biz.demand.dto;

import java.util.Date;
import com.zdy.biz.demand.model.Demand;

public class CreateDemandReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private String demandName;
	
	private String demandProduct;
	
	private String demandPhase;
	
	private String demandClient;
	
	private String demandIntroducer;
	
	private Date demandProposedDate;
	
	private Date demandExpectDate;
	
	private Date demandLaunchDate;
	
	private Long createUser;
	
	private Date createDate;
	
	private String modifyUser;
	
	private String standby1;
	
	private String standby2;
	
	private String standby3;
	
	private String standby4;
	
	public void setDemandName(String demandName) {
		this.demandName = demandName;
	}
	
	public String getDemandName() {
		return this.demandName;
	}
	public void setDemandProduct(String demandProduct) {
		this.demandProduct = demandProduct;
	}
	
	public String getDemandProduct() {
		return this.demandProduct;
	}
	public void setDemandPhase(String demandPhase) {
		this.demandPhase = demandPhase;
	}
	
	public String getDemandPhase() {
		return this.demandPhase;
	}
	public void setDemandClient(String demandClient) {
		this.demandClient = demandClient;
	}
	
	public String getDemandClient() {
		return this.demandClient;
	}
	public void setDemandIntroducer(String demandIntroducer) {
		this.demandIntroducer = demandIntroducer;
	}
	
	public String getDemandIntroducer() {
		return this.demandIntroducer;
	}
	public void setDemandProposedDate(Date demandProposedDate) {
		this.demandProposedDate = demandProposedDate;
	}
	
	public Date getDemandProposedDate() {
		return this.demandProposedDate;
	}
	public void setDemandExpectDate(Date demandExpectDate) {
		this.demandExpectDate = demandExpectDate;
	}
	
	public Date getDemandExpectDate() {
		return this.demandExpectDate;
	}
	public void setDemandLaunchDate(Date demandLaunchDate) {
		this.demandLaunchDate = demandLaunchDate;
	}
	
	public Date getDemandLaunchDate() {
		return this.demandLaunchDate;
	}
	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	
	public Long getCreateUser() {
		return this.createUser;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}
	
	public String getModifyUser() {
		return this.modifyUser;
	}
	public void setStandby1(String standby1) {
		this.standby1 = standby1;
	}
	
	public String getStandby1() {
		return this.standby1;
	}
	public void setStandby2(String standby2) {
		this.standby2 = standby2;
	}
	
	public String getStandby2() {
		return this.standby2;
	}
	public void setStandby3(String standby3) {
		this.standby3 = standby3;
	}
	
	public String getStandby3() {
		return this.standby3;
	}
	public void setStandby4(String standby4) {
		this.standby4 = standby4;
	}
	
	public String getStandby4() {
		return this.standby4;
	}
	
	public CreateDemandReq(){
		
	}
	
	public CreateDemandReq(Demand demand){
		if(demand != null){
				this.setDemandName(demand.getDemandName());
				this.setDemandProduct(demand.getDemandProduct());
				this.setDemandPhase(demand.getDemandPhase());
				this.setDemandClient(demand.getDemandClient());
				this.setDemandIntroducer(demand.getDemandIntroducer());
				this.setDemandProposedDate(demand.getDemandProposedDate());
				this.setDemandExpectDate(demand.getDemandExpectDate());
				this.setDemandLaunchDate(demand.getDemandLaunchDate());
				this.setCreateUser(demand.getCreateUser());
				this.setCreateDate(demand.getCreateDate());
				this.setModifyUser(demand.getModifyUser());
				this.setStandby1(demand.getStandby1());
				this.setStandby2(demand.getStandby2());
				this.setStandby3(demand.getStandby3());
				this.setStandby4(demand.getStandby4());
		}
	}
	public Demand toDemand(){
		Demand  demand = new Demand();
		demand.setDemandName(this.demandName);
		demand.setDemandProduct(this.demandProduct);
		demand.setDemandPhase(this.demandPhase);
		demand.setDemandClient(this.demandClient);
		demand.setDemandIntroducer(this.demandIntroducer);
		demand.setDemandProposedDate(this.demandProposedDate);
		demand.setDemandExpectDate(this.demandExpectDate);
		demand.setDemandLaunchDate(this.demandLaunchDate);
		demand.setCreateUser(this.createUser);
		demand.setCreateDate(this.createDate);
		demand.setModifyUser(this.modifyUser);
		demand.setStandby1(this.standby1);
		demand.setStandby2(this.standby2);
		demand.setStandby3(this.standby3);
		demand.setStandby4(this.standby4);
		return demand;
	}
}
