package ex1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Synchronize {
	private boolean flag;
	private List<Good> list;
	public double sum;
	public int count200;
	private Good good;
	private int count;
	public boolean ready = false;
	

	
	public Synchronize() {
		this.list = new ArrayList<Good>();
		this.flag = false;
		this.sum = 0;
		this.count200 = 200;
		this.count = 0;

	}

	// getters
	public boolean isFlag() {
		return flag;
	}

	public List<Good> getList() {
		return list;
	}

	public double getSum() {
		return sum;
	}

	public int getCount200() {
		return count200;
	}

	public Good getGood() {
		return good;
	}

	// synchronized methods
	synchronized public void read() {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader("../Goods.txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		BufferedReader bufReader = new BufferedReader(fileReader);
		String currentLine = "";
		try {
			while ((currentLine = bufReader.readLine()) != null) {
				String[] words = currentLine.split("\\s");
				for (int i = 0; i < words.length - 1; i += 2) {
					good = new Good(Double.parseDouble(words[i]), Double.parseDouble(words[i + 1]));
					list.add(good);
					count++;
					if (count % 200 == 0) {
						System.out.println("created " + count + " objects");
						this.notify();
						this.flag = true;
						wait();
					}
				}
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		//System.out.println("1");
		this.ready = true;
		this.notify();

	}

	synchronized public void sum() {
		while (!flag) { // not if!!!
			try {
				if (ready) {
					System.out.println("Sum: " + sum);
					list.clear();
					break;
				}
					wait(); 
			
			// invoked on 'this'
			} catch (InterruptedException exc) {
				exc.printStackTrace();
			}
		}
	
		TeacherToldThisWouldWork : while (flag) {
			for (int i = count-200; i < count; i++) {
				sum += list.get(i).weight;
				//System.out.println(sum);
//				if (i + 1 == 100) {
//					System.out.println("counted the weight of " + (count200 - 100) + " goods");
//					this.flag = false;
//					this.notify();
//				}
				if ((i + 1) % 100 == 0) { 
					if ((i + 1) % 200 == 0) { 
					System.out.println("counted the weight of " + count200 + " goods"); 
					count200 += 200; 
					this.flag = false; 
					this.notify(); 
					continue TeacherToldThisWouldWork; 
					} 
					System.out.println("counted the weight of " + (count200 - 100) + " goods"); 
					} 
			}

//			System.out.println("counted the weight of " + count200 + " goods");
//			count200 += 200;
			//flag = true;
			//System.out.println("7");

		}
		//System.out.println(sum);
	}
}
