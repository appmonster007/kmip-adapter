package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP Credential Type enumeration.
 */
@Data
public class CredentialType implements KmipEnumeration {

    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        // Pre-register standard values
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.CREDENTIAL_TYPE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @NonNull
    private final Value value;

    public CredentialType(@NonNull Value value) {
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for %s is not supported for KMIP spec %s",
                            value.getDescription(), kmipTag.getDescription(), spec)
            );
        }
        this.value = value;
    }

    public String getDescription() { return value.getDescription(); }
    public boolean isCustom() { return value.isCustom(); }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) { return value.isSupportedFor(spec); }

    /**
     * Register an extension value.
     */
    public static Value register(int value, @NonNull String description, @NonNull Set<KmipSpec> supportedVersions) {
        if (!isValidExtensionValue(value)) {
            throw new IllegalArgumentException("Extension value must be in vendor range 0x80000000 - 0xFFFFFFFF");
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

    private static boolean isValidExtensionValue(int value) {
        return Integer.compareUnsigned(value, 0x80000000) >= 0;
    }

    public static Value fromValue(@NonNull KmipSpec spec, int value) {
        Value v = VALUE_REGISTRY.get(value);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No value found for %d in KMIP spec %s", value, spec)));
    }

    public static Value fromName(@NonNull KmipSpec spec, @NonNull String name) {
        Value v = DESCRIPTION_REGISTRY.get(name);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No value found for '%s' in KMIP spec %s", name, spec)));
    }

    public static Collection<Value> registeredValues() {
        return List.copyOf(EXTENSION_DESCRIPTION_REGISTRY.values());
    }

    public interface Value {
        int getValue();
        String getDescription();
        boolean isSupportedFor(KmipSpec spec);
        boolean isCustom();
    }

    @Getter
    @RequiredArgsConstructor
    @ToString
    public enum Standard implements Value {
        USERNAME_AND_PASSWORD(0x00000001, "Username and Password", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0, KmipSpec.V1_1, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0)),
        DEVICE(0x00000002, "Device", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0, KmipSpec.V1_1, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0)),
        ATTRIBUTES(0x00000003, "Attributes", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0, KmipSpec.V1_1, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0)),
        EXTENDED(0x00000004, "Extended", Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_0, KmipSpec.V1_1, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0));

        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;
        private final boolean custom = false;

        @Override
        public boolean isSupportedFor(KmipSpec spec) { return supportedVersions.contains(spec); }
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
        public boolean isSupportedFor(KmipSpec spec) { return supportedVersions.contains(spec); }
    }
}
