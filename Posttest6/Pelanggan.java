package Posttest6;
import java.util.ArrayList;

final class Pelanggan {
    private String idPelanggan;
    private final String nama;
    private String nomorTelepon;
    private ArrayList<Layanan> historyPemesanan;

    Pelanggan(String idPelanggan, String nama, String nomorTelepon) {
        this.setIdPelanggan(idPelanggan);
        this.nama = nama;
        this.setNomorTelepon(nomorTelepon);
        this.historyPemesanan = new ArrayList<>();
    }

    public String getIdPelanggan() { return idPelanggan; }
    public String getNama() { return nama; }
    public String getNomorTelepon() { return nomorTelepon; }
    public ArrayList<Layanan> getHistoryPemesanan() { return historyPemesanan; }
    
    public void setIdPelanggan(String idPelanggan) {
        if (idPelanggan == null || idPelanggan.trim().isEmpty()) {
            throw new IllegalArgumentException("ID Pelanggan tidak boleh kosong");
        }
        this.idPelanggan = idPelanggan;
    }

    public void setNomorTelepon(String nomorTelepon) {
        if (nomorTelepon == null || !nomorTelepon.matches("\\d+")) {
            throw new IllegalArgumentException("Nomor telepon harus berupa angka");
        }
        this.nomorTelepon = nomorTelepon;
    }
    
    public final void tambahPemesanan(Layanan layanan) {
        historyPemesanan.add(layanan);
        System.out.println("Layanan " + layanan.getNama() + " ditambahkan ke pelanggan " + nama);
    }
    
    public final void tambahPemesanan(Layanan layanan, double diskon) {
        if (diskon < 0 || diskon > 1) {
            throw new IllegalArgumentException("Diskon harus antara 0 dan 1");
        }
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
        
        if (historyPemesanan.isEmpty()) {
            sb.append("- Belum ada pemesanan\n");
        } else {
            for (Layanan layanan : historyPemesanan) {
                sb.append("- ").append(layanan.getDeskripsi()).append("\n");
            }
        }
        
        return sb.toString();
    }
}