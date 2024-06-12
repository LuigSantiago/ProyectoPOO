/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Ventanas;

import Clases.Usuario;
import Crud.UsuarioJpaController;
import Crud.exceptions.IllegalOrphanException;
import Crud.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class RegistroUsuario extends javax.swing.JDialog {
 private Usuario UsuarioConsultado;

    /**
     * Creates new form RegistroUsuario
     */
    public RegistroUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        CampoNombre = new javax.swing.JTextField();
        CampoApellido = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        CampoCorreo = new javax.swing.JTextField();
        CampoDireccion = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        CampoContraseña = new javax.swing.JPasswordField();
        BotonCrear = new javax.swing.JButton();
        BotonCancelar = new javax.swing.JButton();
        BotonBuscar = new javax.swing.JButton();
        BotonEditar = new javax.swing.JButton();
        BotonEliminar = new javax.swing.JButton();
        CampoID = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos de usuario", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 3, 12), new java.awt.Color(204, 204, 204))); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Nombres:");

        CampoApellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoApellidoActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Apellidos:");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Correo:");

        CampoCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoCorreoActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Direccion:");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Contraseña:");

        BotonCrear.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\proyecto_POO\\src\\Iconos\\save-icon.png")); // NOI18N
        BotonCrear.setText("Crear");
        BotonCrear.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BotonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCrearActionPerformed(evt);
            }
        });

        BotonCancelar.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\proyecto_POO\\src\\Iconos\\Arrow-Back-2-icon.png")); // NOI18N
        BotonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCancelarActionPerformed(evt);
            }
        });

        BotonBuscar.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\proyecto_POO\\src\\Iconos\\search-circle-sharp-icon.png")); // NOI18N
        BotonBuscar.setText("Buscar");
        BotonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBuscarActionPerformed(evt);
            }
        });

        BotonEditar.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\proyecto_POO\\src\\Iconos\\create-sharp-icon.png")); // NOI18N
        BotonEditar.setText("Editar");
        BotonEditar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BotonEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEditarActionPerformed(evt);
            }
        });

        BotonEliminar.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\proyecto_POO\\src\\Iconos\\delete-circle-icon.png")); // NOI18N
        BotonEliminar.setText("Eliminar");
        BotonEliminar.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        BotonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEliminarActionPerformed(evt);
            }
        });

        CampoID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CampoIDKeyTyped(evt);
            }
        });

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("ID");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CampoApellido)
                                    .addComponent(CampoNombre)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(CampoCorreo, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(CampoDireccion, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(CampoContraseña, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(CampoID)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BotonCrear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotonBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotonEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BotonEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotonCancelar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(CampoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CampoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(CampoContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CampoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BotonCrear)
                            .addComponent(BotonBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BotonEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BotonEliminar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BotonCancelar)))
                .addGap(89, 89, 89))
        );

        jLabel1.setFont(new java.awt.Font("Arial Black", 3, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bienvenido");

        jLabel4.setIcon(new javax.swing.ImageIcon("C:\\Users\\HP\\Documents\\NetBeansProjects\\proyecto_POO\\src\\Iconos\\face-happy-icon.png")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CampoApellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoApellidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoApellidoActionPerformed

    private void CampoCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoCorreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoCorreoActionPerformed

    private void BotonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCrearActionPerformed
        String nombre = CampoNombre.getText();
        if(nombre == null || nombre.trim().isEmpty()){
        JOptionPane.showMessageDialog(this, "Escribe un nombre");
        return;
        }
        String apellido = CampoApellido.getText();
        if(apellido == null || apellido.trim().isEmpty()){
        JOptionPane.showMessageDialog(this, "Escribe un apellido");
        return;
        }
        String correo = CampoCorreo.getText();
        if(correo == null || correo.trim().isEmpty()){
        JOptionPane.showMessageDialog(this, "Escribe un correo");
        return;
        }
        String direccion = CampoDireccion.getText();
        if(direccion == null || direccion.trim().isEmpty()){
        JOptionPane.showMessageDialog(this, "Escribe un direccion");
        return;
        }
        char[] pass = CampoContraseña.getPassword();
        String contraseña = String.valueOf(pass);
        if(contraseña == null || contraseña.trim().isEmpty()){
        JOptionPane.showMessageDialog(this, "Escribe un contraseña");
        return;
        }
        String ID = CampoID.getText();
        if(ID == null || ID.trim().isEmpty()){
        JOptionPane.showMessageDialog(this, "Escribe un ID numerico");
        return;
        }
        int idU = Integer.parseInt(ID);
        Date fechaActual = new Date();

        
        Usuario u1 = new Usuario(idU);
        u1.setNombres(nombre);
        u1.setApellidos(apellido);
        u1.setCorreo(correo);
        u1.setDireccion(direccion);
        u1.setContraseña(contraseña);
        u1.setIdUsuario(idU);
        u1.setFechaRegistro(fechaActual);
        
        EntityManagerFactory conexion = Persistence.createEntityManagerFactory("com.mycompany_proyecto_POO_jar_1.0-SNAPSHOTPU");
        
        UsuarioJpaController crudUsuario = new UsuarioJpaController(conexion);
        
        try {
            
            long total = crudUsuario.getUsuarioCount();
        
        String mensaje = "el usuario de id "+idU+" se creo exitosamente";
        JOptionPane.showMessageDialog(this, mensaje, "RESULTADO", JOptionPane.INFORMATION_MESSAGE);
        
        
         CampoNombre.setText("");
         CampoApellido.setText("");
         CampoCorreo.setText("");
         CampoDireccion.setText("");
         CampoContraseña.setText("");
         
            crudUsuario.create(u1);
        } catch (Exception ex) {
             JOptionPane.showMessageDialog(this, ex.getMessage(), "RESULTADO", JOptionPane.ERROR_MESSAGE);       
        }
        
        
    }//GEN-LAST:event_BotonCrearActionPerformed

    private void BotonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCancelarActionPerformed
            dispose();
    }//GEN-LAST:event_BotonCancelarActionPerformed

    private void BotonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBuscarActionPerformed
      this.UsuarioConsultado = null;  
      String IDs = CampoID.getText();
      int ID = Integer.parseInt(IDs);
      
      if(IDs == null || IDs.trim().isEmpty()){
      JOptionPane.showMessageDialog(this, "Escriba un ID para buscarlo", "ERROR", JOptionPane.ERROR_MESSAGE);
      return;
      }
      
      EntityManagerFactory conexionBD = Persistence.createEntityManagerFactory("com.mycompany_proyecto_POO_jar_1.0-SNAPSHOTPU");
      UsuarioJpaController crudUsuario = new UsuarioJpaController(conexionBD);
      int totalUsuarios = crudUsuario.getUsuarioCount();
      
      if(totalUsuarios <= 0){
      JOptionPane.showMessageDialog(this, "La BD esta vacia", "ERROR", JOptionPane.ERROR_MESSAGE);
      return;
      }
      this.UsuarioConsultado = crudUsuario.findUsuario(ID);
      if (this.UsuarioConsultado == null){
      JOptionPane.showMessageDialog(this, "el usuario no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
      return;
      }
      CampoNombre.setText(UsuarioConsultado.getNombres());
      CampoApellido.setText(UsuarioConsultado.getApellidos());
      CampoDireccion.setText(UsuarioConsultado.getDireccion());
      CampoCorreo.setText(UsuarioConsultado.getCorreo());
      CampoContraseña.setText(UsuarioConsultado.getContraseña());
    }//GEN-LAST:event_BotonBuscarActionPerformed

    private void CampoIDKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CampoIDKeyTyped
        int key = evt.getKeyChar();
               
        boolean num = key >= 48 && key <= 57;   
        if(!num){
            evt.consume();
        }
    }//GEN-LAST:event_CampoIDKeyTyped

    private void BotonEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEditarActionPerformed
    if (this.UsuarioConsultado == null){
    JOptionPane.showMessageDialog(this, "Busque un usuario antes");
    return;
    }
    String IDs = CampoID.getText();
    int ID = Integer.parseInt(IDs);
    if(this.UsuarioConsultado != null && !this.UsuarioConsultado.getIdUsuario().equals(IDs)) {
      String msj = "los datos del usuario a editar no corresponden con los datos del usuario consultado";
      JOptionPane.showMessageDialog(this, msj, "ERROR", JOptionPane.ERROR_MESSAGE);
      return;
    }
    String nombres = CampoNombre.getText();
    String apellidos = CampoApellido.getText();
    String direccion = CampoDireccion.getText();
    String Correo = CampoCorreo.getText();
    char[] Pass = CampoContraseña.getPassword();
    String Contraseña = String.valueOf(Pass);
    EntityManagerFactory conexionBD = Persistence.createEntityManagerFactory("com.mycompany_proyecto_POO_jar_1.0-SNAPSHOTPU");
    UsuarioJpaController crudUsuario = new UsuarioJpaController(conexionBD);
     try {
         crudUsuario.edit(UsuarioConsultado);
            JOptionPane.showMessageDialog(this, "Usuario actualizado");
     } catch (NonexistentEntityException ex) {
         JOptionPane.showMessageDialog(this, "Este usuario no existe", "ERROR", JOptionPane.ERROR_MESSAGE);
         return;
     } catch (Exception ex) {
         JOptionPane.showMessageDialog(this, "No puede editar a este usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
         return;
     }
    }//GEN-LAST:event_BotonEditarActionPerformed

    private void BotonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarActionPerformed
   //1
    int ID = Integer.parseInt(CampoID.getText());
    //2
    if(ID == 0){
        JOptionPane.showMessageDialog(this, "Digite un ID", "ERROR", JOptionPane.ERROR_MESSAGE);
        return;
    }
    //3
    EntityManagerFactory conexionBD = Persistence.createEntityManagerFactory("com.mycompany_proyecto_POO_jar_1.0-SNAPSHOTPU");
    UsuarioJpaController crudUsuario = new UsuarioJpaController(conexionBD);
    int totalUsuarios = crudUsuario.getUsuarioCount();
    if (totalUsuarios <= 0) {
    JOptionPane.showMessageDialog(this, "la BD esta vacia", "ERROR", JOptionPane.ERROR_MESSAGE);
    return;
    }
    //4
    if (this.UsuarioConsultado.getIdUsuario().equals(ID)){
    JOptionPane.showMessageDialog(this, "Busque un usuario antes", "ERROR", JOptionPane.ERROR_MESSAGE);
    return;
    }
    int opcion = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar?", "Si - No", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE);
    if(opcion == JOptionPane.YES_OPTION) {
        try {
            crudUsuario.destroy(ID);
            JOptionPane.showMessageDialog(this, "usuario eliminado");
            this.UsuarioConsultado = null;
        } catch (IllegalOrphanException ex) {
            JOptionPane.showMessageDialog(this, "no puede eliminar este usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(RegistroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            JOptionPane.showMessageDialog(this, "no existe este usuario", "ERROR", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(RegistroUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    }//GEN-LAST:event_BotonEliminarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistroUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                RegistroUsuario dialog = new RegistroUsuario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonBuscar;
    private javax.swing.JButton BotonCancelar;
    private javax.swing.JButton BotonCrear;
    private javax.swing.JButton BotonEditar;
    private javax.swing.JButton BotonEliminar;
    private javax.swing.JTextField CampoApellido;
    private javax.swing.JPasswordField CampoContraseña;
    private javax.swing.JTextField CampoCorreo;
    private javax.swing.JTextField CampoDireccion;
    private javax.swing.JTextField CampoID;
    private javax.swing.JTextField CampoNombre;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
