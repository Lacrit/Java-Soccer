
public class ShoppingCart extends BoxParent {

	@Override
	public String toString() {
		String SCcontent = "";
		if (list.size() == 0) {
			SCcontent = " empty ";
		} else {
			for (int i = 0; i < list.size(); i++) {
				SCcontent += list.get(i) + " ";
			}
		}
		return "shopping cart: " + SCcontent;
	}

}
