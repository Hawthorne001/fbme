package smvDebugger.plugin;

/*Generated by MPS */

import jetbrains.mps.workbench.action.BaseAction;
import javax.swing.Icon;
import jetbrains.mps.workbench.action.ActionAccess;
import com.intellij.openapi.actionSystem.AnActionEvent;
import java.util.Map;
import jetbrains.mps.lang.smodel.generator.smodelAdapter.SNodeOperations;
import jetbrains.mps.ide.actions.MPSCommonDataKeys;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import jetbrains.mps.project.MPSProject;
import org.jetbrains.mps.openapi.model.SNode;
import jetbrains.mps.plugins.projectplugins.ProjectPluginManager;
import org.fbme.ide.platform.persistence.IEC61499Persistence;
import java.nio.file.Path;
import org.fbme.ide.iec61499.repository.PlatformRepository;
import org.fbme.ide.platform.PlatformRepositoryProvider;
import org.fbme.lib.iec61499.declarations.CompositeFBTypeDeclaration;
import org.jetbrains.mps.openapi.language.SConcept;
import jetbrains.mps.smodel.adapter.structure.MetaAdapterFactory;

public class Debug_SMV_Action extends BaseAction {
  private static final Icon ICON = null;

  public Debug_SMV_Action() {
    super("Debug SMV", "", ICON);
    this.setIsAlwaysVisible(false);
    this.setActionAccess(ActionAccess.UNDO_PROJECT);
  }
  @Override
  public boolean isDumbAware() {
    return true;
  }
  @Override
  public boolean isApplicable(AnActionEvent event, final Map<String, Object> _params) {
    return SNodeOperations.isInstanceOf(event.getData(MPSCommonDataKeys.NODE), CONCEPTS.CompositeFBTypeDeclaration$Wy);
  }
  @Override
  public void doUpdate(@NotNull AnActionEvent event, final Map<String, Object> _params) {
    this.setEnabledState(event.getPresentation(), this.isApplicable(event, _params));
  }
  @Override
  protected boolean collectActionData(AnActionEvent event, final Map<String, Object> _params) {
    if (!(super.collectActionData(event, _params))) {
      return false;
    }
    {
      Project p = event.getData(CommonDataKeys.PROJECT);
      if (p == null) {
        return false;
      }
    }
    {
      MPSProject p = event.getData(MPSCommonDataKeys.MPS_PROJECT);
      if (p == null) {
        return false;
      }
    }
    {
      SNode node = event.getData(MPSCommonDataKeys.NODE);
      if (node == null) {
        return false;
      }
    }
    return true;
  }
  @Override
  public void doExecute(@NotNull final AnActionEvent event, final Map<String, Object> _params) {
    final Debug_SMV_Tool debugger = event.getData(CommonDataKeys.PROJECT).getComponent(ProjectPluginManager.class).getTool(Debug_SMV_Tool.class);
    debugger.setProject(event.getData(MPSCommonDataKeys.MPS_PROJECT));

    final String rawFbPath = IEC61499Persistence.getPathToElement((SNode) event.getData(MPSCommonDataKeys.NODE));
    final Path fbPath = Path.of(rawFbPath);
    debugger.setFbPath(fbPath);

    final PlatformRepository platformRepository = PlatformRepositoryProvider.getInstance(event.getData(MPSCommonDataKeys.MPS_PROJECT));
    final CompositeFBTypeDeclaration compositeFb = platformRepository.getAdapter(event.getData(MPSCommonDataKeys.NODE), CompositeFBTypeDeclaration.class);
    debugger.setCompositeFb(compositeFb);

    debugger.openTool(true);
  }

  private static final class CONCEPTS {
    /*package*/ static final SConcept CompositeFBTypeDeclaration$Wy = MetaAdapterFactory.getConcept(0x6594f3404d734027L, 0xb7d3c6ca2e70a53bL, 0xd6e959f7e7902ddL, "org.fbme.ide.iec61499.lang.structure.CompositeFBTypeDeclaration");
  }
}
