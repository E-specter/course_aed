package aed.modules.circular_list.view;

import aed.core.view.Theme;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CircularListRenderer extends JPanel {
    private Object[] data = new Object[0];
    private int highlightIndex = -1;
    private Color highlightColor = null;
    private String statusMessage = "";
    private Color statusColor = Theme.TEXT_PRIMARY;

    public CircularListRenderer() {
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

        if (data.length == 0) {
            g2.setColor(Theme.TEXT_SECONDARY);
            g2.setFont(Theme.SECTION_FONT);
            String msg = "Lista circular vac\u00EDa";
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(msg, (w - fm.stringWidth(msg)) / 2, h / 2);
            g2.dispose();
            return;
        }

        int cx = w / 2;
        int cy = h / 2 - 20;
        int radius = Math.min(w, h) / 2 - cellSize - 20;
        if (data.length == 1) radius = 0;

        for (int i = 0; i < data.length; i++) {
            double angle = 2 * Math.PI * i / data.length - Math.PI / 2;
            int x = cx + (int) (radius * Math.cos(angle)) - cellSize / 2;
            int y = cy + (int) (radius * Math.sin(angle)) - cellSize / 2;

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
            FontMetrics fm = g2.getFontMetrics();
            String val = String.valueOf(data[i]);
            int tx = x + (cellSize - fm.stringWidth(val)) / 2;
            int ty = y + (cellSize + fm.getAscent()) / 2 - 2;
            g2.drawString(val, tx, ty);

            g2.setColor(Theme.TEXT_SECONDARY);
            g2.setFont(Theme.SMALL_FONT);
            String idx = "[" + i + "]";
            fm = g2.getFontMetrics();
            g2.drawString(idx, x + (cellSize - fm.stringWidth(idx)) / 2, y + cellSize + 16);

            int nextIdx = (i + 1) % data.length;
            double nextAngle = 2 * Math.PI * nextIdx / data.length - Math.PI / 2;
            int nx = cx + (int) (radius * Math.cos(nextAngle));
            int ny = cy + (int) (radius * Math.sin(nextAngle));

            if (data.length > 1) {
                int ax1 = x + cellSize / 2;
                int ay1 = y + cellSize / 2;
                double dx = nx - ax1;
                double dy = ny - ay1;
                double dist = Math.sqrt(dx * dx + dy * dy);
                if (dist > 0) {
                    double ux = dx / dist;
                    double uy = dy / dist;
                    int fromX = ax1 + (int) (ux * cellSize / 2);
                    int fromY = ay1 + (int) (uy * cellSize / 2);
                    int toX = nx - (int) (ux * cellSize / 2);
                    int toY = ny - (int) (uy * cellSize / 2);

                    g2.setColor(Theme.PRIMARY);
                    g2.setStroke(new BasicStroke(2));
                    g2.drawLine(fromX, fromY, toX, toY);

                    int arrowSize = 10;
                    Polygon head = new Polygon();
                    head.addPoint(toX, toY);
                    head.addPoint(toX - (int) (arrowSize * Math.cos(Math.atan2(dy, dx) - 0.4)),
                            toY - (int) (arrowSize * Math.sin(Math.atan2(dy, dx) - 0.4)));
                    head.addPoint(toX - (int) (arrowSize * Math.cos(Math.atan2(dy, dx) + 0.4)),
                            toY - (int) (arrowSize * Math.sin(Math.atan2(dy, dx) + 0.4)));
                    g2.setColor(Theme.PRIMARY);
                    g2.fill(head);
                }
            }
        }

        if (data.length == 1) {
            g2.setColor(Theme.PRIMARY);
            g2.setStroke(new BasicStroke(2));
            int sx = cx + cellSize / 2 + 5;
            int sy = cy;
            int ex = cx + cellSize / 2 + 35;
            g2.drawArc(cx - 25, cy - 30, 60, 60, 0, 270);
            Polygon head = new Polygon();
            head.addPoint(cx + 5, cy - 30);
            head.addPoint(cx - 3, cy - 38);
            head.addPoint(cx - 3, cy - 22);
            g2.fill(head);
        }

        if (!statusMessage.isEmpty()) {
            g2.setColor(statusColor);
            g2.setFont(Theme.BODY_FONT);
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(statusMessage, (w - fm.stringWidth(statusMessage)) / 2, h - 20);
        }

        g2.dispose();
    }
}
