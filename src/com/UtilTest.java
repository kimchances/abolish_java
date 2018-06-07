package com;

public class UtilTest {

	public UtilTest() {
	
		
	}
	

	
	public static String createRandomNumber(int size) {
		
		int[] word = new int[size];
	
		for( int i = 0;i<word.length;i++)
		{
			word[i] = (int) ((Math.random() * 10) + 48);
		}

		StringBuffer newPassword = new StringBuffer();
		for(int j = 0;j<size;j++)

		{
			newPassword.append((char) word[j]);
		}
					
		
		return newPassword.toString();
		
		
	}

	
	
}
