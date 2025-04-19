package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List; // List masih digunakan

public class Employee {

    // Constants for Grades and Salaries (Sama seperti Tahap 1)
    private static final int GRADE_1_SALARY = 3000000;
    private static final int GRADE_2_SALARY = 5000000;
    private static final int GRADE_3_SALARY = 7000000;
    private static final double FOREIGNER_SALARY_MULTIPLIER = 1.5;

    // ... (atribut employeeId hingga gender sama) ...
	private String employeeId;
	private String firstName;
	private String lastName;
	private String idNumber;
	private String address;
	private int yearJoined;
	private int monthJoined;
	private int dayJoined;
	private int monthWorkingInYear;
	private boolean isForeigner;
	private boolean gender;
	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	private String spouseName;
	private String spouseIdNumber;

    // --- Perubahan di sini ---
    // private List<String> childNames; // Dihapus
    // private List<String> childIdNumbers; // Dihapus
    private List<Child> children; // Diganti dengan List objek Child
    // --- Akhir Perubahan ---


    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
        // ... (inisialisasi atribut lain sama) ...
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.idNumber = idNumber;
		this.address = address;
		this.yearJoined = yearJoined;
		this.monthJoined = monthJoined;
		this.dayJoined = dayJoined;
		this.isForeigner = isForeigner;
		this.gender = gender;

        // --- Perubahan di sini ---
        // childNames = new LinkedList<String>(); // Dihapus
        // childIdNumbers = new LinkedList<String>(); // Dihapus
        children = new LinkedList<>(); // Inisialisasi List Child
        // --- Akhir Perubahan ---
    }

    // setMonthlySalary masih sama seperti Tahap 1 (dengan bug asli)
	public void setMonthlySalary(int grade) {
        if (grade == 1) {
            monthlySalary = GRADE_1_SALARY;
            if (isForeigner) {
                monthlySalary = (int) (GRADE_1_SALARY * FOREIGNER_SALARY_MULTIPLIER);
            }
        } else if (grade == 2) {
            monthlySalary = GRADE_2_SALARY;
            if (isForeigner) {
                monthlySalary = (int) (GRADE_1_SALARY * FOREIGNER_SALARY_MULTIPLIER); // Bug Masih Ada
            }
        } else if (grade == 3) {
            monthlySalary = GRADE_3_SALARY;
            if (isForeigner) {
                monthlySalary = (int) (GRADE_1_SALARY * FOREIGNER_SALARY_MULTIPLIER); // Bug Masih Ada
            }
        }
	}

    // setAnnualDeductible, setAdditionalIncome, setSpouse sama seperti Tahap 1
	public void setAnnualDeductible(int deductible) {
		this.annualDeductible = deductible;
	}
	public void setAdditionalIncome(int income) {
		this.otherMonthlyIncome = income;
	}
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber; // Bug Masih Ada
	}

    // --- Perubahan di sini ---
    public void addChild(String childName, String childIdNumber) {
        // childNames.add(childName); // Dihapus
        // childIdNumbers.add(childIdNumber); // Dihapus
        children.add(new Child(childName, childIdNumber)); // Tambahkan objek Child ke list
    }
    // --- Akhir Perubahan ---

    public int getAnnualIncomeTax() {
        LocalDate date = LocalDate.now();
        if (date.getYear() == yearJoined) {
            monthWorkingInYear = date.getMonthValue() - monthJoined;
        } else {
            monthWorkingInYear = 12; // Masih pakai literal 12
        }

        // --- Perubahan di sini ---
        // Menggunakan children.size() bukan childIdNumbers.size()
        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber != null && !spouseIdNumber.isEmpty(), children.size());
        // Juga perbaiki pengecekan isMarried agar lebih aman dari NullPointerException
        // --- Akhir Perubahan ---
    }
}