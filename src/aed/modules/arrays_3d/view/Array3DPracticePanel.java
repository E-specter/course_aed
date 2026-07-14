package aed.modules.arrays_3d.view;

import aed.core.view.Theme;
import aed.modules.arrays_3d.controller.Array3DController;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Array3DPracticePanel extends JPanel {
    private Array3DController controller;
    private Array3DRenderer renderer;
    private JTextField txtValor, txtCapa, txtFila, txtCol;
    private JLabel lblDim;
    private JLabel lblCapa;
    private JButton btnPrev, btnNext;

    public Array3DPracticePanel(Array3DController controller) {
        this.controller = controller;
        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);
        initComponents();
    }

    private void initComponents() {
        renderer = new Array3DRenderer();
        renderer.setPreferredSize(new Dimension(0, 380));
        renderer.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Theme.BORDER));
        add(renderer, BorderLayout.CENTER);

        JPanel bottom = new JPanel(new BorderLayout(0, 6));
        bottom.setBackground(Theme.BACKGROUND);
        bottom.setBorder(new EmptyBorder(10, 16, 14, 16));

        JPanel navBar = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        navBar.setBackground(Theme.BACKGROUND);
        btnPrev = new JButton("\u25C0 Anterior");
        btnPrev.setFont(Theme.SMALL_FONT);
        btnPrev.addActionListener(e -> controller.capaAnterior(this));
        btnNext = new JButton("Siguiente \u25B6");
        btnNext.setFont(Theme.SMALL_FONT);
        btnNext.addActionListener(e -> controller.capaSiguiente(this));
        lblCapa = new JLabel("Capa: 1 / " + controller.getDepth());
        lblCapa.setFont(Theme.BODY_FONT);
        navBar.add(btnPrev);
        navBar.add(lblCapa);
        navBar.add(btnNext);
        bottom.add(navBar, BorderLayout.NORTH);

        lblDim = new JLabel("Dimensi\u00F3n: " + controller.getDepth() + "\u00D7" + controller.getRows() + "\u00D7" + controller.getCols());
        lblDim.setFont(Theme.BODY_FONT);
        lblDim.setForeground(Theme.TEXT_SECONDARY);
        bottom.add(lblDim, BorderLayout.CENTER);

        JPanel controls = new JPanel(new GridBagLayout());
        controls.setBackground(Theme.BACKGROUND);
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(3, 3, 3, 3);
        c.fill = GridBagConstraints.HORIZONTAL;

        txtValor = new JTextField(4);
        txtValor.setFont(Theme.BODY_FONT);
        txtCapa = new JTextField(2);
        txtCapa.setFont(Theme.BODY_FONT);
        txtFila = new JTextField(2);
        txtFila.setFont(Theme.BODY_FONT);
        txtCol = new JTextField(2);
        txtCol.setFont(Theme.BODY_FONT);

        c.gridx = 0; controls.add(new JLabel("V:"), c);
        c.gridx = 1; controls.add(txtValor, c);
        c.gridx = 2; controls.add(new JLabel("D:"), c);
        c.gridx = 3; controls.add(txtCapa, c);
        c.gridx = 4; controls.add(new JLabel("F:"), c);
        c.gridx = 5; controls.add(txtFila, c);
        c.gridx = 6; controls.add(new JLabel("C:"), c);
        c.gridx = 7; controls.add(txtCol, c);

        c.gridx = 8; c.gridwidth = 2;
        controls.add(crearBoton("Asignar", Theme.SUCCESS, e -> ejecutar("asignar")), c);

        c.gridx = 0; c.gridy = 1; c.gridwidth = 1;
        controls.add(crearBoton("Obtener", Theme.PRIMARY, e -> ejecutar("obtener")), c);
        c.gridx = 1;
        controls.add(crearBoton("Llenar", Theme.WARNING, e -> ejecutar("llenar")), c);
        c.gridx = 2;
        controls.add(crearBoton("Aleatorio", Theme.INFO, e -> ejecutar("aleatorio")), c);
        c.gridx = 3;
        controls.add(crearBoton("Buscar", Theme.INFO, e -> ejecutar("buscar")), c);
        c.gridx = 4;
        controls.add(crearBoton("Sumar", Theme.INFO, e -> ejecutar("sumar")), c);

        c.gridx = 0; c.gridy = 2; c.gridwidth = 10;
        controls.add(crearBoton("Limpiar", Theme.TEXT_SECONDARY, e -> ejecutar("limpiar")), c);

        bottom.add(controls, BorderLayout.SOUTH);
        add(bottom, BorderLayout.SOUTH);
    }

    private JButton crearBoton(String t, Color bg, java.awt.event.ActionListener l) {
        JButton b = new JButton(t);
        b.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        b.setBackground(bg);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setBorder(new EmptyBorder(5, 8, 5, 8));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        b.addActionListener(l);
        return b;
    }

    private void ejecutar(String accion) {
        try {
            int v = txtValor.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtValor.getText().trim());
            int d = txtCapa.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtCapa.getText().trim());
            int r = txtFila.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtFila.getText().trim());
            int co = txtCol.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtCol.getText().trim());
            controller.ejecutar(accion, v, d, r, co, this);
        } catch (NumberFormatException e) {
            renderer.setStatusMessage("Ingrese valores numéricos", Theme.ERROR);
        }
    }

    public void actualizarSlice() {
        renderer.setSlice(controller.getSliceData(), controller.getCurrentDepth(), controller.getDepth());
        lblCapa.setText("Capa: " + (controller.getCurrentDepth() + 1) + " / " + controller.getDepth());
        lblDim.setText("Dimensi\u00F3n: " + controller.getDepth() + "\u00D7" + controller.getRows() + "\u00D7" + controller.getCols());
    }

    public Array3DRenderer getRenderer() { return renderer; }

    public void limpiarCampos() {
        txtValor.setText(""); txtCapa.setText(""); txtFila.setText(""); txtCol.setText("");
    }
}
