public class soalno5c {
        public static void main(String[] args) {
        boolean anggota = true;
        boolean bayar = false;

        if (anggota) {
            System.out.println("selamat, anda telah terdaftar menjadi anggota perpustakaan");

            if (bayar) {
                System.out.println("Akses e-book dibuka");
            } else {
                System.out.println("Harap bayar iuran dulu");
            }
            
        } else {
            System.out.println("silakan daftar menjadi anggota terlebih dahulu");
        }
    }
}