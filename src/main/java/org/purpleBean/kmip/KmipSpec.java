package org.purpleBean.kmip;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.purpleBean.kmip.common.structure.ProtocolVersion;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * An enumeration representing the different versions of the Key Management
 * Interoperability Protocol (KMIP) specification.
 * <p>
 * This enum provides a type-safe way to refer to specific KMIP versions and includes
 * utility methods to convert from a {@link ProtocolVersion} structure.
 *
 * @see KmipContext
 * @see ProtocolVersion
 */
@Getter
@RequiredArgsConstructor
public enum KmipSpec {

    /**
     * An unknown or unspecified KMIP version.
     */
    UnknownVersion(-1, -1),

    /**
     * A KMIP version that is not supported by this implementation.
     */
    UnsupportedVersion(-9, -9),

    /**
     * KMIP version 1.0.
     */
    V1_0(1, 0),

    /**
     * KMIP version 1.1.
     */
    V1_1(1, 1),

    /**
     * KMIP version 1.2.
     */
    V1_2(1, 2),

    /**
     * KMIP version 1.3.
     */
    V1_3(1, 3),

    /**
     * KMIP version 1.4.
     */
    V1_4(1, 4),

    /**
     * KMIP version 2.0.
     */
    V2_0(2, 0),

    /**
     * KMIP version 2.1.
     */
    V2_1(2, 1),

    /**
     * KMIP version 3.0.
     */
    V3_0(3, 0);

    private static final Map<Map.Entry<Integer, Integer>, KmipSpec> SPEC_MAP = new HashMap<>();

    static {
        for (KmipSpec spec : KmipSpec.values()) {
            SPEC_MAP.put(Map.entry(spec.major, spec.minor), spec);
        }
    }

    private final int major;
    private final int minor;

    /**
     * Converts a {@link ProtocolVersion} object into the corresponding {@link KmipSpec} enum constant.
     *
     * @param protocolVersion The {@link ProtocolVersion} to convert.
     * @return The matching {@link KmipSpec}.
     * @throws java.util.NoSuchElementException if no matching spec is found.
     */
    public static KmipSpec fromValue(ProtocolVersion protocolVersion) {
        Map.Entry<Integer, Integer> key = Map.entry(protocolVersion.getMajor(), protocolVersion.getMinor());
        return Optional.ofNullable(SPEC_MAP.get(key)).orElseThrow();
    }

    @Override
    public String toString() {
        return String.format("V%s.%s", major, minor);
    }
}
