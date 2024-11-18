package paymentservice.currencyexchange.DTO;

import org.springframework.stereotype.Component;

@Component
public class Item {

	
	private String item;
	private String itemCategory;
	private float amount;
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getItemCategory() {
		return itemCategory;
	}
	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	
	public Item() {
	}
	public Item(String item, String itemCategory, float amount) {
		
		this.item = item;
		this.itemCategory = itemCategory;
		this.amount = amount;
	}
	
	
}
