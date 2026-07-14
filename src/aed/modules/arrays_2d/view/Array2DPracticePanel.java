package aed.modules.arrays_2d.view;

import aed.core.view.Theme;
import aed.modules.arrays_2d.controller.Array2DController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Array2DPracticePanel extends JPanel {
    private Array2DController controller;
    private Array2DRenderer renderer;
    private JTextField txtValor, txtFila, txtCol;
    private JLabel lblDim;

    public Array2DPracticePanel(Array2DController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);
        initComponents();
    }

    private void initComponents() {
        renderer = new Array2DRenderer();
        renderer.setPreferredSize(new Dimension(0, 350));
        renderer.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Theme.BORDER));
        add(renderer, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout(0, 6));
        bottom.setBackground(Theme.BACKGROUND);
        bottom.setBorder(new EmptyBorder(10, 16, 14, 16));

        lblDim = new JLabel("Dimensi\u00F3n: 4\u00D74");
        lblDim.setFont(Theme.BODY_FONT);
        lblDim.setForeground(Theme.TEXT_SECONDARY);
        bottom.add(lblDim, BorderLayout.NORTH);

        JPanel controls = new JPanel(new GridBagLayout());
        controls.setBackground(Theme.BACKGROUND);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3, 4, 3, 4);
        c.fill = GridBagConstraints.HORIZONTAL;

        txtValor = new JTextField(5);
        txtValor.setFont(Theme.BODY_FONT);
        txtFila = new JTextField(3);
        txtFila.setFont(Theme.BODY_FONT);
        txtCol = new JTextField(3);
        txtCol.setFont(Theme.BODY_FONT);

        c.gridx = 0; controls.add(new JLabel("Valor:"), c);
        c.gridx = 1; controls.add(txtValor, c);
        c.gridx = 2; controls.add(new JLabel("Fila:"), c);
        c.gridx = 3; controls.add(txtFila, c);
        c.gridx = 4; controls.add(new JLabel("Col:"), c);
        c.gridx = 5; controls.add(txtCol, c);

        c.gridx = 6; c.gridwidth = 2;
        controls.add(crearBoton("Asignar", Theme.SUCCESS, e -> ejecutar("asignar")), c);

        c.gridx = 0; c.gridy = 1; c.gridwidth = 1;
        controls.add(crearBoton("Obtener", Theme.PRIMARY, e -> ejecutar("obtener")), c);
        c.gridx = 1;
        controls.add(crearBoton("Llenar", Theme.WARNING, e -> ejecutar("llenar")), c);
        c.gridx = 2;
        controls.add(crearBoton("Aleatorio", Theme.INFO, e -> ejecutar("aleatorio")), c);
        c.gridx = 3;
        controls.add(crearBoton("Fila", Theme.PRIMARY_DARK, e -> ejecutar("fila")), c);
        c.gridx = 4;
        controls.add(crearBoton("Columna", Theme.PRIMARY_DARK, e -> ejecutar("columna")), c);
        c.gridx = 5;
        controls.add(crearBoton("Transponer", Theme.PRIMARY_DARK, e -> ejecutar("transponer")), c);

        c.gridx = 6;
        controls.add(crearBoton("Buscar", Theme.INFO, e -> ejecutar("buscar")), c);
        c.gridx = 7;
        controls.add(crearBoton("Sumar", Theme.INFO, e -> ejecutar("sumar")), c);

        c.gridx = 0; c.gridy = 2; c.gridwidth = 8;
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
            int r = txtFila.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtFila.getText().trim());
            int co = txtCol.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtCol.getText().trim());
            controller.ejecutar(accion, v, r, co, this);
        } catch (NumberFormatException e) {
            renderer.setStatusMessage("Ingrese valores numéricos", Theme.ERROR);
        }
    }

    public void actualizar(int[][] data, int rows, int cols) {
        renderer.setData(data);
        lblDim.setText("Dimensi\u00F3n: " + rows + "\u00D7" + cols);
    }

    public Array2DRenderer getRenderer() { return renderer; }

    public void limpiarCampos() {
        txtValor.setText(""); txtFila.setText(""); txtCol.setText("");
    }
}
