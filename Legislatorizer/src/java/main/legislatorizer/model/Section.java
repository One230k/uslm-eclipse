package legislatorizer.model;

import java.util.ArrayList;
import java.util.List;

public class Section extends Level {
	
	private List<Subsection> subsections = new ArrayList<Subsection>();
	private List<Paragraph> paragraphs = new ArrayList<Paragraph>();
	private String content;
	
	public List<Subsection> getSubsections() {
		return subsections;
	}
	public void setSubsections(List<Subsection> subsections) {
		this.subsections = subsections;
	}
	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}
	public void setParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
