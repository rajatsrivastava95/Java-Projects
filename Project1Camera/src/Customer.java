import java.util.Scanner;

public class Customer {

	public int compare(Camera camOne, Camera camTwo) {
		// Checks if the value, user rating and the price are same. Returns 0
		if (camOne.computeValue() == camTwo.computeValue()
				&& camOne.userRating == camTwo.userRating
				&& Math.abs(camOne.price - camTwo.price) < 0.01) {
			return 0;
		}
		// Checks if user ratings and price are same. Then compare value
		if (camOne.userRating - camTwo.userRating < 0.01
				&& Math.abs(camOne.price - camTwo.price) < 0.01) {
			if (camOne.computeValue() > camTwo.computeValue()) {
				return 1;
			} else
				return 2;
		}
		// Checks if price and value are same. Then campare user rating.
		if (Math.abs(camOne.price - camTwo.price) < 0.01
				&& camOne.computeValue() == camTwo.computeValue()) {
			if (camOne.userRating > camTwo.userRating) {
				return 1;
			} else
				return 2;
		}
		// Checks if value and user rating are same. Then compare price
		if (camOne.computeValue() == camTwo.computeValue()
				&& camOne.userRating == camTwo.userRating) {
			if (camOne.price >= camTwo.price + 0.01) {
				return 2;
			} else
				return 1;
		}
		// For other cases, checks if price to value ratio is greater or less
		if (camOne.price / camOne.computeValue() > camTwo.price
				/ camTwo.computeValue()) {
			return 2;
		} else
			return 1;
	}

	public static void main(String[] args) {
		Scanner scanCamera = new Scanner(System.in);

		// Entering Attributes for Camera 1
		System.out.println("Enter attributes of Camera 1:");

		System.out.println("Is WiFi enabled? (true/false)");
		boolean hasWiFicamOne = scanCamera.nextBoolean();

		System.out.println("Is water resistant? (true/false)");
		boolean isWaterResistantcamOne = scanCamera.nextBoolean();

		System.out.println("Is GPS enabled? (true/false)");
		boolean hasGPScamOne = scanCamera.nextBoolean();

		System.out.println("Condition? (New/Refurbished/Used)");
		String conditioncamOne = scanCamera.next();

		System.out.println("Enter price in $ (0.00 to 1000.00");
		double pricecamOne = scanCamera.nextDouble();

		System.out.println("Enter user rating (0 to 5)");
		int userRatingcamOne = scanCamera.nextInt();

		System.out.println("======================");

		// Entering Attributes for Camera 2
		System.out.println("Enter attributes of Camera 2:");

		System.out.println("Is WiFi enabled? (true/false)");
		boolean hasWiFicamTwo = scanCamera.nextBoolean();

		System.out.println("Is water resistant? (true/false)");
		boolean isWaterResistantcamTwo = scanCamera.nextBoolean();

		System.out.println("Is GPS enabled? (true/false)");
		boolean hasGPScamTwo = scanCamera.nextBoolean();

		System.out.println("Condition? (New/Refurbished/Used)");
		String conditioncamTwo = scanCamera.next();

		System.out.println("Enter price in $ (0.00 to 1000.00");
		double pricecamTwo = scanCamera.nextDouble();

		System.out.println("Enter user rating (0 to 5)");
		int userRatingcamTwo = scanCamera.nextInt();

		System.out.println("======================");

		Camera one = new Camera(hasWiFicamOne, isWaterResistantcamOne,
				hasGPScamOne, conditioncamOne, pricecamOne, userRatingcamOne);
		Camera two = new Camera(hasWiFicamTwo, isWaterResistantcamTwo,
				hasGPScamTwo, conditioncamTwo, pricecamTwo, userRatingcamTwo);

		Customer cust = new Customer();

		System.out.println("Result of Camparison:\n");
		System.out.println("Camera " + cust.compare(one, two) + " is better");

	}

}