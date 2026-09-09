import java.util.Scanner;

public class Holybookstore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Harga konstanta barang
        final double HARGA_BUKU = 5000;
        final double HARGA_PENSIL = 2000;
        final double PAJAK = 0.10; // Pajak 10%

        System.out.println("====================================");
        System.out.println("      WELCOME TO HOLYBOOKSTORE      ");
        System.out.println("====================================");

        // 1. Input jumlah barang
        System.out.print("Masukkan jumlah buku yang dibeli   : ");
        int jumlahBuku = scanner.nextInt();

        System.out.print("Masukkan jumlah pensil yang dibeli : ");
        int jumlahPensil = scanner.nextInt();

        // 2. Perhitungan Total Sebelum dan Sesudah Pajak
        double totalSebelumPajak = (jumlahBuku * HARGA_BUKU) + (jumlahPensil * HARGA_PENSIL);
        double jumlahPajak = totalSebelumPajak * PAJAK;
        double totalSetelahPajak = totalSebelumPajak + jumlahPajak;

        // 3. Menampilkan Rincian Tagihan
        System.out.println("\n------------------------------------");
        System.out.printf("Total Harga (Sebelum Pajak) : IDR %.0f\n", totalSebelumPajak);
        System.out.printf("Pajak Penjualan (10%%)       : IDR %.0f\n", jumlahPajak);
        System.out.printf("Total Pembayaran            : IDR %.0f\n", totalSetelahPajak);
        System.out.println("------------------------------------");

        // 4. Input Uang Pembayaran & Hitung Kembalian
        System.out.print("Masukkan jumlah uang dibayarkan   : IDR ");
        double uangDibayar = scanner.nextDouble();

        if (uangDibayar >= totalSetelahPajak) {
            double kembalian = uangDibayar - totalSetelahPajak;
            System.out.printf("Uang Kembalian               : IDR %.0f\n", kembalian);
            System.out.println("\nTerima kasih telah berbelanja di Holybookstore!");
        } else {
            double kekurangan = totalSetelahPajak - uangDibayar;
            System.out.printf("Uang Anda kurang            : IDR %.0f\n", kekurangan);
            System.out.println("Transaksi gagal. Pembayaran tidak cukup.");
        }

        scanner.close();
    }
}