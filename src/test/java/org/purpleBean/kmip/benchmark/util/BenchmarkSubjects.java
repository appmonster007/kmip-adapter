package org.purpleBean.kmip.benchmark.util;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;

import java.util.*;

/**
 * Utilities to discover benchmark subjects in a single, reusable place.
 */
public final class BenchmarkSubjects {

    private BenchmarkSubjects() {}

    /** Discover all subjects via ServiceLoader and return a name->impl map. */
    public static Map<String, KmipBenchmarkSubject> discoverMap() {
        Map<String, KmipBenchmarkSubject> registry = new LinkedHashMap<>();
        ServiceLoader<KmipBenchmarkSubject> loader = ServiceLoader.load(KmipBenchmarkSubject.class);
        for (KmipBenchmarkSubject subj : loader) {
            registry.putIfAbsent(subj.name(), subj);
        }
        return registry;
    }

    /** Discover all subjects and return their names in a stable order. */
    public static List<String> discoverNames() {
        return new ArrayList<>(discoverMap().keySet());
    }
}
