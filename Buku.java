import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Class Buku merepresentasikan entitas buku dalam perpustakaan digital
 * dengan informasi lengkap termasuk status ketersediaan dan waktu peminjaman
 */
public class Buku {
    private String judulBuku;
    private String penulisBuku;
    private String kodeRakBuku;
    private boolean statusTersedia;
    private LocalDateTime waktuPeminjaman;
    private LocalDateTime batasWaktuKembali;
    private String namaPeminjam;
    
    /**
     * Konstruktor untuk membuat objek Buku baru
     * @param judulBuku judul dari buku
     * @param penulisBuku nama penulis buku
     * @param kodeRakBuku kode identifikasi rak
     */
    public Buku(String judulBuku, String penulisBuku, String kodeRakBuku) {
        this.judulBuku = judulBuku;
        this.penulisBuku = penulisBuku;
        this.kodeRakBuku = kodeRakBuku;
        this.statusTersedia = true;
        this.waktuPeminjaman = null;
        this.batasWaktuKembali = null;
        this.namaPeminjam = "";
    }
    
    // Getter methods
    public String getJudulBuku() {
        return judulBuku;
    }
    
    public String getPenulisBuku() {
        return penulisBuku;
    }
    
    public String getKodeRakBuku() {
        return kodeRakBuku;
    }
    
    public boolean isStatusTersedia() {
        return statusTersedia;
    }
    
    public LocalDateTime getWaktuPeminjaman() {
        return waktuPeminjaman;
    }
    
    public LocalDateTime getBatasWaktuKembali() {
        return batasWaktuKembali;
    }
    
    public String getNamaPeminjam() {
        return namaPeminjam;
    }
    
    // Setter methods
    public void setStatusTersedia(boolean statusTersedia) {
        this.statusTersedia = statusTersedia;
    }
    
    public void setWaktuPeminjaman(LocalDateTime waktuPeminjaman) {
        this.waktuPeminjaman = waktuPeminjaman;
    }
    
    public void setBatasWaktuKembali(LocalDateTime batasWaktuKembali) {
        this.batasWaktuKembali = batasWaktuKembali;
    }
    
    public void setNamaPeminjam(String namaPeminjam) {
        this.namaPeminjam = namaPeminjam;
    }
    
    /**
     * Method untuk meminjam buku
     * @param namaPeminjam nama orang yang meminjam
     * @param lamaPinjamanHari lama peminjaman dalam hari
     */
    public void pinjamBuku(String namaPeminjam, int lamaPinjamanHari) {
        this.statusTersedia = false;
        this.namaPeminjam = namaPeminjam;
        this.waktuPeminjaman = LocalDateTime.now();
        this.batasWaktuKembali = this.waktuPeminjaman.plusDays(lamaPinjamanHari);
    }
    
    /**
     * Method untuk mengembalikan buku
     * @return denda yang harus dibayar (0 jika tidak terlambat)
     */
    public double kembalikanBuku() {
        double dendaKeterlambatan = 0.0;
        LocalDateTime waktuSekarang = LocalDateTime.now();
        
        // Hitung denda jika terlambat (Rp 5000 per hari)
        if (waktuSekarang.isAfter(batasWaktuKembali)) {
            long hariTerlambat = ChronoUnit.DAYS.between(batasWaktuKembali, waktuSekarang);
            dendaKeterlambatan = hariTerlambat * 5000.0;
        }
        
        // Reset status buku
        this.statusTersedia = true;
        this.namaPeminjam = "";
        this.waktuPeminjaman = null;
        this.batasWaktuKembali = null;
        
        return dendaKeterlambatan;
    }
    
    /**
     * Method untuk menampilkan informasi buku
     * @return string representasi dari buku
     */
    @Override
    public String toString() {
        String statusStr = statusTersedia ? "Tersedia" : "Dipinjam oleh " + namaPeminjam;
        return String.format("'%s' - %s [%s] (%s)", 
                judulBuku, penulisBuku, kodeRakBuku, statusStr);
    }
}
