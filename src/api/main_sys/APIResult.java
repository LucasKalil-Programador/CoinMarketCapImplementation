package api.main_sys;

public class APIResult<T> {
	
	private boolean erro = false;
	private String errorDetails = "no error";
	private String rawReturn = "no rawReturn";
	private T result = null;

	
	
	// getters
	
	public boolean isErro() {
		return erro;
	}
	
	public String getErrorDetails() {
		return errorDetails;
	}

	public T getResult() {
		return result;
	}
	
	public String getRawReturn() {
		return rawReturn;
	}

	
	
	// setters
	
	public void setErro(boolean erro) {
		this.erro = erro;
	}

	public void setResult(T result) {
		this.result = result;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	public void setRawReturn(String rawReturn) {
		this.rawReturn = rawReturn;
	}	
}
