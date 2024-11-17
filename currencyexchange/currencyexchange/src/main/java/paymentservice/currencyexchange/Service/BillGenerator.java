package paymentservice.currencyexchange.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import paymentservice.currencyexchange.DTO.Bill;
import paymentservice.currencyexchange.DTO.FinalAmount;
import paymentservice.currencyexchange.DTO.Item;

@Service
public class BillGenerator {

	@Autowired
	Bill billObject;
	@Autowired
	ExchangeRate ex;
	private double nonGroceryTotal = 0f;
	private double groceryTotal = 0f;
	private double finalAmount = 0f;

	public Optional<FinalAmount> getFinalBill(Bill bill) {

		// set UserType and customerTenure
		billObject.setUserType(bill.getUserType());
		billObject.setCustomerTenure(bill.getCustomerTenure());

		// calculate total amount on grocery and other items
		bill.getItem().stream().forEach(x -> calculateTotalAmount(x));

		// calculate applicable discount on non grocery item
		calculateFinalPrice(nonGroceryTotal);

		finalAmount = nonGroceryTotal + groceryTotal;

		// apply 5$ discount for every 100$ purchase
		finalAmount = fiveDollarDiscount(finalAmount);

		// calculate final Amount in Target currency
		return calculateCurrencyRateAndFinalAmount(bill.getOriginalCurrency(), bill.getTargetCurrency());

	}

	private Optional<FinalAmount> calculateCurrencyRateAndFinalAmount(String originalCurrency, String targetCurrency) {
		Optional<Long> rate = ex.convertCurrency(originalCurrency, targetCurrency);

		if (!rate.isEmpty()) {
			groceryTotal = groceryTotal * rate.get();
			FinalAmount f = new FinalAmount((float) finalAmount, targetCurrency, "");
			resetValue();
			return Optional.of((f));
		} else {
			FinalAmount f = new FinalAmount((float) finalAmount, targetCurrency,
					"Requested currency not found, check for errors");
			resetValue();
			return Optional.of((f));

		}
	}

	private void calculateTotalAmount(Item item) {

		if (item.getItemCategory().equalsIgnoreCase("Grocery")) {
			groceryTotal = groceryTotal + item.getAmount();
		} else

		{
			nonGroceryTotal = nonGroceryTotal + item.getAmount();
		}
	}

	private void calculateFinalPrice(double amount)

	{
		if (billObject.getUserType().equalsIgnoreCase("customer") && billObject.getCustomerTenure() > 2) {
			calculateDiscount(amount, 0.05f);

		}

		else if (billObject.getUserType().equalsIgnoreCase("employee")) {
			calculateDiscount(amount, 0.3f);

		} else if (billObject.getUserType().equalsIgnoreCase("affiliate")) {
			calculateDiscount(amount, 0.1f);

		} else {

		}

	}

	private void calculateDiscount(double totalPrice, double discount) {
		System.out.println("calculateDiscount called");
		double discountAmout = totalPrice * discount;
		nonGroceryTotal = totalPrice - discountAmout;

	}

	private double fiveDollarDiscount(double totalPrice) {
		if (totalPrice >= 100) {
			int discount = (int) (totalPrice / 100);
			return totalPrice - (discount * 5);
		} else
			return totalPrice;
	}

	private void resetValue() {
		nonGroceryTotal = 0;
		groceryTotal = 0;
		finalAmount = 0;
	}
}
