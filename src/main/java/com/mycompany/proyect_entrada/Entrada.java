/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyect_entrada;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Entrada extends javax.swing.JFrame {

    public Entrada() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ENTRADA EXPLORADOR DE ARCHIVOS");
        jLabel2.setText("Usuario");
        jLabel3.setText("Contraseña");

        jButton1.setText("Cancelar");
        jButton1.addActionListener(evt -> limpiarCampos());

        jButton2.setText("Registrarse");
        jButton2.addActionListener(evt -> registrarse());

        jButton3.setText("Aceptar");
        jButton3.addActionListener(evt -> iniciarSesion());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3))
                            .addGap(30, 30, 30)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))))
                    .addContainerGap(50, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(jButton1)
                    .addGap(60, 60, 60)
                    .addComponent(jButton2)
                    .addGap(60, 60, 60)
                    .addComponent(jButton3)
                    .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(40, 40, 40)
                    .addComponent(jLabel1)
                    .addGap(30, 30, 30)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(30, 30, 30)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(40, 40, 40)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton3))
                    .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
    }

    private void iniciarSesion() {
        String usuario = jTextField1.getText();
        String contraseña = new String(jPasswordField1.getPassword());

        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            try {
                String sql = "SELECT * FROM usuarios WHERE nombre = ? AND correo = ?";
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setString(1, usuario);
                ps.setString(2, contraseña);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "¡Bienvenido, " + usuario + "!");
                } else {
                    JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
                }

                rs.close();
                ps.close();
                conexion.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al consultar: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo conectar a la base de datos.");
        }
    }

    private void registrarse() {
        String usuario = jTextField1.getText();
        String contraseña = new String(jPasswordField1.getPassword());

        Connection conexion = ConexionBD.conectar();
        if (conexion != null) {
            try {
                String sql = "INSERT INTO usuarios (nombre, correo) VALUES (?, ?)";
                PreparedStatement ps = conexion.prepareStatement(sql);
                ps.setString(1, usuario);
                ps.setString(2, contraseña);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
                ps.close();
                conexion.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al registrar usuario: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo conectar a la base de datos.");
        }
    }

    private void limpiarCampos() {
        jTextField1.setText("");
        jPasswordField1.setText("");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new Entrada().setVisible(true));
    }

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
}

 
