package controllers;

import com.repaso_prueba.services.StudentService;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import com.repaso_prueba.entities.Student;
import com.repaso_prueba.entities.Course;
import com.repaso_prueba.entities.Enrollment;
import com.repaso_prueba.services.CourseService;
import com.repaso_prueba.services.EnrollmentSernvice;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainForm extends javax.swing.JFrame {

    private StudentService studentService = new StudentService();
    private CourseService courseService = new CourseService();
    private EnrollmentSernvice enrollmentService = new EnrollmentSernvice();

    public MainForm() {
        initComponents();
        agregarOyenteSeleccionTabla();
        agregarOyenteTablaCursos();
        generarTablaEstudiantes();
        generarTablaCursos();
        generarTablCursoEstudiante();
        agregarOyenteTablaCursoEstudiante();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public void generarTablaEstudiantes() {

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Correo");

        // Agregar data.
        List<Student> students = null;
        try {
            students = studentService.getAll();
        } catch (Exception ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Student student : students) {
            model.addRow(new Object[]{student.getId(), student.getName(), student.getEmail()});
        }

        this.JTblEstudiantes.setModel(model);
    }

    public void generarTablaCursos() {

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Titulo");
        model.addColumn("Descripcion");

        List<Course> courses = null;
        try {
            courses = courseService.getAll();
        } catch (Exception ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Course course : courses) {
            model.addRow(new Object[]{course.getId(), course.getTitle(), course.getDescription()});
        }

        this.jTblCursos.setModel(model);
    }

    public void generarTablCursoEstudiante() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nombre estudiante");
        model.addColumn("Nombre curso");
        model.addColumn("Nota");

        List<Enrollment> enrollments = null;
        String studentText = lblEstudianteSearch.getText().trim();
        String courseText = lblCursoBuscar.getText().trim();
        
        Integer studentId = (studentText != null && !studentText.isEmpty()) ? Integer.parseInt(studentText) : null;
        Integer courseId = (courseText != null && !courseText.isEmpty()) ? Integer.parseInt(courseText) : null;
        
        try {
            enrollments = this.enrollmentService.getAll(studentId, courseId);
        } catch (Exception ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Enrollment enrollment : enrollments) {
            model.addRow(new Object[]{
                enrollment.getId(),
                enrollment.getStudent().getName(),
                enrollment.getCourse().getTitle(),
                enrollment.getGrade()}
            );
        }

        this.jTblCursoEstudiante.setModel(model);

    }

    public void agregarOyenteSeleccionTabla() {

        this.JTblEstudiantes.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int fila = JTblEstudiantes.getSelectedRow();
                if (fila != -1) {
                    jTxtFNombre.setText((String) JTblEstudiantes.getValueAt(fila, 1));
                    jTxtFCorreo.setText((String) JTblEstudiantes.getValueAt(fila, 2));
                }
            }
        });

    }

    public void agregarOyenteTablaCursos() {

        this.jTblCursos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int row = jTblCursos.getSelectedRow();
                if (row != -1) {
                    jTxtFTitulo.setText((String) jTblCursos.getValueAt(row, 1));
                    jTxtFDescripcion.setText((String) jTblCursos.getValueAt(row, 2));
                }

            }
        });
    }

    public void agregarOyenteTablaCursoEstudiante() {

        this.jTblCursoEstudiante.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                int row = jTblCursoEstudiante.getSelectedRow();
                if (row != -1) {
                    jTxtFEstudiante.setText(jTblCursoEstudiante.getValueAt(row, 1).toString());
                    jTxtFCurso.setText(jTblCursoEstudiante.getValueAt(row, 2).toString());
                    jTxtFNota.setText(jTblCursoEstudiante.getValueAt(row, 3).toString());
                }

            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jBtnCrearEstudiante = new javax.swing.JButton();
        jTxtFNombre = new javax.swing.JTextField();
        jTxtFCorreo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTblEstudiantes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jBtnEliminar = new javax.swing.JButton();
        jBtnActualizar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTxtFTitulo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jBtnCrear = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTblCursos = new javax.swing.JTable();
        jBtnActualizarCursos = new javax.swing.JButton();
        jBtnEliminarCursos = new javax.swing.JButton();
        jTxtFDescripcion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTblCursoEstudiante = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTxtFEstudiante = new javax.swing.JTextField();
        jTxtFCurso = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTxtFNota = new javax.swing.JTextField();
        jBtnCrearCursoEstudiante = new javax.swing.JButton();
        jBtnActualizarCursoEstudiante = new javax.swing.JButton();
        jBtnEliminarCursoEstudiante = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        lblEstudianteSearch = new javax.swing.JTextField();
        lblCursoBuscar = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Estudiantes");

        jBtnCrearEstudiante.setText("Crear");
        jBtnCrearEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCrearEstudianteActionPerformed(evt);
            }
        });

        JTblEstudiantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(JTblEstudiantes);

        jLabel2.setText("Nombre");

        jLabel3.setText("Correo");

        jBtnEliminar.setText("Eliminar");
        jBtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarActionPerformed(evt);
            }
        });

        jBtnActualizar.setText("Actualizar");
        jBtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnActualizarActionPerformed(evt);
            }
        });

        jLabel4.setText("Cursos");

        jLabel5.setText("Titulo");

        jLabel6.setText("Descripcion");

        jBtnCrear.setText("Crear");
        jBtnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCrearActionPerformed(evt);
            }
        });

        jTblCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTblCursos);

        jBtnActualizarCursos.setText("Actualizar");
        jBtnActualizarCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnActualizarCursosActionPerformed(evt);
            }
        });

        jBtnEliminarCursos.setText("Eliminar");
        jBtnEliminarCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarCursosActionPerformed(evt);
            }
        });

        jLabel7.setText("Asociar curso a estudiante");

        jTblCursoEstudiante.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTblCursoEstudiante);

        jLabel8.setText("Estudiante");

        jLabel9.setText("Curso");

        jLabel10.setText("Nota");

        jBtnCrearCursoEstudiante.setText("Agregar");
        jBtnCrearCursoEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnCrearCursoEstudianteActionPerformed(evt);
            }
        });

        jBtnActualizarCursoEstudiante.setText("Actualizar");
        jBtnActualizarCursoEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnActualizarCursoEstudianteActionPerformed(evt);
            }
        });

        jBtnEliminarCursoEstudiante.setText("Eliminar");
        jBtnEliminarCursoEstudiante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnEliminarCursoEstudianteActionPerformed(evt);
            }
        });

        jLabel11.setText("Buscar");

        jLabel12.setText("Estudiante ID:");

        jLabel13.setText("Curso ID:");

        btnSearch.setText("Buscar");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addGap(519, 519, 519))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBtnActualizar)
                                .addGap(18, 18, 18)
                                .addComponent(jBtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(33, 33, 33)
                                    .addComponent(jTxtFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTxtFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)
                                    .addComponent(jBtnCrearEstudiante))
                                .addComponent(jScrollPane1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTxtFTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel6)
                                        .addGap(32, 32, 32)
                                        .addComponent(jTxtFDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jBtnCrear))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jBtnActualizarCursos)
                                .addGap(18, 18, 18)
                                .addComponent(jBtnEliminarCursos)
                                .addGap(39, 39, 39))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblEstudianteSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(lblCursoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(btnSearch))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jBtnActualizarCursoEstudiante)
                            .addGap(33, 33, 33)
                            .addComponent(jBtnEliminarCursoEstudiante))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 631, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTxtFEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTxtFCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel10)
                        .addGap(26, 26, 26)
                        .addComponent(jTxtFNota, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jBtnCrearCursoEstudiante)))
                .addGap(248, 248, 248))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTxtFNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jTxtFCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jBtnCrearEstudiante)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jBtnCrear)
                            .addComponent(jTxtFTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtFDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnActualizar)
                            .addComponent(jBtnEliminar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(208, 208, 208)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jBtnActualizarCursos)
                            .addComponent(jBtnEliminarCursos))))
                .addGap(20, 20, 20)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jTxtFEstudiante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTxtFCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jTxtFNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBtnCrearCursoEstudiante))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(lblEstudianteSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCursoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(btnSearch))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jBtnActualizarCursoEstudiante)
                    .addComponent(jBtnEliminarCursoEstudiante))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnCrearEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCrearEstudianteActionPerformed

        int isSelected = this.JTblEstudiantes.getSelectedRow();

        if (isSelected == -1) {
            String nombre = this.jTxtFNombre.getText().trim();
            String correo = this.jTxtFCorreo.getText().trim();

            Student s = new Student(nombre, correo);
            try {
                this.studentService.create(s);
            } catch (Exception ex) {
                //Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Datos Incorrectos");
            }
            generarTablaEstudiantes();
        }


    }//GEN-LAST:event_jBtnCrearEstudianteActionPerformed

    private void jBtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarActionPerformed

        // -1 si no hay nada seleccionado.
        int selected = this.JTblEstudiantes.getSelectedRow();
        if (selected < 0) return;
        Long id = (Long) this.JTblEstudiantes.getValueAt(selected, 0);
        try {
            boolean result = this.studentService.delete(id);
            if (!result) JOptionPane.showMessageDialog(null, "Posee Asociaciones!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Posee Asociaciones!");
        }
        generarTablaEstudiantes();
    }//GEN-LAST:event_jBtnEliminarActionPerformed

    private void jBtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnActualizarActionPerformed

        int isSelected = this.JTblEstudiantes.getSelectedRow();

        if (isSelected != -1) {

            int row = isSelected;
            Long id = (Long) this.JTblEstudiantes.getValueAt(row, 0);
            String nombre = this.jTxtFNombre.getText().trim();
            String email = this.jTxtFCorreo.getText().trim();
            Student s = new Student(nombre, email);

            try {
                this.studentService.update(id, s);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Datos Incorrectos");
            }
            this.generarTablaEstudiantes();
        }


    }//GEN-LAST:event_jBtnActualizarActionPerformed

    private void jBtnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCrearActionPerformed

        int selected = this.jTblCursos.getSelectedRow();

        if (selected == -1) {
            String titulo = this.jTxtFTitulo.getText().trim();
            String descripcion = this.jTxtFDescripcion.getText().trim();

            Course course = new Course(titulo, descripcion);
            try {
                this.courseService.create(course);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Datos Incorrectos");
            }
            generarTablaCursos();
        }

    }//GEN-LAST:event_jBtnCrearActionPerformed

    private void jBtnEliminarCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarCursosActionPerformed

        int row = this.jTblCursos.getSelectedRow();
        if (row < 0) return;
        Long id = (Long) this.jTblCursos.getValueAt(row, 0);

        try {
            boolean result = courseService.delete(id);
            if (!result) JOptionPane.showMessageDialog(null, "Posee Asociaciones!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Posee Asociaciones!");
        }

        generarTablaCursos();
    }//GEN-LAST:event_jBtnEliminarCursosActionPerformed

    private void jBtnActualizarCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnActualizarCursosActionPerformed

        int selected = this.jTblCursos.getSelectedRow();

        if (selected == -1) return;

        Long id = (Long) this.jTblCursos.getValueAt(selected, 0);
        String titulo = this.jTxtFTitulo.getText().trim();
        String descripcion = this.jTxtFDescripcion.getText().trim();

        Course course = new Course(titulo, descripcion);
        try {
            this.courseService.update(id, course);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datos Incorrectos");
        }
        generarTablaCursos();
        


    }//GEN-LAST:event_jBtnActualizarCursosActionPerformed

    private void jBtnCrearCursoEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnCrearCursoEstudianteActionPerformed

        int selected = this.jTblCursoEstudiante.getSelectedRow();
        if (selected == -1) {

            Long idEstudiante = Long.parseLong(this.jTxtFEstudiante.getText().trim());
            Long idCurso = Long.parseLong(this.jTxtFCurso.getText().trim());
            int nota = Integer.parseInt(this.jTxtFNota.getText().trim());

            Enrollment enrol = new Enrollment(idEstudiante, idCurso, nota);

            try {
                boolean result = this.enrollmentService.create(enrol);
                if (!result) JOptionPane.showMessageDialog(null, "DATOS INCORRECTOS!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Datos Incorrectos");
            }

            generarTablCursoEstudiante();
        }

    }//GEN-LAST:event_jBtnCrearCursoEstudianteActionPerformed

    private void jBtnEliminarCursoEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnEliminarCursoEstudianteActionPerformed

        int selected = this.jTblCursoEstudiante.getSelectedRow();
        if (selected < 0) return;
        Long id = (Long) this.jTblCursoEstudiante.getValueAt(selected, 0);

        try {
            this.enrollmentService.delete(id);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error Servidor!");
        }

        generarTablCursoEstudiante();
    }//GEN-LAST:event_jBtnEliminarCursoEstudianteActionPerformed

    private void jBtnActualizarCursoEstudianteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnActualizarCursoEstudianteActionPerformed

        int row = this.jTblCursoEstudiante.getSelectedRow();
        if (row < 0) return;
        Long id = (Long) this.jTblCursoEstudiante.getValueAt(row, 0);
        Long idEstudiante = Long.parseLong(this.jTxtFEstudiante.getText().trim());
        Long idCurso = Long.parseLong(this.jTxtFCurso.getText().trim());
        Object gradeValue = this.jTblCursoEstudiante.getValueAt(row, 3);
        int grade = Integer.parseInt(gradeValue.toString());

        Enrollment enrol = new Enrollment(idEstudiante, idCurso, grade);
        try {
            this.enrollmentService.update(id, enrol);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datos Incorrectos");
        }

        generarTablCursoEstudiante();
    }//GEN-LAST:event_jBtnActualizarCursoEstudianteActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        generarTablCursoEstudiante();
    }//GEN-LAST:event_btnSearchActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable JTblEstudiantes;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton jBtnActualizar;
    private javax.swing.JButton jBtnActualizarCursoEstudiante;
    private javax.swing.JButton jBtnActualizarCursos;
    private javax.swing.JButton jBtnCrear;
    private javax.swing.JButton jBtnCrearCursoEstudiante;
    private javax.swing.JButton jBtnCrearEstudiante;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnEliminarCursoEstudiante;
    private javax.swing.JButton jBtnEliminarCursos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTblCursoEstudiante;
    private javax.swing.JTable jTblCursos;
    private javax.swing.JTextField jTxtFCorreo;
    private javax.swing.JTextField jTxtFCurso;
    private javax.swing.JTextField jTxtFDescripcion;
    private javax.swing.JTextField jTxtFEstudiante;
    private javax.swing.JTextField jTxtFNombre;
    private javax.swing.JTextField jTxtFNota;
    private javax.swing.JTextField jTxtFTitulo;
    private javax.swing.JTextField lblCursoBuscar;
    private javax.swing.JTextField lblEstudianteSearch;
    // End of variables declaration//GEN-END:variables
}
