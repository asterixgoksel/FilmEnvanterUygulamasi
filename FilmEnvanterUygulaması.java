import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

// Oyunculara ait bilgileri tutan "Actor" class'ı

class Actor {
    private String name;
    private String gender;
    private String nationality;

    public Actor(String name, String gender, String nationality) {
        this.name = name;
        this.gender = gender;
        this.nationality = nationality;
    }

    // Getter ve setter metodları

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }


    // toString metodu
    
    @Override
    public String toString() {
        return "(" + name + ", " + gender + ", " + nationality + ")";
    }
}

// Filme ait bilgileri tutan "Movie" class'ı

class Movie {
    private int year;
    private String title;
    private String genre;
    private String director;
    private ArrayList<Actor> actors;

    public Movie(int year, String title, String genre, String director, ArrayList<Actor> actors) {
        this.year = year;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
    }

    // Getter and setter metodları

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = actors;
    }


    // Dosyanın kaydedildikten sonra yazdırılması
    
    @Override
    public String toString() {
        String parantez = "{";
        for (Actor i: actors){
            parantez += i.toString();
        }
        parantez += "}";
            return year + ", " + title + ", " + genre + ", " + director + ", " + parantez;
    }
}

class Node {
    private Movie movie;
    Node previous;
    Node next;

    public Node(Movie movie) {
        this.movie = movie;
        this.previous = null;
        this.next = null;
    }

    // Getter ve setter metodları

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }



    @Override
    public String toString() {
        return movie.toString();
    }
}

class DoublyLinkedList {
    private Node head;
    private Node tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }
    
    // Getter ve setter metodları 
    
    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }


    // Boş listeye ilk filmin eklenmesi
    
    public void addToEmptyList(Movie movie) {
        Node newNode = new Node(movie);
        head = newNode;
        tail = newNode;
    }
    
    // "head" değişkeni kullanılarak filmlerin önden eklenmesi
    
    public void addToFront(Movie movie) {
        if (head == null) {
            addToEmptyList(movie);
            return;
        }

        Node newNode = new Node(movie);
        newNode.next = head;
        head.previous = newNode;
        head = newNode;
    }
    
    // "tail" değişkenleri kullanılarak filmlerin arkadan eklenmesi
    
    public void addToEnd(Movie movie) {
        if (tail == null) {
            addToEmptyList(movie);
            return;
        }

        Node newNode = new Node(movie);
        newNode.previous = tail;
        tail.next = newNode;
        tail = newNode;
    }
    
    // Filmlerin sırayla eklenmesi
    
    public void addInOrder(Movie movie) {
        if (head == null) {
            addToEmptyList(movie);
            return;
        }

        Node newNode = new Node(movie);
        Node current = head;

        while (current != null) {
            if (movie.getYear() < current.getMovie().getYear()) {
                if (current == head) {
                    addToFront(movie);
                    return;
                } else {
                    newNode.next = current;
                    newNode.previous = current.previous;
                    current.previous.next = newNode;
                    current.previous = newNode;
                    return;
                }
            } else if (movie.getYear() == current.getMovie().getYear()) {
                if (movie.getTitle().compareToIgnoreCase(current.getMovie().getTitle()) < 0) {
                    if (current == head) {
                        addToFront(movie);
                        return;
                    } else {
                        newNode.next = current;
                        newNode.previous = current.previous;
                        current.previous.next = newNode;
                        current.previous = newNode;
                        return;
                    }
                }
            }

            if (current.next == null) {
                break;
            }

            current = current.next;
        }

        addToEnd(movie);
    }
    
    // Filmlerin baştan başlanarak yazdırılması
    
    public void printFromHead() {
        Node current = head;

        while (current != null) {
            System.out.println(current.toString());
            current = current.next;
        }
    }
    
    // Filmlerin sondan başlanarak yazdırılması
    
    public void printFromTail() {
        Node current = tail;

        while (current != null) {
            System.out.println(current.toString());
            current = current.previous;
        }
    }
    
    // 2000 yılı öncesi filmlerin yazdırılması
    
    public void printMoviesBeforeYear(int year) {
        Node current = head;

        while (current != null) {
            if (current.getMovie().getYear() < year) {
                System.out.println(current.toString());
            }

            current = current.next;
        }
    }
    
    // Kullanıcının adını girdiği filmle ilgili bilgilerin silinmesi
    
    public boolean removeMovie(String title) {
        if (head == null) {
            return false;
        }

        Node current = head;

        while (current != null) {
            if (current.getMovie().getTitle().equalsIgnoreCase(title)) {
                if (current == head) {
                    head = current.next;
                }

                if (current == tail) {
                    tail = current.previous;
                }

                if (current.previous != null) {
                    current.previous.next = current.next;
                }

                if (current.next != null) {
                    current.next.previous = current.previous;
                }

                return true;
            }

            current = current.next;
        }

        return false;
    }
    
    // Kullanıcının istediği işlemleri yaptıktan sonra en son dosyanın kaydedilmesi
    
    public void saveToFile(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            Node current = head;

            while (current != null) {
                writer.write(current.toString());
                writer.newLine();
                current = current.next;
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


// Film envanterine ait işlemlerin kullanıcı tarafından yapılması 

public class FilmEnvanterUygulaması {
        public static void main(String[] args) {
        DoublyLinkedList movieList = new DoublyLinkedList();
        Scanner scanner = new Scanner(System.in);

        try {
            File file = new File("bilgiler.txt");
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] data = line.split(",");

                int year = parseInt(data[0].trim());
                String title = data[1].trim();
                String genre = data[2].trim();
                String director = data[3].trim();

                int startIndex = line.indexOf("{(") +1;
                int endIndex = line.indexOf(")}") + 1;

                String actorList = line.substring(startIndex, endIndex);

                String[] actorData = actorList.split("\\)\\(");

                ArrayList<Actor> actors = new ArrayList<>();


                for (String actorString : actorData) {

                    if (!actorString.trim().isEmpty()) {
                        String cleanPart = actorString.replaceAll("[()]", "");
                        String[] actorInfo = cleanPart.split(",");
                        String actorName = actorInfo[0].trim();
                        String actorGender = actorInfo[1].trim();
                        String actorNationality = actorInfo[2].trim();
                        actors.add(new Actor(actorName, actorGender, actorNationality));
                    }
                }


                Movie movie = new Movie(year, title, genre, director, actors);
                movieList.addInOrder(movie);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int choice = 0;

        while (choice != 8) {
            
            // Menünün yazdırılması
            
            System.out.println("Seçenekler:");
            System.out.println("1) Dosyadan veri oku");
            System.out.println("2) Film ekle");
            System.out.println("3) Film bilgilerini görüntüle");
            System.out.println("4) Film sil");
            System.out.println("5) Tüm kayitlari baştan yazdir");
            System.out.println("6) Tüm kayitlari sondan yazdir");
            System.out.println("7) 2000 yilindan önce yapılan filmleri yazdir");
            System.out.println("8) Çikiş");

            System.out.print("Seçiminizi yapin: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    
                    // Dosyadan veri okuma işlemi
                    
                    movieList.saveToFile("bilgiler.txt");
                    System.out.println("Dosyadan okuma ve envanter oluşturma tamamlandi.");
                    break;
                case 2:
                    
                    // Film ekleme işlemi
                    
                    System.out.println("Film bilgilerini giriniz.");

                    Boolean isValidYear = false;
                    int year = 0;
                    while(!isValidYear){
                        try {
                            System.out.print("Yil: ");
                            year = scanner.nextInt();
                            isValidYear = true;
                        } catch (Exception ex){
                            System.out.println("Geçerli bir yil giriniz!");
                            scanner.nextLine();
                        }
                    }


                    scanner.nextLine(); // Integer veri girişinden sonra newline karakterini temizlemek için.
                    System.out.print("Başlık: ");
                    String title = scanner.nextLine();
                    System.out.print("Tür: ");
                    String genre = scanner.nextLine();
                    System.out.print("Yönetmen: ");
                    String director = scanner.nextLine();
                    
                    // Bir filmi oluştururken aktör bilgilerini eklemek için daha fazla girdi alınabilir.
                    // Şimdilik en başta kullanmak için boş bir aktör listesi oluşturuluyor.
                    
                    ArrayList<Actor> actors = new ArrayList<>();
                    String devam = "E";
                    
                    // Eklenen filme ait daha fazla oyuncunun olup olmadığının kullanıcıya sorulma işlemi
                    
                    while (devam.equalsIgnoreCase("E")) {
                        System.out.print("Oyuncuyu giriniz \n");

                        System.out.print("İsim: ");
                        String name = scanner.nextLine();
                        System.out.print("Cinsiyet: ");
                        String gender = scanner.nextLine();
                        System.out.print("Milliyet: ");
                        String nationality = scanner.nextLine();
                        Actor actor = new Actor(name, gender, nationality);
                        actors.add(actor);
                        System.out.print("Başka oyuncu var mi?:(E,H)");
                        devam = scanner.nextLine();
                    }

                    Movie movie = new Movie(year, title, genre, director, actors);
                    movieList.addInOrder(movie);
                    System.out.println("Film eklendi.");
                    break;


                case 3:
                    
                    // Film bilgilerini görüntüleme işlemi
                    
                    System.out.print("Film adini girin: ");
                    scanner.nextLine();
                    String movieTitle = scanner.nextLine();

                    Node curr = movieList.getHead();
                    boolean found = false;

                    while (curr != null) {
                        if (curr.getMovie().getTitle().equalsIgnoreCase(movieTitle)) {
                            System.out.println(curr.getMovie().toString());
                            found = true;
                            break;
                        }

                        curr = curr.getNext();
                    }

                    if (!found) {
                        System.out.println("Film bulunamadi.");
                    }
                    break;
                    
                case 4:
                    
                    // Film silme işlemi
                    
                    scanner.nextLine();
                    System.out.print("Film adini girin: ");
                    String movieTitleToRemove = scanner.nextLine();
                    movieList.removeMovie(movieTitleToRemove);
                    System.out.println("Film başariyla silindi.");
                    break;
                case 5:
                    
                    // Tüm kayıtları baştan yazdırma işlemi

                    System.out.println("Tüm filmler:");
                    movieList.printFromHead();
                    break;
                case 6:
                    
                    // Tüm kayıtları sondan yazdırma işlemi

                    System.out.println("Tüm filmler:");
                    movieList.printFromTail();
                    break;
                case 7:
                    
                    // 2000 yılından önce yapılan filmleri yazdırma işlemi
                    
                    System.out.println("2000'den önce yapilmiş filmler:");
                    movieList.printMoviesBeforeYear(2000);
                    break;
                case 8:
                    // Dosyanın kaydedilmesi ve uygulamadan çıkış işlemi
                    movieList.saveToFile("bilgiler.txt");
                    System.out.println("Envanter kaydedildi. Çikiliyor...");
                    break;
                default:
                    
                    // 1-8 arası tam sayılar dışında geçersiz bir değer girilmesi sonucu uyarı verilme işlemi
                    
                    System.out.println("Geçersiz seçim! Tekrar deneyin.");
            }
        }
        
        // "bilgiler.txt" dosyasının gerekli işlemlerden sonra kapatılması
        
        scanner.close();

        // Çift bağlı liste son halini "bilgiler.txt" dosyasına kaydetme işlemi
        
        movieList.saveToFile("bilgiler.txt");
    }
}