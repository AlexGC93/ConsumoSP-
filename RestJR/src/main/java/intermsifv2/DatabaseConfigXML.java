package intermsifv2;

public class DatabaseConfigXML {
    private String url;
    private String username;
    private String password;

    public DatabaseConfigXML(String url, String username, String password) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	@Override
	public String toString() {
		return "DatabaseConfigXML [url=" + url + ", username=" + username + ", password=" + password + "]";
	}
    
}