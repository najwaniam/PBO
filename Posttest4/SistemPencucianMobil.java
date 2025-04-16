package Posttest4;
import java.util.ArrayList;
import java.util.Scanner;

class Pelanggan {
    private String idPelanggan;
    private String nama;
    private String nomorTelepon;
    private ArrayList<Layanan> historyPemesanan;

    Pelanggan(String idPelanggan, String nama, String nomorTelepon) {
        this.idPelanggan = idPelanggan;
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.historyPemesanan = new ArrayList<>();
    }

    // Getter dan Setter
    public String getIdPelanggan() { return idPelanggan; }
    public void setIdPelanggan(String idPelanggan) { this.idPelanggan = idPelanggan; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getNomorTelepon() { return nomorTelepon; }
    public void setNomorTelepon(String nomorTelepon) { this.nomorTelepon = nomorTelepon; }
    public ArrayList<Layanan> getHistoryPemesanan() { return historyPemesanan; }
    
    // Method overloading untuk menambahkan pemesanan
    public void tambahPemesanan(Layanan layanan) {
        historyPemesanan.add(layanan);
        System.out.println("Layanan " + layanan.getNama() + " ditambahkan ke pelanggan " + nama);
    }
    
    // Overloading method: tambah pemesanan dengan diskon khusus
    public void tambahPemesanan(Layanan layanan, double diskon) {
        double hargaSetelahDiskon = layanan.getHarga() * (1 - diskon);
        System.out.printf("Layanan %s ditambahkan dengan diskon %.0f%%. Harga asli: Rp%.0f, Harga setelah diskon: Rp%.0f%n",
                         layanan.getNama(), diskon*100, layanan.getHarga(), hargaSetelahDiskon);
        
        // Untuk simplicity, kita tetap menyimpan layanan aslinya di history
        historyPemesanan.add(layanan);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID Pelanggan: ").append(idPelanggan)
          .append(", Nama: ").append(nama)
          .append(", Nomor Telepon: ").append(nomorTelepon)
          .append("\nHistory Pemesanan:\n");
        
        for (Layanan layanan : historyPemesanan) {
            sb.append("- ").append(layanan.getDeskripsi()).append("\n");
        }
        
        return sb.toString();
    }
}

// Parent class untuk layanan pencucian
abstract class Layanan {
    private String nama;
    private double harga;

    public Layanan(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    
    public abstract String getDeskripsi();
    
    // Method baru untuk polymorphism
    public void tampilkanDetailSpesial() {
        System.out.println("Layanan standar: " + getDeskripsi());
    }
}

// Subclass 1: Layanan Reguler
class LayananReguler extends Layanan {
    public LayananReguler() {
        super("Cuci Reguler", 50000);
    }

    @Override
    public String getDeskripsi() {
        return getNama() + " (Rp" + getHarga() + ") - Cuci exterior, vacuum interior";
    }
    
    @Override
    public void tampilkanDetailSpesial() {
        System.out.println("✨ Layanan Reguler - Cocok untuk perawatan mingguan");
        System.out.println("✔ Cuci exterior dasar");
        System.out.println("✔ Vacuum interior standar");
        System.out.println("✔ Waktu pengerjaan: 30-45 menit");
    }
}

// Subclass 2: Layanan Premium
class LayananPremium extends Layanan {
    public LayananPremium() {
        super("Cuci Premium", 100000);
    }

    @Override
    public String getDeskripsi() {
        return getNama() + " (Rp" + getHarga() + ") - Cuci exterior wax, vacuum interior, semir ban, pengharum mobil";
    }
    
    @Override
    public void tampilkanDetailSpesial() {
        System.out.println("💎 Layanan Premium - Perawatan menyeluruh untuk mobil Anda");
        System.out.println("✔ Cuci exterior dengan wax pelindung");
        System.out.println("✔ Vacuum interior premium");
        System.out.println("✔ Semir ban dan pengharum mobil");
        System.out.println("✔ Waktu pengerjaan: 60-90 menit");
    }
}

public class SistemPencucianMobil {
    private static ArrayList<Pelanggan> pelangganList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Sistem Manajemen Layanan Pencucian Mobil ===");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Lihat Daftar Pelanggan");
            System.out.println("3. Update Data Pelanggan");
            System.out.println("4. Hapus Pelanggan");
            System.out.println("5. Tambah Pemesanan Layanan");
            System.out.println("6. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (pilihan) {
                case 1: tambahPelanggan(); break;
                case 2: lihatPelanggan(); break;
                case 3: updatePelanggan(); break;
                case 4: hapusPelanggan(); break;
                case 5: tambahPemesanan(); break;
                case 6: 
                    System.out.println("Keluar dari program...");
                    return;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }

    private static void tambahPelanggan() {
        System.out.print("Masukkan ID Pelanggan: ");
        String idPelanggan = scanner.nextLine();
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan Nomor Telepon: ");
        String nomorTelepon = scanner.nextLine();

        Pelanggan pelanggan = new Pelanggan(idPelanggan, nama, nomorTelepon);
        pelangganList.add(pelanggan);
        System.out.println("Pelanggan berhasil ditambahkan!");
    }

    private static void lihatPelanggan() {
        if (pelangganList.isEmpty()) {
            System.out.println("Tidak ada pelanggan yang terdaftar.");
        } else {
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
                System.out.print("Masukkan Nama baru: ");
                pelanggan.setNama(scanner.nextLine());
                System.out.print("Masukkan Nomor Telepon baru: ");
                pelanggan.setNomorTelepon(scanner.nextLine());
                System.out.println("Data pelanggan berhasil diupdate!");
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
                ((LayananReguler)layanan).tampilkanDetailSpesial();
                break;
            case 2: 
                layanan = new LayananPremium();
                ((LayananPremium)layanan).tampilkanDetailSpesial(); 
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
    }
}