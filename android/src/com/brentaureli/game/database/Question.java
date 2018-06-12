package com.brentaureli.game.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "question")
public class Question {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "category")
    private int category;
    @ColumnInfo(name = "question")
    private String question;
    @ColumnInfo(name = "answer_A")
    private String answerA;
    @ColumnInfo(name = "answer_B")
    private String answerB;
    @ColumnInfo(name = "correct_Answer")
    private int correctAnswer;

    public Question(int category, String question, String answerA, String answerB, int correctAnswer) {
        this.category = category;
        this.question = question;
        this.answerA = answerA;
        this.answerB = answerB;
        this.correctAnswer = correctAnswer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public static Question[] populateData() {
        return new Question[] {
                new Question(1, "Na ile typow dziela sie dane w JS?", "2", "4", 1),
                new Question(1, "Co jest typem prostym?", "boolean", "REGEXP", 1),
                new Question(1, "Co jest zlozonym typem danych?", "string", "array", 2),
                new Question(1, "Co jest prawda o typach zlozonych?", "wskazuja tylko na miejsce w pamieci", "maja przypisana wartosc", 1),
                new Question(1, "Czy JS wymaga deklarowania typow?", "tak", "nie", 2),
                new Question(1, "Ktory framework wprowadza do js typy?", "ScriptType", "TypeScript", 2),
                new Question(1, "Jaki bedzie wynik? const someVar = \"napis\" + 20", "\"napis 20\"", "undefined", 1),
                new Question(1, "Jaki bedzie wynik? const someVar = \"20\" + 1", "201", "undefined", 1),
                new Question(1, "Jaki bedzie wynik? const someVar = \"20a\" - 1", "\"20a-1\"", "Nan", 2),
                new Question(1, "Jaki bedzie wynik? const someVar = \"20\" - 1", "\"20-1\"", "19", 2),
                new Question(1, "Czy tablica w js jest obiektem?", "tak", "nie", 1),
                new Question(1, "Czy w js typ null zwraca obiekt?", "tak", "nie", 1),
                new Question(1, "Czy nazwa zmiennej moze zaczynac sie od cyfry?", "tak", "nie", 2),
                new Question(1, "Czy nazwa zmiennej moze zaczynac sie od podkreslenia?", "tak", "nie", 1),
                new Question(1, "Co jest w standardzie ES6?", "var", "let", 2),
                new Question(1, "Co to jest hoisting zmiennych?", "przenoszenie deklaracji na poczatek skryptu", "udostepnianie zmiennych dla innych plikow", 1),
                new Question(1, "Zmienne zadeklarowane przez var maja zasieg?", "blokowy", "funkcyjny", 2),
                new Question(1, "Czy hoisting zmiennych dziala na let i const?", "tak", "nie", 2),
                new Question(1, "Do czego sluzy metoda charAt()? Do: ", "wstawienia znaku na danej pozycji", "pobrania znaku na danej pozycji", 1),
                new Question(1, "Czy metoda indexOf() podaje numer ostatniego wystapienia podtekstu?", "tak", "nie", 2),
                new Question(1, "Czy metoda split dzieli teskt na czesci, zwracajac tablice?", "tak", "nie", 1),
                new Question(1, "Czego trzeba uzyc aby wyswietlic zawartosc tablicy?", "instrukcji warunkowej", "petli", 2),
                new Question(1, "Ktora funkcja wyswietli zawartosc obiektu w konsoli?", "console.log", "console.display", 1),
                new Question(1, "Czy na obiekcie mozna wykonac funkcjie forEach?", "tak", "nie", 2),
                new Question(1, "Ktora funkcja zlaczymy 2 tablice?", "join()", "concat()", 2),
                new Question(1, "Co oznacza symbol \"...\"?", "spread syntax", "rest parameter", 1),
                new Question(1, "Ktora funkcja zmieni kontekst wywolania this?", "call()", "show()", 1),
                new Question(1, "Jakiego typu jest pusta tablica?", "true", "false", 1),
                new Question(1, "Czy funkcja map() operuje na tablicach?", "tak", "nie", 1),
                new Question(1, "Do czego sluzy session storage? Do: ", "zapisywania danych na nieokreslony czas", "obslugi danch podczas trwania sesji", 2),

                new Question(2, "Co wyswietli komunikat w konsoli?", "cin", "cout", 2),
                new Question(2, "Czego nie robi konstruktor?", "tworzy obiekt", "niszczy obiekt", 2),
                new Question(2, "Na jakie podst. grupy dziela sie typy w C++?", "stringi i liczby", "calkowite i zmiennoprzecinkowe", 2),
                new Question(2, "Czy typ long jest typu calkowitego?", "tak", "nie", 2),
                new Question(2, "Czy istnieje taki typ jak unsigned long long?", "tak", "nie", 1),
                new Question(2, "Ile bitow zajmuje typ float?", "64", "32", 2),
                new Question(2, "Trzy struktury zlozone to: ", "struktura, funkcja i tablica", "tablica, struktura i wskaznik", 2),
                new Question(2, "Czy struktura moze zawierac wiele wartosci roznych typow?", "tak", "nie", 1),
                new Question(2, "Czy instanieje taki typ jak unia?", "tak", "nie", 1),
                new Question(2, "Czym jest zakonczony lancuch znakow?", "ostatnim znakiem stringa", "nullem", 2),
                new Question(2, "Jak wyglada operator dereferencji wskaznika?", "->", "-<", 1),
                new Question(2, "Klasa vector jest alternatywa dla?", "tablic dynamicznych", "struktur", 1),
                new Question(2, "Zmienne statyczne instenia caly czas dzialania programu?", "Prawda", "Falsz", 1),
                new Question(2, "Tablice i wskazniki sa blisko ze soba spokrewnione?", "Prawda", "Falsz", 1),
                new Question(2, "jakie petle istanieja w c++?", "for, while, forEach", "for, while, do while", 2),
                new Question(2, "Wyrazenia relacyjne sluza do: ?", "odczytywania znaku wejsciowego", "porownywania wartosci", 2),
                new Question(2, "Co to za operator \"?:\"?", "operator wyboru", "operator bitowy", 1),
                new Question(2, "Zmienna referencyjna jest uzywana jako: ?", "parametr funkcji", "przypisanie jednej zmiennej do drugiej", 1),
                new Question(2, "Aby uzyc funkcji TRZEBA: ", "zdefiniowac i podac prototyp", "Odp. A + wywolac", 2),
                new Question(2, "Prototyp funkcji nie opisuje interfejsu funkji?", "Prawda", "Falsz", 2),
                new Question(2, "Domysle parametry sa przekazywane przez wartosc?", "Prawda", "Falsz", 1),
                new Question(2, "Co to funkcja rekurencyjna?", "wywolanie do niej samej", "funkcja wywolana wewnatrz innej funkcji", 1),
                new Question(2, "W prototypach funkcji nie mozna definiowac wartosci domyslnych parametrow?", "Prawda", "Falsz", 2),
                new Question(2, "Szablony funkcji automatyzuja proces przeciazania funkcji?", "Prawda", "Falsz", 1),
                new Question(2, "Czy polimorfizm funkcji i przeciazanie funkcji to to samo?", "tak", "nie", 1),
                new Question(2, "Identyfikatory sluza do: ", "identyfikowania funkcji", "redukcji ryzyka kolizi nazw", 2),
                new Question(2, "Identyfikator z przestrzeni nazw udostepniac mozna m.in?", "operatorem zasiegu", "nie trzeba udostepniac", 1),
                new Question(2, "Czym jest klasa?", "definicja typu okreslonego przez uzytkownika", "innym rodzajem funkcji", 1),
                new Question(2, "Klasa nie jest typem wlasnym?", "Prawda", "Falsz", 2),
                new Question(2, "Klasy nadaja sie do opisywania ATD?", "Prawda", "Falsz", 1),

                new Question(3, "Java posiada typ unsigned?", "Prawda", "Falsz", 2),
                new Question(3, "Istnieja tu tzw. klasy oslonowe?", "Prawda", "Falsz", 1),
                new Question(3, "Zmienne finalne moga byc zainicjowane wiele razy?", "Prawda", "Falsz", 2),
                new Question(3, "String nie jest typem obiektowym?", "Prawda", "Falsz", 2),
                new Question(3, "Do czego sluzy slowo finalne finaL? Do: ", "deklarowania zmiennych finalnych", "konczenia funkcji", 1),
                new Question(3, "Import statyczny pozwala pomijac przedrostki bibliotek?", "Prawda", "Falsz", 1),
                new Question(3, "Rzutowanie jest tak samo jak konwersja niejawne?", "Prawda", "Falsz", 2),
                new Question(3, "Do wyswietlanie danych sluzy: ", "System.out.print(dane)", "System.in(dane)", 1),
                new Question(3, "Do pobrania danych od uzytkownika sluzy: ", "System.out.print(dane)", "new Sacaner(System.in)", 2),
                new Question(3, "Funkcja nextInt() odczytuj kolejna liczbe calkowita?", "Prawda", "Falsz", 2),
                new Question(3, "Nazwy stalych piszemy wielkimi literami?", "Prawda", "Falsz", 1),
                new Question(3, "W javie wielkosc liter nie ma znaczenia?", "Prawda", "Falsz", 2),
                new Question(3, "W javie nie istnieja tablice wielowymiarowe?", "Prawda", "Falsz", 2),
                new Question(3, "Zakres widocznosci zmiennych wyznaczaja zazwyczaj {}?", "Prawda", "Falsz", 1),
                new Question(3, "Instrukcja sterujaca nie jest: ", "if", "while", 1),
                new Question(3, "Nazwy zmiennych lokalnych i globalnych nie moga byc identyczne?", "Prawda", "Falsz", 2),
                new Question(3, "Slowo public to modyfikator dostepu?", "Prawda", "Falsz", 1),
                new Question(3, "Slowo new sluzy m.in do: ", "tworzenia konstruktora", "deklaracji nowej funkcji", 1),
                new Question(3, "W javie kazda klasa rozszerza klase Object?", "Prawda", "Falsz", 1),
                new Question(3, "Czy mozna przekszatlcic tablice na obiekt bez rzutowania?", "Tak", "Nie", 1),
                new Question(3, "Czy typ Char moze byc przypisay do typu Double bez rzutowania?", "Tak", "Nie", 2),
                new Question(3, "Czy tablice integer mozna rzutowac na tablice byte?", "Tak", "Nie", 1),
                new Question(3, "Czy klasa moze dziedziczyc kilka interfejsow?", "Tak", "Nie", 1),
                new Question(3, "Czy lista ArrayList jest synchronizowana?", "Tak", "Nie", 2),
                new Question(3, "Co zawiera stos?", "instancje obiektow i stos wywolan", "zmienne lokalne i stos wywolan", 1),
                new Question(3, "jak mozna przerwac dzialanie petli for?", "rzucajac wyjatek", "goto", 1),
                new Question(3, "Jak wybrac pojedynczy znak z obiektu String? Metoda: ", "getChar()", "codePointAt()", 2),
                new Question(3, "Czy mozna zmienic istniejacy String?", "Tak", "Nie", 2),
                new Question(3, "Czy klasa moze dziedziczyc z kilku klas jednoczesnie?", "Tak", "Nie", 2),
                new Question(3, "Ktora z klas nie wystepuje w JDK?", "HashSet", "LinkedSet", 2),
        };
    }
}
