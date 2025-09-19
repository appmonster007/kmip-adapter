package org.purpleBean.kmip.benchmark.util;

import org.purpleBean.kmip.benchmark.api.KmipBenchmarkSubject;

import java.util.*;

/**
 * Utilities to discover benchmark subjects in a single, reusable place.
 */
public final class BenchmarkSubjects {

    private BenchmarkSubjects() {
    }

    /**
     * Discover all subjects via ServiceLoader and return a name->impl map.
     */
    public static Map<String, KmipBenchmarkSubject> discoverMap() {
        Map<String, KmipBenchmarkSubject> registry = new LinkedHashMap<>();
        ServiceLoader<KmipBenchmarkSubject> loader = ServiceLoader.load(KmipBenchmarkSubject.class);
        for (KmipBenchmarkSubject subj : loader) {
            registry.putIfAbsent(subj.name(), subj);
        }
        return registry;
    }

    /**
     * Get JSON serialization mapping impl->String
     */
    public static String getJsonStr(String subject) {
        return discoverMap().get(subject).getJsonStr();
    }

    /**
     * Get XML serialization mapping impl->String
     */
    public static String getXmlStr(String subject) {
        return discoverMap().get(subject).getXmlStr();
    }

    /**
     * Get TTLV serialization mapping impl->ByteBuffer
     */
    public static String getTtlvBuf(String subject) {
        HexFormat hexFormat = HexFormat.of();
        return hexFormat.formatHex(discoverMap().get(subject).getTtlvBuf().array());
    }

    /**
     * Discover all subjects and return their names in a stable order.
     */
    public static List<String> discoverNames() {
        return new ArrayList<>(discoverMap().keySet());
    }
}
