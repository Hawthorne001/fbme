package smvDebugger.plugin;

/*Generated by MPS */

import jetbrains.mps.plugins.tool.GeneratedTool;
import javax.swing.Icon;
import jetbrains.mps.project.MPSProject;
import java.nio.file.Path;
import org.fbme.lib.iec61499.declarations.CompositeFBTypeDeclaration;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindowAnchor;
import javax.swing.JComponent;
import smvDebugger.main.SmvDebugger;

public class Debug_SMV_Tool extends GeneratedTool {
  private static final Icon ICON = null;
  private MPSProject project;
  private Path fbPath;
  private CompositeFBTypeDeclaration compositeFb;
  public Debug_SMV_Tool(Project project) {
    super(project, "Debug SMV", null, ICON, ToolWindowAnchor.BOTTOM, false);
  }
  /*package*/ void setProject(final MPSProject project) {
    Debug_SMV_Tool.this.project = project;
  }
  /*package*/ void setFbPath(final Path fbPath) {
    Debug_SMV_Tool.this.fbPath = fbPath;
  }
  /*package*/ void setCompositeFb(final CompositeFBTypeDeclaration compositeFb) {
    Debug_SMV_Tool.this.compositeFb = compositeFb;
  }
  public JComponent getComponent() {
    return new SmvDebugger(Debug_SMV_Tool.this.project).run(Debug_SMV_Tool.this.fbPath, Debug_SMV_Tool.this.compositeFb);
  }
}
