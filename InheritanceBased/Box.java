package zad2;

public class Box extends BoxParent {

	private Customer owner;

	public Box(Customer owner) {
		this.owner = owner;
	}

	public Customer getOwner() {
		return owner;
	}

	@Override
	public String toString() {
		String Bcontent = "";
		if (list.size() == 0) {
			Bcontent = " empty ";
		} else {
			for (int i = 0; i < list.size(); i++) {
				Bcontent += list.get(i) + " ";
			}
		}
		return "box: " + Bcontent;
	}

}
