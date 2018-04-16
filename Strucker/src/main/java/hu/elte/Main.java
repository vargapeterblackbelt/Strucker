package hu.elte;

import hu.elte.strucker.StruckerApplication;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellEditor;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.SystemColor;
import java.util.EventObject;
import java.util.Hashtable;
import java.util.Vector;

public class Main {
    public static final String VERSION = "1.0";

    public static void main(String[] args) {
        new StruckerApplication();
    }

    public class ZebraJTree extends JTree {
        public Color rowColors[] = new Color[2];
        private boolean drawStripes = false;

        public ZebraJTree() {
        }

        public ZebraJTree(Hashtable<?, ?> value) {
            super(value);
        }

        public ZebraJTree(Object[] value) {
            super(value);
        }

        public ZebraJTree(TreeModel newModel) {
            super(newModel);
        }

        public ZebraJTree(TreeNode root) {
            super(root);
        }

        public ZebraJTree(TreeNode root,
                          boolean asksAllowsChildren) {
            super(root, asksAllowsChildren);
        }

        public ZebraJTree(Vector<?> value) {
            super(value);
        }

        /**
         * Add zebra stripes to the background.
         */
        public void paintComponent(Graphics g) {
            if (!(drawStripes = isOpaque())) {
                super.paintComponent(g);
                return;
            }

            // Paint zebra background stripes
            updateZebraColors();
            final Insets insets = getInsets();
            final int w = getWidth() - insets.left - insets.right;
            final int h = getHeight() - insets.top - insets.bottom;
            final int x = insets.left;
            int y = insets.top;
            int nRows = 0;
            int startRow = 0;
            int rowHeight = getRowHeight();
            if (rowHeight > 0)
                nRows = h / rowHeight;
            else {
                // Paint non-uniform height rows first
                final int nItems = getRowCount();
                rowHeight = 17; // A default for empty trees
                for (int i = 0; i < nItems; i++, y += rowHeight) {
                    rowHeight = getRowBounds(i).height;
                    g.setColor(rowColors[i & 1]);
                    g.fillRect(x, y, w, rowHeight);
                }
                // Use last row height for remainder of tree area
                nRows = nItems + (insets.top + h - y) / rowHeight;
                startRow = nItems;
            }
            for (int i = startRow; i < nRows; i++, y += rowHeight) {
                g.setColor(rowColors[i & 1]);
                g.fillRect(x, y, w, rowHeight);
            }
            final int remainder = insets.top + h - y;
            if (remainder > 0) {
                g.setColor(rowColors[nRows & 1]);
                g.fillRect(x, y, w, remainder);
            }

            // Paint component
            setOpaque(false);
            super.paintComponent(g);
            setOpaque(true);
        }

        /**
         * Wrap cell renderer and editor to add zebra background stripes.
         */
        private class RendererEditorWrapper
                implements TreeCellRenderer,
                TreeCellEditor {
            public TreeCellRenderer ren = null;
            public TreeCellEditor ed = null;

            public Component getTreeCellRendererComponent(
                    JTree tree, Object value,
                    boolean selected, boolean expanded,
                    boolean leaf, int row, boolean hasFocus) {
                final Component c =
                        ren.getTreeCellRendererComponent(
                                tree, value, selected, expanded,
                                leaf, row, hasFocus);
                if (selected || !drawStripes)
                    return c;
                if (!(c instanceof DefaultTreeCellRenderer))
                    c.setBackground(rowColors[row & 1]);
                else
                    ((DefaultTreeCellRenderer) c).
                            setBackgroundNonSelectionColor(rowColors[row & 1]);
                return c;
            }

            public Component getTreeCellEditorComponent(
                    JTree tree, Object value,
                    boolean selected, boolean expanded,
                    boolean leaf, int row) {
                final Component c =
                        ed.getTreeCellEditorComponent(
                                tree, value, selected, expanded, leaf, row);
                if (!selected && drawStripes)
                    c.setBackground(rowColors[row & 1]);
                return c;
            }

            public void addCellEditorListener(
                    CellEditorListener l) {
                ed.addCellEditorListener(l);
            }

            public void cancelCellEditing() {
                ed.cancelCellEditing();
            }

            public Object getCellEditorValue() {
                return ed.getCellEditorValue();
            }

            public boolean isCellEditable(
                    EventObject anEvent) {
                return ed.isCellEditable(anEvent);
            }

            public void removeCellEditorListener(
                    CellEditorListener l) {
                ed.removeCellEditorListener(l);
            }

            public boolean shouldSelectCell(
                    EventObject anEvent) {
                return ed.shouldSelectCell(anEvent);
            }

            public boolean stopCellEditing() {
                return ed.stopCellEditing();
            }
        }

        private RendererEditorWrapper wrapper = null;

        /**
         * Return the wrapped cell renderer.
         */
        public TreeCellRenderer getCellRenderer() {
            final TreeCellRenderer ren = super.getCellRenderer();
            if (ren == null)
                return null;
            if (wrapper == null)
                wrapper = new RendererEditorWrapper();
            wrapper.ren = ren;
            return wrapper;
        }

        /**
         * Return the wrapped cell editor.
         */
        public TreeCellEditor getCellEditor() {
            final TreeCellEditor ed = super.getCellEditor();
            if (ed == null)
                return null;
            if (wrapper == null)
                wrapper = new RendererEditorWrapper();
            wrapper.ed = ed;
            return wrapper;
        }

        /**
         * Compute zebra background stripe colors.
         */
        private void updateZebraColors() {
            if ((rowColors[0] = getBackground()) == null) {
                rowColors[0] = rowColors[1] = Color.white;
                return;
            }
            Color sel = UIManager.getColor(
                    "Tree.selectionBackground");
            if (sel == null)
                sel = SystemColor.textHighlight;
            if (sel == null) {
                rowColors[1] = rowColors[0];
                return;
            }
            final float[] bgHSB = Color.RGBtoHSB(
                    rowColors[0].getRed(), rowColors[0].getGreen(),
                    rowColors[0].getBlue(), null);
            final float[] selHSB = Color.RGBtoHSB(
                    sel.getRed(), sel.getGreen(), sel.getBlue(), null);
            rowColors[1] = Color.getHSBColor(
                    (selHSB[1] == 0.0 || selHSB[2] == 0.0) ? bgHSB[0] : selHSB[0],
                    0.1f * selHSB[1] + 0.9f * bgHSB[1],
                    bgHSB[2] + ((bgHSB[2] < 0.5f) ? 0.05f : -0.05f));
        }
    }
}
