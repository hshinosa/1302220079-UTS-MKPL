package lib;

public class TaxFunction {

    private static final double TAX_RATE = 0.05;
    private static final int BASE_PTKP = 54000000; // PTKP WP Pribadi
    private static final int MARRIED_PTKP_INCREMENT = 4500000; // Tambahan PTKP Menikah
    private static final int CHILD_PTKP_INCREMENT = 1500000; // Tambahan PTKP per Anak (sesuai kode awal)
    private static final int MAX_CHILDREN_FOR_PTKP = 3; // Maksimal anak ditanggung
    private static final int MAX_MONTHS_PER_YEAR = 12;

     * ... (komentar lainnya sama) ...
     */
    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthsWorked, int deductible, boolean isMarried, int numberOfChildren) {

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

        return Math.max(0, tax);
    }
        // Validasi Input
        if (numberOfMonthsWorked > MAX_MONTHS_PER_YEAR) {
            System.err.println("Error: More than 12 months working per year detected. Capping to 12.");
            numberOfMonthsWorked = MAX_MONTHS_PER_YEAR;
        }
         if (numberOfMonthsWorked <= 0) {
             return 0; Tidk ada pajak yang harus dibayarkan
         }

        int annualGrossIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthsWorked;

        // --- Perubahan di sini ---
        // Hitung Penghasilan Tidak Kena Pajak (PTKP) tahunan
        int nonTaxableIncome = calculatePTKP(isMarried, numberOfChildren);
        // --- Akhir Perubahan ---

        int taxableIncome = annualGrossIncome - deductible - nonTaxableIncome;

        int tax = (int) Math.round(TAX_RATE * taxableIncome);

        return Math.max(0, tax);
    }

    /**
     * Menghitung Penghasilan Tidak Kena Pajak (PTKP) tahunan.
     *
     * @param isMarried      Status menikah (true jika menikah)
     * @param numberOfChildren Jumlah anak (maksimal 3 yang dihitung)
     * @return Jumlah PTKP tahunan
     */
    // --- Method Baru Hasil Ekstraksi ---
    private static int calculatePTKP(boolean isMarried, int numberOfChildren) {
        int ptkp = BASE_PTKP;

        if (isMarried) {
            ptkp += MARRIED_PTKP_INCREMENT;
        }

        // Batasi jumlah anak yang dihitung untuk PTKP
        int applicableChildren = Math.min(numberOfChildren, MAX_CHILDREN_FOR_PTKP);
        ptkp += applicableChildren * CHILD_PTKP_INCREMENT;

        return ptkp;
    }
    // --- Akhir Method Baru ---
}