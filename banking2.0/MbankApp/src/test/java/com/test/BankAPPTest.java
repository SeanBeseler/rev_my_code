package com.test;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import com.logic.backend.BackEndLogic;
import com.logic.frontend.validate.validate;

public class BankAPPTest {

	@Test
	public void TestRecordData() {
		BackEndLogic BEL = new BackEndLogic();
		String[] input = {"myest", "myest", "myest","myest" ,"myest"};
		Assert.assertEquals(0, BEL.recordData(1, input));
		String[] input2 = {"1", "0"};
		Assert.assertEquals(0, BEL.recordData(3, input2));


	}
	@Test
	public void Testvalidate() {
		validate val = new validate();
		Assert.assertEquals(false, val.isDoub("asdf"));
		Assert.assertEquals(true, val.isDoub("1"));
		Assert.assertEquals(false, val.isName("Sean123"));
		Assert.assertEquals(true, val.isName("Sean"));
		Assert.assertEquals(false, val.isEmail("asdf"));
		Assert.assertEquals(true, val.isEmail("Seanwbeseler@gmail.com"));
		Assert.assertEquals(false, val.CheckFor1Or2("3"));
		Assert.assertEquals(true, val.CheckFor1Or2("1"));
		Assert.assertEquals(true, val.CheckFor1Or2("2"));
		
	}

}
