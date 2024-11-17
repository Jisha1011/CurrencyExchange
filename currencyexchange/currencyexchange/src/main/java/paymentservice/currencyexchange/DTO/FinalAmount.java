package paymentservice.currencyexchange.DTO;

import org.springframework.stereotype.Component;

@Component
public class FinalAmount {
	
	private float amount;
	private String currency;
	private String message;
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public FinalAmount()
	{
		
	}
	public FinalAmount(float amount, String currency, String message) {
		
		this.amount = amount;
		this.currency = currency;
		this.message = message;
	}
	
	

}
