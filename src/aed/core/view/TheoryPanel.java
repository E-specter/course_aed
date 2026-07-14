package aed.core.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TheoryPanel extends JPanel {
    protected JEditorPane editorPane;
    public JButton ejecutarButton;
    private List<Runnable> ejecutarListeners = new ArrayList<>();

    public TheoryPanel(String htmlContent) {
        setLayout(new BorderLayout());
        setBackground(Theme.PANEL_BG);

        editorPane = new JEditorPane("text/html", htmlContent);
        editorPane.setEditable(false);
        editorPane.setCaretPosition(0);

        JScrollPane scrollPane = new JScrollPane(editorPane);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomBar = new JPanel(new BorderLayout());
        bottomBar.setBackground(Theme.BACKGROUND);
        bottomBar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Theme.BORDER));
        bottomBar.setPreferredSize(new Dimension(0, 60));

        ejecutarButton = new JButton("Ejecutar Práctica");
        ejecutarButton.setFont(Theme.BODY_FONT);
        ejecutarButton.setBackground(Theme.PRIMARY);
        ejecutarButton.setForeground(Color.WHITE);
        ejecutarButton.setFocusPainted(false);
        ejecutarButton.setBorder(new EmptyBorder(10, 28, 10, 28));
        ejecutarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ejecutarButton.addActionListener(e -> {
            for (Runnable r : ejecutarListeners) {
                r.run();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Theme.BACKGROUND);
        buttonPanel.add(ejecutarButton);
        bottomBar.add(buttonPanel, BorderLayout.CENTER);

        add(bottomBar, BorderLayout.SOUTH);
    }

    public void addEjecutarListener(Runnable listener) {
        ejecutarListeners.add(listener);
    }

    public void setHtmlContent(String html) {
        editorPane.setText(html);
        editorPane.setCaretPosition(0);
    }
}
