package edu.tamu.aser.rvtest.omcr;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.tamu.aser.reexecution.JUnit4MCRRunner;

@RunWith(JUnit4MCRRunner.class)
public class Example4 {

	private static int x,y;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				int r2 = y;
			}
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				x = 1;
				x = 0;
				y = 1;
			}
		});
		
		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				x = 1;
			}
		});
		

		t1.start();
		t2.start();
		t3.start();

		int r1 = x;

		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() throws InterruptedException {
		try {
			x = 0;
			y = 0;
		Example4.main(null);
		} catch (Exception e) {
			System.out.println("here");
			fail();
		}
	}

}
