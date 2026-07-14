package aed.modules.arrays_1d.view;

import aed.core.view.Theme;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Array1DRenderer extends JPanel {
    private int[] data = new int[0];
    private int highlightIndex = -1;
    private Color highlightColor = null;
    private String statusMessage = "";
    private Color statusColor = Theme.TEXT_PRIMARY;

    public Array1DRenderer() {
        setBackground(Theme.PANEL_BG);
        setFont(Theme.BODY_FONT);
    }

    public void setData(int[] data) {
        this.data = data;
        clearHighlight();
        repaint();
    }

    public void highlightCell(int index, Color color, String message) {
        this.highlightIndex = index;
        this.highlightColor = color;
        this.statusMessage = message;
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

        int width = getWidth();
        int height = getHeight();
        int cellSize = Math.min(60, Math.max(30, (width - 80) / Math.max(data.length, 1)));
        int cellGap = 4;
        int totalWidth = data.length * (cellSize + cellGap) - cellGap;
        int startX = (width - totalWidth) / 2;
        int startY = height / 2 - cellSize - 20;

        if (data.length == 0) {
            g2.setColor(Theme.TEXT_SECONDARY);
            g2.setFont(Theme.SECTION_FONT);
            String msg = "Array vac\u00EDo \u2014 Agregue elementos para comenzar";
            FontMetrics fm = g2.getFontMetrics();
            int x = (width - fm.stringWidth(msg)) / 2;
            g2.drawString(msg, x, height / 2 - 10);
            g2.dispose();
            return;
        }

        int indicesAbove = 16;
        startY += indicesAbove;

        for (int i = 0; i < data.length; i++) {
            int x = startX + i * (cellSize + cellGap);
            int y = startY;

            Color bgColor = Theme.PANEL_BG;
            Color borderColor = Theme.BORDER;
            if (i == highlightIndex && highlightColor != null) {
                bgColor = highlightColor;
                borderColor = highlightColor.darker();
            }

            g2.setColor(bgColor);
            RoundRectangle2D cell = new RoundRectangle2D.Double(x, y, cellSize, cellSize, 6, 6);
            g2.fill(cell);
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(2));
            g2.draw(cell);

            g2.setColor(Theme.TEXT_PRIMARY);
            g2.setFont(new Font("Segoe UI", Font.BOLD, Math.min(16, cellSize / 2 + 4)));
            FontMetrics fm = g2.getFontMetrics();
            String val = String.valueOf(data[i]);
            int textX = x + (cellSize - fm.stringWidth(val)) / 2;
            int textY = y + (cellSize + fm.getAscent()) / 2 - 2;
            g2.drawString(val, textX, textY);

            g2.setColor(Theme.TEXT_SECONDARY);
            g2.setFont(Theme.SMALL_FONT);
            fm = g2.getFontMetrics();
            String idx = "[" + i + "]";
            int idxX = x + (cellSize - fm.stringWidth(idx)) / 2;
            int idxY = y + cellSize + 18;
            g2.drawString(idx, idxX, idxY);
        }

        if (!statusMessage.isEmpty()) {
            g2.setColor(statusColor);
            g2.setFont(Theme.BODY_FONT);
            FontMetrics fm = g2.getFontMetrics();
            int x = (width - fm.stringWidth(statusMessage)) / 2;
            int y = startY + cellSize + 50;
            g2.drawString(statusMessage, x, y);
        }

        g2.dispose();
    }
}
