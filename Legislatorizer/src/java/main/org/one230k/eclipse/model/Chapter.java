package org.one230k.eclipse.model;

public class Chapter extends Level {

	@Override
	public String toString() {
		return String.format("Chapter %s - %s", this.getNum(), this.getHeading());
	}
	
}
