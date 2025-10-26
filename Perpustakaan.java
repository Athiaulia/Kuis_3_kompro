import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Class Perpustakaan sebagai controller utama untuk mengelola
 * operasi perpustakaan digital dengan sistem pemetaan ruang
 */
public class Perpustakaan {
    private Rak rakUtama;
    private Scanner inputScanner;
    
    /**
     * Konstruktor untuk inisialisasi perpustakaan
     */
    public Perpustakaan() {
        this.rakUtama = new Rak();
        this.inputScanner = new Scanner(System.in);
    }
    
    /**
     * Method utama untuk menampilkan menu dan mengelola navigasi
     */
    public void menuUtama() {
        boolean sistemBerjalan = true;
        
        while (sistemBerjalan) {
            tampilkanHeaderMenu();
            tampilkanPilihanMenu();
            
            try {
                int pilihanMenu = inputScanner.nextInt();
                inputScanner.nextLine(); // Consume newline
                
                switch (pilihanMenu) {
                    case 1:
                        tampilkanPetaBuku();
                        break;
                    case 2:
                        cariBuku();
                        break;
                    case 3:
                        pinjamBuku();
                        break;
                    case 4:
                        kembalikanBuku();
                        break;
                    case 5:
                        tambahBukuBaru();
                        break;
                    case 6:
                        tampilkanStatistikPerpustakaan();
                        break;
                    case 0:
                        sistemBerjalan = false;
                        tampilkanPesanKeluar();
                        break;
                    default:
                        System.out.println("❌ Pilihan tidak valid! Silakan pilih menu yang tersedia.");
                }
                
                if (sistemBerjalan) {
                    menungguInputLanjutan();
                }
                
            } catch (InputMismatchException e) {
                System.out.println("❌ Input tidak valid! Harap masukkan angka.");
                inputScanner.nextLine(); // Clear invalid input
                menungguInputLanjutan();
            }
        }
    }
    
    /**
     * Method untuk menampilkan header menu
     */
    private void tampilkanHeaderMenu() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("🏛️  SISTEM PERPUSTAKAAN DIGITAL DENGAN PEMETAAN RUANG");
        System.out.println("=".repeat(60));
    }
    
    /**
     * Method untuk menampilkan pilihan menu
     */
    private void tampilkanPilihanMenu() {
        System.out.println("📚 Menu Utama:");
        System.out.println("1. 🗺️  Tampilkan Peta Buku (Denah Rak)");
        System.out.println("2. 🔍 Cari Buku berdasarkan Judul");
        System.out.println("3. 📖 Pinjam Buku");
        System.out.println("4. 📥 Kembalikan Buku");
        System.out.println("5. ➕ Tambah Buku Baru");
        System.out.println("6. 📊 Statistik Perpustakaan");
        System.out.println("0. 🚪 Keluar");
        System.out.print("\n👉 Pilih menu (0-6): ");
    }
    
    /**
     * Method untuk menampilkan peta/denah buku
     */
    public void tampilkanPetaBuku() {
        System.out.println("\n🗺️  PETA BUKU PERPUSTAKAAN");
        rakUtama.tampilkanDenah();
    }
    
    /**
     * Method untuk mencari buku berdasarkan judul
     */
    public void cariBuku() {
        System.out.println("\n🔍 PENCARIAN BUKU");
        System.out.print("📝 Masukkan judul buku yang dicari: ");
        String judulDicari = inputScanner.nextLine();
        
        if (judulDicari.trim().isEmpty()) {
            System.out.println("❌ Judul buku tidak boleh kosong!");
            return;
        }
        
        int[] koordinatBuku = rakUtama.cariBuku(judulDicari);
        
        if (koordinatBuku != null) {
            Buku bukuDitemukan = rakUtama.getBukuDiPosisi(koordinatBuku[0], koordinatBuku[1]);
            System.out.println("\n✅ BUKU DITEMUKAN!");
            System.out.println("📍 Lokasi: Baris " + koordinatBuku[0] + ", Kolom " + koordinatBuku[1]);
            System.out.println("📚 Detail: " + bukuDitemukan.toString());
            
            if (bukuDitemukan.getBatasWaktuKembali() != null && !bukuDitemukan.isStatusTersedia()) {
                System.out.println("⏰ Batas waktu kembali: " + bukuDitemukan.getBatasWaktuKembali());
            }
        } else {
            System.out.println("❌ Buku dengan judul '" + judulDicari + "' tidak ditemukan!");
            System.out.println("💡 Tips: Pastikan penulisan judul sudah benar atau coba kata kunci yang berbeda.");
        }
    }
    
    /**
     * Method untuk meminjam buku
     */
    public void pinjamBuku() {
        System.out.println("\n📖 PEMINJAMAN BUKU");
        
        try {
            System.out.print("👤 Masukkan nama peminjam: ");
            String namaPeminjam = inputScanner.nextLine();
            
            if (namaPeminjam.trim().isEmpty()) {
                System.out.println("❌ Nama peminjam tidak boleh kosong!");
                return;
            }
            
            System.out.print("📍 Masukkan baris buku (0-4): ");
            int barisPinjam = inputScanner.nextInt();
            
            System.out.print("📍 Masukkan kolom buku (0-4): ");
            int kolomPinjam = inputScanner.nextInt();
            
            System.out.print("📅 Masukkan lama peminjaman (hari): ");
            int lamaPinjaman = inputScanner.nextInt();
            inputScanner.nextLine(); // Consume newline
            
            if (lamaPinjaman <= 0) {
                System.out.println("❌ Lama peminjaman harus lebih dari 0 hari!");
                return;
            }
            
            // Tampilkan info buku sebelum dipinjam
            Buku bukuTarget = rakUtama.getBukuDiPosisi(barisPinjam, kolomPinjam);
            if (bukuTarget != null) {
                System.out.println("\n📚 Buku yang akan dipinjam: " + bukuTarget.toString());
            }
            
            boolean berhasilPinjam = rakUtama.pinjamBuku(barisPinjam, kolomPinjam, namaPeminjam, lamaPinjaman);
            
            if (berhasilPinjam) {
                System.out.println("📅 Lama peminjaman: " + lamaPinjaman + " hari");
                System.out.println("⏰ Harap dikembalikan tepat waktu untuk menghindari denda!");
            }
            
        } catch (InputMismatchException e) {
            System.out.println("❌ Input tidak valid! Harap masukkan angka untuk koordinat dan lama peminjaman.");
            inputScanner.nextLine(); // Clear invalid input
        }
    }
    
    /**
     * Method untuk mengembalikan buku
     */
    public void kembalikanBuku() {
        System.out.println("\n📥 PENGEMBALIAN BUKU");
        
        try {
            System.out.print("📍 Masukkan baris buku (0-4): ");
            int barisKembali = inputScanner.nextInt();
            
            System.out.print("📍 Masukkan kolom buku (0-4): ");
            int kolomKembali = inputScanner.nextInt();
            inputScanner.nextLine(); // Consume newline
            
            // Tampilkan info buku sebelum dikembalikan
            Buku bukuTarget = rakUtama.getBukuDiPosisi(barisKembali, kolomKembali);
            if (bukuTarget != null && !bukuTarget.isStatusTersedia()) {
                System.out.println("\n📚 Buku yang akan dikembalikan: " + bukuTarget.toString());
            }
            
            double dendaKeterlambatan = rakUtama.kembalikanBuku(barisKembali, kolomKembali);
            
            if (dendaKeterlambatan > 0) {
                System.out.println("💰 Total denda yang harus dibayar: Rp " + String.format("%.2f", dendaKeterlambatan));
            }
            
        } catch (InputMismatchException e) {
            System.out.println("❌ Input tidak valid! Harap masukkan angka untuk koordinat.");
            inputScanner.nextLine(); // Clear invalid input
        }
    }
    
    /**
     * Method untuk menambah buku baru ke rak
     */
    private void tambahBukuBaru() {
        System.out.println("\n➕ TAMBAH BUKU BARU");
        
        try {
            System.out.print("📖 Masukkan judul buku: ");
            String judulBaru = inputScanner.nextLine();
            
            System.out.print("✍️  Masukkan nama penulis: ");
            String penulisBaru = inputScanner.nextLine();
            
            System.out.print("🏷️  Masukkan kode rak: ");
            String kodeRakBaru = inputScanner.nextLine();
            
            if (judulBaru.trim().isEmpty() || penulisBaru.trim().isEmpty() || kodeRakBaru.trim().isEmpty()) {
                System.out.println("❌ Semua field harus diisi!");
                return;
            }
            
            System.out.print("📍 Masukkan baris penempatan (0-4): ");
            int barisBaru = inputScanner.nextInt();
            
            System.out.print("📍 Masukkan kolom penempatan (0-4): ");
            int kolomBaru = inputScanner.nextInt();
            inputScanner.nextLine(); // Consume newline
            
            Buku bukuBaru = new Buku(judulBaru, penulisBaru, kodeRakBaru);
            rakUtama.tambahBuku(bukuBaru, barisBaru, kolomBaru);
            
        } catch (InputMismatchException e) {
            System.out.println("❌ Input tidak valid! Harap masukkan angka untuk koordinat.");
            inputScanner.nextLine(); // Clear invalid input
        }
    }
    
    /**
     * Method untuk menampilkan statistik perpustakaan
     */
    private void tampilkanStatistikPerpustakaan() {
        System.out.println("\n📊 STATISTIK PERPUSTAKAAN");
        
        int[] dimensi = rakUtama.getDimensiRak();
        int totalSlot = dimensi[0] * dimensi[1];
        int bukuTersedia = 0;
        int bukuDipinjam = 0;
        int slotKosong = 0;
        
        for (int baris = 0; baris < dimensi[0]; baris++) {
            for (int kolom = 0; kolom < dimensi[1]; kolom++) {
                Buku buku = rakUtama.getBukuDiPosisi(baris, kolom);
                if (buku == null) {
                    slotKosong++;
                } else if (buku.isStatusTersedia()) {
                    bukuTersedia++;
                } else {
                    bukuDipinjam++;
                }
            }
        }
        
        System.out.println("📏 Dimensi Rak: " + dimensi[0] + " x " + dimensi[1] + " = " + totalSlot + " slot");
        System.out.println("📚 Buku Tersedia: " + bukuTersedia + " buku");
        System.out.println("📖 Buku Dipinjam: " + bukuDipinjam + " buku");
        System.out.println("🗃️  Slot Kosong: " + slotKosong + " slot");
        System.out.println("📈 Tingkat Penggunaan Rak: " + 
            String.format("%.1f%%", ((double)(bukuTersedia + bukuDipinjam) / totalSlot * 100)));
    }
    
    /**
     * Method untuk menampilkan pesan keluar
     */
    private void tampilkanPesanKeluar() {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("👋 Terima kasih telah menggunakan Sistem Perpustakaan Digital!");
        System.out.println("📚 Selamat membaca dan sampai jumpa lagi!");
        System.out.println("=".repeat(60));
    }
    
    /**
     * Method untuk menunggu input sebelum melanjutkan
     */
    private void menungguInputLanjutan() {
        System.out.println("\n" + "-".repeat(60));
        System.out.print("⏸️  Tekan Enter untuk kembali ke menu utama...");
        inputScanner.nextLine();
    }
    
    /**
     * Method untuk menutup scanner (dipanggil saat aplikasi selesai)
     */
    public void tutupScanner() {
        if (inputScanner != null) {
            inputScanner.close();
        }
    }
}
