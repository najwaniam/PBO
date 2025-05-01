package Posttest6;

abstract class Layanan {
    private final String nama;
    private double harga;

    public Layanan(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public final String getNama() { return nama; }
    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }
    
    public abstract String getDeskripsi();
    public abstract void tampilkanDetailSpesial();
}

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
        System.out.println("Layanan Reguler - Cocok untuk perawatan mingguan");
        System.out.println("Cuci exterior dasar");
        System.out.println("Vacuum interior standar");
        System.out.println("Waktu pengerjaan: 30-45 menit");
    }
}

class LayananPremium extends Layanan implements LayananTambahan {
    private boolean sudahPakaiLayananTambahan = false;

    public LayananPremium() {
        super("Cuci Premium", 100000);
    }

    @Override
    public String getDeskripsi() {
        String deskripsi = getNama() + " (Rp" + getHarga() + ") - Cuci exterior wax, vacuum interior, semir ban, pengharum mobil";
        if (sudahPakaiLayananTambahan) {
            deskripsi += " + Layanan Tambahan";
        }
        return deskripsi;
    }
    
    @Override
    public void tampilkanDetailSpesial() {
        System.out.println("Layanan Premium - Perawatan menyeluruh untuk mobil Anda");
        System.out.println("Cuci exterior dengan wax pelindung");
        System.out.println("Vacuum interior premium");
        System.out.println("Semir ban dan pengharum mobil");
        System.out.println("Waktu pengerjaan: 60-90 menit");
        if (sudahPakaiLayananTambahan) {
            tampilkanInfoTambahan();
        }
    }

    @Override
    public void tampilkanInfoTambahan() {
        System.out.println("Layanan tambahan termasuk: ");
        System.out.println("- Perawatan khusus jok kulit");
        System.out.println("- Pembersihan AC mobil");
    }

    @Override
    public void applyAdditionalCharge(double persentase) {
        double hargaBaru = getHarga() * (1 + persentase);
        setHarga(hargaBaru);
        sudahPakaiLayananTambahan = true;
        System.out.printf("Biaya tambahan %.0f%% diterapkan. Harga baru: Rp%.0f\n", 
                         persentase*100, hargaBaru);
    }
}