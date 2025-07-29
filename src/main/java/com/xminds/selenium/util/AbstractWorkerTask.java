package com.xminds.selenium.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractWorkerTask implements WorkerTask {

	private static final Logger logger = LogManager.getRootLogger();
	private Object input;
	private Object output;
	private String taskName = "";
	
	public AbstractWorkerTask() {
		
	}

	public AbstractWorkerTask(String name) {
		this.taskName = name;
	}
	@Override
	public void run() {
		try {
			executeTask();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public abstract void executeTask();
	@Override
	public void setName(String name) {
		this.taskName = name;
	}
	@Override
	public String getName() {
		return this.taskName;
	}
	@Override
	public void setInputObject(Object inout) {
		this.input = input;
	}
	@Override
	public Object getOutput() {
		
		return this.output;
	}
	@Override
	public Object getInputObject() {
		return this.input;
	}
	@Override
	public void setOutput(Object output) {
		
		this.output = output;
	}

}
