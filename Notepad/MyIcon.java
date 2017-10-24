
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import javax.swing.Icon;

public class MyIcon implements Icon{
	Color color;

	  public MyIcon(Color color) {
	    this.color = color;
	  }

	  public void paintIcon(Component c, Graphics g, int x, int y) {
	    g.setColor(color);
	    g.fillOval(x, y, getIconWidth(), getIconHeight());
	  }

	  public int getIconWidth() {
	    return 18;
	  }

	  public int getIconHeight() {
	    return 18;
	  }
}
