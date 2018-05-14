package org.one230k.eclipse.ui;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.FindReplaceDocumentAdapter;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
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
		if (ITreeSelection.class.isAssignableFrom(selection.getClass())) {
			ITreeSelection treeS = (ITreeSelection) selection;
			if (treeS != null && treeS.getPaths().length > 0 && Level.class.isAssignableFrom(treeS.getPaths()[0].getLastSegment().getClass())) {
				Level level = (Level) treeS.getPaths()[0].getLastSegment();
				if (level != null) {
					IDocument doc = this.getDocumentProvider().getDocument(this.getEditorInput());
					FindReplaceDocumentAdapter finder = new FindReplaceDocumentAdapter(doc);
					try {
						IRegion region = finder.find(0, String.format("id=\"%s\"", level.getId()), true, false, false, false);
						if (region != null) {
							this.selectAndReveal(region.getOffset(), region.getLength());
						}
					} catch (BadLocationException e) {
						UslmPlugin.logError(String.format("%s", level.getIdentifier()), e);
					}
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
