package com.example.filefilter.stats;

import java.util.List;

public record Statistics(
        List<String> integers,
        List<String> floats,
        List<String> strings
) {}