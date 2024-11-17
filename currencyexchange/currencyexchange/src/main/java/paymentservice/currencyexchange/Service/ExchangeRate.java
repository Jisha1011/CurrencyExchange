package paymentservice.currencyexchange.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import paymentservice.currencyexchange.DTO.FinalAmount;

@Component
public class ExchangeRate {
@Autowired
FinalAmount finalAmount;
	public Optional<Long> convertCurrency(String originalCurrency, String targetCurrency) {
		return getExchangeCurrency(originalCurrency, targetCurrency);
	}

	private Optional<Long> getExchangeCurrency(String originalCurrency, String targetCurrency) {
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(
							"https://v6.exchangerate-api.com/v6/595eeef1df5d71fdb6b47d79/latest/" + originalCurrency))
					.method("GET", HttpRequest.BodyPublishers.noBody()).build();

			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

			Object obj = new JSONParser().parse(response.body());
			JSONObject jsonObj = (JSONObject) obj;
			HashMap conversionRate = ((HashMap) jsonObj.get("conversion_rates"));
			System.out.println(conversionRate.get(targetCurrency).getClass());
			Optional<Long> rateInDouble = Optional.ofNullable((Long) conversionRate.get(targetCurrency));

			return rateInDouble;

		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Optional.empty();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Optional.empty();
		}

	}
}
