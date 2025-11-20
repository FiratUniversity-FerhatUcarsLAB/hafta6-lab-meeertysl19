import java.util.Scanner;

public class NotRaporuProgrami {

    // --- GEREKLI METOTLAR ---

    public static double calculateAverage(double vize, double fin, double odev) {
        return vize * 0.30 + fin * 0.40 + odev * 0.30;
    }

    public static boolean isPassingGrade(double ort) {
        return ort >= 50;
    }

    public static String getLetterGrade(double ort) {
        if (ort >= 90)
            return "A";
        else if (ort >= 80)
            return "B";
        else if (ort >= 70)
            return "C";
        else if (ort >= 60)
            return "D";
        else
            return "F";
    }

    public static boolean isHonorList(double ort, double vize, double fin, double odev) {
        return ort >= 85 && vize >= 70 && fin >= 70 && odev >= 70;
    }

    public static boolean hasRetakeRight(double ort) {
        return ort >= 40 && ort < 50;
    }

    // --- MAIN METOT ---

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Vize: ");
        double vize = scanner.nextDouble();

        System.out.print("Final: ");
        double fin = scanner.nextDouble();

        System.out.print("Odev: ");
        double odev = scanner.nextDouble();

        double ort = calculateAverage(vize, fin, odev);
        String harf = getLetterGrade(ort);
        boolean gectiMi = isPassingGrade(ort);
        boolean onur = isHonorList(ort, vize, fin, odev);
        boolean but = hasRetakeRight(ort);

        // --- ÇIKTI ---
        System.out.println("\n=== OGRENCI NOT RAPORU ===");
        System.out.println("Vize Notu : " + vize);
        System.out.println("Final Notu : " + fin);
        System.out.println("Odev Notu : " + odev);
        System.out.println("------------------------------");
        System.out.printf("Ortalama : %.1f\n", ort);
        System.out.println("Harf Notu : " + harf);
        System.out.println("Durum : " + (gectiMi ? "GECTI" : "KALDI"));
        System.out.println("Onur Listesi : " + (onur ? "EVET" : "HAYIR"));
        System.out.println("Butunleme : " + (but ? "VAR" : "YOK"));
    }
}
