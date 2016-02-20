package com.nexters.taiger.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

@Service
public class GcmService {

	
	@Value("${android.key}")
	private String apiKey;
	
	
	public void sendMessage(GcmDto gcmDto){
		
		Sender sender = new Sender(apiKey); 
		// GCM으로부터 발급받은 단말기 RegID 입력. 
		String regId = " GCM으로부터 발급받은 단말기 RegID 입력.";
		Message message = new Message.Builder().addData("msg", gcmDto.getMessage()).build();
		
		List<String> list = new ArrayList<String>();

		list.add(regId);

		MulticastResult multiResult;

		try {
	
			multiResult = sender.send(message, list, 5);

			if (multiResult != null) {

				List<Result> resultList = multiResult.getResults();

				for (Result result : resultList) {

					System.out.println(result.getMessageId());

				}

			}

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

}
