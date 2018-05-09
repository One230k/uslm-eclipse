package legislatorizer.model;

import java.util.ArrayList;
import java.util.List;

public class Chapter extends Level {
	
	private List<Section> sections = new ArrayList<Section>();

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	
}
