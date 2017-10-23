/**
 *
 *  @author Voitenkova Anna S15486
 *
 */

package ex2;


public class Writer implements Runnable {
	private Author author;

	public Writer(Author author) {
		this.author = author;
	}

	@Override
	public void run() {
		while(!author.isFlag()) {
			if (!author.isEmpty()) {
				try {
					System.out.println(author.elem());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
