package characters;

/** 
 * @author      Erik-Jan Krielen 	erik-jan.krielen@atos.net
 * @version     0.1					Current version number of program
 * @since November 2nd 2014 Creation of this file
 * @update November 13rd 2014 Latest update of this file
 * @LatestUpdate Added variables and constructor
 * 
 * */

public class PlayerCharacter {
	
	private String name; 
	private int iniative;
	private String notes;
	private int hp;
	private int debuffTopLeft;
	private int debuffTopCenter;
	private int debuffTopRight;
	private int debuffBottomLeft;
	private int debuffBottomCenter;
	private int debuffBottomRight;
	
	public PlayerCharacter(){
		name = "Player 1"; 
		iniative = 0;
		notes = "Notes: ";
		hp = 6;
		debuffTopLeft = 0;
		debuffTopCenter = 0;
		debuffTopRight = 0;
		debuffBottomLeft = 0;
		debuffBottomCenter = 0;
		debuffBottomRight = 0;
	}

	
	
	
	
	//getters and setters
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
	
}
