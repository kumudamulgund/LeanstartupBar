package controller;

import service.Chef;
import service.OrderManager;
import service.Waiter;

public class LeanStartUpBar {
	
	public static void main(String[] args){
		Waiter waiter = new Waiter();
		Chef chef = new Chef();
		OrderManager.getInstance().addChef(chef);
		Thread waiterThread = new Thread(waiter);
		Thread chefThread = new Thread(chef);
		waiterThread.start();
		chefThread.start();
	}
}
