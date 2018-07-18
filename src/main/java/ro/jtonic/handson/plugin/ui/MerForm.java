package ro.jtonic.handson.plugin.ui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Created by Antonel Ernest Pazargic on 18/07/2018.
 *
 * @author Antonel Ernest Pazargic
 */
public class MerForm {
  private JTextField txtPrjName;
  private JLabel lblPrjName;
  JPanel panel;

  public JTextField getTxtPrjName() {
    return txtPrjName;
  }

  public void setProjectName(String prjName) {
    txtPrjName.setText(prjName);
  }
}
