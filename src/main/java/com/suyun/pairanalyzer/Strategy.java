package com.suyun.pairanalyzer;

import java.util.Map;

/**
 * Created by IT on 2017/5/28.
 */
public interface Strategy {

    int ACCEPTED = 50;

    /**
     * Determine whether this strategy is suitable.
     *
     * @param s
     * @return true - if return value >= ACCEPTED, false - else.
     */
    int isSuitable(String s);

    /**
     * Parse to pairs
     *
     * @param s
     * @return
     */
    Map<String, String> parse(String s);
}
