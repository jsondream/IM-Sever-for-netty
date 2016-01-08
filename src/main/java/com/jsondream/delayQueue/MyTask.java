package com.jsondream.delayQueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class MyTask implements Delayed {

	// 过期时间:单位(纳秒)
	private long triggerTime;

	public long getTriggerTime() {
		return triggerTime;
	}

	// 多少秒后过期
	public void setTriggerTime(long triggerTime) {
		this.triggerTime = TimeUnit.NANOSECONDS.convert(triggerTime, TimeUnit.SECONDS);;
	}

	@Override
	public int compareTo(Delayed other) {
		// TODO Auto-generated method stub
		if (other == this) // compare zero ONLY if same object
			return 0;
		if (other instanceof MyTask) {
			MyTask t = (MyTask) other;
			return (int) (this.triggerTime - t.getTriggerTime());
		} else {
			return 0;
		}
	}

	/**
	 * 返回与此对象相关的剩余延迟时间，以给定的时间单位表示
	 */
	@Override
	public long getDelay(TimeUnit unit) {
		return unit.convert(this.triggerTime - System.nanoTime() , TimeUnit.NANOSECONDS);
	}

}
