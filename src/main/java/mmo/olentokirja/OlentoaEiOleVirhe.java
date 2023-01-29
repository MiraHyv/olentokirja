package mmo.olentokirja;

public class OlentoaEiOleVirhe extends RuntimeException {
    OlentoaEiOleVirhe() {
        super();
    }

    OlentoaEiOleVirhe(String viesti) {
        super(viesti);
    }
}
