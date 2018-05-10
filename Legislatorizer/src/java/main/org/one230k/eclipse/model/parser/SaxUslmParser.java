package org.one230k.eclipse.model.parser;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.one230k.eclipse.model.Chapter;
import org.one230k.eclipse.model.Level;
import org.one230k.eclipse.model.Part;
import org.one230k.eclipse.model.Section;
import org.one230k.eclipse.model.Subsection;
import org.one230k.eclipse.model.Title;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxUslmParser extends DefaultHandler {

	private Level root = null;
	private Level currentLevel = null;
	private boolean bHeading = false;
	private boolean bContent = false;
	private StringBuilder content = null;

	private void startLevel(Level newLevel, Attributes attributes) {

		newLevel.setParent(currentLevel);
		newLevel.setId(attributes.getValue("id"));
		newLevel.setIdentifier(attributes.getValue("identifier"));

		if (currentLevel != null) {
			currentLevel.getChildren().add(newLevel);
		}
		currentLevel = newLevel;
	}
	
	private void endLevel() {
		if (currentLevel != null) {
			currentLevel = currentLevel.getParent();
		}
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if (bContent) {
			// skip HTML
		} else if (qName.equalsIgnoreCase("title")) {
			startLevel(new Title(), attributes);
		} else if (qName.equalsIgnoreCase("chapter")) {
			startLevel(new Chapter(), attributes);
		} else if (qName.equalsIgnoreCase("subsection")) {
			startLevel(new Subsection(), attributes);
		} else if (qName.equalsIgnoreCase("part")) {
			startLevel(new Part(), attributes);
		} else if (qName.equalsIgnoreCase("num")) {
			if (currentLevel != null) {
				currentLevel.setNum(attributes.getValue("value"));
			}
		} else if (qName.equalsIgnoreCase("section")) {
			if (currentLevel != null) {
				currentLevel = new Section();
			}
		} else if (qName.equalsIgnoreCase("heading")) {
			bHeading = true;
		} else if (qName.equalsIgnoreCase("content")) {
			bContent = true;
			content = new StringBuilder();
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		if (qName.equalsIgnoreCase("title")) {
			endLevel();
		} else if (qName.equalsIgnoreCase("part")) {
			endLevel();
		} else if (qName.equalsIgnoreCase("chapter")) {
			endLevel();
		} else if (qName.equalsIgnoreCase("section")) {
			endLevel();
		} else if (qName.equalsIgnoreCase("subsection")) {
			endLevel();
		} else if (qName.equalsIgnoreCase("content")) {
			bContent = false;
		} else if (qName.equalsIgnoreCase("heading")) {
			bHeading = false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (bHeading && currentLevel != null) {
			currentLevel.setHeading(new String(ch, start, length));
			bHeading = false;
		} else if (bContent) {
			content.append(new String(ch, start, length));
		}
	}
	
	public Title getTitle() {
		return (Title) root;
	}
	
	public static Title parse(IEditorInput file) throws PartInitException {
		
		Title title = new Title();
		
		IFileEditorInput file2 = file.getAdapter(IFileEditorInput.class);
		
		if (file2.exists() && file2 != null) {
			try {
				
				InputStream stream = new BufferedInputStream(file2.getFile().getContents());
				
				SaxUslmParser handler = new SaxUslmParser();
				SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
				parser.parse(new InputSource(stream), handler);
				
				title = handler.getTitle();
								
			} catch (ParserConfigurationException e) {
				throw new PartInitException("Failed to configure parser", e);
			} catch (SAXException e) {
				throw new PartInitException("Parse exceptionr", e);
			} catch (IOException e) {
				throw new PartInitException("File access error" + file2.getName(), e);
			} catch (CoreException e) {
				throw new PartInitException("System error", e);
			}
		} else {
			throw new PartInitException("Did not get editor input");
		}
		
		return title;
	}
}
