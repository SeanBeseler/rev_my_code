package com.messageHelper;

import project1.com.objects.User;

public class MessageHelper {
	public String RequestMess(User us, int PTID) {
		String output = us.getFname() + " " +  us.getLname() + " has requested reimbursement of " + us.getPendingTransactions().get(PTID); 
		return output;
	}
}
