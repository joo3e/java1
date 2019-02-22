package Model;

public class UserInfo {

	private String name;
	private String mobile;
	private String prodName;
	private String amount;
	private String sum;
	public UserInfo(String name, String mobile, String prodName, String amount, String sum) {
		super();
		this.name = name;
		this.mobile = mobile;
		this.prodName = prodName;
		this.amount = amount;
		this.sum = sum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}

	
	
}
