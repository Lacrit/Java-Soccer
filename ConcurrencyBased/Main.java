

public class Main {

  public static void main(String[] args) throws InterruptedException {
	  Synchronize syn = new Synchronize();
		ThreadA a = new ThreadA(syn);
		ThreadB b = new ThreadB(syn);
		a.start();
		b.start();
  }
}
