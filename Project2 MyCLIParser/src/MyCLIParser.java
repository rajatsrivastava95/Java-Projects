import java.util.Scanner;

public class MyCLIParser {

	public static void main(String[] args) {
		// if no arguments, print help and return
		if (args.length == 0) {
			help(args);
			return;
		}
		// -help
		else if (args[0].equals("-help"))
			help(args);
		// -add
		else if (args[0].equals("-add"))
			add(args);
		// -sub
		else if (args[0].equals("-sub"))
			sub(args);
		// -mul
		else if (args[0].equals("-mul"))
			mul(args);
		// -div
		else if (args[0].equals("-div"))
			div(args);
		// -stats
		else if (args[0].equals("-stats"))
			stats(args);
		// -table
		else if (args[0].equals("-seq"))
			seq(args);
	}

	public static boolean isInteger(String s) {
		Scanner scan = new Scanner(s);
		if (scan.hasNextInt()) {
			return true;
		}
		return false;
	}

	private static void help(String[] args) {
		System.out.println("List of Command and Instructions: ");
		System.out.println("-add num1 num2 ... :   Enter one or more numbers. Will add the numbers (must be intege"
				+ "rs).");
		System.out.println("-sub num1 num2 :       Enter two numbers. Will subtract number 2 from number 1 (must b"
				+ "e integers).");
		System.out.println("-mul num1 num2 ... :   Enter one or more numbers. Will mutiply the numbers (must be in"
				+ "tegers)");
		System.out.println("-div num1 num2 :       Enter two numbers. Will perform Euclidean Division to divide nu"
				+ "mber 1 by number 2 and return quotient and Remainder (must be integers with number 2 not equal "
				+ "to 0)");
		System.out.println("-stats num1 num2 ... : Enter one or more numbers. Returns Total, Maximum, Minimum and "
				+ "Average of the input numbers (must be integers).");
		System.out.println("-seq num1 num2 :       Enter two numbers. Generates arithmetic sequence of numbers wit"
				+ "h number 1 as intitial value and number 2 as common difference (must be integers).");
	}

	// ADDITION
	private static void add(String[] args) {
		for (int a = 1; a < args.length; a++) {
			if (isInteger(args[a]) == false) {
				System.out.println("Error: Argument type mismatch");
				return;
			}
		}

		if (args.length < 2) {
			System.out.println("Error: Argument count mismatch");
		} else {
			int sum = 0;
			for (int n = 1; n < args.length; n++) {
				sum = sum + Integer.parseInt(args[n]);
			}
			System.out.println(sum % 97);
		}
	}

	// SUBTRACTION
	private static void sub(String[] args) {
		for (int b = 1; b < args.length; b++) {
			if (isInteger(args[b]) == false) {
				System.out.println("Error: Argument type mismatch");
				return;
			}
		}

		if (args.length != 3) {
			System.out.println("Error: Argument count mismatch");
		} else {
			System.out.println(Integer.parseInt(args[1])
					- Integer.parseInt(args[2]));
		}

	}

	// MULTIPLICATION
	private static void mul(String[] args) {
		for (int c = 1; c < args.length; c++) {
			if (isInteger(args[c]) == false) {
				System.out.println("Error: Argument type mismatch");
				return;
			}
		}

		if (args.length < 2) {
			System.out.println("Error: Argument count mismatch");
		} else {
			int product = 1;
			for (int p = 1; p < args.length; p++) {
				product = product * Integer.parseInt(args[p]);
			}
			System.out.println(product % 97);
		}
	}

	// DIVISION
	private static void div(String[] args) {
		for (int d = 1; d < args.length; d++) {
			if (isInteger(args[d]) == false) {
				System.out.println("Error: Argument type mismatch");
				return;
			}
		}

		if (args.length != 3) {
			System.out.println("Error: Argument count mismatch");
		} else if (Integer.parseInt(args[2]) == 0) {
			System.out.println("Undefined");
		} else {
			int quotient = Integer.parseInt(args[1])
					/ Integer.parseInt(args[2]);
			int remainder = Integer.parseInt(args[1])
					% Integer.parseInt(args[2]);
			System.out.println("Quotient " + quotient);
			System.out.println("Remainder " + remainder);
		}
	}

	// STATISTICS
	private static void stats(String[] args) {
		for (int e = 1; e < args.length; e++) {
			if (isInteger(args[e]) == false) {
				System.out.println("Error: Argument type mismatch");
				return;
			}
		}

		if (args.length < 2) {
			System.out.println("Error: Argument count mismatch");
		} else {
			int statTotal = 0;
			for (int s = 1; s < args.length; s++) {
				statTotal = statTotal + Integer.parseInt(args[s]);
			}
			System.out.println("Total " + statTotal);

			int statArray[] = new int[args.length - 1];
			for (int sA = 1; sA < args.length; sA++) {
				statArray[sA - 1] = Integer.parseInt(args[sA]);
			}
			int maxValue = statArray[0];
			for (int mx = 0; mx < statArray.length; mx++) {
				if (statArray[mx] > maxValue) {
					maxValue = statArray[mx];
				}
			}
			System.out.println("Max " + maxValue);

			int minValue = statArray[0];
			for (int mn = 0; mn < statArray.length; mn++) {
				if (statArray[mn] < minValue) {
					minValue = statArray[mn];
				}
			}
			System.out.println("Min " + minValue);
			double average = ((double) statTotal) / (statArray.length);
			System.out.printf("Average %.2f\n", average);
		}
	}

	// ARITHMETIC SEQUENCE
	private static void seq(String[] args) {
		for (int f = 1; f < args.length; f++) {
			if (isInteger(args[f]) == false) {
				System.out.println("Error: Argument type mismatch");
				return;
			}
		}

		if (args.length != 3) {
			System.out.println("Error: Argument count mismatch");
		} else {
			int[] sequence = new int[10];
			sequence[0] = Integer.parseInt(args[1]);
			System.out.print(sequence[0] + " ");
			for (int sq = 1; sq < 10; sq++) {
				sequence[sq] = sequence[sq - 1] + Integer.parseInt(args[2]);
				System.out.print(sequence[sq] + " ");
			}
			System.out.println();

		}
	}
}
