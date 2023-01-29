package mmo.olentokirja;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class OlentokirjaOhjain {

    Olentokirja kirja = new Olentokirja();

    @PostMapping("/")
    public ResponseEntity<String> postPyynto(@RequestBody Olento pyynnonSisalto) {
        kirja.lisaaOlento(pyynnonSisalto);
        URI sijainti = URI.create("/" + pyynnonSisalto.tunniste());
        String vastauksenSisalto = Muunto.tuotaJSON(pyynnonSisalto);
        return ResponseEntity.created(sijainti).body(vastauksenSisalto);
    }   

    @GetMapping("/{tunniste}")
    public String getPyynto(@PathVariable String tunniste, HttpServletResponse vastaus){
        if(kirja.olentoOnKirjassa(tunniste) == false) {
            throw new OlentoaEiOleVirhe();
        }
        Olento haettuOlento = kirja.hae(tunniste);
        vastaus.addHeader(
            "Content-Type", 
            "text/html; charset=utf8"
        );
        return Muunto.tuotaJSON(haettuOlento);
    }
}
