package pageobjects.restaurants;

public interface Restaurant {
	public void verifyRestaurantTitle(String title);
	public void addItems(String items, int quantity);
	public void clickOnCheckout();
}
