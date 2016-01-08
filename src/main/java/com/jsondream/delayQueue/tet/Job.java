package com.jsondream.delayQueue.tet;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

public class Job {

	private Job(){}
	private static class LazyHolder{
		private static Job job = new Job();
	}
	public static Job getInstance() {
        return LazyHolder.job;
    }
	/**
	 * 守护线程
	 */
	private Thread daemonThread;
	
	/*public Job(){
		Runnable daemonTask = new DaemonThread();
		daemonThread = new Thread(daemonTask);
		daemonThread.setDaemon(true);
		daemonThread.setName("Cache Daemon");
		daemonThread.start();
	}*/
	public void init(){
//		Runnable daemonTask = new DaemonThread();
		Thread daemonThread = new Thread(()->execute());
		daemonThread.setDaemon(true);
		daemonThread.setName("Cache Daemon");
		daemonThread.start();
	}
	
	//执行线程
	/*class DaemonThread implements Runnable{
		@Override
		public void run() {
			execute();
		}	
	}*/
	
	public void execute(){
		System.out.println("start:"+System.currentTimeMillis());
		while(true) {
			try {
				//从延迟队列中取值,如果没有对象过期则队列一直等待，
				Task t1 = t.take();
				if (t1 != null) {
					//修改问题的状态
					QQ q = t1.getQuestion();
					/*if(q!= null && TestQ.contians.containsKey(q.getId())){
						put(TestQ.contians.get(q.getId()),q);
						TestQ.contians.remove(q.getId());
						continue;
					}*/
					System.out.println("at task"+q+"  [Time:"+System.currentTimeMillis()+"]");
//					questionDao.setQuestionSolved(q.getId(), 5600);
				}
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}
	

/**
	 * 创建一个最初为空的新 DelayQueue
	 */
	private static final DelayQueue<Task> t = new DelayQueue<Task>();
	/**
	 * 添加任务，
	 * time 延迟时间 
	 * q 问题
	 * 用户为问题设置延迟时间 
	 */
	public void put(long time,Question q){
		//转换成ns
		long nanoTime = TimeUnit.NANOSECONDS.convert(time, TimeUnit.MILLISECONDS);
		//创建一个任务
		Task k = new Task(nanoTime,q);
		//将任务放在延迟的队列中
		t.put(k);
	}
	
	public boolean remove(Task task){
		 return t.remove(task);
	}
	
	protected DelayQueue<Task> getTT(){
		return t;
	}
}
