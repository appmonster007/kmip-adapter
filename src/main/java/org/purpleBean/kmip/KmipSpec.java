package org.purpleBean.kmip;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@RequiredArgsConstructor
public enum KmipSpec {

    UnknownVersion(-1, -1),
    UnsupportedVersion(-9, -9),
    V1_0(1, 0),
    V1_1(1, 1),
    V1_2(1, 2),
    V1_3(1, 3),
    V1_4(1, 4),
    V2_0(2, 0),
    V2_1(2, 1),
    V3_0(3, 0);

    private static final Map<Map.Entry<Integer, Integer>, KmipSpec> SPEC_MAP = new HashMap<>();

    static {
        for (KmipSpec spec : KmipSpec.values()) {
            SPEC_MAP.put(Map.entry(spec.major, spec.minor), spec);
        }
    }

    private final int major;
    private final int minor;

    public static KmipSpec fromValue(ProtocolVersion protocolVersion) {
        Map.Entry<Integer, Integer> key = Map.entry(protocolVersion.getMajor(), protocolVersion.getMinor());
        return Optional.ofNullable(SPEC_MAP.get(key)).orElseThrow();
    }

    @Override
    public String toString() {
        return String.format("V%s.%s", major, minor);
    }
}
