package legislatorizer.editors;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import legislatorizer.model.Title;

public class TitleEditor extends EditorPart {

	private Title title;
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		IFileEditorInput fInput = (IFileEditorInput) input;
		if (input.exists() && fInput != null) {
			try {
				
				IFile file = fInput.getFile();
				InputStream stream = new BufferedInputStream(file.getContents());
				
				SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
				XMLReader reader = parser.getXMLReader();
				reader.parse(new InputSource(stream));
				title = parse(reader);
				
			} catch (ParserConfigurationException e) {
				throw new PartInitException("Failed to initialize editor", e);
			} catch (SAXException e) {
				throw new PartInitException("Failed to initialize editor", e);
			} catch (IOException e) {
				throw new PartInitException("Failed to initialize editor", e);
			} catch (CoreException e) {
				throw new PartInitException("Failed to initialize editor", e);
			}
		}
		
		setSite(site);
		setInput(input);
	}
	
	private Title parse(XMLReader reader) {
		Title title = new Title();
		
		reader.getContentHandler();
		
		return title;
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}
	
	public Title getModel() {
		return title;
	}

}
