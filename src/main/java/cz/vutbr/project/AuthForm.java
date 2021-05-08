package cz.vutbr.project;

import cz.vutbr.project.entity.Salary;
import cz.vutbr.project.entity.Stipend;
import cz.vutbr.project.entity.Subjects;
import cz.vutbr.project.entity.User;
import cz.vutbr.project.entity.enums.ERole;
import cz.vutbr.project.service.Delete;
import cz.vutbr.project.service.Info;
import cz.vutbr.project.service.Search;
import cz.vutbr.project.service.SubjectWork;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AuthForm {
    private JTextField name;
    private JTextField DOB;
    private JTextField lastname;
    private JButton add;
    private JPanel main;
    private JRadioButton Student;
    private JRadioButton Teacher;
    private JTabbedPane tabbedPane1;
    private JTextArea textArea3;
    private JTextField EditName;
    private JTextField EditLastname;
    private JTextField EditDOB;
    private JButton button1;
    private JTextField SearchTextField;
    private JTextField EditId;
    private JTextArea textArea4;
    private JButton editButton;
    private JButton removeButton;
    private JButton EditSearch;
    private JTextField EditSalary;
    private JLabel SalaryLabel;
    private JButton addSubjectButton;
    private JTextField StudentId;
    private JTextField SubjectId;
    private JButton removeSubjactButton;
    private JTextField Mark;
    private JButton markButton;
    private JComboBox comboBox1;
    private JButton getStudentStipendButton;
    private JTextField UserId;
    private JButton getTeacherSalaryButton;
    private JPanel pane2;
    private JPanel Panel1;
    private JPanel Panel2;
    private JPanel Panel3;
    private JPanel Panel4;
    private JLabel userCount;
    private JLabel studentCount;
    private JLabel teacherCount;
    private JLabel totalMoney;
    private JEditorPane editorPane1;


    public AuthForm() {


        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Subjects> query = builder.createQuery(Subjects.class);
        Root<Subjects> root = query.from(Subjects.class);
        List<Subjects> subjectsList = session.createQuery(query).getResultList();
        for (Subjects subjects : subjectsList) {
            textArea4.append(String.valueOf(subjects.getId()));
            textArea4.append(" - ");
            textArea4.append(subjects.getName());
            textArea4.append(" - ");
            textArea4.append(subjects.getDescription());
            textArea4.append("\n");

        }

        Info info = new Info();
        float totalStipend = info.stipends();
        float totalSalary = info.salarys();
        studentCount.setText(info.InfoStudents().toString());
        teacherCount.setText(info.InfoTeachers().toString());
        userCount.setText(info.Users().toString());
        totalMoney.setText(String.valueOf(totalStipend + totalSalary));



        EditSalary.setVisible(false);
        SalaryLabel.setVisible(false);


        //RadioButton ADD FRAME
        Student.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

                if (Teacher.isSelected()) {
                    Teacher.setSelected(false);
                }

            }            @Override
            public void setEnabled(boolean b) {

            }




        });
        Teacher.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

                if (Student.isSelected()) {
                    Student.setSelected(false);
                }
            }            @Override
            public void setEnabled(boolean b) {

            }




        });


        //EditSearch user
        button1.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

                Session session = sessionFactory.openSession();
                CriteriaBuilder builder = session.getCriteriaBuilder();
                CriteriaQuery<User> query = builder.createQuery(User.class);
                Root<User> root = query.from(User.class);
                query.select(root);
                Search search = new Search();
                if (comboBox1.getSelectedIndex() == 0) {
                    List<User> userList = search.SearchUserById(Long.valueOf(SearchTextField.getText()));
                    textArea3.setText("");
                    for (User users : userList) {

                        textArea3.append(String.valueOf(users.getId()));
                        textArea3.append(" - ");
                        textArea3.append(users.getName());
                        textArea3.append(" - ");
                        textArea3.append(users.getLastname());
                        textArea3.append(" - ");
                        textArea3.append(users.getDOB());
                        textArea3.append("\n");

                    }
                } else if (comboBox1.getSelectedIndex() == 1) {
                    List<User> userList = search.SearchUserByName(SearchTextField.getText());
                    textArea3.setText("");
                    for (User users : userList) {

                        textArea3.append(String.valueOf(users.getId()));
                        textArea3.append(" - ");
                        textArea3.append(users.getName());
                        textArea3.append(" - ");
                        textArea3.append(users.getLastname());
                        textArea3.append(" - ");
                        textArea3.append(users.getDOB());
                        textArea3.append("\n");

                    }
                } else if (comboBox1.getSelectedIndex() == 2) {
                    List<User> userList = search.SearchUserByLastname(SearchTextField.getText());
                    textArea3.setText("");
                    for (User users : userList) {

                        textArea3.append(String.valueOf(users.getId()));
                        textArea3.append(" - ");
                        textArea3.append(users.getName());
                        textArea3.append(" - ");
                        textArea3.append(users.getLastname());
                        textArea3.append(" - ");
                        textArea3.append(users.getDOB());
                        textArea3.append("\n");

                    }
                } else if (comboBox1.getSelectedIndex() == 3) {
                    List<User> userList = search.SearchUserByYear(SearchTextField.getText());
                    textArea3.setText("");
                    for (User users : userList) {

                        textArea3.append(String.valueOf(users.getId()));
                        textArea3.append(" - ");
                        textArea3.append(users.getName());
                        textArea3.append(" - ");
                        textArea3.append(users.getLastname());
                        textArea3.append(" - ");
                        textArea3.append(users.getDOB());
                        textArea3.append("\n");

                    }
                } else if (comboBox1.getSelectedIndex() == 4) {


                    textArea3.setText("");
                    List<User> userList = session.createQuery(query).getResultList();
                    userList.sort(new Comparator<User>() {
                        @Override
                        public int compare(User o1, User o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                    });
                    textArea3.append("Id - ");
                    textArea3.append("Name - ");
                    textArea3.append("Last Name - ");
                    textArea3.append("Date Of Birth - ");
                    textArea3.append("Role");
                    textArea3.append("\n");
                    textArea3.append("\n");
                    for (User user : userList) {
                        if (user.getRoles().toString().equals("[STUDENT]")) {
                            textArea3.append(String.valueOf(user.getId()));
                            textArea3.append(" - ");
                            textArea3.append(user.getName());
                            textArea3.append(" - ");
                            textArea3.append(user.getLastname());
                            textArea3.append(" - ");
                            textArea3.append(user.getDOB());
                            textArea3.append(" - ");
                            textArea3.append(user.getRoles().toString());
                            textArea3.append("\n");
                        }
                    }

                } else if (comboBox1.getSelectedIndex() == 5) {
                    textArea3.setText("");
                    List<User> userList = session.createQuery(query).getResultList();

                    userList.sort(new Comparator<User>() {
                        @Override
                        public int compare(User o1, User o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                    });
                    textArea3.append("Id - ");
                    textArea3.append("Name - ");
                    textArea3.append("Last Name - ");
                    textArea3.append("Date Of Birth - ");
                    textArea3.append("Role");
                    textArea3.append("\n");
                    textArea3.append("\n");

                    for (User user : userList) {
                        if (user.getRoles().toString().equals("[TEACHER]")) {
                            textArea3.append(String.valueOf(user.getId()));
                            textArea3.append(" - ");
                            textArea3.append(user.getName());
                            textArea3.append(" - ");
                            textArea3.append(user.getLastname());
                            textArea3.append(" - ");
                            textArea3.append(user.getDOB());
                            textArea3.append(" - ");
                            textArea3.append(user.getRoles().toString());
                            textArea3.append("\n");
                        }
                    }

                } else if (comboBox1.getSelectedIndex() == 6) {
                    List<User> userList = search.studentsOfTheTeacher(Long.valueOf(SearchTextField.getText()));
                    textArea3.setText("");
                    textArea3.append("Id - ");
                    textArea3.append("Name - ");
                    textArea3.append("Last Name - ");
                    textArea3.append("Date Of Birth - ");
                    textArea3.append("Role");
                    textArea3.append("\n");
                    textArea3.append("\n");
                    for (User user : userList) {
                        textArea3.append(String.valueOf(user.getId()));
                        textArea3.append(" - ");
                        textArea3.append(user.getName());
                        textArea3.append(" - ");
                        textArea3.append(user.getLastname());
                        textArea3.append(" - ");
                        textArea3.append(user.getDOB());
                        textArea3.append(" - ");
                        textArea3.append(user.getRoles().toString());
                        textArea3.append("\n");
                    }
                } else if (comboBox1.getSelectedIndex() == 7) {

                    List<User> userList = search.teachersOfTheStudents(Long.parseLong(SearchTextField.getText()));
                    textArea3.setText("");
                    textArea3.append("Id - ");
                    textArea3.append("Name - ");
                    textArea3.append("Last Name - ");
                    textArea3.append("Date Of Birth - ");
                    textArea3.append("Role");
                    textArea3.append("\n");
                    textArea3.append("\n");
                    for (User user : userList) {
                        textArea3.append(String.valueOf(user.getId()));
                        textArea3.append(" - ");
                        textArea3.append(user.getName());
                        textArea3.append(" - ");
                        textArea3.append(user.getLastname());
                        textArea3.append(" - ");
                        textArea3.append(user.getDOB());
                        textArea3.append(" - ");
                        textArea3.append(user.getRoles().toString());
                        textArea3.append("\n");
                    }

                } else if (comboBox1.getSelectedIndex() == 8) {

                    List<User> userList = session.createQuery(query).getResultList();
                    userList.sort(new Comparator<User>() {
                        @Override
                        public int compare(User o1, User o2) {
                            return o1.getName().compareTo(o2.getName());
                        }
                    });
                    textArea3.setText("");
                    textArea3.append("Id - ");
                    textArea3.append("Name - ");
                    textArea3.append("Last Name - ");
                    textArea3.append("Date Of Birth - ");
                    textArea3.append("Role");
                    textArea3.append("\n");
                    textArea3.append("\n");
                    for (User user : userList) {
                        textArea3.append(String.valueOf(user.getId()));
                        textArea3.append(" - ");
                        textArea3.append(user.getName());
                        textArea3.append(" - ");
                        textArea3.append(user.getLastname());
                        textArea3.append(" - ");
                        textArea3.append(user.getDOB());
                        textArea3.append(" - ");
                        textArea3.append(user.getRoles().toString());
                        textArea3.append("\n");
                    }
                }
                session.close();
                System.out.println("Session Close");


            }            @Override
            public void setEnabled(boolean b) {

            }




        });

        //add new user to DataBase
        add.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

                Session session = sessionFactory.openSession();
                ERole role = null;
                if (Student.isSelected()) {
                    role = ERole.STUDENT;
                } else {
                    role = ERole.TEACHER;
                }


                User user = new User();
                boolean createProblem = false;
                if (name.getText().equals("") | lastname.getText().equals("") | DOB.getText().equals("")) {
                    JOptionPane.showMessageDialog(main, "Error", "Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    List<User> list = session.createQuery("FROM User", User.class).list();
                    for (User userList : list) {
                        if (name.getText().toLowerCase(Locale.ROOT).equals(userList.getName().toLowerCase(Locale.ROOT))) {
                            createProblem = true;
                            if (lastname.getText().toLowerCase(Locale.ROOT).equals(userList.getLastname().toLowerCase(Locale.ROOT))) {
                                createProblem = true;
                                break;
                            } else {
                                createProblem = false;
                            }
                        }
                    }
                    if (!createProblem) {

                        if (DOB.getText().length() != 10) {
                            JOptionPane.showMessageDialog(main, "Wrong Date of Birth\n Must have format: dd-mm-yyyy", "Error!", JOptionPane.ERROR_MESSAGE);
                        } else {

                            //User users = new User();
                            String pattern = "dd-MM-yyyy";
                            SimpleDateFormat format = new SimpleDateFormat(pattern);
                            Date docDate;


                            try {
                                session.getTransaction().begin();
                                docDate = format.parse(DOB.getText());
                                user.setName(name.getText().toUpperCase(Locale.ROOT));
                                user.setLastname(lastname.getText().toUpperCase(Locale.ROOT));
                                user.setId(user.getId());
                                user.setDOB(DOB.getText());
                                user.getRoles().add(role);
                                Serializable id = session.save(user);
                                if (role.equals(ERole.TEACHER)) {
                                    Salary salary = new Salary();
                                    salary.setTeacher_id(user.getId());
                                    session.save(salary);
                                }else {
                                    Stipend stipend = new Stipend();
                                    stipend.setUserId(user.getId());
                                    stipend.setStipend(Float.valueOf(0));
                                    session.save(stipend);
                                }


                            } catch (ParseException parseException) {
                                parseException.printStackTrace();
                                JOptionPane.showMessageDialog(main, "Wrong Date of Birth\n Must have format: dd-mm-yyyy", "Error!", JOptionPane.ERROR_MESSAGE);
                                session.getTransaction().rollback();
                                session.close();


                            }
                            JOptionPane.showMessageDialog(main, "Successfully create user in database", "Success",JOptionPane.INFORMATION_MESSAGE);
                            session.getTransaction().commit();
                            session.close();
                        }


                    } else {
                        JOptionPane.showMessageDialog(main, "Already exist", "Error!", JOptionPane.ERROR_MESSAGE);
                        session.getTransaction().rollback();
                        session.close();


                    }
                }
            }            @Override
            public void setEnabled(boolean b) {

            }




        });

        //Search user for edit
        EditSearch.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {


                Search search = new Search();
                Session session = sessionFactory.openSession();
                List<User> userList = search.SearchUserById(Long.valueOf(EditId.getText()));
                EditId.setText("");
                EditName.setText("");
                EditLastname.setText("");
                EditDOB.setText("");
                for (User user : userList) {
                    EditId.setText(user.getId().toString());
                    EditName.setText(user.getName());
                    EditLastname.setText(user.getLastname());
                    EditDOB.setText(user.getDOB());
                    System.out.println(user.getRoles().toString());
                    if (user.getRoles().toString().equals("[TEACHER]")) {
                        SalaryLabel.setVisible(true);
                        EditSalary.setVisible(true);
                        Long id = user.getId();
                        CriteriaBuilder builder = session.getCriteriaBuilder();
                        CriteriaQuery<Salary> query = builder.createQuery(Salary.class);
                        Root<Salary> root = query.from(Salary.class);
                        query.select(root).where(builder.equal(root.<String>get("teacher_id"), id));
                        List<Salary> salaryList = session.createQuery(query).getResultList();
                        for (Salary salary : salaryList) {
                            EditSalary.setText(String.valueOf(salary.getSalary()));
                        }
                    } else {
                        SalaryLabel.setVisible(false);
                        EditSalary.setVisible(false);
                    }
                }

            }            @Override
            public void setEnabled(boolean b) {

            }




        });

        //Edit user
        editButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

                String pattern = "dd-MM-yyyy";
                SimpleDateFormat format = new SimpleDateFormat(pattern);
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                if (EditName.getText().equals("") || EditLastname.getText().equals("") || EditDOB.getText().equals("")) {
                    JOptionPane.showMessageDialog(main, "Name, lastname or Date of Birth cannot be empty", "Error!", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (EditDOB.getText().length() != 10) {
                        JOptionPane.showMessageDialog(main, "Wrong Date of Birth\n Must have format: dd-mm-yyyy", "Error!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            format.parse(EditDOB.getText());
                            User user = session.get(User.class, Long.valueOf(EditId.getText()));
                            Salary salary = session.get(Salary.class, Long.valueOf(EditId.getText()));
                            user.setName(EditName.getText());
                            user.setLastname(EditLastname.getText());
                            user.setDOB(EditDOB.getText());
                            session.save(user);
                            if (user.getRoles().toString().equals("[TEACHER]")) {
                                if (EditSalary.getText().equals("")) {
                                    JOptionPane.showMessageDialog(main, "Salary cannot be empty", "Error!", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    salary.setSalary(Float.valueOf(EditSalary.getText()));
                                    session.save(salary);
                                }
                            }
                            transaction.commit();
                            session.close();
                        } catch (ParseException parseException) {
                            parseException.printStackTrace();
                            JOptionPane.showMessageDialog(main, "Wrong Date of Birth\n Must have format: dd-mm-yyyy", "Error!", JOptionPane.ERROR_MESSAGE);
                            session.getTransaction().rollback();
                            session.close();
                        }
                        JOptionPane.showMessageDialog(main, "successfully changed", "OK!", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

            }            @Override
            public void setEnabled(boolean b) {

            }




        });

        //Delete User
        removeButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

                Delete delete = new Delete();
                delete.DeleteUser(Long.valueOf(EditId.getText()));

            }




        });

        //Add subject for Student
        addSubjectButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {


                SubjectWork subjectWork = new SubjectWork();
                String message = subjectWork.addSubject(Long.parseLong(StudentId.getText()),
                        Long.parseLong(SubjectId.getText()));
                JOptionPane.showMessageDialog(main, message, "Message", JOptionPane.INFORMATION_MESSAGE);


            }




        });

        removeSubjactButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

                SubjectWork subjectWork = new SubjectWork();
                String message = subjectWork.DeleteSubject(StudentId.getText(), SubjectId.getText());
                JOptionPane.showMessageDialog(main, message, "Message", JOptionPane.INFORMATION_MESSAGE);
            }




        });

        markButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {

                SubjectWork subjectWork = new SubjectWork();
                String message = subjectWork.AddMark(StudentId.getText(), SubjectId.getText(), Mark.getText());
                JOptionPane.showMessageDialog(main, message, "Message", JOptionPane.INFORMATION_MESSAGE);
            }




        });

        getStudentStipendButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long id = Long.valueOf(UserId.getText());
                    User user = session.get(User.class, id);
                    Stipend stipend = new Stipend();
                    if (user.getRoles().toString().equals("[STUDENT]")) {
                        textArea3.setText("");
                        textArea3.append("Name " + user.getName());
                        textArea3.append(" - ");
                        textArea3.append("Stipend - ");
                        textArea3.append(String.valueOf(stipend.CalculateStipend(id)));
                        textArea3.append("\n");
                    } else {
                        JOptionPane.showMessageDialog(main, "Cannot find Stipend for TEACHER please use STUDENT ID for Stipend ", "ERROR!", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(main, " ID was not found in the DATABASE", "ERROR!", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(main, " please write ID Correctly", "ERROR!", JOptionPane.ERROR_MESSAGE);
                }
            }




        });

        getTeacherSalaryButton.addActionListener(new Action() {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {


                try {
                    double value = 0;
                    Long id = Long.valueOf(UserId.getText());
                    Salary salary = session.get(Salary.class, id);
                    User user = session.get(User.class, id);

                    if (user.getRoles().toString().equals("[TEACHER]")) {
                        value = salary.CalculateSalaryAfterTax(id);
                        textArea3.setText("");
                        textArea3.append("Name - " + user.getName());
                        textArea3.append("\n");
                        textArea3.append("Last Name - " + user.getLastname());
                        textArea3.append("\n");
                        textArea3.append("Salary - " + salary.getSalary());
                        textArea3.append("\n");
                        textArea3.append("Salary after tax - " + value);
                        textArea3.append("\n");
                    } else {
                        JOptionPane.showMessageDialog(main, "Cannot find Salary for STUDENT please use TEACHER ID for salary ", "ERROR!", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NullPointerException ex) {
                    JOptionPane.showMessageDialog(main, " ID was not found in the DATABASE", "ERROR!", JOptionPane.ERROR_MESSAGE);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(main, " please write ID Correctly", "ERROR!", JOptionPane.ERROR_MESSAGE);
                }
            }




        });



    }

    public JPanel getMain() {
        return main;
    }
}


