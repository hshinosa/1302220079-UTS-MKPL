package lib;

public class TaxFunction {

    // Constants for Tax Calculation
    private static final double TAX_RATE = 0.05;
    private static final int BASE_PTKP = 54000000; // PTKP WP Pribadi
    private static final int MARRIED_PTKP_INCREMENT = 4500000; // Tambahan PTKP Menikah
    private static final int CHILD_PTKP_INCREMENT = 1500000; // Tambahan PTKP per Anak (sesuai kode awal)
    private static final int MAX_CHILDREN_FOR_PTKP = 3; // Maksimal anak ditanggung
    private static final int MAX_MONTHS_PER_YEAR = 12;

    /**
     * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
     * ... (komentar lainnya sama) ...
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorked, int deductible, boolean isMarried, int numberOfChildren) {

        // Validate and cap number of months worked
        if (numberOfMonthsWorked > MAX_MONTHS_PER_YEAR) {
            System.err.println("More than 12 month working per year. Capping calculation to 12 months.");
            numberOfMonthsWorked = MAX_MONTHS_PER_YEAR; 
        }
         if (numberOfMonthsWorked <= 0) {
             return 0;
         }

        int applicableChildren = Math.min(numberOfChildren, MAX_CHILDREN_FOR_PTKP);

        int nonTaxableIncome = BASE_PTKP;
        if (isMarried) {
            nonTaxableIncome += MARRIED_PTKP_INCREMENT;
        }
        nonTaxableIncome += applicableChildren * CHILD_PTKP_INCREMENT;

        int taxableIncome = ((monthlySalary + otherMonthlyIncome) * numberOfMonthsWorked) - deductible - nonTaxableIncome;

        int tax = (int) Math.round(TAX_RATE * taxableIncome);

        // Return non-negative tax
        return Math.max(0, tax);
    }
}