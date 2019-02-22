package Model;

public class Product {
	private String num;
	private String userID;
	private String prodName;
	private String groupName;
	private String price;
	private String amount;
	public Product(String num, String userID, String prodName, String groupName, String price, String amount) {
		super();
		this.num = num;
		this.userID = userID;
		this.prodName = prodName;
		this.groupName = groupName;
		this.price = price;
		this.amount = amount;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	
	
}
