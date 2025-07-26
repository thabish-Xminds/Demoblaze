package com.xminds.selenium.util;

/**
 * The Interface WorkerTask.
 */
public interface WorkerTask extends Runnable {

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name);
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName();
	
	
	/**
	 * Sets the input object.
	 *
	 * @param inout the new input object
	 */
	public void setInputObject(Object inout);
	
	
	public Object getInputObject(); 
	
	/**
	 * Execute task.
	 */
	public void executeTask();
	
	/**
	 * Gets the output.
	 *
	 * @return the output
	 */
	public Object getOutput();

	public void setOutput(Object output);

}
