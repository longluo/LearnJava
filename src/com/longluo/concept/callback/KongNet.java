package com.concept.callback;

public class KongNet implements ServiceProvider {

	@Override
	public void showCustomHint() {
		System.out.println("优惠活动开始啦，即日起登陆空中网就有好礼送！ 详情见网站公告。");
	}

	public void init() {
		ChinaMobile cm = new ChinaMobile();

		// 告诉ChinaMobile这是哪家sp
		cm.setSP(new KongNet());

		// KongNet（也就是当前类）实现了ServiceProvider，因此就能把当前类作为适合
		// ChinaMobile.setSp()的参数，可以修改为ct.setSP(this);
		cm.init();
	}

	public static void main(String[] args) {
		KongNet sp = new KongNet();
		sp.init();
	}

}
