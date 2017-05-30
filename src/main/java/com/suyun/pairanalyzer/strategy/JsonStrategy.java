package com.suyun.pairanalyzer.strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suyun.pairanalyzer.Strategy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IT on 2017/5/28.
 */
public class JsonStrategy implements Strategy {

    private ObjectMapper mapper = new ObjectMapper();

    private Map<String, String> map;

    public int isSuitable(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        s = s.trim();
        if (!(s.startsWith("{") && s.endsWith("}"))) {
            map = new HashMap<>();
            return 0;
        }
        try {
            map = mapper.readValue(s, Map.class);
            return ACCEPTED + 50;
        } catch (IOException e) {
            map = new HashMap<>();
            return 0;
        }
    }

    public Map<String, String> parse(String s) {
        return map;
    }


}
