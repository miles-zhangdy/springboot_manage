package com.zdy.biz.demand.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zdy.util.Page;


public class Demand extends Page implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * id       db_column: id 
     */	
	private Long id;
    /**
     * 需求名称       db_column: demand_name 
     */	
	private String demandName;
    /**
     * 所属产品       db_column: demand_product 
     */	
	private String demandProduct;
    /**
     * 所处阶段       db_column: demand_phase 
     */	
	private String demandPhase;
    /**
     * 所属客户       db_column: demand_client 
     */	
	private String demandClient;
    /**
     * 提出人       db_column: demand_introducer 
     */	
	private String demandIntroducer;
    /**
     * 提出日期       db_column: demand_proposed_date 
     */	
	private Date demandProposedDate;
    /**
     * 期望日期       db_column: demand_expect_date 
     */	
	private Date demandExpectDate;
    /**
     * 发布日期       db_column: demand_launch_date 
     */	
	private Date demandLaunchDate;
    /**
     * createUser       db_column: create_user 
     */	
	private Long createUser;
    /**
     * createDate       db_column: create_date 
     */	
	private Date createDate;
    /**
     * modifyUser       db_column: modify_user 
     */	
	private String modifyUser;
    /**
     * standby1       db_column: standby1 
     */	
	private String standby1;
    /**
     * standby2       db_column: standby2 
     */	
	private String standby2;
    /**
     * standby3       db_column: standby3 
     */	
	private String standby3;
    /**
     * standby4       db_column: standby4 
     */	
	private String standby4;
	
	private Long[] ids;
	
	public Demand(){
	}

	public Demand(Long id){
		this.id = id;
	}


	public Demand(Demand demand){
		if(null != demand){
				this.setId(demand.getId());
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
	
	public Map<String,Object> toMap(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("id",this.id);
		map.put("demandName",this.demandName);
		map.put("demandProduct",this.demandProduct);
		map.put("demandPhase",this.demandPhase);
		map.put("demandClient",this.demandClient);
		map.put("demandIntroducer",this.demandIntroducer);
		map.put("demandProposedDate",this.demandProposedDate);
		map.put("demandExpectDate",this.demandExpectDate);
		map.put("demandLaunchDate",this.demandLaunchDate);
		map.put("createUser",this.createUser);
		map.put("createDate",this.createDate);
		map.put("modifyUser",this.modifyUser);
		map.put("standby1",this.standby1);
		map.put("standby2",this.standby2);
		map.put("standby3",this.standby3);
		map.put("standby4",this.standby4);
		return map;
	}

		
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
		
	public void setDemandName(String value) {
		this.demandName = value;
	}
	
	public String getDemandName() {
		return this.demandName;
	}
		
	public void setDemandProduct(String value) {
		this.demandProduct = value;
	}
	
	public String getDemandProduct() {
		return this.demandProduct;
	}
		
	public void setDemandPhase(String value) {
		this.demandPhase = value;
	}
	
	public String getDemandPhase() {
		return this.demandPhase;
	}
		
	public void setDemandClient(String value) {
		this.demandClient = value;
	}
	
	public String getDemandClient() {
		return this.demandClient;
	}
		
	public void setDemandIntroducer(String value) {
		this.demandIntroducer = value;
	}
	
	public String getDemandIntroducer() {
		return this.demandIntroducer;
	}
		
	public void setDemandProposedDate(Date value) {
		this.demandProposedDate = value;
	}
	
	public Date getDemandProposedDate() {
		return this.demandProposedDate;
	}
		
	public void setDemandExpectDate(Date value) {
		this.demandExpectDate = value;
	}
	
	public Date getDemandExpectDate() {
		return this.demandExpectDate;
	}
		
	public void setDemandLaunchDate(Date value) {
		this.demandLaunchDate = value;
	}
	
	public Date getDemandLaunchDate() {
		return this.demandLaunchDate;
	}
		
	public void setCreateUser(Long value) {
		this.createUser = value;
	}
	
	public Long getCreateUser() {
		return this.createUser;
	}
		
	public void setCreateDate(Date value) {
		this.createDate = value;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
		
	public void setModifyUser(String value) {
		this.modifyUser = value;
	}
	
	public String getModifyUser() {
		return this.modifyUser;
	}
		
	public void setStandby1(String value) {
		this.standby1 = value;
	}
	
	public String getStandby1() {
		return this.standby1;
	}
		
	public void setStandby2(String value) {
		this.standby2 = value;
	}
	
	public String getStandby2() {
		return this.standby2;
	}
		
	public void setStandby3(String value) {
		this.standby3 = value;
	}
	
	public String getStandby3() {
		return this.standby3;
	}
		
	public void setStandby4(String value) {
		this.standby4 = value;
	}
	
	public String getStandby4() {
		return this.standby4;
	}
	public void setIds(Long[] ids){
		this.ids = ids;
	}
	
	public Long[] getIds(){
		return this.ids;
	}
}

