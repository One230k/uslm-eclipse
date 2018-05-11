package org.one230k.eclipse.model;

public class Section extends Level {
	
	private String content;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return String.format("§ %s - %s", this.getNum(), this.getHeading());
	}
	
}
