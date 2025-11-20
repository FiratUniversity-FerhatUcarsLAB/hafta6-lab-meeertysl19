AD SOYAD  : İSMET MERT UYSAL
NO:250542017



import java.util.Scanner;

public class SiparisSistemi {

    // 1) Ana Yemek
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85;   // Izgara Tavuk
            case 2: return 120;  // Adana Kebap
            case 3: return 110;  // Levrek
            case 4: return 65;   // Mantı
            default: return 0;
        }
    }

    // 2) Başlangıç
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25;   // Çorba
            case 2: return 45;   // Humus
            case 3: return 55;   // Sigara Böreği
            default: return 0;   // Yok
        }
    }

    // 3) İçecek
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15;   // Kola
            case 2: return 12;   // Ayran
            case 3: return 35;   // Meyve Suyu
            case 4: return 25;   // Limonata
            default: return 0;
        }
    }

    // 4) Tatlı
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65;   // Künefe
            case 2: return 55;   // Baklava
            case 3: return 35;   // Sütlaç
            default: return 0;
        }
    }

    // 5) Combo kontrolü
    public static boolean isComboOrder(boolean ana, boolean icecek, boolean tatli) {
        return ana && icecek && tatli;
    }

    // 6) Happy Hour kontrolü
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // 7) İndirim hesaplama
    public static double calculateDiscount(double tutar,
                                           boolean combo,
                                           boolean ogrenci,
                                           int saat,
                                           boolean icecekVar,
                                           int gun) {

        double toplamIndirim = 0;

        // Combo %15
        if (combo) {
            toplamIndirim += tutar * 0.15;
        }

        // Happy Hour - sadece içecek varsa %20
        if (isHappyHour(saat) && icecekVar) {
            toplamIndirim += tutar * 0.20;
        }

        // 200 TL üzeri %10
        if (tutar >= 200) {
            toplamIndirim += tutar * 0.10;
        }

        // Öğrenci indirimi (Hafta içi)
        // Gün 1-5 → Pzt–Cuma
        if (ogrenci) {
            if (gun >= 1 && gun <= 5) {
                toplamIndirim += tutar * 0.10;
            }
        }

        return toplamIndirim;
    }

    // 8) Bahşiş önerisi
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }


    // ---------------- MAIN PROGRAM ----------------

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Ana Yemek (1-4) : ");
        int anaYemek = sc.nextInt();

        System.out.println("Baslangic (0-3) : ");
        int baslangic = sc.nextInt();

        System.out.println("Icecek (0-4) : ");
        int icecek = sc.nextInt();

        System.out.println("Tatli (0-3) : ");
        int tatli = sc.nextInt();

        System.out.println("Saat (8-23): ");
        int saat = sc.nextInt();

        System.out.println("Ogrenci misiniz? (E/H): ");
        boolean ogrenci = sc.next().equalsIgnoreCase("E");

        System.out.println("Hangi gün? (1=Pzt ... 7=Paz): ");
        int gun = sc.nextInt();


        // Fiyatlar
        double fAna = getMainDishPrice(anaYemek);
        double fBas = getAppetizerPrice(baslangic);
        double fIcecek = getDrinkPrice(icecek);
        double fTatli = getDessertPrice(tatli);

        boolean anaVar = fAna > 0;
        boolean icecekVar = fIcecek > 0;
        boolean tatliVar = fTatli > 0;

        boolean combo = isComboOrder(anaVar, icecekVar, tatliVar);

        double araToplam = fAna + fBas + fIcecek + fTatli;

        double indirim = calculateDiscount(araToplam, combo, ogrenci, saat, icecekVar, gun);

        double toplam = araToplam - indirim;

        double bahsis = calculateServiceTip(toplam);


        // ---------- ÇIKTI ----------
        System.out.println("\n===== SIPARIS OZETI =====");
        System.out.println("Ara Toplam: " + araToplam + " TL");
        System.out.printf("Indirimler: -%.2f TL\n", indirim);
        System.out.printf("Toplam: %.2f TL\n", toplam);
        System.out.printf("Bahsis Onerisi: %.2f TL\n", bahsis);
    }
}

