package org.one230k.eclipse.model;

public class Subsection extends Level {

	@Override
	public String toString() {
		return String.format("(%s)", this.getNum());
	}
}
