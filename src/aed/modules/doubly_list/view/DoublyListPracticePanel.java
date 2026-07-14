package aed.modules.doubly_list.view;

import aed.core.view.Theme;
import aed.modules.doubly_list.controller.DoublyListController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DoublyListPracticePanel extends JPanel {
    private DoublyListController controller;
    private DoublyListRenderer renderer;
    private JTextField txtValor, txtIndice;
    private JLabel lblSize;

    public DoublyListPracticePanel(DoublyListController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);
        initComponents();
    }

    private void initComponents() {
        renderer = new DoublyListRenderer();
        renderer.setPreferredSize(new Dimension(0, 280));
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

        txtValor = new JTextField(6);
        txtValor.setFont(Theme.BODY_FONT);
        txtIndice = new JTextField(4);
        txtIndice.setFont(Theme.BODY_FONT);

        c.gridx = 0; controls.add(new JLabel("Valor:"), c);
        c.gridx = 1; controls.add(txtValor, c);
        c.gridx = 2; controls.add(new JLabel("Índice:"), c);
        c.gridx = 3; controls.add(txtIndice, c);

        c.gridx = 4; c.gridwidth = 2;
        controls.add(crearBoton("Add First", Theme.SUCCESS, e -> ejecutar("first")), c);

        c.gridx = 0; c.gridy = 1; c.gridwidth = 1;
        controls.add(crearBoton("Add Last", Theme.PRIMARY, e -> ejecutar("last")), c);
        c.gridx = 1;
        controls.add(crearBoton("Insertar", Theme.PRIMARY, e -> ejecutar("insertar")), c);
        c.gridx = 2;
        controls.add(crearBoton("Eliminar", Theme.ERROR, e -> ejecutar("eliminar")), c);
        c.gridx = 3;
        controls.add(crearBoton("Obtener", Theme.INFO, e -> ejecutar("obtener")), c);
        c.gridx = 4;
        controls.add(crearBoton("Modificar", Theme.WARNING, e -> ejecutar("modificar")), c);
        c.gridx = 5;
        controls.add(crearBoton("Buscar", Theme.INFO, e -> ejecutar("buscar")), c);

        c.gridx = 0; c.gridy = 2; c.gridwidth = 3;
        controls.add(crearBoton("Ordenar", Theme.PRIMARY_DARK, e -> ejecutar("ordenar")), c);
        c.gridx = 3;
        controls.add(crearBoton("Limpiar", Theme.TEXT_SECONDARY, e -> ejecutar("limpiar")), c);

        bottom.add(controls, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String t, Color bg, java.awt.event.ActionListener l) {
        JButton b = new JButton(t);
        b.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(5, 10, 5, 10));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addActionListener(l);
        return b;
    }

    private void ejecutar(String accion) {
        try {
            int v = txtValor.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtValor.getText().trim());
            int i = txtIndice.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtIndice.getText().trim());
            controller.ejecutar(accion, v, i, this);
        } catch (NumberFormatException e) {
            renderer.setStatusMessage("Ingrese valores numéricos", Theme.ERROR);
        }
    }

    public void actualizar() {
        renderer.setData(controller.getDatos());
        lblSize.setText("Tama\u00F1o: " + controller.getSize());
    }

    public DoublyListRenderer getRenderer() { return renderer; }
    public void limpiarCampos() { txtValor.setText(""); txtIndice.setText(""); }
}
