package aed.modules.queue.view;

import aed.core.view.Theme;
import aed.modules.queue.controller.QueueController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class QueuePracticePanel extends JPanel {
    private QueueController controller;
    private QueueRenderer renderer;
    private JTextField txtValor;
    private JLabel lblSize;

    public QueuePracticePanel(QueueController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);
        initComponents();
    }

    private void initComponents() {
        renderer = new QueueRenderer();
        renderer.setPreferredSize(new Dimension(0, 300));
        renderer.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Theme.BORDER));
        add(renderer, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout(0, 6));
        bottom.setBackground(Theme.BACKGROUND);
        bottom.setBorder(new EmptyBorder(10, 16, 14, 16));

        lblSize = new JLabel("Tama\u00F1o: 0");
        lblSize.setFont(Theme.BODY_FONT);
        lblSize.setForeground(Theme.TEXT_SECONDARY);
        bottom.add(lblSize, BorderLayout.NORTH);

        JPanel controls = new JPanel(new GridBagLayout());
        controls.setBackground(Theme.BACKGROUND);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3, 4, 3, 4);
        c.fill = GridBagConstraints.HORIZONTAL;

        txtValor = new JTextField(8);
        txtValor.setFont(Theme.BODY_FONT);

        c.gridx = 0; controls.add(new JLabel("Valor:"), c);
        c.gridx = 1; controls.add(txtValor, c);

        c.gridx = 2; c.gridwidth = 2;
        controls.add(crearBoton("Enqueue", Theme.SUCCESS, e -> ejecutar("enqueue")), c);

        c.gridx = 0; c.gridy = 1; c.gridwidth = 1;
        controls.add(crearBoton("Dequeue", Theme.ERROR, e -> ejecutar("dequeue")), c);
        c.gridx = 1;
        controls.add(crearBoton("Peek", Theme.WARNING, e -> ejecutar("peek")), c);
        c.gridx = 2;
        controls.add(crearBoton("Buscar", Theme.INFO, e -> ejecutar("buscar")), c);
        c.gridx = 3;
        controls.add(crearBoton("Limpiar", Theme.TEXT_SECONDARY, e -> ejecutar("limpiar")), c);

        bottom.add(controls, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String t, Color bg, java.awt.event.ActionListener l) {
        JButton b = new JButton(t);
        b.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(6, 14, 6, 14));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addActionListener(l);
        return b;
    }

    private void ejecutar(String accion) {
        try {
            int v = txtValor.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtValor.getText().trim());
            controller.ejecutar(accion, v, this);
        } catch (NumberFormatException e) {
            renderer.setStatusMessage("Ingrese un valor numérico", Theme.ERROR);
        }
    }

    public void actualizar() {
        renderer.setData(controller.getDatos());
        lblSize.setText("Tama\u00F1o: " + controller.getSize());
    }

    public QueueRenderer getRenderer() { return renderer; }
    public void limpiarCampos() { txtValor.setText(""); }
}
