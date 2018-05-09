package legislatorizer;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import legislatorizer.editors.TitleEditor;

public class USLMAdapterFactory implements IAdapterFactory {

	@Override
	public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (IContentOutlinePage.class.equals(adapterType)) {
			TitleEditor editor = (TitleEditor) adaptableObject;
			return (T) new USLMContentOutlinePage(editor.getModel());
		}
		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return new Class<?>[] { IContentOutlinePage.class };
	}

}
