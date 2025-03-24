import java.util.ArrayList;
import java.util.Scanner;

class Pelanggan {
    private String idPelanggan;
    private String nama;
    private String nomorTelepon;
    private String historyPemesanan;

    Pelanggan(String idPelanggan, String nama, String nomorTelepon, String historyPemesanan) {
        this.idPelanggan = idPelanggan;
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.historyPemesanan = historyPemesanan;
    }

    // Getter dan Setter untuk idPelanggan
    public String getIdPelanggan() {
        return idPelanggan;
    }

    public void setIdPelanggan(String idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    // Getter dan Setter untuk nama
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    // Getter dan Setter untuk nomorTelepon
    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    // Getter dan Setter untuk historyPemesanan
    public String getHistoryPemesanan() {
        return historyPemesanan;
    }

    public void setHistoryPemesanan(String historyPemesanan) {
        this.historyPemesanan = historyPemesanan;
    }

    @Override
    public String toString() {
        return "ID Pelanggan: " + idPelanggan + ", Nama: " + nama + ", Nomor Telepon: " + nomorTelepon + ", History Pemesanan: " + historyPemesanan;
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
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (pilihan) {
                case 1:
                    tambahPelanggan();
                    break;
                case 2:
                    lihatPelanggan();
                    break;
                case 3:
                    updatePelanggan();
                    break;
                case 4:
                    hapusPelanggan();
                    break;
                case 5:
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
        System.out.print("Masukkan History Pemesanan: ");
        String historyPemesanan = scanner.nextLine();

        Pelanggan pelanggan = new Pelanggan(idPelanggan, nama, nomorTelepon, historyPemesanan);
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
                System.out.print("Masukkan History Pemesanan baru: ");
                pelanggan.setHistoryPemesanan(scanner.nextLine());
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
}