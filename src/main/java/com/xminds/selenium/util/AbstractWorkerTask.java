package com.xminds.selenium.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Class AbstractWorkerTask.
 *
 * @author sumeshr
 */
public abstract class AbstractWorkerTask implements WorkerTask {

	private static final Logger logger = LogManager.getRootLogger();
	
	/** The input. */
	private Object input;
	
	/** The output. */
	private Object output;
	
	/** The task name. */
	private String taskName = "";
	
	
	/**
	 * Instantiates a new abstract worker task.
	 */
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

	/* (non-Javadoc)
	 * @see com.ults.hrms.services.worker.WorkerTask#executeTask()
	 */
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
