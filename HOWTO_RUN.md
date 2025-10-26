# 🚀 Cara Menjalankan Sistem Perpustakaan Digital

## ⚠️ Catatan Penting
File Java telah dibuat namun Java compiler tidak tersedia di environment ini. Berikut adalah langkah-langkah untuk menjalankan sistem di environment lokal Anda:

## 📋 Persyaratan Sistem
- Java Development Kit (JDK) 8 atau lebih baru
- Terminal/Command Prompt
- Text editor atau IDE Java (opsional: IntelliJ IDEA, Eclipse, VS Code)

## 🔧 Langkah Instalasi dan Eksekusi

### 1. **Persiapan Environment**
```bash
# Cek apakah Java sudah terinstall
java -version
javac -version

# Jika belum terinstall, download dari:
# https://www.oracle.com/java/technologies/downloads/
# atau gunakan OpenJDK
```

### 2. **Copy File Java**
Salin semua file Java berikut ke folder project Anda:
- `Buku.java`
- `Rak.java`  
- `Perpustakaan.java`
- `MainPerpus.java`

### 3. **Kompilasi**
```bash
# Masuk ke direktori project
cd /path/to/your/project

# Kompilasi semua file Java
javac *.java

# Atau kompilasi satu per satu
javac Buku.java
javac Rak.java
javac Perpustakaan.java
javac MainPerpus.java
```

### 4. **Eksekusi**
```bash
# Jalankan aplikasi
java MainPerpus
```

## 🎮 Demo Penggunaan

### Contoh Sesi Interaktif:
```
============================================================
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

👉 Pilih menu (0-6): 1
```

### Contoh Output Denah:
```
=== DENAH RAK PERPUSTAKAAN ===
Posisi Rak (Baris x Kolom):
     Kolom 0             Kolom 1             Kolom 2
B0:  Java Programming   Data Structures     Algorithms
B1:  Database Systems   Computer Networks   [KOSONG]
B2:  Operating Systems  Software Engineering[KOSONG]
```

## ✅ Testing yang Disarankan

### 1. **Test Pencarian Buku**
- Cari "Java Programming" → Harus ditemukan di (0,0)
- Cari "buku tidak ada" → Harus menampilkan tidak ditemukan

### 2. **Test Peminjaman**
- Pinjam buku di koordinat (0,0) dengan nama "John Doe", 7 hari
- Coba pinjam buku yang sama lagi → Harus ditolak

### 3. **Test Pengembalian**
- Kembalikan buku tepat waktu → Tidak ada denda  
- Ubah waktu sistem untuk test denda (manual testing)

### 4. **Test Validasi Input**
- Masukkan koordinat di luar range (5,5) → Harus error
- Masukkan string untuk input angka → Harus error

## 🐛 Troubleshooting

### **Error: "javac command not found"**
- Install JDK dari Oracle atau OpenJDK
- Set PATH environment variable

### **Error: "Could not find or load main class"** 
- Pastikan kompilasi berhasil (ada file .class)
- Jalankan dari direktori yang sama dengan file .class

### **Error: Input/Output Issues**
- Pastikan menggunakan Scanner dengan benar
- Close Scanner di akhir program

## 📊 Expected Output Statistik

Setelah beberapa operasi, statistik akan menampilkan:
```
📊 STATISTIK PERPUSTAKAAN
📏 Dimensi Rak: 5 x 5 = 25 slot
📚 Buku Tersedia: 8 buku
📖 Buku Dipinjam: 2 buku  
🗃️  Slot Kosong: 15 slot
📈 Tingkat Penggunaan Rak: 40.0%
```

---
**📝 Catatan:** Sistem ini dirancang untuk demonstrasi konsep OOP Java dengan implementasi array 2D, datetime handling, dan user interface yang user-friendly.
