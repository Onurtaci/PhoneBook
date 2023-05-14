package phonebook;

public class DoublyLinkedList {

    private class Node {

        private Student student;
        private Node next;
        private Node previous;

        public Node(Student student) {
            this.student = new Student(student);
            this.next = null;
            this.previous = null;
        }

        public Node(Student student, Node previous, Node next) {
            this.student = new Student(student);
            this.previous = previous;
            this.next = next;
        }
    } // Node

    private Node head;
    private Node tail;
    private Node position; // ekleme, silme, bulma gibi işlemlerde kullanılacak..

    public DoublyLinkedList() {
        head = null;
        tail = null;
    }

    // parametre olarak gelen öğrencinin numarasına göre hangi pozisyona eklenmesi
    // gerektiğini bulan metotu çağıran sonra da başa, sona veya araya ekleme 
    // yapmayı sağlayan metot.
    public void addStudent(Student newStudent) {
        position = findAddPosition(newStudent); // listeye sırasına göre eklenecek
        // öğrencinin hangi pozisyona ekleneceğini bulan metot.

        if (position == null && head != null) { // add to end of list.  First move                                                                       
            Node newNode = new Node(newStudent, tail, null);
            tail.next = newNode;
            tail = tail.next;
        } else if (head == null || position.previous == null) {// at head of list
            Node newHead = new Node(newStudent, null, head);
            if (head != null) {
                head.previous = newHead;
            } else {
                tail = newHead;
                tail.next = null;
            }
            head = newHead;
        } else { // Insert before the current position
            Node temp = new Node(newStudent, position.previous, position);
            position.previous.next = temp;
            position.previous = temp;
        }
    }

    // listeye eklenecek öğrencinin numarası ile listedeki öğrencilerin numarası 
    // karşılaştırılarak yeni öğrencinin hangi öğrenciden önce gelmesi gerektiğini
    // bulan metot.
    private Node findAddPosition(Student newStudent) {
        position = head;
        while (hasNext() && position.student.compareTo(newStudent) == 1) {
            position = position.next;
        }
        return position;
    }

    // parametre olarak verilen isimdeki öğrencileri listede dolaşarak bulan ve
    // bilgilerini kullanıcıya gösteren metot.
    public void findStudent(String targetName) {
        position = head;
        Student studentAtPosition;
        boolean flag = false; // listede aranan öğrenci var mı?...
        while (position != null) {
            studentAtPosition = position.student;
            if (studentAtPosition.getName().equalsIgnoreCase(targetName)) {
                studentAtPosition.showInfos();
                flag = true; // aranan isimde en az bir öğrenci bulundu...
            }
            position = position.next;
        }
        if (flag) {
            System.out.println("Öğrencinin bilgileri yukarıda listelenmiştir...");
        } else {
            System.out.println("Öğrenci kaydı bulunamadı...");
        }
        System.out.println();
    }

    // listeden silinecek öğrencinin parametre olarak gelen öğrenci numarasına
    // göre listede dolaşarak arayan eğer listede bu öğrenci bulunursa silen 
    // yok ise kullanıcıya bulunamadığını gösteren metot.
    public void deleteStudent(int studentNum) {
        findDeletePosition(studentNum); // öğrenci aranıyor...
        if (position == null) { // öğrenci bulunamaz ise...
            System.out.println("\nSilinecek öğrenci kaydı bulunamadı...\n");
        } else if (position.previous == null) {  // öğrenci ilk sırada ise...
            if (head.next == null) {
                head = null;
                tail = null;
            } else {
                head = position.next;
                head.previous = null;
            }
            System.out.println("\nÖğrenci rehberden silindi...\n");
        } else if (position.next == null) {  // öğrenci son sırada ise...
            position.previous.next = null;
            tail = position.previous;

            System.out.println("\nÖğrenci rehberden silindi...\n");
        } else {  // öğrenci aradaki düğümlerden birinde ise...
            position.previous.next = position.next;
            position.next.previous = position.previous;
            position = position.next;
            System.out.println("\nÖğrenci rehberden silindi...\n");
        }
    }

    // parametre olarak verilen öğrenci numarasına sahip olan öğrenciyi listede
    // dolaşarak hangi position da olduğunu bulan metot.
    private Node findDeletePosition(int targetStudentNum) {
        position = head;
        Student studentAtPosition;
        while (hasNext()) {
            studentAtPosition = position.student;
            if (studentAtPosition.getStudentNum() == targetStudentNum) {
                break;
            }
            position = position.next;
        }
        return position;
    }

    // listeyi öğrenci numarasına göre artan sırada yazdıran metot.
    public void printInAscendingOrder() {
        System.out.println();
        header();
        restart(); // head ile başlanıyor.
        while (position != null) {
            position.student.showInfos();
            position = position.next;
        }
        System.out.println();
    }

    // listeyi öğrenci numarasına göre azalan sırada yazdıran metot.
    public void printInDescendingOrder() {
        System.out.println();
        header();
        Node currentPosition = tail; // tail ile başlanıyor.
        while (currentPosition != null) {
            currentPosition.student.showInfos();
            currentPosition = currentPosition.previous;
        }
        System.out.println();
    }

    // öğrenci bilgisi istenilen bazı kısımlarda anlaşılırlığı arttırmak için
    // kullanılan metot.
    private void header() {
        if (size() > 0) {
            System.out.println("Öğrenci No  Adı Soyadı          Telefon Numara(ları)sı");
            System.out.println("----------  ----------          ----------------------");
        }
    }

    // listeyi dolaşırken sonuna gelip gelmediğimizi kontrol etmek için kullanılan metot.
    private boolean hasNext() {
        return (position != null);
    }

    // liste ile ilgili dolaşma, silme veya ekleme gibi işlemlerde position 
    // değişkenini başa (head) almayı sağlayan metot.
    public void restart() {
        position = head;
    }

    // listenin kaç elemanlı olduğunu bulmayı sağlayan metot.
    public int size() {
        int count = 0;
        restart();
        while (position != null) {
            count++;
            position = position.next;
        }
        return count;
    }
}
