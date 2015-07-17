package service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import model.Order;
import model.Status;
import exceptions.NotAvailableException;

import service.Chef;

public class OrderManager {

	public static List<Order> orderList = new ArrayList<Order>();
	private static List<Chef> chefs = new ArrayList<Chef>();
	private static OrderManager orderManager;

	private OrderManager() {
	}

	public static OrderManager getInstance() {
		if (orderManager == null) {
			synchronized (OrderManager.class) {
				if (orderManager == null) {
					orderManager = new OrderManager();
				}
			}

		}
		return orderManager;
	}

	public void takeOrder(Order order) {
		orderList.add(order);
		this.assignOrderToChef(order);
	}

	public List<Order> getOrders() {
		return orderList;
	}

	public void removeOrder(Order order) {
		orderList.remove(order);
	}

	public void assignOrderToChef(Order order) {
		for (Chef chef : chefs) {
			try {
				chef.setOrder(order);
				order.setStatus(Status.COOKING);
				break;
			} catch (NotAvailableException e) {
			}
		}
	}

	public void addChef(Chef chef) {
		chefs.add(chef);
	}

	public void completedOrder(Chef chef) {
		for (Order order : this.getOrders()) {
			if (order.equals(chef.getOrder())) {
				order.setStatus(Status.COMPLETED);
			}
		}
		for (Order order : this.getOrders()) {
			if (order.getStatus() == Status.WAITING) {
				try {
					chef.setOrder(order);
					order.setStatus(Status.COOKING);
					break;
				} catch (NotAvailableException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
