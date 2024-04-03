package pemlan;

import java.util.Scanner;

class driver {
    public String nama;
    public int noSim;

    driver(String nama, int noSim) {
        this.nama = nama;
        this.noSim = noSim;
    }
}

class penumpang {
    public String nama;

    penumpang(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }

}

class kendaraan {
    public String jenis;
    public String merk;
    public String platNomor;

    public driver supir;

    public int maxPenumpang;
    public penumpang[] penumpangs;
    public int jumlahPenumpang;

    Scanner scanner = new Scanner(System.in);

    kendaraan(String pn, String m, int max, String jenis) {
        this.merk = m;
        this.platNomor = pn;
        this.maxPenumpang = max;
        this.jenis = jenis;

        this.penumpangs = new penumpang[maxPenumpang];
        this.jumlahPenumpang = 0;
    }

    public void cekPenumpang() {
        System.out.println("======================");
        System.out.println("Penumpang saat ini : " + this.jumlahPenumpang);
        if (this.jumlahPenumpang != 0) {
            System.out.println("Nama penumpang saat ini:");
            for (int i = 0; i < jumlahPenumpang; i++) {
                System.out.println(penumpangs[i].getNama());
            }
        } else {
            System.out.println("Belum ada penumpang dalam kendaraan.");
        }
    }

    public void penumpangNaik(int naik) {
        System.out.println("Ada penumpang yang ingin naik: " + naik);
        if (jumlahPenumpang + naik <= maxPenumpang) {
            for (int i = 0; i < naik; i++) {
                System.out.println("Masukkan nama penumpang: ");
                String namaPenumpang = scanner.nextLine();
                tambahPenumpang(new penumpang(namaPenumpang));
            }
            System.out.println("Penumpang berhasil naik");
        } else {
            System.out.println("Maaf, penumpang melebihi kapasitas");
        }
        cekPenumpang();
    }

    public void tambahPenumpang(penumpang penumpang) {
        this.penumpangs[jumlahPenumpang] = penumpang; // inisialisasi elemen array
        jumlahPenumpang++;
    }

    public void penumpangTurun(int turun) {
        System.out.println("ada penumpang mau turun: " + turun);
        int current = this.jumlahPenumpang;
        if (current - turun < 0) {
            System.out.println("maaf penumpang ghoib yang turun");
        } else {
            System.out.println("Siapa yang ingin turun?");
            for (int i = 0; i < turun; i++) {
                String namanya = scanner.nextLine();
                turunkanPenumpang(namanya);
            }
        }
        cekPenumpang();
    }

    public void turunkanPenumpang(String nama) {
        boolean penumpangDitemukan = false;
        for (int i = 0; i < jumlahPenumpang; i++) {
            if (penumpangs[i].getNama().equalsIgnoreCase(nama)) {
                // loop mencari nama
                for (int j = i; j < jumlahPenumpang - 1; j++) {
                    penumpangs[j] = penumpangs[j++]; // menggeser array
                }
                penumpangs[jumlahPenumpang--] = null; // hapus penumpang terakhir
                jumlahPenumpang--;
                System.out.println("Penumpang " + nama + " berhasil diturunkan.");
                penumpangDitemukan = true;
                break;
            }
        }
        if (!penumpangDitemukan) {
            System.out.println("Penumpang dengan nama " + nama + " tidak ditemukan dalam kendaraan.");
        }
    }

    public void maju() {
        System.out.println(this.merk + " " + this.platNomor + " Maju");
    }

    public void mundur() {
        System.out.println(this.merk + " " + this.platNomor + " Mundur");
    }

    public void belok() {
        System.out.println(this.merk + " " + this.platNomor + " Belok");
    }

    public void berhenti() {
        System.out.println(this.merk + " " + this.platNomor + " Berhenti");
    }

    public String getPlat() {
        return platNomor;
    }

    public String getMerk() {
        return merk;
    }

    // bagian driver
    public void setDriver(driver supir) {
        this.supir = supir;
    }

    public String getDriver() {
        return supir.nama;
    }

    public int getNoSim() {
        return supir.noSim;
    }

}

class truk extends kendaraan {
    int kapasitasMuatan;

    public truk(String pn, String m, int max, String jenis) {
        super(pn, m, max, jenis);
    }

    @Override
    public void penumpangNaik(int naik) {
        System.out.println("Ada penumpang yang ingin naik: " + naik);
        if (jumlahPenumpang + naik <= maxPenumpang) {
            for (int i = 0; i < naik; i++) {
                System.out.println("Masukkan nama penumpang: ");
                String namaPenumpang = scanner.nextLine();
                tambahPenumpang(new penumpang(namaPenumpang));
            }
            System.out.println("Penumpang berhasil naik");
        } else {
            System.out.println("Maaf, penumpang melebihi kapasitas");
        }
        cekPenumpang();

        // tambahan berat badan ketika penumpang naik
        kapasitasMuatan += 50;
        System.out.println("Kapasitas muatan truk bertambah menjadi: " + kapasitasMuatan + " kg");
    }

    public void setKapasitas(int kapasitas) {
        this.kapasitasMuatan = kapasitas;
    }

    public void cekKapasitasMuatan() {
        System.out.println("Kapasitas muatan truk ini adalah: " + this.kapasitasMuatan + " kg");
    }
}

class bus extends kendaraan {
    boolean isToilet;

    public bus(String pn, String m, int max, String jenis) {
        super(pn, m, max, jenis);
    }

    @Override
    public void penumpangNaik(int naik) {
        System.out.println("Ada penumpang yang ingin naik: " + naik);
        if (jumlahPenumpang + naik <= maxPenumpang) {
            for (int i = 0; i < naik; i++) {
                System.out.println("Masukkan nama penumpang: ");
                String namaPenumpang = scanner.nextLine();
                tambahPenumpang(new penumpang(namaPenumpang));
            }
            System.out.println("Penumpang berhasil naik");
        } else {
            System.out.println("Maaf, penumpang melebihi kapasitas");
        }
        cekPenumpang();
        
        // random penggunaan toilet
        if (this.isToilet == true) {
            int nilaiAcak2 = (int) (Math.random() * 10) + 1;
            int nomAcak = Math.min(jumlahPenumpang, nilaiAcak2);
            System.out.println("Toilet baru saja dipakai oleh penumpang: " + penumpangs[nomAcak - 1].getNama());
            System.out.println("Nilai acak: " + nilaiAcak2);
            System.out.println("Nomor acak yang dipilih: " + nomAcak);
        }
    }

    public void setToilet(boolean adaToilet) {
        this.isToilet = adaToilet;
    }

    public void cekToilet() {
        if (isToilet) {
            System.out.println("Bus ini memiliki toilet.");
        } else {
            System.out.println("Bus ini tidak memiliki toilet.");
        }
    }
}

// ========= MAIN =========
public class cobaKendaraan {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        bus b1 = new bus("AB 1234 XY", "Harapan Jaya", 10, "BUS");
        truk t1 = new truk("N 1234 YY", "HINO", 1, "TRUK");

        boolean bus1 = true;
        boolean truk1 = true;
        int pil1 = 0;
        while (pil1 != 3) {
            System.out.println("Pilih kendaraan");
            System.out.println("1. Bus");
            System.out.println("2. Truck");
            System.out.println("3. Keluar aplikasi");
            System.out.print("Pilih menu (masukkan angka): ");
            pil1 = scanner.nextInt();
            scanner.nextLine();
            System.out.println("======================");

            switch (pil1) {

                case 1:
                    while (bus1) {
                        boolean pil2 = true;
                        while (pil2) {
                            System.out.print("Apakah bus memiliki toilet? (iya/tidak) ");
                            String apatoilet = scanner.nextLine();
                            if (apatoilet.equals("iya")) {
                                b1.setToilet(true);
                                pil2 = false;
                            } else if (apatoilet.equals("tidak")) {
                                b1.setToilet(false);
                                pil2 = false;
                            } else {
                                System.out.println("Jawaban tidak valid.");
                            }
                        }

                        setSupir(b1);

                        bus1 = false;
                    }
                    menu(b1);
                    break;

                case 2:
                    while (truk1) {
                        System.out.print("Berapa kapasitas muatan truk? (... kg) ");
                        int kaps = scanner.nextInt();
                        t1.setKapasitas(kaps);

                        setSupir(t1);

                        truk1 = false;
                    }
                    menu(t1);
                    break;

                case 3:
                    System.out.println("Terima kasih. Program berhenti");
                    break;

                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }
        }
        scanner.close();
    }

    public static void setSupir(kendaraan kendaraan) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nama supir: ");
        String namaSupir = scanner.nextLine();
        System.out.print("Masukkan nomor SIM supir: ");
        int simSupir = scanner.nextInt();
        scanner.nextLine();
        driver driverTruk = new driver(namaSupir, simSupir);
        kendaraan.supir = driverTruk;

    }

    public static void menu(kendaraan kendaraan) {
        Scanner scanner = new Scanner(System.in);
        int pilihan = 0;
        while (pilihan != 8) {
            // Menampilkan menu
            System.out.println("======================");
            System.out.println("MENU " + kendaraan.jenis);
            System.out.println("======================");
            System.out.println("1. Naik");
            System.out.println("2. Turun");
            System.out.println("3. Cek Penumpang");
            System.out.println("4. Cek Kapasitas Muatan (Hanya untuk truk)");
            System.out.println("5. Cek Keberadaan Toilet (Hanya untuk bus)");
            System.out.println("6. Cek Kendaraan");
            System.out.println("7. Cek Supir");
            System.out.println("8. Keluar");
            System.out.println("======================");

            // Meminta input dari pengguna
            System.out.print("Pilih menu (masukkan angka): ");
            pilihan = scanner.nextInt();

            // Proses pemilihan menu
            switch (pilihan) {
                case 1: // penumpang naik
                    System.out.println("Berapa jumlah penumpang naik?");
                    System.out.print("(masukkan angka): ");
                    int naik = scanner.nextInt();
                    kendaraan.penumpangNaik(naik);
                    break;
                case 2: // penumpang turun
                    System.out.println("Berapa jumlah penumpang turun?");
                    System.out.print("(masukkan angka): ");
                    int turun = scanner.nextInt();
                    kendaraan.penumpangTurun(turun);
                    break;
                case 3: // mengecek penumpang
                    kendaraan.cekPenumpang();
                    break;
                case 4: // cek kapasitas truk
                    if (kendaraan instanceof truk) {
                        ((truk) kendaraan).cekKapasitasMuatan();
                    } else {
                        System.out.println("Menu ini hanya untuk truk.");
                    }
                    break;
                case 5: // apakah ada toilet
                    if (kendaraan instanceof bus) {
                        ((bus) kendaraan).cekToilet();
                    } else {
                        System.out.println("Menu ini hanya untuk bus.");
                    }
                    break;
                case 6: // cek kendaraan
                    System.out.println("Plat Nomor : " + kendaraan.getPlat());
                    System.out.println("Merk Kendaraan : " + kendaraan.getMerk());
                    break;
                case 7:
                    System.out.println("Nama supir : " + kendaraan.getDriver());
                    System.out.println("Nomor SIM supir : " + kendaraan.getNoSim());
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih antara 1-8.");
            }
        }
    }

}
