/**
 *
 *  @author Voitenkova Anna S15486
 *
 */

package ex1;

public class Main {

  public static void main(String[] args) throws InterruptedException {
	  Synchronize syn = new Synchronize();
		ThreadA a = new ThreadA(syn);
		ThreadB b = new ThreadB(syn);
		a.start();
		b.start();
  }
}
