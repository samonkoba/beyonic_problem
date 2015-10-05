package com.beyonic.SMS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.AvailablePhoneNumber;
import com.twilio.sdk.resource.instance.Call;
import com.twilio.sdk.resource.instance.Message;
import com.twilio.sdk.resource.list.AvailablePhoneNumberList;

public class TwilioSimple {
private static final String ACCOUNT_SID = "ACfdf7195ed7b569c054bea1197295f6f1";
private static final String AUTH_TOKEN = "22ba59678f03bdf6b86ea6ece6d6f78b";
	
	
Account mainAccount;
TwilioRestClient client;
MessageFactory messageFactory;
CallFactory callFactory;
String Phone;


public TwilioSimple(String account_sid,String auth_token,String phone){
	
	
	 client = new TwilioRestClient(account_sid, auth_token);
	// Get the main account (The one we used to authenticate the client)
	 mainAccount = client.getAccount();
	 messageFactory = mainAccount.getMessageFactory();
		 callFactory = mainAccount.getCallFactory();
		 this.Phone = phone;
	
}



	public boolean sendSMS(String to,String msg) throws TwilioRestException{
		// Send an sms (using the new messages endpoint)
		// Create a rest client
				
				List<NameValuePair> messageParams = new ArrayList<NameValuePair>();
				messageParams.add(new BasicNameValuePair("To", "+"+to)); 
				messageParams.add(new BasicNameValuePair("From", Phone)); 
				messageParams.add(new BasicNameValuePair("Body",msg));
				Message message = messageFactory.create(messageParams);
		//message.getSid();
		return false;
	}
	
	
	public List<AvailablePhoneNumber> getNumbers(){
		// Search for all available phone numbers
        AvailablePhoneNumberList phoneNumbers = mainAccount.getAvailablePhoneNumbers();
        List<AvailablePhoneNumber> phoneNumberList = phoneNumbers.getPageData();
		return phoneNumberList;
	}
	
	public List<AvailablePhoneNumber> getPhoneNumbersByAreaCode(String area_code){
		Map<String, String> areaCodeFilter = new HashMap<String, String>();
        areaCodeFilter.put("AreaCode", "94103");
        AvailablePhoneNumberList phoneNumbersByAreaCode = mainAccount.getAvailablePhoneNumbers(areaCodeFilter);
        List<AvailablePhoneNumber> phoneNumbersByAreaCodeList = phoneNumbersByAreaCode.getPageData();
        return phoneNumbersByAreaCodeList;
	}
	
	public void MakeCall(String to){
		
		// Make a call
				Map<String, String> callParams = new HashMap<String, String>();
				callParams.put("To", "+"+to); // Replace with a valid phone number
				callParams.put("From", "(510) 555-1212"); // Replace with a valid phone
				// number in your account
				callParams.put("Url", "http://demo.twilio.com/welcome/voice/");
				try {
					Call call = callFactory.create(callParams);
				} catch (TwilioRestException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
	}
	
}
