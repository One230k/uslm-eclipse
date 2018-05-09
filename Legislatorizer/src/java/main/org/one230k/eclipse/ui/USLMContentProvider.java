package org.one230k.eclipse.ui;

import org.eclipse.jface.viewers.ITreePathContentProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;
import org.one230k.eclipse.model.Level;

public class USLMContentProvider implements ITreePathContentProvider {

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		
	}

	@Override
	public Object[] getElements(Object inputElement) {
		
		Level level = (Level) inputElement;
		if (level != null) {
			return level.getChildren().toArray();
		}
		
		// Nothing
		
		return new Object[] {};
	}

	@Override
	public Object[] getChildren(TreePath parentPath) {
		return getElements(parentPath.getLastSegment());
	}

	@Override
	public boolean hasChildren(TreePath path) {
		return true;
	}

	@Override
	public TreePath[] getParents(Object element) {
		return null;
	}

}
