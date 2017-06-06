import java.text.DecimalFormat;
import java.util.Random;

public class BankAccount {

	private int accountNumber;
	private String name;
	private double balance;
	private static double interestRate = 0.3;

	public static int count = 0;
	public static int number;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public BankAccount(String name) {
        /*
		count = count + 1;
		this.name = name;
		Random rand = new Random();
		this.accountNumber = (100000 + rand.nextInt(899999));
		this.balance = 0;
		number = count;
		if (number % 5 == 0) {
			interestRate = interestRate - 0.02;
		}
         */
        this(name, 0);
	}

	public BankAccount(String name, int balance) {
		count = count + 1;
		this.name = name;
		this.balance = balance;
		Random rand = new Random();
		this.accountNumber = (100000 + rand.nextInt(899999));
		number = count;
		if (number % 5 == 0) {
			interestRate = interestRate - 0.02;
		}
	}

	public static int numberOfAccounts() {
		return number;
	}

	public double deposit(double money) {
		if (money < 0) {
			return -1;
		}
		this.balance = this.balance + money;
		return money;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double withdrawMoney(double money) {
		if (money < 0 || this.balance < money) {
			return -1;
		}
		this.balance = this.balance - money;
		return balance;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public static double getInterestRate() {

		DecimalFormat df = new DecimalFormat("#0.00");
		String rate = df.format(interestRate);
		interestRate = Double.parseDouble(rate);

		return interestRate;
	}

	public double transfer(BankAccount destinationBankAccount, double amount) {
		if (amount < 0 || amount > this.balance || destinationBankAccount == null) {
			return -1;
		}
		withdrawMoney(amount);
		destinationBankAccount.deposit(amount);
		return amount;
	}

	public double transfer(BankAccount[] destinationBankAccount, double amount) {
		if (destinationBankAccount == null) {
			return -1;
		}
		double bal = withdrawMoney(amount * destinationBankAccount.length);
		
		if ( bal < 0 || amount < 0 || destinationBankAccount.length == 0) {
			return -1;
		}
		for (int i = 0; i < destinationBankAccount.length; i++) {
			if (destinationBankAccount[i] == null) {
				return -1;
			}
			destinationBankAccount[i].deposit(amount);
		}
		return (amount * destinationBankAccount.length);
	}

	public double getBalance() {
		return balance;
	}

	public static void main(String[] args) {

		BankAccount one = new BankAccount("Rajat", 1000);
		BankAccount two = new BankAccount("Anjali");
		BankAccount three = new BankAccount("Omar", 7000);
		BankAccount four = new BankAccount("Shivam", 9);
		BankAccount five = new BankAccount("Mom", 8900);
		BankAccount six = new BankAccount("Dad", 900000);
		BankAccount seven = new BankAccount("Sis");
		BankAccount eight = new BankAccount("Bro");
		BankAccount nine = new BankAccount("Aunt", 900);
		BankAccount ten = new BankAccount("Uncle", 800);
		BankAccount eleven = new BankAccount("Hello", 0);
		BankAccount twelve = new BankAccount("Bysvsvde");
		BankAccount thirteen = new BankAccount("Bysvsbverde");
		BankAccount forteen = new BankAccount("Bysvqefbersvde");
		BankAccount fifteen = new BankAccount("Bysvqfrsvebebde");
		BankAccount sixteen = new BankAccount("Bysverqfsvede");
		BankAccount seventeen = new BankAccount("Bysvserbbeevvde");
		BankAccount eighteen = new BankAccount("Bysvsqvebeqvde");
		BankAccount nineteen = new BankAccount("Byvqesbvebsvde");
		BankAccount twenty = new BankAccount("Bysvsverbbwde");
		BankAccount twentyone = new BankAccount("Bysbwbqvsvde");
		BankAccount twentytwo = new BankAccount("Bysbwvsvevvde");
		BankAccount twentythree = new BankAccount("Bysbwvrvsvde");
		BankAccount twentyfour = new BankAccount("Bysbwvsvvde");
		BankAccount twentyfive = new BankAccount("er");
		BankAccount twentysix = new BankAccount("Bysbwvvesvde");
		BankAccount twentyseven = new BankAccount("Byserbwrvvsvde");
		BankAccount twentyeight = new BankAccount("Bysbwvrvsvde");
		BankAccount twentynine = new BankAccount("Bysbwvvsvde");
		BankAccount thirty = new BankAccount("Bysvsdvsbwvvsvde");

		// System.out.println(BankAccount.getInterestRate());

		//BankAccount[] accounts = {two, three, four};

		//double transfer = one.transfer(accounts, 300);
		//System.out.println(transfer);
		//System.out.println(one.getBalance());
		
		//double transfer2 = one.transfer(three, 700);
		//System.out.println(transfer2);
		//System.out.println(one.getBalance());
		//System.out.println(three.getBalance()); 

	}

}
