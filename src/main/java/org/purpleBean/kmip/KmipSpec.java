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
    V1_2(1, 2);

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
