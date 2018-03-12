package com.logic.backend;

import java.util.ArrayList;

import com.interfaces.backend.recordkeeper;
import com.objects.user.BankUser;
import com.sql.SqlCalls;

public class BackEndLogic implements recordkeeper {
	SqlCalls cs = new SqlCalls();

	@Override
	public ArrayList<BankUser> getData() {
		ArrayList<BankUser> output = new ArrayList<BankUser>();
		output = cs.getUsers();
		for(int x = 0; x < output.size(); x++) {
			output.get(x).setBlance(cs.getAcount(output.get(x)));
		}
		return output;

	}

	@Override
	public int recordData(int type, String[] info) {
		switch(type) {
		case 1:
			cs.makeUser(info[0], info[1], info[2], info[3], info[4]);
			return 0;
		case 2:
			return cs.makeAcount(info[0]);
		case 3: 
			cs.updateAcountBalance(Integer.parseInt(info[0]), Integer.parseInt(info[1]));
			return 0;
		}
	return -1;

	}
}

