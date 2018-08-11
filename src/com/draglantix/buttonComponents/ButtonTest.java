package com.draglantix.buttonComponents;

public class ButtonTest extends ButtonComponent {

	private int num;
	
	public ButtonTest(int num) {
		this.num = num;
	}

	public void update() {
		System.out.println(num);
	}

}
