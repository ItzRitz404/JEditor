package Components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import Utility.ImageLoader;

public class TreeCellRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value,
			boolean sel, boolean expanded, boolean leaf, int row,
			boolean hasFocus) {

		Component c = super.getTreeCellRendererComponent(tree, value,
				selected, expanded, leaf, row, hasFocus);

		// setOpaque(true);
		setFont(new Font("Ubuntu", Font.PLAIN, 14));

		if (getText().equalsIgnoreCase("Files opened: ")) {
			return c;
		}

		setIcon(ImageLoader.loadImage("images/document_small.png"));
		if (sel) {

			// setBackground(Color.GREEN);

			setBackground((backgroundSelectionColor));
			setForeground(getBackgroundSelectionColor());
		} else {
			setOpaque(false);
		}

		return c;
	}

	@Override
	public Color getBackgroundNonSelectionColor() {
		return (null);
	}

	@Override
	public Color getBackgroundSelectionColor() {
		return Color.GREEN;
	}

	@Override
	public Color getBackground() {
		return (null);
	}

}
