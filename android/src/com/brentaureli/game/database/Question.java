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
                new Question(1, "Na ile typów dzielą się dane w JS?", "2", "4", 1),
                new Question(1, "Co jest typem prostym?", "boolean", "REGEXP", 1),
                new Question(1, "Co jest złożonym typem danych?", "string", "array", 2),
                new Question(1, "Co jest prawdą o typach złożonych?", "wskazują tylko na miejsce w pamięci", "mają przypisaną wartość", 1),
                new Question(1, "Czy JS wymaga deklarowania typów?", "tak", "nie", 2),
                new Question(1, "Który framework wprowadza do js typy?", "ScriptType", "TypeScript", 2),
                new Question(1, "Jaki będzie wynik? const someVar = \"napis\" + 20", "\"napis 20\"", "undefined", 1),
                new Question(1, "Jaki będzie wynik? const someVar = \"20\" + 1", "201", "undefined", 1),
                new Question(1, "Jaki będzie wynik? const someVar = \"20a\" - 1", "\"20a-1\"", "Nan", 2),
                new Question(1, "Jaki będzie wynik? const someVar = \"20\" - 1", "\"20-1\"", "19", 2),
                new Question(1, "Czy tablica w js jest obiektem?", "tak", "nie", 1),
                new Question(1, "Czy w js typ null zwraca obiekt?", "tak", "nie", 1),
                new Question(1, "Czy nazwa zmiennej może zaczynac sie od cyfry?", "tak", "nie", 2),
                new Question(1, "Czy nazwa zmiennej może zaczynać się od podkreślenia?", "tak", "nie", 1),
                new Question(1, "Co jest w standardzie ES6?", "var", "let", 2),
                new Question(1, "Co to jest hoisting zmiennych?", "przenoszenie deklaracji na początek skryptu", "udostępnianie zmiennych dla innych plików", 1),
                new Question(1, "Zmienne zadeklarowane przez var mają zasięg?", "blokowy", "funkcyjny", 2),
                new Question(1, "Czy hoisting zmiennych działa na let i const?", "tak", "nie", 2),
                new Question(1, "Do czego służy metoda charAt()? Do: ", "wstawienia znaku na danej pozycji", "pobrania znaku na danej pozycji", 1),
                new Question(1, "Czy metoda indexOf() podaje numer ostatniego wystąpienia podtekstu?", "tak", "nie", 2),
                new Question(1, "Czy metoda split dzieli teskt na części, zwracając tablicę?", "tak", "nie", 1),
                new Question(1, "Czego trzeba użyć aby wyświetlić zawartość tablicy?", "instrukcji warunkowej", "pętli", 2),
                new Question(1, "Która funkcja wyświetli zawartość obiektu w konsoli?", "console.log", "console.display", 1),
                new Question(1, "Czy na obiekcie można wykonać funkcjie forEach?", "tak", "nie", 2),
                new Question(1, "Którą funkcją złączymy 2 tablice?", "join()", "concat()", 2),
                new Question(1, "Co oznacza symbol \"...\"?", "spread syntax", "rest parameter", 1),
                new Question(1, "Która funkcja zmieni kontekst wywołania this?", "call()", "show()", 1),
                new Question(1, "Jakiego typu jest pusta tablica?", "true", "false", 1),
                new Question(1, "Czy funkcja map() operuje na tablicach?", "tak", "nie", 1),
                new Question(1, "Do czego służy session storage? Do: ", "zapisywania danych na nieokreślony czas", "obsługi danch podczas trwania sesji", 2),

                new Question(2, "Co wyświetli komunikat w konsoli?", "cin", "cout", 2),
                new Question(2, "Czego nie robi konstruktor?", "tworzy obiekt", "niszczy obiekt", 2),
                new Question(2, "Na jakie podst. grupy dzielą sie typy w C++?", "stringi i liczby", "całkowite i zmiennoprzecinkowe", 2),
                new Question(2, "Czy typ long jest typu całkowitego?", "tak", "nie", 2),
                new Question(2, "Czy istnieje taki typ jak unsigned long long?", "tak", "nie", 1),
                new Question(2, "Ile bitów zajmuje typ float?", "64", "32", 2),
                new Question(2, "Trzy struktury złożone to: ", "struktura, funkcja i tablica", "tablica, struktura i wskaźnik", 2),
                new Question(2, "Czy struktura może zawierać wiele wartości różnych typów?", "tak", "nie", 1),
                new Question(2, "Czy instanieje taki typ jak unia?", "tak", "nie", 1),
                new Question(2, "Czym jest zakończony łańcuch znaków?", "ostatnim znakiem stringa", "nullem", 2),
                new Question(2, "Jak wygląda operator dereferencji wskażnika?", "->", "-<", 1),
                new Question(2, "Klasa vector jest alternatywą dla?", "tablic dynamicznych", "struktur", 1),
                new Question(2, "Zmienne statyczne instenią cały czas działania programu?", "Prawda", "Fałsz", 1),
                new Question(2, "Tablice i wskaźniki są blisko ze sobą spokrewnione?", "Prawda", "Fałsz", 1),
                new Question(2, "jakie pętle istanieją w c++?", "for, while, forEach", "for, while, do while", 2),
                new Question(2, "Wyrażenia relacyjne służa do: ?", "odczytywania znaku wejściowego", "porównywania wartości", 2),
                new Question(2, "Co to za operator \"?:\"?", "operator wyboru", "operator bitowy", 1),
                new Question(2, "Zmienna referencyjna jestużywana jako: ?", "parametr funkcji", "przypisanie jednej zmiennej do drugiej", 1),
                new Question(2, "Aby użyć funkcji TRZEBA: ", "zdefiniować i podać prototyp", "Odp. A + wywołać", 2),
                new Question(2, "Prototyp funkcji nie opisuje interfejsu funkji?", "Prawda", "Fałsz", 2),
                new Question(2, "Domyśle parametry są przekazywane przez wartość?", "Prawda", "Fałsz", 1),
                new Question(2, "Co to funkcja rekurencyjna?", "wywołanie do niej samej", "funkcja wywołana wewnątrz innej funkcji", 1),
                new Question(2, "W prototypach funkcji nie można definiować wartości domyślnych parametrów?", "Prawda", "Fałsz", 2),
                new Question(2, "Szablony funkcji automatyzują proces przeciążania funkcji?", "Prawda", "Fałsz", 1),
                new Question(2, "Czy polimorfizm funkcji i przeciązanie funkcji to to samo?", "tak", "nie", 1),
                new Question(2, "Identyfikatory służa do: ", "identyfikowania funkcji", "redukcji ryzyka kolizi nazw", 2),
                new Question(2, "Identyfikator z przestrzeni nazw udostępniać można m.in?", "operatorem zasięgu", "nie tzreba udostępniać", 1),
                new Question(2, "Czym jest klasa?", "definicją typu okreslonego przez użytkownika", "innym rodzajem funkcji", 1),
                new Question(2, "Klasa nie jest typem własnym?", "Prawda", "Fałsz", 2),
                new Question(2, "Klasy nadają się do opisywania ATD?", "Prawda", "Fałsz", 1),

                new Question(3, "Java posiada typ unsigned?", "Prawda", "Fałsz", 2),
                new Question(3, "Istnieją tu tzw. klasy osłonowe?", "Prawda", "Fałsz", 1),
                new Question(3, "Zmienne finalne mogą być zainicjowane wiele razy?", "Prawda", "Fałsz", 2),
                new Question(3, "String nie jest typem obiektowym?", "Prawda", "Fałsz", 2),
                new Question(3, "Do czego służy slowo finalne finaL? Do: ", "deklarowania zmiennych finalnych", "kończenia funkcji", 1),
                new Question(3, "Import statyczny pozwala pomijać przedrostki bibliotek?", "Prawda", "Fałsz", 1),
                new Question(3, "Rzutowanie jest tak samo jak konwersja niejawne?", "Prawda", "Fałsz", 2),
                new Question(3, "Do wyświetlanie danych służy: ", "System.out.print(dane)", "System.in(dane)", 1),
                new Question(3, "Do pobrania danych od użytkownika słuzy: ", "System.out.print(dane)", "new Sacaner(System.in)", 2),
                new Question(3, "Funkcja nextInt() odczytuj kolejną liczbę całkowitą?", "Prawda", "Fałsz", 2),
                new Question(3, "Nazwy stałych piszemy wielkimi literami?", "Prawda", "Fałsz", 1),
                new Question(3, "W javie wielkość liter nie ma znaczenia?", "Prawda", "Fałsz", 2),
                new Question(3, "W javie nie istnieją tablice wielowymiarowe?", "Prawda", "Fałsz", 2),
                new Question(3, "Zakres widoczności zmiennych wyznaczają zazwyczaj {}?", "Prawda", "Fałsz", 1),
                new Question(3, "Instrukcją sterującą nie jest: ", "if", "while", 1),
                new Question(3, "Nazwy zmiennych lokalnych i globalnych nie mogą byc identyczne?", "Prawda", "Fałsz", 2),
                new Question(3, "Słowo public to modyfikator dostępu?", "Prawda", "Fałsz", 1),
                new Question(3, "Słowo new służy m.in do: ", "tworzenia konstruktora", "deklaracji nowej funkcji", 1),
                new Question(3, "W javie każda klasa rozszerza klase Object?", "Prawda", "Fałsz", 1),
                new Question(3, "Czy można przekszatłcić tablicę na obiekt bez rzutowania?", "Tak", "Nie", 1),
                new Question(3, "Czy typ Char może być przypisay do typu Double bez rzutowania?", "Tak", "Nie", 2),
                new Question(3, "Czy tablice integer można rzutować na tablice byte?", "Tak", "Nie", 1),
                new Question(3, "Czy klasa może dziedziczyc kilka interfejsów?", "Tak", "Nie", 1),
                new Question(3, "Czy lista ArrayList jest synchronizowana?", "Tak", "Nie", 2),
                new Question(3, "Co zawiera stos?", "instancje obiektów i stos wywołań", "zmienne lokalne i stos wywołań", 1),
                new Question(3, "jak można przerwać działanie pętli for?", "rzucając wyjątek", "goto", 1),
                new Question(3, "Jak wybrać pojedyńczy znak z obiektu String? Metodą: ", "getChar()", "codePointAt()", 2),
                new Question(3, "Czy można zmienić istniejący String?", "Tak", "Nie", 2),
                new Question(3, "Czy klasa może dziedziczyć z kilku klas jednocześnie?", "Tak", "Nie", 2),
                new Question(3, "Która z klas nie występuje w JDK?", "HashSet", "LinkedSet", 2),
        };
    }
}
