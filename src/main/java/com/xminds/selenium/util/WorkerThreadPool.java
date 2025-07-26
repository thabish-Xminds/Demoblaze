package com.xminds.selenium.util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The Class WorkerThreadPool.
 */
public class WorkerThreadPool {

	/** The executor. */
	private ExecutorService executor = Executors.newFixedThreadPool(5);
	
	//private ExecutorService queueExecutor = Executors.newFixedThreadPool(1);
	
	/** The queue. */
	private Queue<WorkerTask> queue = new LinkedList<WorkerTask>();
	
	/** The queued executor. */
	private ExecutorService queuedExecutor = Executors.newFixedThreadPool(1);

	/** The me. */
	private static WorkerThreadPool me = new WorkerThreadPool();
	
	
	/**
	 * Gets the single instance of WorkerThreadPool.
	 *
	 * @return single instance of WorkerThreadPool
	 */
	public static WorkerThreadPool getInstance() {
		return me;
	}

	/**
	 * Instantiates a new worker thread pool.
	 */
	private WorkerThreadPool() {
		//default constructor
	}
	
	
	/**
	 * Push task.
	 *
	 * @param task the task
	 */
	public void pushTask(WorkerTask task) {
		executor.execute(task);
	}
	
	/**
	 * Push queued task.
	 *
	 * @param task the task
	 */
	public synchronized void pushQueuedTask(WorkerTask task) {
		queuedExecutor.execute(task);
	}
	
	/**
	 * Shutdown and await termination.
	 */
	public void shutdownAndAwaitTermination() {
		executor.shutdown(); // Disable new tasks from being submitted
		//requestShutdown = true;
		try {
			// Wait a while for existing tasks to terminate
			if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
				executor.shutdownNow(); // Cancel currently executing tasks
				// Wait a while for tasks to respond to being cancelled
				if (!executor.awaitTermination(60, TimeUnit.SECONDS))
					System.err.println("executor did not terminate");
			}
		} catch (InterruptedException ie) {
			// (Re-)Cancel if current thread also interrupted
			executor.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}
		
		//queuedExecutor
		queuedExecutor.shutdown(); // Disable new tasks from being submitted
		//requestShutdown = true;
		try {
			// Wait a while for existing tasks to terminate
			if (!queuedExecutor.awaitTermination(60, TimeUnit.SECONDS)) {
				queuedExecutor.shutdownNow(); // Cancel currently executing tasks
				// Wait a while for tasks to respond to being cancelled
				if (!queuedExecutor.awaitTermination(60, TimeUnit.SECONDS))
					System.err.println("queuedExecutor did not terminate");
			}
		} catch (InterruptedException ie) {
			// (Re-)Cancel if current thread also interrupted
			queuedExecutor.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
		}	
	}
	
}
