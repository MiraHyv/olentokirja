package mmo.olentokirja;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class OlentokirjaOhjainTestit {
    @Autowired
    private MockMvc mvc;

    @Test
    void post_vastausOn201jaOikeaURIjaLuotuAsia() throws Exception {
        String sisalto = Muunto.muotoileJSON("""
            {"tunniste":"olento0","nimi":"Mauri","laji":"örkki",
            "viimeksiNahty":{"vuosi":2023,"kuukausi":1,"paiva":30},
            "kyvyt":{"nopeus":4,"voima":4,"puhekyky":100}}
        """);
        MvcResult vastaus = mvc.perform(MockMvcRequestBuilders
            .post("/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(sisalto)
        ).andExpect(status().isCreated()).andReturn();
        String vastauksenHeader = vastaus.getResponse().getHeader("location");
        assertEquals("/olento0", vastauksenHeader);
        String vastauksenSisalto = vastaus.getResponse().getContentAsString();
        assertEquals(sisalto, vastauksenSisalto);
    }

    @Test
    void get_vastausOn200jaOikeaJSON_josSeOnPostattu() throws Exception {
        String sisalto = Muunto.muotoileJSON("""
            {"tunniste":"olento0","nimi":"Liisa","laji":"örkki",
            "viimeksiNahty":{"vuosi":2023,"kuukausi":2,"paiva":21},
            "kyvyt":{"nopeus":5,"voima":4,"puhekyky":100}}
        """);
        mvc.perform(MockMvcRequestBuilders
            .post("/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(sisalto)
        ).andExpect(status().isCreated());
        
        MvcResult palaute = mvc.perform(MockMvcRequestBuilders
            .get("/olento0")
            .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn();

        assertEquals(sisalto, palaute.getResponse().getContentAsString());
    }

    @Test
    void get_vastausOn404_kunPyydettyaTunnistettaEiOle() throws Exception {
        mvc.perform(MockMvcRequestBuilders
            .get("/olento1000")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound());
    }
}
