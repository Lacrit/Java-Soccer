
import java.util.ArrayList;
import java.util.List;

public abstract class BoxParent {

	protected List<Flowers> list = new ArrayList<>();

	public List<Flowers> getList() {
		return list;
	}

	@Override
	public abstract String toString();

}
