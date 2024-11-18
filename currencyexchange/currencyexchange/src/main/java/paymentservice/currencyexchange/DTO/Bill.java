package paymentservice.currencyexchange.DTO;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Bill {
	
	private List<Item> item;
	private  String userType;
	private  int customerTenure;
	private String originalCurrency;
	private String targetCurrency;
	
	
	
	public List<Item> getItem() {
		return item;
	}
	public void setItem(List<Item> item) {
		this.item = item;
	}
	
	public  String getUserType() {
		return userType;
	}
	public  void setUserType(String userType) {
		this.userType = userType;
	}
	
	public  int getCustomerTenure() {
		return customerTenure;
	}
	public  void setCustomerTenure(int customerTenure) {
		this.customerTenure = customerTenure;
	}
	public String getOriginalCurrency() {
		return originalCurrency;
	}
	public void setOriginalCurrency(String originalCurrency) {
		this.originalCurrency = originalCurrency;
	}
	public String getTargetCurrency() {
		return targetCurrency;
	}
	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}
	
	
	public Bill() {
		
	}
	public Bill(List<Item> item, String userType, int customerTenure, String originalCurrency, String targetCurrency) {
		
		this.item = item;
		this.userType = userType;
		this.customerTenure = customerTenure;
		this.originalCurrency = originalCurrency;
		this.targetCurrency = targetCurrency;
	}
	@Override
	public String toString() {
		return "Bill [item=" + item + ", originalCurrency=" + originalCurrency + ", targetCurrency=" + targetCurrency
				+ ", getItem()=" + getItem() + ", getOriginalCurrency()=" + getOriginalCurrency()
				+ ", getTargetCurrency()=" + getTargetCurrency() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	

}
