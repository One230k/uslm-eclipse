package legislatorizer.model;

import java.util.ArrayList;
import java.util.List;

public class Subsection extends Level {

	private List<Paragraph> paragraphs = new ArrayList<Paragraph>();

	public List<Paragraph> getParagraphs() {
		return paragraphs;
	}

	public void setParagraphs(List<Paragraph> paragraphs) {
		this.paragraphs = paragraphs;
	}
	
	
}
