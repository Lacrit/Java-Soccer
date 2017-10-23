package zad2;

public class Freesia extends Flowers {

	public Freesia(int i) {
		super(i);
	}

	@Override
	public String toString() {
		return getName() + " ( " + getColor() + ", " + getAmount() + " ) ";
	}

	@Override
	public String getName() {
		return "freesia";
	}

	@Override
	public String getColor() {
		return "pink";
	}

}
