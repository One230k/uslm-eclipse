package org.one230k.eclipse.ui;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.one230k.eclipse.UslmPlugin;
import org.one230k.eclipse.model.parser.SaxUslmParser;

public class USLMContentOutlinePage extends ContentOutlinePage {
	
	private IEditorInput file;
	
	public USLMContentOutlinePage(IEditorInput iEditorInput) {
		super();
		file = iEditorInput;
	}

	public void createControl(Composite parent) {
		super.createControl(parent);
		TreeViewer viewer = getTreeViewer();
		viewer.setContentProvider(new USLMContentProvider());
		viewer.setLabelProvider(new LabelProvider());
		viewer.addSelectionChangedListener(this);
		try {
			viewer.setInput(SaxUslmParser.parse(file));
		} catch (PartInitException e) {
			UslmPlugin.getPlugin().getLog().log(new Status(Status.ERROR, "org.one230k.eclipse", Status.OK, "Failed to create outline control", e));
		}
	}
	
	
}
