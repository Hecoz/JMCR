package edu.tamu.aser.rvtest_simple_tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.tamu.aser.exploration.JUnit4MCRRunner;
import junit.framework.Assert;

@RunWith(JUnit4MCRRunner.class)
public class Example1 {

//	private static int x, y;
	//private static Object lock = new Object();

	static int x = new Integer(0);
	static int y = new Integer(0);
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				int b = x;
				y = 1;
			}
		});

		t1.start();

		int a = y;
		x = 1;

		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() throws InterruptedException {
		try {
//			x = 0;
//			y = 0;
		Example1.main(null);
		} catch (Exception e) {
			System.out.println("here");
			fail();
		}
	}
}