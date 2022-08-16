package api.main_sys;

import api.endpoints.CryptocurrencyMap;
import api.endpoints.EndPoint;

public class CoinMarketCapAPI {

	
	
	// token and class builder
	
	private String apiKeyToken;
	
	public CoinMarketCapAPI(String apiKeyToken) {
		this.apiKeyToken =  apiKeyToken;
	}
	
	
	
	// get Methods
	
	public String getApiKeyToken() {
		return apiKeyToken;
	}
	
	public static String getBASE_URL() {
		return EndPoint.BASE_URL;
	}
	
	
	
	// EndPoints
	
	public CryptocurrencyMap newCryptocurrencyMap() {
		return new CryptocurrencyMap(apiKeyToken);
	}	
}
