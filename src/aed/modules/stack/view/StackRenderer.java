package aed.modules.stack.view;

import aed.core.view.Theme;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class StackRenderer extends JPanel {
    private Object[] data = new Object[0];
    private int highlightIndex = -1;
    private Color highlightColor = null;
    private String statusMessage = "";
    private Color statusColor = Theme.TEXT_PRIMARY;

    public StackRenderer() {
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
        int cellW = 80;
        int cellH = 40;

        if (data.length == 0) {
            g2.setColor(Theme.TEXT_SECONDARY);
            g2.setFont(Theme.SECTION_FONT);
            String msg = "Stack vac\u00EDo";
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(msg, (w - fm.stringWidth(msg)) / 2, h / 2);
            g2.dispose();
            return;
        }

        int totalH = data.length * (cellH + 4) - 4;
        int startX = (w - cellW) / 2;
        int startY = (h - totalH) / 2;

        g2.setColor(Theme.PRIMARY);
        g2.setFont(Theme.SMALL_FONT);
        FontMetrics fm = g2.getFontMetrics();
        String topLabel = "TOP \u25B2";
        g2.drawString(topLabel, startX + cellW + 8, startY + cellH / 2 + 4);

        for (int i = 0; i < data.length; i++) {
            int x = startX;
            int y = startY + i * (cellH + 4);

            Color bg = Theme.PANEL_BG;
            Color border = Theme.BORDER;
            if (i == highlightIndex && highlightColor != null) {
                bg = highlightColor;
                border = highlightColor.darker();
            }

            g2.setColor(bg);
            RoundRectangle2D cell = new RoundRectangle2D.Double(x, y, cellW, cellH, 6, 6);
            g2.fill(cell);
            g2.setColor(border);
            g2.setStroke(new BasicStroke(2));
            g2.draw(cell);

            g2.setColor(Theme.TEXT_PRIMARY);
            g2.setFont(new Font("Segoe UI", Font.BOLD, 14));
            fm = g2.getFontMetrics();
            String val = String.valueOf(data[i]);
            int tx = x + (cellW - fm.stringWidth(val)) / 2;
            int ty = y + (cellH + fm.getAscent()) / 2 - 2;
            g2.drawString(val, tx, ty);

            g2.setColor(Theme.TEXT_SECONDARY);
            g2.setFont(Theme.SMALL_FONT);
            String idx = "[" + i + "]";
            fm = g2.getFontMetrics();
            g2.drawString(idx, x - fm.stringWidth(idx) - 8, y + cellH / 2 + 5);
        }

        if (!statusMessage.isEmpty()) {
            g2.setColor(statusColor);
            g2.setFont(Theme.BODY_FONT);
            fm = g2.getFontMetrics();
            g2.drawString(statusMessage, (w - fm.stringWidth(statusMessage)) / 2,
                    startY + totalH + 30);
        }

        g2.dispose();
    }
}
