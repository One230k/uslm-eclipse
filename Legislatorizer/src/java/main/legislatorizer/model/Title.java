package legislatorizer.model;

import java.util.ArrayList;
import java.util.List;

public class Title extends Level {

	private List<Chapter> chapters = new ArrayList<Chapter>();
	private List<Section> sections = new ArrayList<Section>();
	
	public List<Chapter> getChapters() {
		return chapters;
	}
	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}
	public List<Section> getSections() {
		return sections;
	}
	public void setSections(List<Section> sections) {
		this.sections = sections;
	}
}
