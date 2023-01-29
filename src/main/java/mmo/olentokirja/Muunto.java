package mmo.olentokirja;

import com.google.gson.Gson;

public class Muunto{
    public static <T> String tuotaJSON(T olio) {
        Gson g = new Gson();
        return g.toJson(olio);
    }

    public static String muotoileJSON(String jsonStr) {
        String tulos = jsonStr
            .replace("\n", "")
            .replace(" ", "");
        return tulos;
    }
}
