package com.concept.callback;

public class ChinaMobile {
	private ServiceProvider mSP;
	
	public void setSP(ServiceProvider sp) {
		this.mSP = sp;
	}
	
	public void init() {
		 System.out.println("Welcome, This is ChinaMobile!");
		 mSP.showCustomHint(); //sp自定义的操作
	}
}
