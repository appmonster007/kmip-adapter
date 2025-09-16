package org.purpleBean.kmip.benchmark.api;

import java.nio.ByteBuffer;

/**
 * SPI for adding new KMIP serialization/deserialization benchmark subjects
 * without modifying the core benchmark class.
 */
public interface KmipBenchmarkSubject {

    /**
     * A short, unique name used to select this subject (e.g., via -p subject=Name).
     */
    String name();

    /**
     * Prepare mappers and test objects.
     */
    void setup() throws Exception;

    /**
     * Cleanup any state after benchmark.
     */
    void tearDown();

    // JSON
    String jsonSerialize() throws Exception;

    Object jsonDeserialize() throws Exception;

    // XML
    String xmlSerialize() throws Exception;

    Object xmlDeserialize() throws Exception;

    // TTLV
    ByteBuffer ttlvSerialize() throws Exception;

    Object ttlvDeserialize() throws Exception;
}
