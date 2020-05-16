package smvDebugger.execution;

/*Generated by MPS */

import jetbrains.mps.project.MPSProject;
import smvDebugger.integration.SmvService;
import smvDebugger.panel.DebugPanelService;
import com.intellij.openapi.project.Project;
import jetbrains.mps.ide.project.ProjectHelper;
import smvDebugger.plugin.SmvBinaryPaths_PreferencesComponent;
import jetbrains.mps.plugins.projectplugins.ProjectPluginManager;
import javax.swing.JComponent;
import java.nio.file.Path;
import org.fbme.lib.iec61499.declarations.CompositeFBTypeDeclaration;
import java.util.Optional;
import smvDebugger.model.Counterexample;
import javax.swing.JOptionPane;

public class SmvDebugger {
  private static final String FB_FILE_EXTENSION = ".xml";

  private final MPSProject project;
  private final SmvService smvService;
  private final DebugPanelService debugPanelService;

  public SmvDebugger(final MPSProject project) {
    this.project = project;

    final Project ideaProject = ProjectHelper.toIdeaProject(project);
    final SmvBinaryPaths_PreferencesComponent smvBinaryPaths = ideaProject.getComponent(ProjectPluginManager.class).getPrefsComponent(SmvBinaryPaths_PreferencesComponent.class);
    smvService = new SmvService(smvBinaryPaths);

    debugPanelService = new DebugPanelService(project);
  }

  public JComponent run(final Path fbPath, final CompositeFBTypeDeclaration compositeFb) {
    try {
      final String specification = getSpecification();
      final Optional<Counterexample> counterexample = smvService.verify(fbPath, specification);
      if (counterexample.isEmpty()) {
        notifySuccess();
        return null;
      }
      return debugPanelService.run(compositeFb, counterexample.get());
    } catch (final Exception e) {
      JOptionPane.showMessageDialog(null, e.getMessage());
      return null;
    }
  }

  private static String getSpecification() {
    final String specification = JOptionPane.showInputDialog(null, "Enter LTL/CTL specification");
    return specification;
  }

  private static void notifySuccess() {
    JOptionPane.showMessageDialog(null, "Success");
  }
}
