package ro.jtonic.handson.plugin.ui;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Created by Antonel Ernest Pazargic on 24/10/2018.
 *
 * @author Antonel Ernest Pazargic
 */
public class InfoForm {
  private JTextArea txtaInfo;
  private JScrollPane scrInfo;
  private JPanel root;
  private JLabel lblInfo;

  public void setText(String text) {
    txtaInfo.setText(String.format("%s\n", text));
  }

  public String getText() {
    return txtaInfo.getText();
  }

  public JComponent getRoot() {
    return root;
  }
}
