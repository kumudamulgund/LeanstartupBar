package model;

import java.util.List;

public class Order {

	private static int orderNumber;
	private List<Item> items;
	private Status status;
	
	public Order(List<Item> items){
		this.items = items;
		orderNumber++;
	}
	public int getOrderNumber() {
		return orderNumber;
	}
	
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	public String toString(){
		String itemsString = null;
		System.out.println(this.items.get(0).getName());
		for (Item item : this.getItems()) {
			itemsString = itemsString + " " + item.getName();
		}
		return itemsString;
	}
}
