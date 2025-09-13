public class soalno3b {
    public static void main(String[] args) {
        int jamMasuk = 10;

        if (jamMasuk < 12) {
            System.out.println("Pagi, tarif Rp5.000");

        } else if (jamMasuk < 18) {    
            System.out.println("Siang, tarif Rp7.000");

        } else {
            System.out.println("Malam, tarif Rp10.000");
        }
    }
}    