package phonebook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PhoneBook {

    public static void main(String[] args) {
        System.out.println("-----------------------------------REHBER UYGULAMASINA HOŞ GELDİNİZ-----------------------------------\n");
        DoublyLinkedList list = new DoublyLinkedList(); // liste oluşturuldu..
        // input almak için Scanner sınıfından bir nesne oluşturuldu..
        Scanner keyboard = new Scanner(System.in);

        boolean flag = true; // uygulamadan çıkış istendiğinde false olacak..
        while (flag) {
            int secim;
            secim = secimAl();

            switch (secim) {

                // ogrenciler.txt isimli metin dosyasından okuma yapılarak listeye
                // öğrencileri sıralı olarak ekleme..
                case 1 -> {
                    Scanner fileIn;
                    fileIn = null;
                    try {
                        fileIn = new Scanner(new FileInputStream("ogrenciler.txt"));
                        fileIn.useDelimiter(",");
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found.");
                        System.exit(0);
                    }

                    while (fileIn.hasNext()) {
                        int studentNum = fileIn.nextInt();
                        String adSoyad = fileIn.next();
                        ArrayList<String> telNumbers = new ArrayList<>();
                        // öğrencinin kaç adet telefon numarası olduğu bilinmediği
                        // için dosyadaki öğrenci numarası ve isminden sonraki 
                        // olan kısım geçici olarak bir değişkene atanıyor..
                        String tempNumbers = fileIn.nextLine().substring(2);
                        // ve bu geçici değişkendeki her bir öğrenci numarası 
                        // öğrenci nesnesinin telNumbers dizisine aktarılıyor..
                        for (int j = 0; j < tempNumbers.length(); j += 15) {
                            telNumbers.add(tempNumbers.substring(j, j + 13));
                        }
                        Student student = new Student(studentNum, adSoyad.substring(1), telNumbers);
                        list.addStudent(student);
                    }
                    System.out.println("Dosyadaki öğrenciler rehbere eklendi...\n");
                }

                // Klavyeden tüm verileri girilen öğrenciyi, öğrenci numarasına 
                // göre sıralı olacak şekilde listede uygun olan pozisyona ekleme..
                case 2 -> {
                    int studentNum;
                    while (true) { // kullanıcı öğrenci numarasını int tipi dışında
                        //bir şey girdiğinde uygulamanın hata vermeden devam edebilmesi
                        //ve kullanıcıdan yeniden öğrenci numarası istemesi için
                        // try-catch yapısı ile hata kontrolü..
                        try {
                            System.out.print("Öğrenci Numarasını Giriniz: ");
                            studentNum = keyboard.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.println("Hatalı Giriş...");
                            keyboard.nextLine();
                        }
                    }
                    keyboard.nextLine();
                    System.out.print("Öğrencinin Adı ve Soyadını Giriniz: ");
                    String adSoyad = keyboard.nextLine();
                    ArrayList<String> telNumbers = new ArrayList<>();

                    // listeye eklenecek öğrencinin kaç adet telefon numarası olduğu
                    // bilinmediği için eklenecek başka telefon numarası olduğu sürece 
                    // öğrencinin telefon numaralarını tutan telNumbers dizisine 
                    // ekleme yapılıyor...
                    String isThereAnother = "e";
                    while (isThereAnother.equalsIgnoreCase("e")) {
                        System.out.print("Öğrencinin Telofon Numarasını giriniz: ");
                        String telNum = keyboard.nextLine();
                        telNumbers.add(telNum);
                        System.out.print("Öğrencinin başka bir telefon numarası var mı? <e/h>");
                        isThereAnother = keyboard.nextLine();
                        while (!(isThereAnother.equalsIgnoreCase("e") || isThereAnother.equalsIgnoreCase("h"))) {
                            System.out.println("Hatalı Giriş...");
                            System.out.print("Öğrencinin başka bir telefon numarası var mı? <e/h>");
                            isThereAnother = keyboard.nextLine();
                        }
                    }
                    Student student = new Student(studentNum, adSoyad, telNumbers);
                    list.addStudent(student);
                    System.out.println("\nÖğrenci rehbere eklendi...\n");
                }

                // Klavyeden adı ve soyadı girilen bir öğrencinin (varsa aynı ad
                // soyada sahip tüm öğrencilerin) bilgilerini yazdırma.
                case 3 -> {
                    System.out.print("Bilgilerini görüntelemek istediğiniz öğrencinin adını ve soyadını giriniz: ");
                    String name = keyboard.nextLine();
                    System.out.println();
                    list.findStudent(name);
                }

                // Klavyeden öğrenci numarası girilen bir öğrenciyi listeden silme.
                case 4 -> {
                    int num;
                    while (true) {
                        try {
                            System.out.print("Rehberden silmek istediğiniz öğrencinin numarasını giriniz: ");
                            num = keyboard.nextInt();
                            keyboard.nextLine();
                            break;
                        } catch (Exception e) {
                            System.out.println("Hatalı Giriş...");
                            keyboard.nextLine();
                        }
                    }
                    list.deleteStudent(num);

                }

                // Listenin içindeki tüm kayıtlar artan sırada (küçük öğrenci 
                // numarasından büyük öğrenci numarasına) ekrana yazdırma.
                case 5 -> {
                    list.printInAscendingOrder();
                }

                // Listenin içindeki tüm kayıtlar azalan sırada (büyük öğrenci 
                // numarasından küçük öğrenci numarasına) ekrana yazdırma.
                case 6 -> {
                    list.printInDescendingOrder();
                }

                // ÇIKIŞ.
                default -> {
                    System.out.println("Uygulamadan çıkmak istediğinize em"
                            + "in misiniz?<e/h>");
                    String cikis = keyboard.next();
                    while (!(cikis.equalsIgnoreCase("e") || cikis.equalsIgnoreCase("h"))) {
                        System.out.println("Hatalı Giriş...");
                        System.out.println("Uygulamadan çıkmak istediğinize emin misiniz?<e/h>");
                        cikis = keyboard.next();
                    }
                    if (cikis.equalsIgnoreCase("e")) {
                        flag = false;
                    } else {
                        flag = true;
                    }
                }
            }
        }
    } // main

    // kullanıcıdan gösterilen menüdeki hangi işlemi yapmak istediğini (hata kontrolü ile)
    // almaya olanak sağlayan static metot.
    public static int secimAl() {
        int secim;
        Scanner keyboard = new Scanner(System.in);
        menu();
        while (true) {
            try {
                System.out.print("Seçiminiz: ");
                secim = keyboard.nextInt();
                if (secim >= 1 && secim <= 7) {
                    return secim;
                }
                while (secim < 1 || secim > 7) {
                    System.out.println("Hatalı Giriş...Lütfen tekrar deneyiniz...");
                    System.out.print("Seçiminiz: ");
                    secim = keyboard.nextInt();
                    return secim;
                }
            } catch (Exception e) {
                System.out.println("Hatalı Giriş...Lütfen tekrar deneyiniz...");
                keyboard.nextLine();
            }
        }
    }

    // kullanıcıya rehber uygulamasında sunulan seçenekleri gösteren metot.
    public static void menu() {
        System.out.println("""
                           ------------------------------------------------------------------------------------------------------
                           1) Metin dosyasından(ogrenciler.txt) okuyarak Rehber oluşturma
                           2) Rehbere öğrenci ekleme
                           3) \u0130stenilen \u00f6\u011frenci bilgilerini g\u00f6r\u00fcnt\u00fcleme
                           4) Rehberden \u00f6\u011frenci silme
                           5) Rehberdeki kay\u0131tlar\u0131 \u00f6\u011frenci numaras\u0131na g\u00f6re artan s\u0131rada g\u00f6r\u00fcnt\u00fcleme
                           6) Rehberdeki kay\u0131tlar\u0131 \u00f6\u011frenci numaras\u0131na g\u00f6re azalan s\u0131rada g\u00f6r\u00fcnt\u00fcleme
                           7) \u00c7IKI\u015e
                           ------------------------------------------------------------------------------------------------------""");
    }
} // PhoneBook
