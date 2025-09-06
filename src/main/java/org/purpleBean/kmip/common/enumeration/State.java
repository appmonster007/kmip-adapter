package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipEnumeration;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.KmipCodecContext;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Builder
public class State implements KmipEnumeration {
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.STATE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    @NonNull
    private final Value value;

    public State(@NonNull Value value) {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipCodecContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for %s is not supported for KMIP spec %s", kmipTag.getDescription(), value.getDescription(), spec)
            );
        }

        this.value = value;
    }

    private static boolean isValidExtensionValue(int value) {
        int extensionStart = 0x80000000;
        return !(value < extensionStart || value > 0);
    }

    public static Value register(int value, @NonNull String description, @NonNull Set<KmipSpec> supportedVersions) {
        if (!isValidExtensionValue(value)) {
            throw new IllegalArgumentException(
                    String.format("Extension value %d must be in range 8XXXXXXX (hex)", value)
            );
        }
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (supportedVersions.isEmpty()) {
            throw new IllegalArgumentException("At least one supported version must be specified");
        }
        Extension custom = new Extension(value, description, supportedVersions);
        VALUE_REGISTRY.put(custom.getValue(), custom);
        DESCRIPTION_REGISTRY.put(custom.getDescription(), custom);
        EXTENSION_DESCRIPTION_REGISTRY.put(custom.getDescription(), custom);
        return custom;
    }

    public static Value fromValue(@NonNull KmipSpec spec, int value) {
        Value v = VALUE_REGISTRY.get(value);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No value found for %d in KMIP spec %s", value, spec)
                ));
    }

    public static Value fromName(@NonNull KmipSpec spec, @NonNull String name) {
        Value v = DESCRIPTION_REGISTRY.get(name);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No value found for '%s' in KMIP spec %s", name, spec)
                ));
    }

    public static Collection<Value> registeredValues() {
        return List.copyOf(EXTENSION_DESCRIPTION_REGISTRY.values());
    }

    public String getDescription() {
        return value.getDescription();
    }

    public boolean isCustom() {
        return value.isCustom();
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return value.isSupportedFor(spec);
    }

    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        PRE_ACTIVE(0x00000001, "PreActive", Set.of(KmipSpec.V1_2)),
        ACTIVE(0x00000002, "Active", Set.of(KmipSpec.V1_2)),
        DEACTIVATED(0x00000003, "Deactivated", Set.of(KmipSpec.V1_2)),
        COMPROMISED(0x00000004, "Compromised", Set.of(KmipSpec.V1_2)),
        DESTROYED(0x00000005, "Destroyed", Set.of(KmipSpec.V1_2)),
        DESTROYED_COMPROMISED(0x00000006, "DestroyedCompromised", Set.of(KmipSpec.V1_2));

        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;

        private final boolean custom = false;

        @Override
        public boolean isSupportedFor(KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }

    public interface Value {
        int getValue();

        String getDescription();

        boolean isSupportedFor(KmipSpec spec);

        boolean isCustom();
    }

    @Getter
    @ToString
    @RequiredArgsConstructor
    public static final class Extension implements Value {
        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;

        private final boolean custom = true;

        @Override
        public boolean isSupportedFor(KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }
}
