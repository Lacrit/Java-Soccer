package zad2;

public class Peony extends Flowers {

	public Peony(int i) {
		super(i);
	}

	@Override
	public String toString() {
		return getName() + " ( " + getColor() + ", " + getAmount() + " ) ";
	}

	@Override
	public String getName() {
		return "peony";
	}

	@Override
	public String getColor() {
		return "purple";
	}

}
