package aed.modules.arrays_3d.view;

import aed.core.view.Theme;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Array3DRenderer extends JPanel {
    private int[][] sliceData = new int[0][0];
    private int currentDepth = 0;
    private int totalDepth = 0;
    private int highlightR = -1, highlightC = -1;
    private Color highlightColor = null;
    private String statusMessage = "";
    private Color statusColor = Theme.TEXT_PRIMARY;

    public Array3DRenderer() {
        setBackground(Theme.PANEL_BG);
    }

    public void setSlice(int[][] slice, int depthIdx, int totalDepth) {
        this.sliceData = slice;
        this.currentDepth = depthIdx;
        this.totalDepth = totalDepth;
        clearHighlight();
        repaint();
    }

    public void highlightCell(int r, int c, Color color, String msg) {
        this.highlightR = r;
        this.highlightC = c;
        this.highlightColor = color;
        this.statusMessage = msg;
        this.statusColor = color;
        repaint();
    }

    public void clearHighlight() {
        this.highlightR = -1;
        this.highlightC = -1;
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

        g2.setColor(Theme.PRIMARY);
        g2.setFont(Theme.SECTION_FONT);
        String header = "Capa " + (currentDepth + 1) + " / " + totalDepth;
        FontMetrics fm = g2.getFontMetrics();
        g2.drawString(header, (w - fm.stringWidth(header)) / 2, 28);

        if (sliceData.length == 0 || sliceData[0].length == 0) {
            g2.setColor(Theme.TEXT_SECONDARY);
            g2.setFont(Theme.SECTION_FONT);
            String msg = "Sin datos";
            fm = g2.getFontMetrics();
            g2.drawString(msg, (w - fm.stringWidth(msg)) / 2, h / 2);
            g2.dispose();
            return;
        }

        int rows = sliceData.length;
        int cols = sliceData[0].length;
        int headerH = 24;
        int headerW = 30;
        int gap = 3;
        int cellSize = Math.min(52, Math.max(24,
                (w - headerW - 30) / cols - gap));
        int gridW = cols * (cellSize + gap);
        int gridH = rows * (cellSize + gap);
        int startX = (w - gridW - headerW) / 2 + headerW;
        int startY = (h - gridH - headerH - 40) / 2 + headerH + 20;

        g2.setFont(Theme.SMALL_FONT);
        fm = g2.getFontMetrics();

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
                if (r == highlightR && c == highlightC && highlightColor != null) {
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
                g2.setFont(new Font("Segoe UI", Font.BOLD, Math.min(13, cellSize / 2 + 1)));
                fm = g2.getFontMetrics();
                String val = String.valueOf(sliceData[r][c]);
                int tx = x + (cellSize - fm.stringWidth(val)) / 2;
                int ty = y + (cellSize + fm.getAscent()) / 2 - 2;
                g2.drawString(val, tx, ty);
            }
        }

        String dim = totalDepth + "\u00D7" + rows + "\u00D7" + cols;
        g2.setFont(Theme.SMALL_FONT);
        fm = g2.getFontMetrics();
        g2.setColor(Theme.TEXT_SECONDARY);
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
