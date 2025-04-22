package Posttest5;
import java.util.ArrayList;

final class Pelanggan {
    private String idPelanggan;
    private final String nama;
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
    public String getNomorTelepon() { return nomorTelepon; }
    public void setNomorTelepon(String nomorTelepon) { this.nomorTelepon = nomorTelepon; }
    public ArrayList<Layanan> getHistoryPemesanan() { return historyPemesanan; }
    
    // Method final untuk menambahkan pemesanan
    public final void tambahPemesanan(Layanan layanan) {
        historyPemesanan.add(layanan);
        System.out.println("Layanan " + layanan.getNama() + " ditambahkan ke pelanggan " + nama);
    }
    
    // Overloading method dengan diskon
    public final void tambahPemesanan(Layanan layanan, double diskon) {
        double hargaSetelahDiskon = layanan.getHarga() * (1 - diskon);
        System.out.printf("Layanan %s ditambahkan dengan diskon %.0f%%. Harga asli: Rp%.0f, Harga setelah diskon: Rp%.0f%n",
                         layanan.getNama(), diskon*100, layanan.getHarga(), hargaSetelahDiskon);
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