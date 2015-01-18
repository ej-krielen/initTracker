package units;

import java.awt.Color;

public class Unit {
	private String name;
	private int iniative = 0;
	private String notes = "";
	private int hp = 6;
	private int debuffTopLeft = 0;
	private int debuffTopCenter = 0;
	private int debuffTopRight = 0;
	private int debuffBottomLeft = 0;
	private int debuffBottomCenter = 0;
	private int debuffBottomRight = 0;

	private Color background;
	
	public Unit(String name, Color background) {
		this.name = name;
		this.background = background;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIniative() {
		return iniative;
	}

	public void setIniative(int iniative) {
		this.iniative = iniative;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getDebuffTopLeft() {
		return debuffTopLeft;
	}

	public void setDebuffTopLeft(int debuffTopLeft) {
		this.debuffTopLeft = debuffTopLeft;
	}

	public int getDebuffTopCenter() {
		return debuffTopCenter;
	}

	public void setDebuffTopCenter(int debuffTopCenter) {
		this.debuffTopCenter = debuffTopCenter;
	}

	public int getDebuffTopRight() {
		return debuffTopRight;
	}

	public void setDebuffTopRight(int debuffTopRight) {
		this.debuffTopRight = debuffTopRight;
	}

	public int getDebuffBottomLeft() {
		return debuffBottomLeft;
	}

	public void setDebuffBottomLeft(int debuffBottomLeft) {
		this.debuffBottomLeft = debuffBottomLeft;
	}

	public int getDebuffBottomCenter() {
		return debuffBottomCenter;
	}

	public void setDebuffBottomCenter(int debuffBottomCenter) {
		this.debuffBottomCenter = debuffBottomCenter;
	}

	public int getDebuffBottomRight() {
		return debuffBottomRight;
	}

	public void setDebuffBottomRight(int debuffBottomRight) {
		this.debuffBottomRight = debuffBottomRight;
	}

	public Color getBackground() {
		return background;
	}

	public void setBackground(Color background) {
		this.background = background;
	}
}
