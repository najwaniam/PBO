import java.util.Scanner;

public class UTS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selamat datang di Asroshan Motor!");

        // Input data mobil dari pengguna
        System.out.print("Masukkan merk mobil: ");
        String merk = scanner.nextLine();
        System.out.print("Masukkan model mobil: ");
        String model = scanner.nextLine();
        System.out.print("Masukkan warna mobil: ");
        String warna = scanner.nextLine();
        System.out.print("Masukkan kondisi mobil (contoh: Kotor, Berdebu, dll): ");
        String kondisi = scanner.nextLine();

        Mobil mobil = new Mobil(merk, model, warna, kondisi);

        System.out.println("\nMobil yang akan dicuci:");
        mobil.tampilkanInfo();

        System.out.println("\nPilih jenis layanan:");
        System.out.println("1. Cuci Biasa");
        System.out.println("2. Cuci Wax");
        System.out.println("3. Cuci Detailing");
        System.out.print("Masukkan pilihan (1-3): ");
        int pilihan = scanner.nextInt();

        LayananCuci layanan = new LayananCuci();
        switch (pilihan) {
            case 1:
                layanan.setJenisLayanan("Cuci Biasa");
                layanan.setHarga(50000);
                break;
            case 2:
                layanan.setJenisLayanan("Cuci Wax");
                layanan.setHarga(100000);
                break;
            case 3:
                layanan.setJenisLayanan("Cuci Detailing");
                layanan.setHarga(200000);
                break;
            default:
                System.out.println("Pilihan tidak valid, menggunakan Cuci Biasa sebagai default.");
                layanan.setJenisLayanan("Cuci Biasa");
                layanan.setHarga(50000);
                break;
        }

        System.out.println("\nAnda memilih " + layanan.getJenisLayanan() + " dengan harga Rp" + layanan.getHarga());

        System.out.println("\nProses pencucian mobil...");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Langkah " + i + " selesai.");
            try {
                Thread.sleep(1000); // Simulasi waktu pencucian
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nPencucian mobil selesai!");
        mobil.setKondisi("Bersih dan mengkilap");
        System.out.println("\nInformasi mobil setelah dicuci:");
        mobil.tampilkanInfo();
    }
}

class Mobil {
    private String merk;
    private String model;
    private String warna;
    private String kondisi;

    public Mobil(String merk, String model, String warna, String kondisi) {
        this.merk = merk;
        this.model = model;
        this.warna = warna;
        this.kondisi = kondisi;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public void tampilkanInfo() {
        System.out.println("Merk: " + merk);
        System.out.println("Model: " + model);
        System.out.println("Warna: " + warna);
        System.out.println("Kondisi: " + kondisi);
    }
}

class LayananCuci {
    private String jenisLayanan;
    private int harga;

    public String getJenisLayanan() {
        return jenisLayanan;
    }

    public void setJenisLayanan(String jenisLayanan) {
        this.jenisLayanan = jenisLayanan;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
}