package org.one230k.eclipse.model;

public class Part extends Level {
	
	@Override
	public String toString() {
		return String.format("Part %s - %s", this.getNum(), this.getHeading());
	}

}
