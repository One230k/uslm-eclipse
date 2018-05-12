package org.one230k.eclipse.ui;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.editors.text.TextEditor;
import org.one230k.eclipse.UslmPlugin;
import org.one230k.eclipse.model.Level;

public class TitleEditor extends TextEditor implements ISelectionListener {

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (selection.getClass().isAssignableFrom(ITreeSelection.class)) {
			ITreeSelection treeS = (ITreeSelection) selection;
			if (treeS != null && treeS.getFirstElement().getClass().isAssignableFrom(Level.class)) {
				Level level = (Level) treeS.getPaths()[0].getLastSegment();
				if (level != null) {
					UslmPlugin.logInfo(String.format("%s", level.getIdentifier()));
				}
			}
		}
/*		if (part.getClass().equals(ContentOutline.class)) {
			TextSelection text = (TextSelection) selection;
			if (text != null && title != null) {
				TreePath path = lineToPath(text.getStartLine(), title);
				if (path != null) {
					viewer.setSelection(new TreeSelection(path));
				}
			}
		}
*/	}

	@Override
	public void dispose() {
		getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(this);
		super.dispose();
	}

}
