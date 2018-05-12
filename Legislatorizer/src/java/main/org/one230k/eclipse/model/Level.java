package org.one230k.eclipse.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public abstract class Level {

	private String heading;
	private String num;
	private String id;
	private String identifier;
	private int lineNumber;
	private Level parent;
	private List<Level> children = new ArrayList<Level>();

	
	public List<Level> getChildren() {
		return children;
	}
	public void setChildren(List<Level> children) {
		this.children = children;
	}
	public Level getParent() {
		return parent;
	}
	public void setParent(Level parent) {
		this.parent = parent;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public int getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	
	@Override
	public String toString() {
		return this.heading;
	}
	public Object[] getPath() {
		Stack<Object> stack = new Stack<Object>(); 
		Level node = this;

		do {
			stack.push(node);
			node = node.parent;
		} while (node != null);
		
		Object[] reverse = stack.toArray();
		Object[] finished = new Object[reverse.length];
		
		for (int i = 0; i < reverse.length; i++) {
			finished[reverse.length - i - 1] = reverse[i];
		}
		
		return finished;
	}
}
