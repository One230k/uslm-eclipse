package org.one230k.eclipse.model;

public class Note extends Level {

	@Override
	public String toString() {
		return String.format("%s", this.getHeading());
	}
}
