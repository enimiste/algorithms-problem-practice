package com.nouni.bytebyByte.StackFromQueues;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class StackFromQueues {

	public static void main(String[] args) {
		LIFOStack<Integer> stack = new LIFOStackQueueImpl<Integer>();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		Integer e;
		while((e = stack.pop()) != null) {
			System.out.println(e);
		}
	}

}

interface LIFOStack<T> {
	void push(T t);
	T pop();
}

class LIFOStackQueueImpl<T> implements LIFOStack<T> {
	private Queue<T> temp;
	private Queue<T> queue;
	
	public LIFOStackQueueImpl() {
		queue = new LinkedBlockingQueue<T>();
		temp = new LinkedBlockingQueue<T>();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void push(T t) {
		temp.offer(t);
		while(!queue.isEmpty()) {
			temp.offer(queue.poll());
		}
		Queue[] objs = {temp, queue};
		temp = objs[1];
		queue = objs[0];
	}

	@Override
	public T pop() {
		return queue.poll();
	}
	
}
