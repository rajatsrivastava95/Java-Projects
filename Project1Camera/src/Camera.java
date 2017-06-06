public class Camera {

	// Declaring Instance Variables (Attributes)
	boolean hasWiFi; 			// True is camera has WiFi else False

	boolean isWaterResistant; 	// True of camera is water resistant else False

	boolean hasGPS; 			// True of camera has GPS else False

	String condition; 			// Amongst: "New", "Refurbished", and "Used"

	double price; 				// Range: 0.00 to 1000.00

	int userRating; 			// Range 0 to 5

	//Constructor: Initializes the instance variables
	public Camera(boolean hasWifi, boolean isWaterResistant, boolean hasGPS,
			String condition, double price, int userRating) {
		this.hasWiFi = hasWifi;
		this.isWaterResistant = isWaterResistant;
		this.hasGPS = hasGPS;
		this.condition = condition;
		this.price = price;
		this.userRating = userRating;
	}

	public int computeValue() {			//Method to compute the value based on features and condition
		int value = 0;
		
		if (hasWiFi) {
			value = value + 1;			// Value increased by 1 if true
		}
		
		if (isWaterResistant) {
			value = value + 1;			// Value increased by 1 if true
		}

		if (hasGPS) {
			value = value + 1;			// Value increased by 1 if true
		}

		if (condition.equals("New")) {
			value = value + 2;			// Value increased by 2 if true
		} else if (condition.equals("Refurbished")) {
			value = value + 1;
		} else if (condition.equals("Used")) {
			value = value + 0;			// Value not increased
		}
		return value;					// Return the final value of "value"
		
	}
	
}
