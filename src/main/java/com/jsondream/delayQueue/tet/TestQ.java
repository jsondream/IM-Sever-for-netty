package com.jsondream.delayQueue.tet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class TestQ {

	public static Map<String, Long> contians = new HashMap<>();

	public static Set<String> cacheOutId = new HashSet<>();

	public static void main(String[] args) throws Exception {
		contians.put("3",4000l);
		
		Job j = Job.getInstance();
		j.init();
		// TODO Auto-generated method stub
		List<Question> qs = new ArrayList<Question>();
		//从数据库中取出10条数据
		for (int i = 1; i <= 10; i++) {
//			Question q = questionDao.queryQuestionById(i) ;
			if(i==5){
				Question q = new Question(String.valueOf(3),"outId"+5);
				qs.add(q);
				continue;
			}
			Question q = new Question(String.valueOf(i),"outId"+i);
			qs.add(q);
		}
		Random random = new Random();
		List<Integer> timeList = new ArrayList<>();
		//设置数据库中数据的过期时间
		for (int k = 1; k <= 10; k++) {
			
			if(k==6){
				
			}
			int a =random.nextInt(10)*2*1000;
			timeList.add(a);
			j.put(a,qs.get(k-1));
			cacheOutId.add("outId"+k);
		}
		boolean flag = true;
		while(flag){
			flag = j.getTT().remove(new Task(0, new Question("3", "outId3")));
		}
		System.out.println("||||"+new Task(1, new Question("3", "outId3")).
				equals(new Task(11111, new Question("3", "outId3"))));
		System.out.println((int)(1448852889532l*33l));
		Thread.interrupted();
		System.out.println(timeList);
		System.out.println("["+TestQ.class.getSimpleName()+"]loading......");
		Thread.sleep(20000);

	}

}
