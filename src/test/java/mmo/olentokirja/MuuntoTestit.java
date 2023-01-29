package mmo.olentokirja;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MuuntoTestit {
    @Test
    void muotoileJSON_tulosEiRiipuValeistaTaiRivinvaihdoista() throws Exception{
        String json0 = """
                {
                    "tunniste":"olento0",
                    "nimi":"Jukka",
                    "laji": "tuhon silmä",
                    "viimeksiNahty":{"vuosi":2023,"kuukausi":1,"paiva":1},
                    "kyvyt":{"nopeus":10,"voima":0,"puhekyky":1000}
                }
                """;
        String json1 = """
{"tunniste":"olento0","nimi":"Jukka","laji":"tuhon silmä",\
"viimeksiNahty":{"vuosi":2023,"kuukausi":1,"paiva":1},\
"kyvyt":{"nopeus":10,"voima":0,"puhekyky":1000}}\
""";
        assertEquals(Muunto.muotoileJSON(json1), Muunto.muotoileJSON(json0));
    }

    @Test
    void tuotaJSON_paivamaara() {
        Paivamaara t0 = new Paivamaara(2023, 2, 17);
        Paivamaara t1 = new Paivamaara(1999, 12, 31);
        String oikeaVastaus0 = Muunto.muotoileJSON("""
            {"vuosi":2023, "kuukausi":2, "paiva":17}
        """);
        String oikeaVastaus1 = Muunto.muotoileJSON("""
            {"vuosi":1999,"kuukausi":12,"paiva":31}
        """);
        assertEquals(oikeaVastaus0, Muunto.tuotaJSON(t0));
        assertEquals(oikeaVastaus1, Muunto.tuotaJSON(t1));
    }

    @Test
    void tuotaJSON_kyvyt() {
        Kyvyt k0 = new Kyvyt(1, 2, 20);
        Kyvyt k1 = new Kyvyt(6, 4, 200);
        String oikeaVastaus0 = Muunto.muotoileJSON("""
            {"nopeus":1,"voima":2,"puhekyky":20}\
        """);
        String oikeaVastaus1 = Muunto.muotoileJSON("""
            {"nopeus":6,"voima":4,"puhekyky":200}\
        """);
        assertEquals(oikeaVastaus0, Muunto.tuotaJSON(k0));
        assertEquals(oikeaVastaus1, Muunto.tuotaJSON(k1));
    }

    @Test
    void tuotaJSON_olento() {
        Kyvyt k0 = new Kyvyt(4, 4, 100);
        Paivamaara t0 = new Paivamaara(2023, 1, 30);
        Olento o0 = new Olento("olento0", "Mauri", "örkki", t0, k0);
        Kyvyt k1 = new Kyvyt(3, 3, 90);
        Paivamaara t1 = new Paivamaara(2024, 2, 15);
        Olento o1 = new Olento("olento1", "Sanna", "ihminen", t1, k1);
        String oikeaVastaus0 = Muunto.muotoileJSON("""
            {"tunniste":"olento0","nimi":"Mauri","laji":"örkki","viimeksiNahty":
            {"vuosi":2023,"kuukausi":1,"paiva":30},"kyvyt":
            {"nopeus":4,"voima":4,"puhekyky":100}}
        """);

        String oikeaVastaus1 = Muunto.muotoileJSON("""
            {"tunniste":"olento1","nimi":"Sanna","laji":"ihminen","viimeksiNahty":
            {"vuosi":2024,"kuukausi":2,"paiva":15},"kyvyt":
            {"nopeus":3,"voima":3,"puhekyky":90}}
        """);        

        assertEquals(oikeaVastaus0, Muunto.tuotaJSON(o0));
        assertEquals(oikeaVastaus1, Muunto.tuotaJSON(o1));
    }
}
