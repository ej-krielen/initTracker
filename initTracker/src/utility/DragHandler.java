package utility;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.dnd.DragSource;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

//http://java-swing-tips.blogspot.jp/2013/04/rearrange-jtoolbar-icon-by-drag-and-drop.html

/**
 * Functionality for dragging and dropping
 * 
 * @author Erik-Jan Krielen erik-jan.krielen@atos.net
 * @version 0.1 Current version number of program
 * @since January 13th 2015 Creation of this file
 * @update January 13th 2015 Latest update of this file
 * @LatestUpdate Added drop and drag functionality
 * 
 */
public class DragHandler extends MouseAdapter {
	private final JWindow window = new JWindow();
	private Component draggingComonent;
	private int index = -1;
	private Component gap;
	private Point startPt;
	private final int gestureMotionThreshold = DragSource.getDragThreshold();

	public DragHandler() {
		super();
		window.setBackground(new Color(0, true));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JComponent parent = (JComponent) e.getComponent();
		if (parent.getComponentCount() <= 1) {
			startPt = null;
			return;
		}
		startPt = e.getPoint();
	}

	private void startDragging(JComponent parent, Point pt) {
		Component c = parent.getComponentAt(pt);
		index = parent.getComponentZOrder(c);
		if (Objects.equals(c, parent) || index < 0) {
			return;
		}
		draggingComonent = c;
		gap = Box.createRigidArea(c.getSize());
		swapComponentLocation(parent, c, gap, index);

		window.add(draggingComonent);
		window.pack();

		Dimension d = draggingComonent.getPreferredSize();
		Point p = new Point(pt.x - d.width / 2, pt.y - d.height / 2);
		SwingUtilities.convertPointToScreen(p, parent);
		window.setLocation(p);
		window.setVisible(true);
	}

	private static void swapComponentLocation(Container parent,
			Component remove, Component add, int idx) {
		parent.remove(remove);
		parent.add(add, idx);
		parent.revalidate();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point pt = e.getPoint();
		JComponent parent = (JComponent) e.getComponent();

		if (startPt != null
				&& Math.sqrt(Math.pow(pt.x - startPt.x, 2)
						+ Math.pow(pt.y - startPt.y, 2)) > gestureMotionThreshold) {
			startDragging(parent, pt);
			startPt = null;
			return;
		}
		if (!window.isVisible() || draggingComonent == null) {
			return;
		}

		Dimension d = draggingComonent.getPreferredSize();
		Point p = new Point(pt.x - d.width / 2, pt.y - d.height / 2);
		SwingUtilities.convertPointToScreen(p, parent);
		window.setLocation(p);

		for (int i = 0; i < parent.getComponentCount(); i++) {
			Component c = parent.getComponent(i);
			Rectangle r = c.getBounds();
			int ht2 = r.height / 2;
			Rectangle r1 = new Rectangle(r.x, r.y, r.width, ht2);
			Rectangle r2 = new Rectangle(r.x, r.y + ht2, r.width, ht2);
			if (r1.contains(pt)) {
				swapComponentLocation(parent, gap, gap, i - 1 > 0 ? i : 0);
				return;
			} else if (r2.contains(pt)) {
				swapComponentLocation(parent, gap, gap, i);
				return;
			}
		}
		parent.remove(gap);
		parent.revalidate();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		startPt = null;
		if (!window.isVisible() || draggingComonent == null) {
			return;
		}
		Point pt = e.getPoint();
		JComponent parent = (JComponent) e.getComponent();

		Component cmp = draggingComonent;
		draggingComonent = null;
		window.setVisible(false);

		for (int i = 0; i < parent.getComponentCount(); i++) {
			Component c = parent.getComponent(i);
			Rectangle r = c.getBounds();
			int wd2 = r.width / 2;
			Rectangle r1 = new Rectangle(r.x, r.y, wd2, r.height);
			Rectangle r2 = new Rectangle(r.x + wd2, r.y, wd2, r.height);
			if (r1.contains(pt)) {
				swapComponentLocation(parent, gap, cmp, i - 1 > 0 ? i : 0);
				return;
			} else if (r2.contains(pt)) {
				swapComponentLocation(parent, gap, cmp, i);
				return;
			}
		}
		if (parent.getBounds().contains(pt)) {
			swapComponentLocation(parent, gap, cmp, parent.getComponentCount());
		} else {
			swapComponentLocation(parent, gap, cmp, index);
		}
		parent.revalidate();
		parent.repaint();
	}
}