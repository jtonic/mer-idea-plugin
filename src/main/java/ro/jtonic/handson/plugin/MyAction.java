package ro.jtonic.handson.plugin;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created by Antonel Ernest Pazargic on 29/03/2018.
 *
 * @author Antonel Ernest Pazargic
 */
public class MyAction extends AnAction {

  @Override
  public void actionPerformed(AnActionEvent e) {
    System.out.println("e.isFromActionToolbar() = " + e.isFromActionToolbar());
  }
}
