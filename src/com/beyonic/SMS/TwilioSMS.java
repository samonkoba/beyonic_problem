package com.beyonic.SMS;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;

public class TwilioSMS {
private static final String ACCOUNT_SID = "ACfdf7195ed7b569c054bea1197295f6f1";
private static final String AUTH_TOKEN = "22ba59678f03bdf6b86ea6ece6d6f78b";
	
	
	
	public static boolean sendSMS(String from,String to,String msg) throws TwilioRestException{
		// Send an sms (using the new messages endpoint)
		// Create a rest client
				TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

				// Get the main account (The one we used to authenticate the client)
				Account mainAccount = client.getAccount();
		
		
				MessageFactory messageFactory = mainAccount.getMessageFactory();
				List<NameValuePair> messageParams = new ArrayList<NameValuePair>();
				messageParams.add(new BasicNameValuePair("To", "+"+to)); 
				messageParams.add(new BasicNameValuePair("From", from)); 
				messageParams.add(new BasicNameValuePair("Body",msg));
				Message message = messageFactory.create(messageParams);
		//message.getSid();
		return false;
	}
	
	
	
	
	
	
}
