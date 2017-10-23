/**
 *
 *  @author Voitenkova Anna S15486
 *
 */

package ex2;

import java.util.concurrent.ArrayBlockingQueue;


public class Author implements Runnable {
	private ArrayBlockingQueue<String> queue;
	private String[] args;
	private boolean flag = false;

	public Author(String[] args) {
		this.args = args;
		queue = new ArrayBlockingQueue<String>(256);
	}


	public boolean isFlag() {
		return flag;
	}

	public boolean isEmpty() { 
		return queue.isEmpty();
	}

	public String elem() throws InterruptedException {
		return queue.take();
	}
	@Override
	public void run() {
		
		for ( int i = 0; i < args.length; i++ ) {
			try {
				queue.put(args[i]);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if ( i == args.length-1 ) {
				flag = true;
			}
		}
	
	}
}
