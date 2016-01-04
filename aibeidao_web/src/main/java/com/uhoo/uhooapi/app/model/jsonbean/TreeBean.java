package com.uhoo.uhooapi.app.model.jsonbean;

import java.util.List;

public class TreeBean {
	
	private String id;
	
	private int index;
	
	private String treename;
	
	private String text;
	
	private List children;
	
	private String state;
	
	private boolean checked;
	
	private String iconCls;
	
	private float score;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Float getScore() {
		return score;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTreename() {
		return treename;
	}

	public void setTreename(String treename) {
		this.treename = treename;
	}

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}
