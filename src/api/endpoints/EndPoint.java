package api.endpoints;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.concurrent.FutureTask;

import org.json.simple.JSONObject;

import api.main_sys.APIResult;

public abstract class EndPoint<T> {
	
	public static final String BASE_URL = "https://pro-api.coinmarketcap.com";
	public static final String CMC_KEY = "CMC_PRO_API_KEY";
	
	protected final JSONObject params = new JSONObject();
	
	public EndPoint(String apiKeyToken) {
		params.put(CMC_KEY, apiKeyToken);
	}
	
	// execulte methods
	
	public FutureTask<APIResult<T[]>> execulteAsync() {
		FutureTask<APIResult<T[]>> task = new FutureTask<APIResult<T[]>>(() -> this.execulte());
		Thread thread = new Thread(task);
		thread.setDaemon(true);
		thread.start();
		return task;
	}
	
	public APIResult<T[]> execulte() {
		APIResult<T[]> apiResult = new APIResult<T[]>();
		
		String result = makeHttpRequest(getURL(), 1000, 1000, apiResult);
		execulteHendler(result, apiResult);
		
		return apiResult;
	}
	
	public String buildParams() {
		StringBuilder sb = new StringBuilder();
		if (params.size() > 0) {
			sb.append("?");
			params.forEach((k, v) -> {
				sb.append(k + "=" + v + "&");
			});
			sb.setLength(sb.length() - 1);
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return getURL();
	}
	
	
	// abstract methods
	
	public abstract String getURL();
	
	protected abstract void execulteHendler(String resultOfExeculte, APIResult<T[]> apiResult);

	
	
	// http request
	
	private static String makeHttpRequest(	
			String urlString, 
			long connectionTimeoutSeconds, 
			long requestTimeoutSeconds,
			APIResult<?> result) {
		
		Duration cTimeout = Duration.ofSeconds(connectionTimeoutSeconds);
		Duration rTimeout = Duration.ofSeconds(requestTimeoutSeconds);

		HttpClient client = HttpClient.newBuilder().connectTimeout(cTimeout).build();
		HttpRequest request = HttpRequest.newBuilder(URI.create(urlString)).timeout(rTimeout).build();

		try {
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			result.setRawReturn(response.body());
			return response.body();
		} catch (Exception e) {
			result.setErro(true);
			result.setErrorDetails(e.getMessage());
			return "Error";
		}
	}
}
