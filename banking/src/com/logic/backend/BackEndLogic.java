package com.logic.backend;

import com.objects.user.BankUser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.interfaces.backend.*;

public class BackEndLogic implements recordkeeper {

	@Override
	public ArrayList<BankUser> getData() {
		final String filename = "../src/data/acountInfo.txt";
		ArrayList<BankUser> acounts = new ArrayList<BankUser>();
		try(BufferedReader br = new BufferedReader(new FileReader(filename))){
			String line = null;
			while((line = br.readLine()) != null) {
				String[] data = line.split(":");
				BankUser current = new BankUser(data[0], data[1], data[2], data[3], data[4], Double.parseDouble(data[5]));
				acounts.add(current);
			}
		}catch (FileNotFoundException e) {
			System.out.println("hi");
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}

		
		return acounts;
	}

	@Override
	public void recordData(ArrayList<BankUser> accounts) {
		// TODO Auto-generated method stub
		final String filename ="../src/data/acountInfo.txt";
		File file = new File(filename);
		file.delete();
		try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))){
			for(BankUser x : accounts) {
				bw.write(x.toString());
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
