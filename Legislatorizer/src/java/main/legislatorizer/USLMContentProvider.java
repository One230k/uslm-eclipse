package legislatorizer;

import org.eclipse.jface.viewers.ITreePathContentProvider;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.Viewer;

import legislatorizer.model.Chapter;
import legislatorizer.model.Section;
import legislatorizer.model.Subsection;
import legislatorizer.model.Title;

public class USLMContentProvider implements ITreePathContentProvider {

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		
		// Titles
		
		if (inputElement.getClass().equals(Title.class)) {
			Title title = (Title) inputElement;
			if (title != null && title.getChapters().size() > 0) {
				return title.getChapters().toArray();
			}
			if (title != null && title.getSections().size() > 0) {
				return title.getSections().toArray();
			}
		}
		
		// Chapters
		
		if (inputElement.getClass().equals(Chapter.class)) {
			Chapter chapter = (Chapter) inputElement;
			if (chapter != null) {
				return chapter.getSections().toArray();
			}
		}
		
		// Sections
		
		if (inputElement.getClass().equals(Section.class)) {
			Section section = (Section) inputElement;
			if (section != null && section.getSubsections().size() > 0) {
				return section.getSubsections().toArray();
			}
			if (section != null && section.getParagraphs().size() > 0) {
				return section.getParagraphs().toArray();
			}
		}
		
		// Subsections
		
		if (inputElement.getClass().equals(Subsection.class)) {
			Subsection sub = (Subsection) inputElement;
			if (sub != null) {
				return sub.getParagraphs().toArray();
			}
		}
		
		// Nothing
		
		return null;
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
