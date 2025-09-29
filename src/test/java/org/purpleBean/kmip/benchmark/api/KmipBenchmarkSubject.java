package org.purpleBean.kmip.benchmark.api;

import lombok.Getter;
import org.purpleBean.kmip.KmipSpec;

import java.nio.ByteBuffer;

/**
 * SPI for adding new KMIP serialization/deserialization benchmark subjects
 * without modifying the core benchmark class.
 */
public abstract class KmipBenchmarkSubject {

    @Getter
    public KmipSpec spec = KmipSpec.UnknownVersion;

    /**
     * A short, unique name used to select this subject (e.g., via -p subject=Name).
     */
    public abstract String name();

    /**
     * Prepare mappers and test objects.
     */
    public abstract void setup() throws Exception;

    /**
     * Cleanup any state after benchmark.
     */
    public abstract void tearDown();

    public abstract String getJsonStr();

    public abstract String getXmlStr();

    public abstract ByteBuffer getTtlvBuf();

    // JSON
    public abstract String jsonSerialize() throws Exception;

    public abstract Object jsonDeserialize() throws Exception;

    // XML
    public abstract String xmlSerialize() throws Exception;

    public abstract Object xmlDeserialize() throws Exception;

    // TTLV
    public abstract ByteBuffer ttlvSerialize() throws Exception;

    public abstract Object ttlvDeserialize() throws Exception;
}
