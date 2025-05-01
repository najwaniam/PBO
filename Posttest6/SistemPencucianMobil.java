package Posttest6;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemPencucianMobil {
    private static final ArrayList<Pelanggan> pelangganList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static int totalTransaksi = 0;

    public static void tampilkanInfoAplikasi() {
        System.out.println("\n=== Aplikasi Pencucian Mobil ===");
        System.out.println("Versi 6.0");
        System.out.println("Total transaksi: " + totalTransaksi);
        System.out.println("Total pelanggan: " + pelangganList.size());
        System.out.println("===============================");
    }

    public static void main(String[] args) {
        tampilkanInfoAplikasi();
        while (true) {
            System.out.println("\n=== Sistem Manajemen Layanan Pencucian Mobil ===");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Lihat Daftar Pelanggan");
            System.out.println("3. Update Data Pelanggan");
            System.out.println("4. Hapus Pelanggan");
            System.out.println("5. Tambah Pemesanan Layanan");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            
            try {
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1: tambahPelanggan(); break;
                    case 2: lihatPelanggan(); break;
                    case 3: updatePelanggan(); break;
                    case 4: hapusPelanggan(); break;
                    case 5: tambahPemesanan(); break;
                    case 6: 
                        System.out.println("Keluar dari program...");
                        tampilkanInfoAplikasi();
                        return;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Silakan coba lagi.");
                scanner.nextLine();
            }
        }
    }

    private static void tambahPelanggan() {
        try {
            System.out.print("Masukkan ID Pelanggan: ");
            String idPelanggan = scanner.nextLine();
            System.out.print("Masukkan Nama: ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan Nomor Telepon: ");
            String nomorTelepon = scanner.nextLine();

            Pelanggan pelanggan = new Pelanggan(idPelanggan, nama, nomorTelepon);
            pelangganList.add(pelanggan);
            System.out.println("Pelanggan berhasil ditambahkan!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void lihatPelanggan() {
        if (pelangganList.isEmpty()) {
            System.out.println("Tidak ada pelanggan yang terdaftar.");
        } else {
            System.out.println("\nDaftar Pelanggan:");
            for (Pelanggan pelanggan : pelangganList) {
                System.out.println(pelanggan);
                System.out.println("-----------------------------");
            }
        }
    }

    private static void updatePelanggan() {
        System.out.print("Masukkan ID Pelanggan yang ingin diupdate: ");
        String idPelanggan = scanner.nextLine();

        for (Pelanggan pelanggan : pelangganList) {
            if (pelanggan.getIdPelanggan().equals(idPelanggan)) {
                try {
                    System.out.print("Masukkan Nomor Telepon baru: ");
                    String nomorBaru = scanner.nextLine();
                    pelanggan.setNomorTelepon(nomorBaru);
                    System.out.println("Data pelanggan berhasil diupdate!");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                return;
            }
        }
        System.out.println("Pelanggan dengan ID tersebut tidak ditemukan.");
    }

    private static void hapusPelanggan() {
        System.out.print("Masukkan ID Pelanggan yang ingin dihapus: ");
        String idPelanggan = scanner.nextLine();

        for (int i = 0; i < pelangganList.size(); i++) {
            if (pelangganList.get(i).getIdPelanggan().equals(idPelanggan)) {
                pelangganList.remove(i);
                System.out.println("Pelanggan berhasil dihapus!");
                return;
            }
        }
        System.out.println("Pelanggan dengan ID tersebut tidak ditemukan.");
    }

    private static void tambahPemesanan() {
        try {
            System.out.print("Masukkan ID Pelanggan: ");
            String idPelanggan = scanner.nextLine();

            Pelanggan pelangganDitemukan = null;
            for (Pelanggan pelanggan : pelangganList) {
                if (pelanggan.getIdPelanggan().equals(idPelanggan)) {
                    pelangganDitemukan = pelanggan;
                    break;
                }
            }

            if (pelangganDitemukan == null) {
                System.out.println("Pelanggan tidak ditemukan!");
                return;
            }

            System.out.println("\nPilih Layanan:");
            System.out.println("1. Cuci Reguler");
            System.out.println("2. Cuci Premium");
            System.out.print("Pilihan: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();

            Layanan layanan = null;
            switch (pilihan) {
                case 1: 
                    layanan = new LayananReguler();
                    layanan.tampilkanDetailSpesial();
                    break;
                case 2: 
                    layanan = new LayananPremium();
                    layanan.tampilkanDetailSpesial();
                    
                    System.out.print("\nIngin menambahkan layanan khusus (perawatan jok kulit & AC)? (y/n): ");
                    String tambahLayanan = scanner.nextLine();
                    if (tambahLayanan.equalsIgnoreCase("y")) {
                        ((LayananTambahan)layanan).applyAdditionalCharge(0.15);
                        ((LayananTambahan)layanan).tampilkanInfoTambahan();
                    }
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
                    return;
            }

            System.out.print("\nApakah pelanggan memiliki kode diskon? (y/n): ");
            String punyaDiskon = scanner.nextLine();
            
            if (punyaDiskon.equalsIgnoreCase("y")) {
                System.out.print("Masukkan persentase diskon (contoh: 0.1 untuk 10%): ");
                double diskon = scanner.nextDouble();
                scanner.nextLine();
                pelangganDitemukan.tambahPemesanan(layanan, diskon);
            } else {
                pelangganDitemukan.tambahPemesanan(layanan);
            }
            
            totalTransaksi++;
            System.out.println("Pemesanan berhasil ditambahkan!");
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            scanner.nextLine();
        }
    }
}