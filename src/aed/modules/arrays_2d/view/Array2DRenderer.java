package aed.modules.arrays_2d.view;

import aed.core.view.Theme;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Array2DRenderer extends JPanel {
    private int[][] data = new int[0][0];
    private int highlightRow = -1;
    private int highlightCol = -1;
    private Color highlightColor = null;
    private String statusMessage = "";
    private Color statusColor = Theme.TEXT_PRIMARY;

    public Array2DRenderer() {
        setBackground(Theme.PANEL_BG);
    }

    public void setData(int[][] data) {
        this.data = data;
        clearHighlight();
        repaint();
    }

    public void highlightCell(int row, int col, Color color, String message) {
        this.highlightRow = row;
        this.highlightCol = col;
        this.highlightColor = color;
        this.statusMessage = message;
        this.statusColor = color;
        repaint();
    }

    public void clearHighlight() {
        this.highlightRow = -1;
        this.highlightCol = -1;
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

        if (data.length == 0 || data[0].length == 0) {
            g2.setColor(Theme.TEXT_SECONDARY);
            g2.setFont(Theme.SECTION_FONT);
            String msg = "Matriz vac\u00EDa";
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(msg, (w - fm.stringWidth(msg)) / 2, h / 2);
            g2.dispose();
            return;
        }

        int rows = data.length;
        int cols = data[0].length;
        int headerH = 24;
        int headerW = 30;
        int gap = 3;
        int cellSize = Math.min(54, Math.max(28,
                (w - headerW - 30) / cols - gap));
        int gridW = cols * (cellSize + gap);
        int gridH = rows * (cellSize + gap);
        int startX = (w - gridW - headerW) / 2 + headerW;
        int startY = (h - gridH - headerH - 30) / 2 + headerH;

        g2.setFont(Theme.SMALL_FONT);
        FontMetrics fm = g2.getFontMetrics();

        for (int c = 0; c < cols; c++) {
            String label = "C" + c;
            int x = startX + c * (cellSize + gap) + (cellSize - fm.stringWidth(label)) / 2;
            g2.setColor(Theme.TEXT_SECONDARY);
            g2.drawString(label, x, startY - headerH / 2 + 4);
        }
        for (int r = 0; r < rows; r++) {
            String label = "R" + r;
            int y = startY + r * (cellSize + gap) + (cellSize + fm.getAscent()) / 2 - 4;
            g2.setColor(Theme.TEXT_SECONDARY);
            g2.drawString(label, startX - headerW + 4, y);
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int x = startX + c * (cellSize + gap);
                int y = startY + r * (cellSize + gap);

                Color bg = Theme.PANEL_BG;
                Color border = Theme.BORDER;
                if (r == highlightRow && c == highlightCol && highlightColor != null) {
                    bg = highlightColor;
                    border = highlightColor.darker();
                }

                g2.setColor(bg);
                RoundRectangle2D cell = new RoundRectangle2D.Double(x, y, cellSize, cellSize, 4, 4);
                g2.fill(cell);
                g2.setColor(border);
                g2.setStroke(new BasicStroke(1.5f));
                g2.draw(cell);

                g2.setColor(Theme.TEXT_PRIMARY);
                g2.setFont(new Font("Segoe UI", Font.BOLD, Math.min(14, cellSize / 2 + 2)));
                fm = g2.getFontMetrics();
                String val = String.valueOf(data[r][c]);
                int tx = x + (cellSize - fm.stringWidth(val)) / 2;
                int ty = y + (cellSize + fm.getAscent()) / 2 - 2;
                g2.drawString(val, tx, ty);
            }
        }

        g2.setColor(Theme.TEXT_SECONDARY);
        g2.setFont(Theme.SMALL_FONT);
        String dim = rows + "\u00D7" + cols;
        fm = g2.getFontMetrics();
        g2.drawString(dim, (w - fm.stringWidth(dim)) / 2, startY + gridH + 24);

        if (!statusMessage.isEmpty()) {
            g2.setColor(statusColor);
            g2.setFont(Theme.SMALL_FONT);
            fm = g2.getFontMetrics();
            g2.drawString(statusMessage, (w - fm.stringWidth(statusMessage)) / 2,
                    startY + gridH + 44);
        }

        g2.dispose();
    }
}
