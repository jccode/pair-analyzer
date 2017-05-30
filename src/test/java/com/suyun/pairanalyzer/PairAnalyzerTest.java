package com.suyun.pairanalyzer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by IT on 2017/5/27.
 */
public class PairAnalyzerTest {

    PairAnalyzer pairAnalyzer = PairAnalyzer.getInstance();

    @Test
    public void foo() {
        System.out.println("foo");
        assertTrue(true);
    }

    @Test
    public void jsonTest() throws IOException {
        String s = "{\"bit0\":\"Pump\",\"bit1\":\"Mass\",\"bit2\":\"MAF\",\"bit3\":\"PowerOn\",\"bit4\":\"Wrong\",\"bit5\":\"Plausibility\",\"bit6\":\"Temperature\",\"bit7\":\"Over\"}";
        ObjectMapper mapper = new ObjectMapper();
        Map map = mapper.readValue(s, Map.class);
        System.out.println(map);
    }

    @Test
    public void crlfTest() {
        String s = "0=未拉手刹\n" +
                "1=已拉手刹";
        System.out.println(s.indexOf("\n"));
        System.out.println(s.indexOf("\r"));
        System.out.println(s.indexOf("\n\r"));
        System.out.println(Arrays.toString(s.split("\n")));
    }

    @Test
    public void parseTest() {
        String[] ss = new String[] {
                "0=未拉手刹\n" +
                        "1=已拉手刹",
                "{\"bit0\":\"Pump\",\"bit1\":\"Mass\",\"bit2\":\"MAF\",\"bit3\":\"PowerOn\",\"bit4\":\"Wrong\",\"bit5\":\"Plausibility\",\"bit6\":\"Temperature\",\"bit7\":\"Over\"}",
                "0=normal\n" +
                        "1=alarm",
                "-40~210",
                "{\"0\":\"Motor\",\"Direction\\n2\":\"Motor\",\"Off\\n1\":\"Motor\"}",
                "0=normal;1=alarm;2=fault",
                "0=normal,1=alarm,2=fault",
                "-40~210",
                "{\"0\":\"静止\",\"1\":\"纯电动\",\"2\":\"混合动力\",\"3\":\"行车充电\",\"4\":\"制动回馈\"}"
        };

        for (String s : ss) {
            Map<String, String> map = pairAnalyzer.parse(s);
            System.out.println(s);
            System.out.println("----------------");
            System.out.println(map);
            System.out.println("========================");
        }
    }
}