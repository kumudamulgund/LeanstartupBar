package service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;

import model.Item;
import model.Order;
import exceptions.NotAvailableException;

public class Chef implements Runnable{
	
	private Boolean isAvailable;
	private Order order;
	private File outputFile;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	
	public Chef(){
		this.isAvailable = true;
		order = null;
		outputFile = new File("OrderProcessed.txt");
		
		if (!outputFile.exists()) {
			try {
				outputFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean getIsAvailable(){
		return this.isAvailable;
	}
	
	public void setIsAvailable(boolean isAvailable){
		this.isAvailable = isAvailable;
	}
	
	public void cook() throws IOException {
		fileWriter = new FileWriter(outputFile,true);
		bufferedWriter = new BufferedWriter(fileWriter);
		bufferedWriter.append("Chef: Picked up ORD" + order.getOrderNumber() + " at "+ getFormattedTime());
		bufferedWriter.newLine();
		Iterator iterator = order.getItems().iterator();
		while(iterator.hasNext()){
			Item item = (Item) iterator.next();	
			bufferedWriter.append("Chef:Cooking " + item.getName());
			bufferedWriter.newLine();
			try {
				Thread.sleep(item.getPreparationTime()*60* 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			bufferedWriter.append("Chef:Finished making " + item.getName() + " at "
					+ getFormattedTime());
			bufferedWriter.newLine();
		}
		bufferedWriter.append("Chef:Finished ORD"+order.getOrderNumber()+" at " + this.getFormattedTime());
		bufferedWriter.newLine();
		bufferedWriter.close();
	}

	public void run() {
		boolean run = true;	
		while (run) {
			if(this.order != null){
				try {
					this.cook();
				this.orderCompleted();
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void orderCompleted(){
		this.isAvailable = true;
		this.order = null;
		OrderManager.getInstance().completedOrder(this);
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) throws NotAvailableException {
		if(!this.isAvailable){
			throw new NotAvailableException();
		}
		this.order = order;
		this.isAvailable = false;
	}

	private String getFormattedTime() {
		Calendar now = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("h:mm:ss a");
		String formattedDate = sdf.format(now.getTime());
		return formattedDate;
	}

}
