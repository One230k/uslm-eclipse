package org.one230k.eclipse.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.one230k.eclipse.UslmPlugin;
import org.one230k.eclipse.model.Level;
import org.one230k.eclipse.model.Title;
import org.one230k.eclipse.model.parser.SaxUslmParser;

public class USLMContentOutlinePage extends ContentOutlinePage implements ISelectionListener {
	
	private IEditorInput editorInput;
	private Title title;
	private TreeViewer viewer;
	
	public USLMContentOutlinePage(IEditorInput iEditorInput) {
		super();
		editorInput = iEditorInput;
	}

	public void createControl(Composite parent) {
		super.createControl(parent);
		viewer = getTreeViewer();
		viewer.setContentProvider(new USLMContentProvider());
		viewer.setLabelProvider(new LabelProvider());
		viewer.addSelectionChangedListener(this);
		getSite().setSelectionProvider(viewer);
		getSite().getWorkbenchWindow().getSelectionService().addSelectionListener(this);

	      Job job = new Job("parse-xml") {
	            @Override
	            protected IStatus run(IProgressMonitor monitor) {
	        		try {
						title = SaxUslmParser.parse(editorInput);
					} catch (PartInitException e) {
						UslmPlugin.logError("Couldn't create the Outline", e);
						return Status.CANCEL_STATUS;
					}
	                Display.getDefault().asyncExec(new Runnable() {
	                    public void run() {
	            			viewer.setInput(title);
	                    }
	                });
	                return Status.OK_STATUS;
	            }

	        };

	        job.schedule();
	
	}
	
	public void dispose() {
		// important: We need do unregister our listener when the view is disposed
		getSite().getWorkbenchWindow().getSelectionService().removeSelectionListener(this);
		super.dispose();
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part.getClass().equals(TitleEditor.class)) {
			TextSelection text = (TextSelection) selection;
			if (text != null && title != null) {
				TreePath path = lineToPath(text.getStartLine(), title);
				if (path != null) {
					viewer.setSelection(new TreeSelection(path));
				}
			}
		}
	}
	
	private TreePath lineToPath(int line, Level node) {
		if (node.getLineNumber() == line) {
			return new TreePath(node.getPath());
		} else {
			for (Level testLevel : node.getChildren()) {
				TreePath testPath = lineToPath(line, testLevel);
				if (testPath != null) {
					return testPath;
				}
			}
			return null;
		}
	}
}
