package com.midlogic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import project1.com.objects.PendingTransactions;
import project1.com.objects.User;

public class MidLogic {
	
	public User getAmount(User us) {
		PendingTransactions pt = us.getPendingTransactions().get(us.getPendingTransactions().size() -1);

		switch(pt.getType()) {
		case 1:
			pt.setAmount(pt.getAmount() * .80);
			break;
		case 2:
			pt.setAmount(pt.getAmount() * .60);
			break;
		case 3:
			pt.setAmount(pt.getAmount() * .75);
			break;
		case 4:
			pt.setAmount(pt.getAmount());
			break;
		case 5:
			pt.setAmount(pt.getAmount() * .90);
			break;
		case 6:
			pt.setAmount(pt.getAmount() * .30);
			break;
			
		}
		if(us.getReimbursementUsed() >= 1000) {
			return null;
		}
		else if(us.getReimbursementUsed() + pt.getAmount() > 1000) {
			double dif = 1000 - us.getReimbursementUsed() - pt.getAmount();
			pt.setAmount(pt.getAmount() + dif);
			
		}
		us.getPendingTransactions().set(us.getPendingTransactions().size() -1, pt);
		return us;
	}
	
	//public boolean CheckDate(String nextDate) {
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	//}
	
}
