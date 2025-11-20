AD SOYAD  : İSMET MERT UYSAL
NO:250542017


import java.util.Scanner;

public class SinemaBiletSistemi {

    // 1) Hafta sonu mu?
    public static boolean isWeekend(int gun) {
        return gun == 6 || gun == 7;   // 6=Cumartesi, 7=Pazar
    }

    // 2) Matine mi? (12:00 öncesi)
    public static boolean isMatinee(int saat) {
        return saat < 12;
    }

    // 3) Temel fiyatı hesapla
    public static double calculateBasePrice(int gun, int saat) {
        boolean weekend = isWeekend(gun);
        boolean matinee = isMatinee(saat);

        if (!weekend && matinee) return 45;     // Hafta içi matine
        if (!weekend && !matinee) return 65;    // Hafta içi normal
        if (weekend && matinee) return 55;      // Hafta sonu matine
        return 85;                               // Hafta sonu normal
    }

    // 4) İndirim hesapla
    public static double calculateDiscount(int yas, int meslek, int gun) {

        // Yaş indirimleri (her gün geçerli)
        if (yas >= 65) return 0.30;   // %30
        if (yas < 12) return 0.25;    // %25

        // Meslek indirimleri
        switch (meslek) {
            case 1: // Öğrenci
                if (gun >= 1 && gun <= 4) return 0.20; // Pzt–Per: %20
                else return 0.15;                       // Cuma–Pazar: %15

            case 2: // Öğretmen
                if (gun == 3) return 0.35; // Çarşamba
                break;
        }

        return 0.0; // İndirim yok
    }

    // 5) Format ücreti
    public static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 1: return 0;    // 2D
            case 2: return 25;   // 3D
            case 3: return 35;   // IMAX
            case 4: return 50;   // 4DX
            default: return 0;
        }
    }

    // 6) Final fiyat hesaplama
    public static double calculateFinalPrice(int gun, int saat, int yas, int meslek, int filmTuru) {
        double base = calculateBasePrice(gun, saat);
        double disc = calculateDiscount(yas, meslek, gun);
        double extra = getFormatExtra(filmTuru);

        double price = base + extra;
        price = price - (price * disc); // indirim uygula

        return price;
    }

    // 7) Bilet Bilgisi oluştur
    public static void generateTicketInfo(int gun, int saat, int yas, int meslek, int filmTuru) {

        String gunAdi;
        switch (gun) {
            case 1: gunAdi = "Pazartesi"; break;
            case 2: gunAdi = "Salı"; break;
            case 3: gunAdi = "Çarşamba"; break;
            case 4: gunAdi = "Perşembe"; break;
            case 5: gunAdi = "Cuma"; break;
            case 6: gunAdi = "Cumartesi"; break;
            case 7: gunAdi = "Pazar"; break;
            default: gunAdi = "Bilinmiyor"; break;
        }

        String meslekAdi;
        switch (meslek) {
            case 1: meslekAdi = "Öğrenci"; break;
            case 2: meslekAdi = "Öğretmen"; break;
            default: meslekAdi = "Diğer"; break;
        }

        String filmAdi;
        switch (filmTuru) {
            case 1: filmAdi = "2D"; break;
            case 2: filmAdi = "3D"; break;
            case 3: filmAdi = "IMAX"; break;
            case 4: filmAdi = "4DX"; break;
            default: filmAdi = "Bilinmiyor"; break;
        }

        double toplam = calculateFinalPrice(gun, saat, yas, meslek, filmTuru);

        System.out.println("\n===== SINEMA BILET BILGISI =====");
        System.out.println("Gün: " + gunAdi);
        System.out.println("Saat: " + saat + ":00");
        System.out.println("Yas: " + yas);
        System.out.println("Meslek: " + meslekAdi);
        System.out.println("Film Turu: " + filmAdi);
        System.out.println("--------------------------------");
        System.out.printf("Toplam Ücret: %.2f TL\n", toplam);
    }

    // ---- MAIN ----
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("1=Pzt, 2=Salı, 3=Çarş, 4=Perş, 5=Cuma, 6=Cts, 7=Pazar");
        System.out.print("Gün seçiniz: ");
        int gun = sc.nextInt();

        System.out.print("Saat (0-23): ");
        int saat = sc.nextInt();

        System.out.print("Yaş: ");
        int yas = sc.nextInt();

        System.out.println("Meslek: 1=Öğrenci  2=Öğretmen  3=Diğer");
        int meslek = sc.nextInt();

        System.out.println("Film Türü: 1=2D, 2=3D, 3=IMAX, 4=4DX");
        int filmTuru = sc.nextInt();

        generateTicketInfo(gun, saat, yas, meslek, filmTuru);
    }
}

