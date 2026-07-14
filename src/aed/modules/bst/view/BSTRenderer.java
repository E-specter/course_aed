package aed.modules.bst.view;

import aed.core.view.Theme;
import aed.modules.bst.model.BinarySearchTree;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class BSTRenderer extends JPanel {
    private BinarySearchTree.Node<Integer> root;
    private Integer highlightValue = null;
    private Color highlightColor = null;
    private String statusMessage = "";
    private Color statusColor = Theme.TEXT_PRIMARY;

    private static final int RADIUS = 22;
    private static final int LEVEL_H = 65;

    public BSTRenderer() {
        setBackground(Theme.PANEL_BG);
    }

    public void setRoot(BinarySearchTree.Node<Integer> root) {
        this.root = root;
        clearHighlight();
        repaint();
    }

    public void highlightNode(Integer value, Color color, String msg) {
        this.highlightValue = value;
        this.highlightColor = color;
        this.statusMessage = msg;
        this.statusColor = color;
        repaint();
    }

    public void clearHighlight() {
        this.highlightValue = null;
        this.highlightColor = null;
        this.statusMessage = "";
        repaint();
    }

    public void setStatusMessage(String msg, Color color) {
        this.statusMessage = msg;
        this.statusColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (root == null) {
            g2.setColor(Theme.TEXT_SECONDARY);
            g2.setFont(Theme.SECTION_FONT);
            String msg = "BST vacío";
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(msg, (getWidth() - fm.stringWidth(msg)) / 2, getHeight() / 2);
            g2.dispose();
            return;
        }

        Map<BinarySearchTree.Node<Integer>, Point> positions = new HashMap<>();
        int totalW = layout(root, 0, positions);
        int startX = (getWidth() - totalW) / 2 + RADIUS;

        Map<Integer, Point> lines = new HashMap<>();
        for (Map.Entry<BinarySearchTree.Node<Integer>, Point> e : positions.entrySet()) {
            BinarySearchTree.Node<Integer> node = e.getKey();
            Point p = e.getValue();
            p.x = p.x + startX;
            if (node.left != null) lines.put(System.identityHashCode(node.left), p);
            if (node.right != null) lines.put(System.identityHashCode(node.right), p);
        }

        for (Map.Entry<BinarySearchTree.Node<Integer>, Point> e : positions.entrySet()) {
            BinarySearchTree.Node<Integer> node = e.getKey();
            Point p = e.getValue();
            Point parentP = lines.get(System.identityHashCode(node));
            if (parentP != null) {
                g2.setColor(new Color(0xBB, 0xDE, 0xFB));
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(parentP.x, parentP.y + RADIUS, p.x, p.y - RADIUS);
            }
        }

        for (Map.Entry<BinarySearchTree.Node<Integer>, Point> e : positions.entrySet()) {
            BinarySearchTree.Node<Integer> node = e.getKey();
            Point p = e.getValue();

            Color bg = Theme.PANEL_BG;
            Color border = Theme.BORDER;
            if (node.data.equals(highlightValue) && highlightColor != null) {
                bg = highlightColor;
                border = highlightColor.darker();
            }

            g2.setColor(bg);
            g2.fillOval(p.x - RADIUS, p.y - RADIUS, RADIUS * 2, RADIUS * 2);
            g2.setColor(border);
            g2.setStroke(new BasicStroke(2));
            g2.drawOval(p.x - RADIUS, p.y - RADIUS, RADIUS * 2, RADIUS * 2);

            g2.setColor(Theme.TEXT_PRIMARY);
            g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
            FontMetrics fm = g2.getFontMetrics();
            String val = String.valueOf(node.data);
            int tx = p.x - fm.stringWidth(val) / 2;
            int ty = p.y + fm.getAscent() / 2 - 1;
            g2.drawString(val, tx, ty);
        }

        if (!statusMessage.isEmpty()) {
            g2.setColor(statusColor);
            g2.setFont(Theme.BODY_FONT);
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(statusMessage, (getWidth() - fm.stringWidth(statusMessage)) / 2,
                    getHeight() - 20);
        }

        g2.dispose();
    }

    private int layout(BinarySearchTree.Node<Integer> node, int depth,
                       Map<BinarySearchTree.Node<Integer>, Point> positions) {
        if (node == null) return 0;
        int leftW = layout(node.left, depth + 1, positions);
        int rightW = layout(node.right, depth + 1, positions);
        int myX = leftW + RADIUS;
        int y = depth * LEVEL_H + 40;
        positions.put(node, new Point(myX, y));
        return leftW + RADIUS * 2 + rightW;
    }
}
