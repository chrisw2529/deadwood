import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;

public class UI extends JFrame implements ActionListener {

  private JButton twoPlayers;
  private JButton threePlayers;


  private JScrollPane scroller;
  private JTextArea text;

  public UI() {

    super("Testing UI");
    initialize();
    setSize(500, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

  }

  private void initialize() {

    JPanel toolbar = new JPanel();
    add(toolbar, BorderLayout.NORTH);
    addButton(toolbar, twoPlayers, "2 Players");
    addButton(toolbar, threePlayers, "3 Players");
    text = new JTextArea();
    scroller = new JScrollPane(text);
    add(scroller, BorderLayout.CENTER);

  }

  public void actionPerformed (ActionEvent e) {

    System.out.println(e.getActionCommand() + " pressed");

    if (e.getActionCommand() == "2 Players")
      text.cut();


    if (e.getActionCommand() == "3 Players")
      text.copy();

  }

  private void readFile() {

    JFileChooser chooser = new JFileChooser();
    int option = chooser.showOpenDialog(this);

    if(option == JFileChooser.APPROVE_OPTION) {

      try {
        String filename = chooser.getName(chooser.getSelectedFile());
        text.setText(new String(Files.readAllBytes(Paths.get(filename))));
      }

      catch (IOException e) {
        System.out.println("Cannot read the file " + e);
      }

    }

  }

  private void writeFile() {

    JFileChooser chooser = new JFileChooser();
    int option = chooser.showSaveDialog(this);

    if (option == JFileChooser.APPROVE_OPTION) {

      try{
        String filename = chooser.getName(chooser.getSelectedFile());
        Files.write(Paths.get(filename), text.getText().getBytes());
      }
      catch (IOException e) {
        System.out.println("Cannot write to file " + e);
      }
    }

  }

  private void addButton(JPanel panel, JButton button, String label) {

    button = new JButton(label);
    panel.add(button);
    button.addActionListener(this);

  }


  public static void main(String[] args) {

    UI window = new UI();

  }

}
