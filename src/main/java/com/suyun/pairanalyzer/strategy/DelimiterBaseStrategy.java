package com.suyun.pairanalyzer.strategy;

import com.suyun.pairanalyzer.Strategy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Delimiter base strategy
 *
 * Created by IT on 2017/5/30.
 */
public class DelimiterBaseStrategy implements Strategy {

    /**
     * delimiters definition
     */
    private String[] delimiters;

    /**
     * separators definition
     */
    private String[] separators;

    /**
     * Current separator
     */
    private String separator;

    /**
     * separated items
     */
    private String[] items;


    public DelimiterBaseStrategy(String[] delimiters, String[] separators) {
        this.delimiters = delimiters;
        this.separators = separators;
    }

    @Override
    public int isSuitable(String s) {
        assert delimiters != null && delimiters.length > 0;
        assert separators != null && separators.length > 0;

        if (s == null || "".equals(s)) {
            return 0;
        }
        s = s.trim();

        for (String delimiter : delimiters) {
            String[] splits = s.split(delimiter);
            if (splits.length > 1) {
                for (String separator : separators) {

                    if(Arrays.stream(splits).allMatch(sp -> sp.split(separator).length >= 2)) {
                        this.separator = separator;
                        this.items = splits;
                        return ACCEPTED + 40;
                    }

                }
            }
        }

        return 0;
    }

    @Override
    public Map<String, String> parse(String s) {
        Map<String, String> result = new HashMap<>();
        if (items == null || items.length <= 1) {
            return result;
        }

        for (String item : items) {
            String[] pair = item.split(separator);
            if (pair.length >= 2) {
                result.put(pair[0], pair[1]);
            }
        }

        return result;
    }
}
