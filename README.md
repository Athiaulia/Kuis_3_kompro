# README - Sistem Perpustakaan Digital dengan Pemetaan Ruang

## 📚 Deskripsi Sistem
Sistem Perpustakaan Digital ini adalah implementasi dari studi kasus perpustakaan yang menggunakan sistem penataan buku berdasarkan zona rak 2D (array 2D). Sistem ini memungkinkan pengguna untuk mencari buku berdasarkan judul, menampilkan lokasi koordinat (baris, kolom), dan melakukan peminjaman serta pengembalian otomatis dengan perhitungan denda keterlambatan.

## 🏗️ Struktur Kode

### 1. Class `Buku.java`
**Atribut:**
- `judulBuku` (String) - Judul buku
- `penulisBuku` (String) - Nama penulis
- `kodeRakBuku` (String) - Kode identifikasi rak
- `statusTersedia` (boolean) - Status ketersediaan buku
- `waktuPeminjaman` (LocalDateTime) - Waktu peminjaman
- `batasWaktuKembali` (LocalDateTime) - Batas waktu pengembalian
- `namaPeminjam` (String) - Nama peminjam

**Method Utama:**
- `pinjamBuku()` - Mengubah status buku menjadi dipinjam
- `kembalikanBuku()` - Mengembalikan buku dan hitung denda
- `toString()` - Menampilkan informasi buku

### 2. Class `Rak.java`
**Atribut:**
- `lokasiBuku` (Buku[][]) - Array 2D berukuran 5x5 untuk menyimpan buku

**Method Utama:**
- `tampilkanDenah()` - Looping 2D untuk menampilkan posisi buku secara visual
- `cariBuku(String judul)` - Mencari buku dan mengembalikan koordinat
- `pinjamBuku(int baris, int kolom)` - Meminjam buku di posisi tertentu
- `kembalikanBuku(int baris, int kolom)` - Mengembalikan buku dan hitung denda
- `tambahBuku()` - Menambahkan buku baru ke rak
- `getBukuDiPosisi()` - Mendapatkan informasi buku di koordinat tertentu

### 3. Class `Perpustakaan.java`
**Atribut:**
- `rakUtama` (Rak) - Instance rak utama perpustakaan
- `inputScanner` (Scanner) - Scanner untuk input pengguna

**Method Utama:**
- `menuUtama()` - Menu utama sistem dengan navigasi
- `tampilkanPetaBuku()` - Menampilkan denah rak visual
- `cariBuku()` - Interface pencarian buku
- `pinjamBuku()` - Interface peminjaman buku
- `kembalikanBuku()` - Interface pengembalian buku
- `tambahBukuBaru()` - Interface penambahan buku
- `tampilkanStatistikPerpustakaan()` - Statistik penggunaan rak

### 4. Class `MainPerpus.java`
**Fungsi:**
- Entry point aplikasi
- Inisialisasi sistem perpustakaan
- Error handling dan resource management
- Pesan selamat datang dan informasi sistem

## 🚀 Fitur Utama

### 1. **Pemetaan Ruang 2D**
- Rak berukuran 5x5 (25 slot total)
- Visualisasi denah rak yang mudah dibaca
- Koordinat berbasis indeks (0-4 untuk baris dan kolom)

### 2. **Pencarian Buku**
- Pencarian berdasarkan judul (case-insensitive)
- Menampilkan koordinat lokasi buku
- Informasi detail buku dan status ketersediaan

### 3. **Sistem Peminjaman**
- Tracking waktu peminjaman real-time
- Validasi ketersediaan buku
- Input nama peminjam dan durasi peminjaman

### 4. **Sistem Pengembalian**
- Perhitungan denda otomatis (Rp 5,000 per hari keterlambatan)
- Validasi buku yang sedang dipinjam
- Reset status buku setelah dikembalikan

### 5. **Manajemen Buku**
- Penambahan buku baru ke rak
- Validasi posisi kosong
- Informasi lengkap buku

### 6. **Statistik Perpustakaan**
- Total buku tersedia dan dipinjam
- Slot kosong di rak
- Tingkat penggunaan rak (persentase)

## 📊 Interface dan User Experience

### Menu Utama:
```
🏛️  SISTEM PERPUSTAKAAN DIGITAL DENGAN PEMETAAN RUANG
============================================================
📚 Menu Utama:
1. 🗺️  Tampilkan Peta Buku (Denah Rak)
2. 🔍 Cari Buku berdasarkan Judul
3. 📖 Pinjam Buku
4. 📥 Kembalikan Buku
5. ➕ Tambah Buku Baru
6. 📊 Statistik Perpustakaan
0. 🚪 Keluar
```

### Contoh Denah Rak:
```
=== DENAH RAK PERPUSTAKAAN ===
Legend: [KOSONG] = Slot kosong, [JUDUL] = Buku tersedia, [DIPINJAM] = Buku sedang dipinjam

Posisi Rak (Baris x Kolom):
     Kolom 0             Kolom 1             Kolom 2             Kolom 3             Kolom 4
B0:  Java Programming   Data Structures     Algorithms          [KOSONG]            [KOSONG]
B1:  Database Systems   Computer Networks   [KOSONG]            [KOSONG]            [KOSONG]
B2:  Operating Systems  Software Engineering[KOSONG]            [KOSONG]            [KOSONG]
B3:  Machine Learning   [DIPINJAM]          [KOSONG]            [KOSONG]            [KOSONG]
B4:  Web Development    [KOSONG]            [KOSONG]            [KOSONG]            [KOSONG]
```

## 🔧 Cara Menjalankan

### Persyaratan Sistem:
- Java Development Kit (JDK) 8 atau lebih baru
- Terminal/Command Prompt

### Langkah Instalasi:
1. **Kompilasi semua file Java:**
   ```bash
   javac *.java
   ```

2. **Jalankan aplikasi:**
   ```bash
   java MainPerpus
   ```

### Contoh Penggunaan:
1. **Mencari Buku:**
   - Pilih menu 2
   - Masukkan judul buku (contoh: "Java Programming")
   - Sistem akan menampilkan lokasi dan detail buku

2. **Meminjam Buku:**
   - Pilih menu 3
   - Masukkan nama peminjam
   - Masukkan koordinat buku (baris, kolom)
   - Tentukan lama peminjaman

3. **Mengembalikan Buku:**
   - Pilih menu 4
   - Masukkan koordinat buku yang dipinjam
   - Sistem akan hitung denda jika terlambat

## 💡 Fitur Unggulan

### 1. **User-Friendly Interface**
- Menggunakan emoji dan formatting yang menarik
- Pesan error yang informatif
- Navigasi menu yang intuitif

### 2. **Robust Error Handling**
- Validasi input yang komprehensif
- Penanganan exception untuk input yang salah
- Resource management yang aman

### 3. **Real-time Tracking**
- Menggunakan `LocalDateTime` untuk tracking waktu
- Perhitungan denda yang akurat
- Status real-time untuk setiap buku

### 4. **Scalable Architecture**
- Pemisahan concern yang jelas (MVC pattern)
- Code yang mudah dipelihara dan dikembangkan
- Penamaan yang konsisten dan informatif

## 🔍 Contoh Data Default

Sistem dilengkapi dengan beberapa buku default untuk testing:
- "Java Programming" - Oracle Team (A01)
- "Data Structures" - Michael Goodrich (A02)
- "Algorithms" - Robert Sedgewick (A03)
- "Database Systems" - Raghu Ramakrishnan (B01)
- "Computer Networks" - Andrew Tanenbaum (B02)
- Dan lain-lain...

## 📝 Catatan Teknis

### Penamaan Konvensi:
- **Class**: PascalCase (contoh: `MainPerpus`)
- **Method**: camelCase (contoh: `tampilkanDenah`)
- **Variable**: camelCase (contoh: `lokasiBuku`)
- **Constant**: UPPER_SNAKE_CASE (contoh: `UKURAN_BARIS_RAK`)

### Logika Spasial:
- Array 2D `[baris][kolom]` untuk representasi rak
- Koordinat dimulai dari (0,0) di pojok kiri atas
- Validasi koordinat untuk mencegah `ArrayIndexOutOfBoundsException`

### Perhitungan Denda:
- Denda: Rp 5,000 per hari keterlambatan
- Menggunakan `ChronoUnit.DAYS.between()` untuk akurasi
- Tidak ada denda jika dikembalikan tepat waktu atau lebih awal

---

**Developed by:** Tim Pengembang Java  
**Version:** 1.0  
**License:** Educational Use
