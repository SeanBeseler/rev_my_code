package com.startApp.test;

import java.util.ArrayList;

import com.logic.backend.BackEndLogic;
import com.objects.user.BankUser;
import com.sql.SqlCalls;

public class StartAppTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BackEndLogic BEL = new BackEndLogic(); 
		System.out.println(BEL.getData().get(0).getAcounts().get(0).getBalance());


	}

}
