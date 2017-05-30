package com.suyun.pairanalyzer;

import com.suyun.pairanalyzer.strategy.DelimiterBaseStrategy;
import com.suyun.pairanalyzer.strategy.JsonStrategy;

/**
 * Created by IT on 2017/5/31.
 */
public class Strategies {

    private static final String[] LINE_BREAKS = new String[]{"\n", "\r", "\n\r"};

    private static final String[] SEPARATORS = new String[]{"=", ":"};

    public static final JsonStrategy JSON = new JsonStrategy();

    public static final DelimiterBaseStrategy CRLF = new DelimiterBaseStrategy(
            LINE_BREAKS,
            SEPARATORS
    );

    public static final DelimiterBaseStrategy INLINE = new DelimiterBaseStrategy(
            new String[]{",",";"},
            SEPARATORS
    );
}
