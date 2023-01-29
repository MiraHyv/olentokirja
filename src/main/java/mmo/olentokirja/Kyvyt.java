package mmo.olentokirja;

public record Kyvyt(int nopeus, int voima, int puhekyky) {
    Kyvyt() {
        this(4,4,4);
    }
}
