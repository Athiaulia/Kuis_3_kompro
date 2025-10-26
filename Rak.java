/**
 * Class Rak merepresentasikan sistem penyimpanan buku dalam format 2D
 * dengan kemampuan untuk mengelola posisi dan pencarian buku
 */
public class Rak {
    private static final int UKURAN_BARIS_RAK = 5;
    private static final int UKURAN_KOLOM_RAK = 5;
    private Buku[][] lokasiBuku;
    
    /**
     * Konstruktor untuk inisialisasi rak kosong
     */
    public Rak() {
        this.lokasiBuku = new Buku[UKURAN_BARIS_RAK][UKURAN_KOLOM_RAK];
        inisialisasiRakDenganBukuDefault();
    }
    
    /**
     * Method untuk mengisi rak dengan beberapa buku default
     */
    private void inisialisasiRakDenganBukuDefault() {
        // Menambahkan beberapa buku default untuk testing
        lokasiBuku[0][0] = new Buku("Java Programming", "Oracle Team", "A01");
        lokasiBuku[0][1] = new Buku("Data Structures", "Michael Goodrich", "A02");
        lokasiBuku[0][2] = new Buku("Algorithms", "Robert Sedgewick", "A03");
        lokasiBuku[1][0] = new Buku("Database Systems", "Raghu Ramakrishnan", "B01");
        lokasiBuku[1][1] = new Buku("Computer Networks", "Andrew Tanenbaum", "B02");
        lokasiBuku[2][0] = new Buku("Operating Systems", "Abraham Silberschatz", "C01");
        lokasiBuku[2][1] = new Buku("Software Engineering", "Ian Sommerville", "C02");
        lokasiBuku[3][0] = new Buku("Machine Learning", "Tom Mitchell", "D01");
        lokasiBuku[3][1] = new Buku("Artificial Intelligence", "Stuart Russell", "D02");
        lokasiBuku[4][0] = new Buku("Web Development", "Jon Duckett", "E01");
    }
    
    /**
     * Method untuk menampilkan denah rak secara visual
     */
    public void tampilkanDenah() {
        System.out.println("\n=== DENAH RAK PERPUSTAKAAN ===");
        System.out.println("Legend: [KOSONG] = Slot kosong, [JUDUL] = Buku tersedia, [DIPINJAM] = Buku sedang dipinjam");
        System.out.println("\nPosisi Rak (Baris x Kolom):");
        
        // Header kolom
        System.out.print("     ");
        for (int kolom = 0; kolom < UKURAN_KOLOM_RAK; kolom++) {
            System.out.printf("%-20s", "Kolom " + kolom);
        }
        System.out.println();
        
        // Tampilkan setiap baris
        for (int baris = 0; baris < UKURAN_BARIS_RAK; baris++) {
            System.out.printf("B%-2d: ", baris);
            for (int kolom = 0; kolom < UKURAN_KOLOM_RAK; kolom++) {
                if (lokasiBuku[baris][kolom] == null) {
                    System.out.printf("%-20s", "[KOSONG]");
                } else {
                    String statusDisplay;
                    if (lokasiBuku[baris][kolom].isStatusTersedia()) {
                        String judul = lokasiBuku[baris][kolom].getJudulBuku();
                        // Potong judul jika terlalu panjang
                        statusDisplay = judul.length() > 15 ? judul.substring(0, 15) + "..." : judul;
                    } else {
                        statusDisplay = "[DIPINJAM]";
                    }
                    System.out.printf("%-20s", statusDisplay);
                }
            }
            System.out.println();
        }
        System.out.println("=".repeat(50));
    }
    
    /**
     * Method untuk mencari buku berdasarkan judul
     * @param judulBuku judul buku yang dicari
     * @return koordinat buku dalam format int[] {baris, kolom} atau null jika tidak ditemukan
     */
    public int[] cariBuku(String judulBuku) {
        for (int baris = 0; baris < UKURAN_BARIS_RAK; baris++) {
            for (int kolom = 0; kolom < UKURAN_KOLOM_RAK; kolom++) {
                if (lokasiBuku[baris][kolom] != null && 
                    lokasiBuku[baris][kolom].getJudulBuku().equalsIgnoreCase(judulBuku)) {
                    return new int[]{baris, kolom};
                }
            }
        }
        return null; // Buku tidak ditemukan
    }
    
    /**
     * Method untuk meminjam buku berdasarkan koordinat
     * @param barisPosisi posisi baris buku
     * @param kolomPosisi posisi kolom buku
     * @param namaPeminjam nama peminjam
     * @param lamaPinjamanHari lama peminjaman dalam hari
     * @return true jika berhasil dipinjam, false jika gagal
     */
    public boolean pinjamBuku(int barisPosisi, int kolomPosisi, String namaPeminjam, int lamaPinjamanHari) {
        // Validasi koordinat
        if (!isKoordinatValid(barisPosisi, kolomPosisi)) {
            System.out.println("❌ Koordinat tidak valid!");
            return false;
        }
        
        // Cek apakah ada buku di posisi tersebut
        if (lokasiBuku[barisPosisi][kolomPosisi] == null) {
            System.out.println("❌ Tidak ada buku di posisi tersebut!");
            return false;
        }
        
        // Cek apakah buku tersedia
        if (!lokasiBuku[barisPosisi][kolomPosisi].isStatusTersedia()) {
            System.out.println("❌ Buku sedang dipinjam oleh: " + 
                lokasiBuku[barisPosisi][kolomPosisi].getNamaPeminjam());
            return false;
        }
        
        // Pinjam buku
        lokasiBuku[barisPosisi][kolomPosisi].pinjamBuku(namaPeminjam, lamaPinjamanHari);
        System.out.println("✅ Buku berhasil dipinjam oleh: " + namaPeminjam);
        return true;
    }
    
    /**
     * Method untuk mengembalikan buku berdasarkan koordinat
     * @param barisPosisi posisi baris buku
     * @param kolomPosisi posisi kolom buku
     * @return denda yang harus dibayar
     */
    public double kembalikanBuku(int barisPosisi, int kolomPosisi) {
        // Validasi koordinat
        if (!isKoordinatValid(barisPosisi, kolomPosisi)) {
            System.out.println("❌ Koordinat tidak valid!");
            return -1;
        }
        
        // Cek apakah ada buku di posisi tersebut
        if (lokasiBuku[barisPosisi][kolomPosisi] == null) {
            System.out.println("❌ Tidak ada buku di posisi tersebut!");
            return -1;
        }
        
        // Cek apakah buku sedang dipinjam
        if (lokasiBuku[barisPosisi][kolomPosisi].isStatusTersedia()) {
            System.out.println("❌ Buku tidak sedang dipinjam!");
            return -1;
        }
        
        // Kembalikan buku dan hitung denda
        double dendaKeterlambatan = lokasiBuku[barisPosisi][kolomPosisi].kembalikanBuku();
        System.out.println("✅ Buku berhasil dikembalikan!");
        
        if (dendaKeterlambatan > 0) {
            System.out.println("⚠️  Denda keterlambatan: Rp " + String.format("%.2f", dendaKeterlambatan));
        } else {
            System.out.println("✅ Tidak ada denda keterlambatan.");
        }
        
        return dendaKeterlambatan;
    }
    
    /**
     * Method untuk mendapatkan informasi buku di koordinat tertentu
     * @param barisPosisi posisi baris
     * @param kolomPosisi posisi kolom
     * @return objek Buku atau null jika tidak ada
     */
    public Buku getBukuDiPosisi(int barisPosisi, int kolomPosisi) {
        if (isKoordinatValid(barisPosisi, kolomPosisi)) {
            return lokasiBuku[barisPosisi][kolomPosisi];
        }
        return null;
    }
    
    /**
     * Method untuk menambah buku baru ke rak
     * @param buku objek Buku yang akan ditambahkan
     * @param barisPosisi posisi baris target
     * @param kolomPosisi posisi kolom target
     * @return true jika berhasil ditambahkan, false jika gagal
     */
    public boolean tambahBuku(Buku buku, int barisPosisi, int kolomPosisi) {
        if (!isKoordinatValid(barisPosisi, kolomPosisi)) {
            System.out.println("❌ Koordinat tidak valid!");
            return false;
        }
        
        if (lokasiBuku[barisPosisi][kolomPosisi] != null) {
            System.out.println("❌ Posisi sudah terisi!");
            return false;
        }
        
        lokasiBuku[barisPosisi][kolomPosisi] = buku;
        System.out.println("✅ Buku berhasil ditambahkan ke rak!");
        return true;
    }
    
    /**
     * Method helper untuk memvalidasi koordinat
     * @param barisPosisi posisi baris
     * @param kolomPosisi posisi kolom
     * @return true jika koordinat valid, false jika tidak
     */
    private boolean isKoordinatValid(int barisPosisi, int kolomPosisi) {
        return barisPosisi >= 0 && barisPosisi < UKURAN_BARIS_RAK && 
               kolomPosisi >= 0 && kolomPosisi < UKURAN_KOLOM_RAK;
    }
    
    /**
     * Method untuk mendapatkan dimensi rak
     * @return array dengan format {baris, kolom}
     */
    public int[] getDimensiRak() {
        return new int[]{UKURAN_BARIS_RAK, UKURAN_KOLOM_RAK};
    }
}
