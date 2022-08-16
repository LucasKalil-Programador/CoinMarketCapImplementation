package api.entities;

import org.json.simple.JSONObject;

public class Cryptocurrency extends Entity {

	public final String symbol;
	public final boolean active;
	public final String firstHistoricalData;
	public final String lastHistoricalData;
	public final String name;
	public final long rank;
	public final long id;
	public final String slug;
	public final Platform platform;
	
	public Cryptocurrency(String symbol, boolean active, String firstHistoricalData,String lastHistoricalData, String name, long rank, long id,
			String slug, Platform platform) {
		this.symbol = symbol;
		this.active = active;
		this.firstHistoricalData = firstHistoricalData;
		this.lastHistoricalData = lastHistoricalData;
		this.name = name;
		this.rank = rank;
		this.id = id;
		this.slug = slug;
		this.platform = platform;
	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		
		json.put("symbol", symbol);
		json.put("is_active", active ? 1:0);
		json.put("first_historical_data", firstHistoricalData);
		json.put("last_historical_data", lastHistoricalData);
		json.put("name", name);
		json.put("rank", rank);
		json.put("id", id);
		json.put("slug", slug);
		
		json.put("platform", platform != null? platform.toJSON(): null);

		return json;
	}
	
	
	
	// static methods

	public static Cryptocurrency fromJSON(JSONObject json) {
		if(json == null) json = new JSONObject();
		
		String symbol              = (String)   json.getOrDefault("symbol", default_Value);
		boolean active             = ((long)    json.getOrDefault("is_active", 0l)) == 1l;
		String firstHistoricalData = (String)   json.getOrDefault("first_historical_data", default_Value);
		String LastHistoricalData  = (String)   json.getOrDefault("last_historical_data", default_Value);
		String name                = (String)   json.getOrDefault("name", default_Value);
		long rank                  = (long)     json.getOrDefault("rank", 0l);
		long id                    = (long)     json.getOrDefault("id", 0l);
		String slug                = (String)   json.getOrDefault("slug", default_Value);
		
		Platform platform = null;
		if(json.getOrDefault("platform", null) != null)
			platform = Platform.fromJSON((JSONObject) json.getOrDefault("platform", null));
		
		return new Cryptocurrency(symbol, active, firstHistoricalData,LastHistoricalData, name, rank, id, slug, platform);
	}
}
 