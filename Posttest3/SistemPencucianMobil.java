package Posttest3; 
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
    public void tambahPemesanan(Layanan layanan) { historyPemesanan.add(layanan); }

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

        for (Pelanggan pelanggan : pelangganList) {
            if (pelanggan.getIdPelanggan().equals(idPelanggan)) {
                pelangganList.remove(pelanggan);
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

        System.out.println("Pilih Layanan:");
        System.out.println("1. Cuci Reguler");
        System.out.println("2. Cuci Premium");
        System.out.print("Pilihan: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        Layanan layanan = null;
        switch (pilihan) {
            case 1: layanan = new LayananReguler(); break;
            case 2: layanan = new LayananPremium(); break;
            default:
                System.out.println("Pilihan tidak valid!");
                return;
        }

        pelangganDitemukan.tambahPemesanan(layanan);
        System.out.println("Layanan berhasil ditambahkan ke history pelanggan!");
    }
}