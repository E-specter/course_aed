package aed.modules.queue.view;

import aed.core.view.Theme;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class QueueRenderer extends JPanel {
    private Object[] data = new Object[0];
    private int highlightIndex = -1;
    private Color highlightColor = null;
    private String statusMessage = "";
    private Color statusColor = Theme.TEXT_PRIMARY;

    public QueueRenderer() {
        setBackground(Theme.PANEL_BG);
    }

    public void setData(Object[] data) {
        this.data = data;
        clearHighlight();
        repaint();
    }

    public void highlightCell(int index, Color color, String msg) {
        this.highlightIndex = index;
        this.highlightColor = color;
        this.statusMessage = msg;
        this.statusColor = color;
        repaint();
    }

    public void clearHighlight() {
        this.highlightIndex = -1;
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

        int w = getWidth();
        int h = getHeight();
        int cellSize = 50;
        int gap = 40;

        if (data.length == 0) {
            g2.setColor(Theme.TEXT_SECONDARY);
            g2.setFont(Theme.SECTION_FONT);
            String msg = "Queue vac\u00EDo";
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(msg, (w - fm.stringWidth(msg)) / 2, h / 2);
            g2.dispose();
            return;
        }

        int totalW = data.length * (cellSize + gap) - gap;
        int startX = Math.max(80, (w - totalW) / 2);
        int y = h / 2 - cellSize / 2 - 10;

        g2.setColor(Theme.ERROR);
        g2.setFont(Theme.SMALL_FONT);
        FontMetrics fm = g2.getFontMetrics();
        String front = "FRONT \u25B6";
        g2.drawString(front, startX - fm.stringWidth(front) - 8, y + cellSize / 2 + 4);

        for (int i = 0; i < data.length; i++) {
            int x = startX + i * (cellSize + gap);

            Color bg = Theme.PANEL_BG;
            Color border = Theme.BORDER;
            if (i == highlightIndex && highlightColor != null) {
                bg = highlightColor;
                border = highlightColor.darker();
            }

            g2.setColor(bg);
            RoundRectangle2D cell = new RoundRectangle2D.Double(x, y, cellSize, cellSize, 8, 8);
            g2.fill(cell);
            g2.setColor(border);
            g2.setStroke(new BasicStroke(2));
            g2.draw(cell);

            g2.setColor(Theme.TEXT_PRIMARY);
            g2.setFont(new Font("Segoe UI", Font.BOLD, 14));
            fm = g2.getFontMetrics();
            String val = String.valueOf(data[i]);
            int tx = x + (cellSize - fm.stringWidth(val)) / 2;
            int ty = y + (cellSize + fm.getAscent()) / 2 - 2;
            g2.drawString(val, tx, ty);

            g2.setColor(Theme.TEXT_SECONDARY);
            g2.setFont(Theme.SMALL_FONT);
            String idx = "[" + i + "]";
            fm = g2.getFontMetrics();
            g2.drawString(idx, x + (cellSize - fm.stringWidth(idx)) / 2, y + cellSize + 16);

            if (i < data.length - 1) {
                int ax1 = x + cellSize;
                int ay = y + cellSize / 2;
                int ax2 = x + cellSize + gap;
                g2.setColor(Theme.INFO);
                g2.setStroke(new BasicStroke(2));
                g2.drawLine(ax1, ay, ax2, ay);
                drawArrowHead(g2, ax2, ay, Theme.INFO);
            }
        }

        fm = g2.getFontMetrics();
        String rear = "\u25C0 REAR";
        int lastX = startX + (data.length - 1) * (cellSize + gap) + cellSize + 6;
        g2.setColor(Theme.SUCCESS);
        g2.setFont(Theme.SMALL_FONT);
        g2.drawString(rear, lastX, y + cellSize / 2 + 4);

        if (!statusMessage.isEmpty()) {
            g2.setColor(statusColor);
            g2.setFont(Theme.BODY_FONT);
            fm = g2.getFontMetrics();
            g2.drawString(statusMessage, (w - fm.stringWidth(statusMessage)) / 2,
                    y + cellSize + 44);
        }

        g2.dispose();
    }

    private void drawArrowHead(Graphics2D g2, int x, int y, Color color) {
        g2.setColor(color);
        Polygon p = new Polygon();
        p.addPoint(x, y);
        p.addPoint(x - 10, y - 5);
        p.addPoint(x - 10, y + 5);
        g2.fill(p);
    }
}
