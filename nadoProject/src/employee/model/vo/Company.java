package employee.model.vo;

public class Company implements java.io.Serializable{
	private static final long serialVersionUID = 101L;
	private String driver;
	private String url;
	private String id;
	private String password;
	
	public Company() {}

	public Company(String driver, String url, String id, String password) {
		super();
		this.driver = driver;
		this.url = url;
		this.id = id;
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Company [driver=" + driver + ", url=" + url + ", id=" + id + ", password=" + password + "]";
	}
	
}
