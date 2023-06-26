package GerenciadorTarefas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.util.ArrayList;

public class GerenciadorTarefas extends JFrame {
    private ArrayList<String> taskList;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskJList;
    private JTextField taskField;
    private JButton addButton;
    private JButton updateButton;
    private JButton removeButton;

    public GerenciadorTarefas() {
        taskList = new ArrayList<>();
        taskListModel = new DefaultListModel<>();
        initComponents();
    }

    private void initComponents() {
        taskJList = new JList<>(taskListModel);
        taskField = new JTextField(20);
        addButton = new JButton("Adicionar Tarefa");
        updateButton = new JButton("Atualizar Tarefa");
        removeButton = new JButton("Remover Tarefa");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTask();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeTask();
            }
        });

        JPanel panel = new JPanel();
        panel.add(taskField);
        panel.add(addButton);
        panel.add(updateButton);
        panel.add(removeButton);

        setLayout(new BorderLayout());
        add(new JScrollPane(taskJList), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Atualizar Tarefas");
        pack();
        setLocationRelativeTo(null);
    }

    private void addTask() {
        String task = taskField.getText().trim();

        if (!task.isEmpty()) {
            taskList.add(task);
            taskListModel.addElement(task);
            taskField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Digite uma tarefa válida.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTask() {
        int selectedIndex = taskJList.getSelectedIndex();

        if (selectedIndex != -1) {
            String updatedTask = taskField.getText().trim();

            if (!updatedTask.isEmpty()) {
                taskList.set(selectedIndex, updatedTask);
                taskListModel.set(selectedIndex, updatedTask);
                taskField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Digite uma tarefa válida.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa para atualizar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeTask() {
        int selectedIndex = taskJList.getSelectedIndex();

        if (selectedIndex != -1) {
            taskList.remove(selectedIndex);
            taskListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma tarefa para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GerenciadorTarefas().setVisible(true);
            }
        });
    }
}
