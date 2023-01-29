package mmo.olentokirja;

public record Olento (
    String tunniste,
    String nimi,
    String laji,
    Paivamaara viimeksiNahty,
    Kyvyt kyvyt
) {
    final static String OLETUS_NIMI = "tuntematon";
    final static String OLETUS_LAJI = "örkki";
    final static String[] LAJIT = {
        "örkki", "ihminen", "koira", "tuhon silmä"
    };
    
    public Olento(String uusiTunniste) {
        this(
            uusiTunniste, 
            OLETUS_NIMI,  
            OLETUS_LAJI, 
            new Paivamaara(), 
            new Kyvyt()
        );
    }

    public Olento(String uusiTunniste, String uusiNimi) {
        this(
            uusiTunniste, 
            uusiNimi,  
            OLETUS_LAJI, 
            new Paivamaara(), 
            new Kyvyt()
        );        
    }
}
