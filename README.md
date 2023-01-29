# Olentokirja
MMO-tyyppiseen peliin tarkoitettu API, jolla voi tallentaa ja hakea pelin olentojen tietoja.

## Toiminta
Tietoja voi tallentaa tekemällä osoitteeseen ```/``` POST-pyynnön, joka sisältää tämän tyylisen JSON:n:
```
{
    "tunniste": "olento0",
    "nimi": "Pekka",
    "laji": "örkki",
    "viimeksiNahty": {
        "vuosi": 2023,
        "kuukausi": 1,
        "paiva": 30
    },
    "kyvyt":{
        "nopeus": 4,
        "voima": 4,
        "puhekyky": 100
    }
}
```
Tietoja voi hakea tekemällä GET-pyynnön esim. osoitteeseen ```/olento0```, missä osoite on sen olennon tunniste joka halutaan hakea.
