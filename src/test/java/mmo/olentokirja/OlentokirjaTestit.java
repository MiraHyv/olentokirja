package mmo.olentokirja;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OlentokirjaTestit {
    @Test
    void olentoOnKirjassa_palauttaaFalseTyhjallaTunnisteella() {
        Olentokirja kirja = new Olentokirja();
        assertFalse(kirja.olentoOnKirjassa(""));
    }

    @Test
    void olentoOnKirjassa_TrueVainKunOnLisattySamallaTunnisteella() {
        Olentokirja kirja = new Olentokirja();
        kirja.lisaaOlento(new Olento("olento0"));
        assertTrue(kirja.olentoOnKirjassa("olento0"));
        assertFalse(kirja.olentoOnKirjassa("olento1"));
    }

    @Test
    void haeOlentoPalauttaaLisatynOlennon() {
        Olentokirja kirja = new Olentokirja();
        Olento o = new Olento(
            "olento512",
            "Jenna",
            "koira",
            new Paivamaara(1984, 10, 2),
            new Kyvyt(4, 4, 20)
        );
        kirja.lisaaOlento(o);
        Olento haettuOlento = kirja.hae("olento512");
        assertEquals(o, haettuOlento);
    }

    @Test
    void haeOlentoTuottaaVirheenJosHuonoTunniste() {
        Olentokirja kirja = new Olentokirja();
        assertThrows(OlentoaEiOleVirhe.class, () ->
            kirja.hae("huono tunniste")
        );
   }

    @Test 
    void lisaaOlento_KirjoittaaPaalle() {
        Olentokirja kirja = new Olentokirja();
        Olento o0 = new Olento(
            "olento0",
            "Aili",
            "ihminen",
            new Paivamaara(2000, 1, 1),
            new Kyvyt(4, 4, 100)
        );
        Olento o1 = new Olento(
            "olento0",
            "Sami",
            "tuhon silmä",
            new Paivamaara(2000, 1, 2),
            new Kyvyt(6, 0, 1000)
        );        
        kirja.lisaaOlento(o0);
        kirja.lisaaOlento(o1);
        Olento haettuOlento = kirja.hae("olento0");
        assertEquals(o1, haettuOlento);
    }

    @Test 
    void etsiTunnisteNimella_palauttaaOikeanTunnisteen() {
        Olentokirja kirja = new Olentokirja();
        Olento o0 = new Olento("olento0","Jukka");
        Olento o1 = new Olento("olento1","Henna");    
        kirja.lisaaOlento(o0);
        kirja.lisaaOlento(o1);
        String haettuTunniste = kirja.etsiTunnisteNimella("Henna");
        assertEquals("olento1", haettuTunniste);
    }

    @Test
    void nimiOnKirjassa_trueVainJosNimiOnKirjassa() {
        Olentokirja kirja = new Olentokirja();
        Olento o0 = new Olento("olento1", "Suvi");
        kirja.lisaaOlento(o0);
        assertFalse(kirja.nimiOnKirjassa("Marja"));
        assertTrue(kirja.nimiOnKirjassa("Suvi"));
    }
}