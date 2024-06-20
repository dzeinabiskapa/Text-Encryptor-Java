// Swing, Džeina Bīskapa, 191RBC018, 10.grupa

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {
    private static DefaultListModel<String> leftListModel = new DefaultListModel<>();
    private static DefaultListModel<String> rightListModel = new DefaultListModel<>();
    private static JList<String> leftList;
    private static JList<String> rightList;
    private static JTextField text;

    private static void createAndShowGUI() {

        JFrame jFrame = new JFrame("Controls");
        jFrame.setSize(500, 360);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2,1));
        text = new JTextField();
        text.setPreferredSize(new Dimension(200, 20));
        JPanel subPanel = new JPanel();
        subPanel.add(text);
        panel.add(subPanel);
        JButton btnAdd = new JButton("Add");
        btnAdd.setMinimumSize((new Dimension(200, 20)));
        JButton btnRun = new JButton("Run");
        btnRun.setMinimumSize((new Dimension(200, 20)));
        subPanel = new JPanel();
        subPanel.add(btnAdd);
        subPanel.add(btnRun);
        panel.add(subPanel);
        jFrame.add(panel, BorderLayout.NORTH);

        leftList = new JList<>(leftListModel);
        leftList.setPreferredSize(new Dimension(150, 100));
        leftList.setBorder(BorderFactory.createLineBorder(Color.black));

        rightList = new JList<>(rightListModel);
        rightList.setPreferredSize(new Dimension(150, 100));
        rightList.setBorder(BorderFactory.createLineBorder(Color.black));
        panel = new JPanel();
        panel.add(leftList);
        panel.add(rightList);
        jFrame.add(panel, BorderLayout.CENTER);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setMinimumSize((new Dimension(200, 20)));
        JButton btnClear = new JButton("Clear");
        btnClear.setMinimumSize(new Dimension(200, 20));
        panel = new JPanel();
        panel.add(btnDelete);
        panel.add(btnClear);
        jFrame.add(panel, BorderLayout.SOUTH);
        jFrame.pack();
        jFrame.setVisible(true);

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = text.getText();
                leftListModel.addElement(input);
                text.setText("");
            }
        });

        btnRun.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightListModel.clear();
                for (int i = 0; i < leftListModel.size(); i++) {
                    String input = leftListModel.getElementAt(i);
                    String encrypted = encrypt(input);
                    rightListModel.addElement(encrypted);
                }
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedIndices = leftList.getSelectedIndices();
                for (int i = selectedIndices.length - 1; i >= 0; i--) {
                    leftListModel.removeElementAt(selectedIndices[i]);
                }
            }
        });

        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rightListModel.clear();
            }
        });
    }

    private static String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder();
        int length = input.length();
        if (length % 2 == 0) {
            for (int i = 0; i < length/2; i++) {
                encrypted.append(input.charAt(i));
                encrypted.append(input.charAt(length - i - 1));
            }
        }
        if (length % 2 != 0) {
            int middleIndex = (length-1)/2;
            char middleLetter = input.charAt(middleIndex);
            String nowEvenWord = input.substring(0, middleIndex) + input.substring(middleIndex + 1);
            int nowEW_lenght = nowEvenWord.length();
            for (int i = 0; i < nowEW_lenght/2; i++) {
                encrypted.append(nowEvenWord.charAt(i));
                encrypted.append(nowEvenWord.charAt(nowEW_lenght - i - 1));
            }
            encrypted.append(middleLetter);
        }
        return encrypted.toString();
    }

    public static void main(String[] args) {
        createAndShowGUI();
    }
}
