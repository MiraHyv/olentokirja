package mmo.olentokirja;

public record Paivamaara (int vuosi, int kuukausi, int paiva) {
    Paivamaara() {
        this(2023,1,1);
    }
}
