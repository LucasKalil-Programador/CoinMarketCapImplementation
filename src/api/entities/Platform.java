package api.entities;

import org.json.simple.JSONObject;

public class Platform extends Entity {
	
	public final String symbol;
	public final String name;
	public final String tokenAddress;
	public final long id;
	public final String slug;
	
	public Platform(String symbol, String name, String tokenAddress, long id, String slug) {
		this.symbol = symbol;
		this.name = name;
		this.tokenAddress = tokenAddress;
		this.id = id;
		this.slug = slug;
	}

	@Override
	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("symbol", symbol);
		json.put("name", name);
		json.put("token_address", tokenAddress);
		json.put("id", id);
		json.put("slug", slug);
		
		return json;
	}
	
	
	
	// static methods
	
	public static Platform fromJSON(JSONObject json) {
		if(json == null) json = new JSONObject();
	
		String symbol       = (String) json.getOrDefault("symbol", default_Value);
		String name         = (String) json.getOrDefault("name", default_Value);
		String tokenAddress = (String) json.getOrDefault("token_address", default_Value);
		long   id           = (long)   json.getOrDefault("id", 0l);
		String slug         = (String) json.getOrDefault("slug", default_Value);
		
		return new Platform(symbol, name, tokenAddress, id, slug);
	}
}
