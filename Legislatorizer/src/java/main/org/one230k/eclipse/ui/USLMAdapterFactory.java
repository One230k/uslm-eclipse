package org.one230k.eclipse.ui;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class USLMAdapterFactory implements IAdapterFactory {

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (IContentOutlinePage.class.equals(adapterType)) {
			EditorPart editor = (EditorPart) adaptableObject;
			return (T) new USLMContentOutlinePage(editor.getEditorInput());
		}
		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return new Class<?>[] { IContentOutlinePage.class };
	}

}
