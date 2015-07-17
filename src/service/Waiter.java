package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Cereal;
import model.Coffee;
import model.Item;
import model.Order;
import model.Pizza;
import model.Sandwich;
import model.Status;

public class Waiter implements Runnable {

	//private Order order;
	@Override
	public void run() {
		this.getOrder();
	}
	
	private void finishOrder(List<Item> items){
		Order order = new Order(items);
		order.setStatus(Status.WAITING);
		System.out.print("OrderNumber:ORD"+order.getOrderNumber()+" for");
		for(Item item : items){
			System.out.print(" "+item.getName()+",");
		}
		System.out.print("has neen placed at" +this.getFormattedTime());
		OrderManager.getInstance().takeOrder(order);
	}

	private void getOrder() {
		int itemId = 0;
		int order = 1;
		Item item;
		List<Item> items;
		items = new ArrayList<Item>();
		while (order != 0) {
			message("press 1 for sandwich,2 for coffee,3 for cereal, 4 for pizza,5 to finish order,6 to close thr Bar");
			itemId = getItemId();
			switch (itemId) {
			case 1:
				item = new Sandwich();
				items.add(item);
				break;
			case 2:
				item = new Coffee();
				items.add(item);
				break;
			case 3:
				item = new Cereal();
				items.add(item);
				break;
			case 4:
				item = new Pizza();
				items.add(item);
				break;
			case 5:
				finishOrder(items);
				items = new ArrayList<Item>();
				message("order placed.");
				break;
			case 6:
				order = 0;
				System.out.println("Bar is closed");
				break;
			}
		}
	}

	public void message(String string) {

		System.out.println(string);
	}

	private int getItemId() {
		message("Enter item id:");
		int itemId = 0;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			String stringItemId = reader.readLine();
			itemId = Integer.parseInt(stringItemId);
			if (itemId > 6) {
				message("Item Id not present");
			} else if (itemId <= 0) {
				message("Item Id not present");
			}
		} catch (NumberFormatException e) {
			message("not a valid item id");
			getItemId();
		} catch (IOException e) {
			message("not a valid item id");
		}
		return itemId;
	}
	
	private String getFormattedTime() {
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a");
		String formattedDate = sdf.format(now.getTime());
		return formattedDate;
	}

}
