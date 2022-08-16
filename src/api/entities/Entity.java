package api.entities;

import org.json.simple.JSONObject;

public abstract class Entity {
	
	public static final String default_Value = "null";
	
	public abstract JSONObject toJSON();
	
	@Override
	public String toString() {
		return toJSON().toJSONString();
	}
}
