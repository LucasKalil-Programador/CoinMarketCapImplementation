package api.endpoints;

import java.util.LinkedList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import api.entities.Cryptocurrency;
import api.main_sys.APIResult;

public class CryptocurrencyMap extends EndPoint<Cryptocurrency> {
	
	public CryptocurrencyMap(String apiKeyToken) { super(apiKeyToken); }
	
	@Override
	public String getURL() {
		return BASE_URL + "/v1/cryptocurrency/map" + buildParams();
	}
	
	@Override
	protected void execulteHendler(String resultOfExeculte, APIResult<Cryptocurrency[]> apiResult) {
		LinkedList<Cryptocurrency> cryptocurrencies = new LinkedList<Cryptocurrency>();
		
		try {
			JSONObject json = (JSONObject) new JSONParser().parse(resultOfExeculte);
			JSONArray jsonDatas = (JSONArray) json.get("data");
			jsonDatas.forEach(data -> {
				cryptocurrencies.add(Cryptocurrency.fromJSON((JSONObject) data));
			});
		} catch (Exception e) {
			apiResult.setErro(true);
			apiResult.setErrorDetails(e.getMessage());
		}
		
		apiResult.setResult(cryptocurrencies.toArray(i -> new Cryptocurrency[i]));
	}

	
	
	// ListingStatus
	
	public static enum ListingStatusOptions {
		active("active"),
		inactive("inactive"),
		untracked("untracked");
		
		private static final String key = "listing_status";
		private final String value;
		
		private ListingStatusOptions(String value) {
			this.value = value;
		}
		
		public String getValue() {
			return value;
		}
	}
	
	public CryptocurrencyMap setListingStatus(ListingStatusOptions option) {
		params.put(ListingStatusOptions.key, option.value);
		return this;
	}
	
	
	
	// offset
	
	public CryptocurrencyMap setOffset(int offset) {
		String key = "start";
		
		if(offset > 1) {
			params.put(key, offset);
		} else if(params.containsKey(key)) {
			params.remove(key);
		}
		
		return this;
	}
	
	
	
	// limit
	
	public CryptocurrencyMap setLimit(int limit) {
		String key = "limit";
		
		if(limit > 1) {
			params.put(key, limit);
		}else if(params.containsKey(key)) {
			params.remove(key);
		}
		
		return this;
	}

	
	
	// symbol
	
	public CryptocurrencyMap searchBySymbol(String symbol) {
		String key = "symbol";
		
		if(symbol != null && !symbol.isBlank()) {
			params.put(key, symbol);
		} else if(params.containsKey(key)) {
			params.remove(key);
		}
		
		return this;
	}
}
