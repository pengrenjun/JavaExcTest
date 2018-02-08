package com.ronglian.fssc.webapp.webservice;


import com.ronglian.fssc.webapp.TestBase;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


public class FsscAppWebServiceTest  extends TestBase {
	
	private final static String WSDL_ADDRESS = "http://localhost:8080/ws/soap/FsscAppWebService?wsdl";

	public static void main(String[] args) {
		// 支付通知测试
		payNotice();
	}

	public static void payNotice(){
		Client client =  getClient();
		JSONObject param1Json = new JSONObject();
		param1Json.put("formNo", "101AP20160101071");
		param1Json.put("userId", "2820");
		param1Json.put("taskId", "530067");
		param1Json.put("optDesc", "通过");
		param1Json.put("optType", "审核通过");
		param1Json.put("pkID", "9227765EF7794C559815AE29DCD98572");

		try {
			System.out.println(param1Json.toJSONString());
			Object[] objs = client.invoke("payNotice",param1Json.toJSONString(),"预留字段");
			System.out.println(objs[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void finishStepTest(){
		String headerStr  = getHeaderObj();
		Client client =  getClient();
		JSONObject billObj = new JSONObject();
		billObj.put("formNo", "101AP20160101071");
		billObj.put("userId", "2820");
		billObj.put("taskId", "530067");
		billObj.put("optDesc", "通过");
		billObj.put("optType", "审核通过");
		billObj.put("pkID", "9227765EF7794C559815AE29DCD98572");
		
		System.out.println(billObj.toJSONString());
		
		try {
			System.out.println(headerStr);
	    	Object[] objs = client.invoke("finishStep",headerStr,billObj.toJSONString());
	    	System.out.println(objs[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public static void getBillDetailTest(){
		String headerStr  = getHeaderObj();
		Client client =  getClient();
		JSONObject billObj = new JSONObject();
		//员工报账
		billObj.put("formNo", "101AP20160101114");
		//		billObj.put("formNoColumn", "ecFormNo");
		//		billObj.put("className", "payment.TFormEmpECApp");

				//付款
		//		billObj.put("formNo", "101AP20160101065");
		//		billObj.put("formNoColumn", "loanFormNo");
		//		billObj.put("className", "payment.TFormEmpLoanApp");
		
		
		try {
			System.out.println(headerStr);
	    	Object[] objs = client.invoke("getBillDetail",headerStr,billObj.toJSONString());
	    	System.out.println(objs[0]);
	    	JSONObject obj = JSONObject.parseObject(String.valueOf(objs[0]));
	    	String ecDetailSetJson = obj.getString("payDetailSetJson");
	    	JSONArray array = JSONArray.parseArray(ecDetailSetJson);
	    	for(int i=0;i<array.size();i++){
	    		System.out.println(array.getJSONObject(i).toJSONString());
	    	}
	    	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//附件信息
	@Test
	public static void getAttachDetailTest(){
		String headerStr = getHeaderObj();
		Client client =  getClient();
		try {
			System.out.println(headerStr);
	    	Object[] objs = client.invoke("getAttachDetail",headerStr,"101AP20160101065");
	    	System.out.println(objs[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//审批历史
	@Test
	public static void getApprovalHistHistoryTest(){
		String headerStr = getHeaderObj();
		Client client =  getClient();
		try {
			System.out.println(headerStr);
	    	Object[] objs = client.invoke("getApprovalHistDetail",headerStr,"530057");
	    	System.out.println("resp:"+objs[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//代办数量
	@Test
	public void getTodoCountTest(){
		String headerStr = getHeaderObj();
		Client client =  getClient();
		try {
			System.out.println(headerStr);
	    	Object[] obj = client.invoke("getTodoCount",headerStr,"2820");
	    	System.out.println("resp:"+obj[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//代办集合
	@Test
	public static void getTodoListTest(){
		String headerStr = getHeaderObj();
		Client client =  getClient();
		JSONObject reqObj = new JSONObject();
		reqObj.put("userId","2820");
		reqObj.put("pageNo", "2");
		reqObj.put("pageSize", "2");
		System.out.println(reqObj.toJSONString());
		try {
			System.out.println(headerStr);
	    	Object[] objs = client.invoke("getTodoList",headerStr,reqObj.toJSONString());
	    	for(Object obj:objs){
	    		System.out.println("resp:"+obj);
	    	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//校验用户信息
	@Test
	private void validUserInfoTest(){
		
		JSONObject reqJsonObj = new JSONObject();
		reqJsonObj.put("userId", "2820");
		reqJsonObj.put("userPwd", "1234566767");
		System.out.println(reqJsonObj.toJSONString());
		
		JaxWsDynamicClientFactory  factory =JaxWsDynamicClientFactory.newInstance();
	    Client client =factory.createClient(WSDL_ADDRESS);
	    
		
	    try {
	    	Object[] obj =client.invoke("validUserInfo",getHeaderObj(),reqJsonObj.toJSONString());
	    	System.out.println("resp:"+obj[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Client getClient(){
		JaxWsDynamicClientFactory  factory =JaxWsDynamicClientFactory.newInstance();
	    Client client =factory.createClient(WSDL_ADDRESS);
	    return client;
	}
	
	private static String getHeaderObj(){
		JSONObject headObj = new JSONObject();
		headObj.put("reqSid", "appCode");
		headObj.put("reqUser", "appAdmin");
		headObj.put("reqPwd", "appAdmin");
		headObj.put("reqSn","生成唯一值即可");
		System.out.println(headObj.toJSONString());
		return headObj.toJSONString();
	}

}
