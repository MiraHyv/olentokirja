package mmo.olentokirja;

import java.util.HashMap;

public class Olentokirja {
    HashMap<String, Olento> olennot;
    
    public Olentokirja() {
        olennot = new HashMap<String, Olento>();
    }

    public boolean olentoOnKirjassa(String tunniste) {
        if(olennot.containsKey(tunniste)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void lisaaOlento(Olento uusiOlento) {
        olennot.put(uusiOlento.tunniste(), uusiOlento);
    }

    public Olento hae(String tunniste) {
        Olento tulos = olennot.get(tunniste);
        if(tulos == null) {
            throw new OlentoaEiOleVirhe();
        }
        return tulos;
    }

    public String etsiTunnisteNimella(String nimi) {
        for(var entry : olennot.entrySet()) {
            if(entry.getValue().nimi() == nimi) {
                return entry.getKey();
            }
        }
        throw new OlentoaEiOleVirhe();
    }

    public boolean nimiOnKirjassa(String nimi) {
        for(var entry : olennot.entrySet()) {
            if(entry.getValue().nimi() == nimi) {
                return true;
            }
        }
        return false;
    }
}
