package com.xminds.selenium.util;

public class TestWorker extends AbstractWorkerTask {

	public TestWorker() {
	}

	public TestWorker(String name) {
		super(name);
	}

	
	@Override
	public void executeTask() {
		System.out.println(super.getName()+": Hi I am a sample worker");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
