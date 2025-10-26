/**
 * Class MainPerpus sebagai entry point untuk menjalankan
 * Sistem Perpustakaan Digital dengan Pemetaan Ruang
 * 
 * @author Sistem Perpustakaan Digital
 * @version 1.0
 */
public class MainPerpus {
    
    /**
     * Method main untuk memulai aplikasi perpustakaan
     * @param args argument command line (tidak digunakan)
     */
    public static void main(String[] args) {
        // Inisialisasi sistem perpustakaan
        Perpustakaan sistemPerpustakaan = null;
        
        try {
            // Tampilkan pesan selamat datang
            tampilkanPesanSelamatDatang();
            
            // Buat instance perpustakaan baru
            sistemPerpustakaan = new Perpustakaan();
            
            // Jalankan menu utama
            sistemPerpustakaan.menuUtama();
            
        } catch (Exception e) {
            // Tangani error yang tidak terduga
            System.err.println("❌ Terjadi kesalahan sistem: " + e.getMessage());
            System.err.println("🔧 Silakan restart aplikasi atau hubungi administrator.");
            
            // Print stack trace untuk debugging (bisa dikomentari di production)
            e.printStackTrace();
            
        } finally {
            // Pastikan resource dibersihkan dengan baik
            if (sistemPerpustakaan != null) {
                sistemPerpustakaan.tutupScanner();
                System.out.println("🔧 Sistem berhasil ditutup dengan aman.");
            }
        }
    }
    
    /**
     * Method untuk menampilkan pesan selamat datang dan informasi sistem
     */
    private static void tampilkanPesanSelamatDatang() {
        String garisPembatas = "=".repeat(80);
        String garisSubPembatas = "-".repeat(80);
        
        System.out.println(garisPembatas);
        System.out.println("🏛️             SELAMAT DATANG DI SISTEM PERPUSTAKAAN DIGITAL");
        System.out.println("                         DENGAN PEMETAAN RUANG 2D");
        System.out.println(garisPembatas);
        
        System.out.println("\n📚 FITUR YANG TERSEDIA:");
        System.out.println("   • 🗺️  Pemetaan ruang rak dalam format 2D (5x5)");
        System.out.println("   • 🔍 Pencarian buku berdasarkan judul");
        System.out.println("   • 📖 Sistem peminjaman otomatis dengan tracking waktu");
        System.out.println("   • 📥 Sistem pengembalian dengan perhitungan denda");
        System.out.println("   • ➕ Penambahan buku baru ke rak");
        System.out.println("   • 📊 Statistik penggunaan perpustakaan");
        
        System.out.println("\n⚙️  SPESIFIKASI SISTEM:");
        System.out.println("   • Kapasitas Rak: 5 baris x 5 kolom = 25 slot");
        System.out.println("   • Denda Keterlambatan: Rp 5,000 per hari");
        System.out.println("   • Tracking Waktu: Real-time dengan LocalDateTime");
        System.out.println("   • Interface: Command Line Interface (CLI)");
        
        System.out.println("\n💡 PETUNJUK PENGGUNAAN:");
        System.out.println("   • Gunakan koordinat (baris, kolom) untuk lokasi buku");
        System.out.println("   • Baris dan kolom dimulai dari angka 0");
        System.out.println("   • Ikuti instruksi yang muncul di setiap menu");
        System.out.println("   • Pastikan input sesuai dengan format yang diminta");
        
        System.out.println("\n" + garisSubPembatas);
        System.out.println("🚀 Sistem siap digunakan! Silakan pilih menu yang diinginkan.");
        System.out.println(garisSubPembatas);
        
        // Pause sebentar untuk memberikan waktu baca
        try {
            Thread.sleep(2000); // 2 detik
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Method untuk menampilkan informasi sistem (bisa dipanggil dari menu bantuan)
     */
    public static void tampilkanInformasiSistem() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ℹ️  INFORMASI SISTEM PERPUSTAKAAN DIGITAL");
        System.out.println("=".repeat(60));
        
        System.out.println("📋 Nama Sistem: Sistem Perpustakaan Digital dengan Pemetaan Ruang");
        System.out.println("🔢 Versi: 1.0");
        System.out.println("👨‍💻 Pengembang: Tim Pengembang Java");
        System.out.println("📅 Tanggal Rilis: 2024");
        System.out.println("☕ Platform: Java SE");
        
        System.out.println("\n🏗️  ARSITEKTUR SISTEM:");
        System.out.println("   • Buku.java - Model data buku dengan tracking peminjaman");
        System.out.println("   • Rak.java - Manajemen rak 2D dengan operasi CRUD");
        System.out.println("   • Perpustakaan.java - Controller utama dan business logic");
        System.out.println("   • MainPerpus.java - Entry point dan inisialisasi sistem");
        
        System.out.println("\n🔧 FITUR UNGGULAN:");
        System.out.println("   • Pemetaan spasial 2D untuk lokasi buku");
        System.out.println("   • Perhitungan denda otomatis berdasarkan waktu");
        System.out.println("   • Interface user-friendly dengan emoji dan formatting");
        System.out.println("   • Error handling yang komprehensif");
        System.out.println("   • Validasi input yang ketat");
        
        System.out.println("=".repeat(60));
    }
}
