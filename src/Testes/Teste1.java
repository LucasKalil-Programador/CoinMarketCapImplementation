package Testes;

import api.main_sys.CoinMarketCapAPI;

public class Teste1 {

	private static String apiKey = "8488f88e-88e2-418a-8a55-c66f6da0b50e";

	public static void main(String[] args) {
		CoinMarketCapAPI api = new CoinMarketCapAPI(apiKey);
		System.out.println(api.newCryptocurrencyMap()
				.searchBySymbol("BTC")
				.getURL());
	}
}
