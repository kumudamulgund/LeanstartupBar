package model;

public abstract class Item {
	
	protected int itemId;
	protected int preparationTime;
	protected String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getPreparationTime() {
		return preparationTime;
	}
	public void setPreparationTime(int preparationTime) {
		this.preparationTime = preparationTime;
	}
	

}
