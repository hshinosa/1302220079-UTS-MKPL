package lib;

import java.time.LocalDate;
import java.time.Month; 

import java.util.LinkedList;
import java.util.List;

public class Employee {

    // Constants (Sama seperti Tahap 1)
    private static final int GRADE_1_SALARY = 3000000;
    private static final int GRADE_2_SALARY = 5000000;
    private static final int GRADE_3_SALARY = 7000000;
    private static final double FOREIGNER_SALARY_MULTIPLIER = 1.5;


    // Atribut (Sama seperti Tahap 2)
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
	private boolean gender; //true = Laki-laki, false = Perempuan

	private int monthlySalary;
	private int otherMonthlyIncome;
	private int annualDeductible;
	private String spouseName;
	private String spouseIdNumber;
	private List<String> childNames;
	private List<String> childIdNumbers;


    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
        // ... (isi constructor sama seperti awal) ...

    // --- Perubahan di sini ---
    // private List<String> childNames; // Dihapus
    // private List<String> childIdNumbers; // Dihapus
    private List<Child> children; // Diganti dengan List objek Child
    // --- Akhir Perubahan ---


    // Constructor (Sama seperti Tahap 2)
    public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, int yearJoined, int monthJoined, int dayJoined, boolean isForeigner, boolean gender) {
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
		childNames = new LinkedList<String>();
		childIdNumbers = new LinkedList<String>();
    }

    /**
     * Fungsi untuk menentukan gaji bulanan pegawai berdasarkan grade kepegawaiannya.
     * Jika pegawai adalah warga negara asing gaji bulanan diperbesar sebanyak 50%
     */
    public void setMonthlySalary(int grade) {
        children = new LinkedList<>();
    }


    /**
     * Menetapkan gaji bulanan pegawai berdasarkan grade.
     * Gaji WNA dinaikkan sebesar 50%.
     * Memperbaiki bug perhitungan gaji WNA dan menghilangkan duplikasi.
     *
     * @param grade Tingkat grade pegawai (1, 2, atau 3).
     */
    public void setMonthlySalary(int grade) {
        // --- Perubahan di sini ---
        int baseSalary;
        switch (grade) {
            case 1:
                baseSalary = GRADE_1_SALARY;
                break;
            case 2:
                baseSalary = GRADE_2_SALARY;
                break;
            case 3:
                baseSalary = GRADE_3_SALARY;
                break;
            default:
                 baseSalary = 0; // Jika grade tidak valid, set gaji ke 0
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
                monthlySalary = (int) (GRADE_1_SALARY * FOREIGNER_SALARY_MULTIPLIER);

            }
        } else if (grade == 3) {
            monthlySalary = GRADE_3_SALARY;
            if (isForeigner) {
                monthlySalary = (int) (GRADE_1_SALARY * FOREIGNER_SALARY_MULTIPLIER);
            }
        }
    }

    // ... (method lainnya setAnnualDeductible, setAdditionalIncome, setSpouse, addChild sama seperti awal) ...
            }
        if (isForeigner) {
            // Terapkan multiplier HANYA SEKALI setelah base salary ditentukan
            this.monthlySalary = (int) (baseSalary * FOREIGNER_SALARY_MULTIPLIER);
        } else {
            this.monthlySalary = baseSalary;
        }
        // --- Akhir Perubahan ---
    }

    // Method lain (setAnnualDeductible, setAdditionalIncome, setSpouse, addChild, getAnnualIncomeTax) sama seperti Tahap 2
	public void setAnnualDeductible(int deductible) { this.annualDeductible = deductible; }
	public void setAdditionalIncome(int income) { this.otherMonthlyIncome = income; }
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber; // Bug Masih Ada
	}
	public void addChild(String childName, String childIdNumber) {
		childNames.add(childName);
		childIdNumbers.add(childIdNumber);
	}


    public int getAnnualIncomeTax() {
		children.add(new Child(childName, childIdNumber));
	}
	public int getAnnualIncomeTax() {
        LocalDate date = LocalDate.now();

        // Masih menggunakan konstanta literal 12
        if (date.getYear() == yearJoined) {
            monthWorkingInYear = date.getMonthValue() - monthJoined;
        } else {
            monthWorkingInYear = 12;
        }

        // Memanggil TaxFunction (yang sudah pakai konstanta internalnya)
        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());

    // --- Perubahan di sini ---
    public void addChild(String childName, String childIdNumber) {
        // childNames.add(childName); // Dihapus
        // childIdNumbers.add(childIdNumber); // Dihapus
        children.add(new Child(childName, childIdNumber)); // Tambahkan objek Child ke list
    }
    // --- Akhir Perubahan ---
        // Cek isMarried masih kurang ideal
        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber != null && !spouseIdNumber.isEmpty(), children.size());
	}
}