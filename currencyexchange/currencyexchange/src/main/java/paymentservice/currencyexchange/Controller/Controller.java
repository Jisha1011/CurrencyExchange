package paymentservice.currencyexchange.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import paymentservice.currencyexchange.DTO.Bill;
import paymentservice.currencyexchange.DTO.FinalAmount;
import paymentservice.currencyexchange.DTO.Item;
import paymentservice.currencyexchange.Service.BillGenerator;
import paymentservice.currencyexchange.Service.ExchangeRate;

@RestController
public class Controller {

	@Autowired
	Bill billObject;
	@Autowired
	BillGenerator billGenerator;

	@Autowired
	ExchangeRate ex;

	private Optional<FinalAmount> finalBill;

	@PostMapping("app/calculate")
	public ResponseEntity<Optional<FinalAmount>> calculate(@RequestBody Bill bill) {

		if (bill.getItem().isEmpty())
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Optional.empty());
		else {
			finalBill = billGenerator.getFinalBill(bill);
			if (!finalBill.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(finalBill);

			}

			else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(finalBill);
		}

	}
}
