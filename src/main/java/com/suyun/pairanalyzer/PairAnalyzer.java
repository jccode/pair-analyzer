package com.suyun.pairanalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Pair analyzer
 *
 * Created by IT on 2017/5/27.
 */
public class PairAnalyzer {

    private static PairAnalyzer instance;

    private List<Strategy> strategies;

    private PairAnalyzer() {
        strategies = new ArrayList<>();
        strategies.add(Strategies.JSON);
        strategies.add(Strategies.CRLF);
        strategies.add(Strategies.INLINE);
    }

    public static PairAnalyzer getInstance() {
        if (instance == null) {
            instance = new PairAnalyzer();
        }
        return instance;
    }

    public Map<String, String> parse(String s) {
        List<Integer> scores = new ArrayList<>(strategies.size());
        for (int i = 0, len = strategies.size(); i < len; i++) {
            Strategy strategy = strategies.get(i);
            int score = strategy.isSuitable(s);
            if (score >= Strategy.ACCEPTED) {
                return strategy.parse(s);
            }
            scores.add(score);
        }

        // max score
        int maxScore = Collections.max(scores);
        int index = scores.indexOf(maxScore);
        return strategies.get(index).parse(s);
    }
}
