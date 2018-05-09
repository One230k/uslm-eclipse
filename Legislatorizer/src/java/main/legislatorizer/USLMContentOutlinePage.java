package legislatorizer;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;

import legislatorizer.model.Title;

public class USLMContentOutlinePage extends ContentOutlinePage {
	
	private Title t;
	
	public USLMContentOutlinePage(Title title) {
		super();
		t = title;
	}

	public void createControl(Composite parent) {
		super.createControl(parent);
		TreeViewer viewer = getTreeViewer();
		viewer.setContentProvider(new USLMContentProvider());
		viewer.setLabelProvider(new LabelProvider());
		viewer.addSelectionChangedListener(this);
		viewer.setInput(t);
	}
	
	
}
