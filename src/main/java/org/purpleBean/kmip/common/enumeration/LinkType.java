package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP LinkType enumeration.
 */
@Data
@Builder
public class LinkType implements KmipEnumeration {
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }

    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.LINK_TYPE);
    private final EncodingType encodingType = EncodingType.ENUMERATION;

    @NonNull
    private final Value value;

    public LinkType(@NonNull Value value) {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for LinkType is not supported for KMIP spec %s", value.getDescription(), spec)
            );
        }
        this.value = value;
    }

    private static void checkValidExtensionValue(int value) {
        int extensionStart = 0x80000000;
        if (value < extensionStart || value > 0) {
            throw new IllegalArgumentException(
                    String.format("Extension value %d must be in range 8XXXXXXX (hex)", value)
            );
        }
    }

    /**
     * Register an extension value.
     */
    public static Value register(int value, @NonNull String description, @NonNull Set<KmipSpec> supportedVersions) {
        checkValidExtensionValue(value);
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

    /**
     * Look up by name.
     */
    public static Value fromName(KmipSpec spec, String name) {
        Value v = DESCRIPTION_REGISTRY.get(name);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No LinkType value found for '%s' in KMIP spec %s", name, spec)
                ));
    }

    /**
     * Look up by value.
     */
    public static Value fromValue(KmipSpec spec, int value) {
        // Check standard values first
        Value v = VALUE_REGISTRY.get(value);
        return Optional.ofNullable(v)
                .filter(x -> x.isSupportedFor(spec))
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No LinkType value found for %d in KMIP spec %s", value, spec)
                ));
    }

    /**
    * Get registered values.
    */
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
        CERTIFICATE_LINK(0x00000101, "CertificateLink", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        PUBLIC_KEY_LINK(0x00000102, "PublicKeyLink", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        PRIVATE_KEY_LINK(0x00000103, "PrivateKeyLink", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        DERIVATION_BASE_OBJECT_LINK(0x00000104, "DerivationBaseObjectLink", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        DERIVED_KEY_LINK(0x00000105, "DerivedKeyLink", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        REPLACEMENT_OBJECT_LINK(0x00000106, "ReplacementObjectLink", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        REPLACED_OBJECT_LINK(0x00000107, "ReplacedObjectLink", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        PARENT_LINK(0x00000108, "ParentLink", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        CHILD_LINK(0x00000109, "ChildLink", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        PREVIOUS_LINK(0x0000010A, "PreviousLink", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        NEXT_LINK(0x0000010B, "NextLink", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1),
        PKCS_12_CERTIFICATE_LINK(0x0000010C, "Pkcs12CertificateLink", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        PKCS_12_PASSWORD_LINK(0x0000010D, "Pkcs12PasswordLink", KmipSpec.UnknownVersion, KmipSpec.V2_1),
        WRAPPING_KEY_LINK(0x0000010E, "WrappingKeyLink", KmipSpec.UnknownVersion, KmipSpec.V2_1);

        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;

        private final boolean custom = false;

        Standard(int value, String description, KmipSpec... supportedVersions) {
            this.value = value;
            this.description = description;
            this.supportedVersions = Set.of(supportedVersions);
        }

        @Override
        public boolean isSupportedFor(KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }

    // ----- Value hierarchy -----
    public interface Value {
        int getValue();
        String getDescription();
        boolean isSupportedFor(KmipSpec spec);
        boolean isCustom();
    }

    @Getter
    @AllArgsConstructor
    @ToString
    public static class Extension implements Value {
        private final int value;
        private final String description;
        private final Set<KmipSpec> supportedVersions;

        private final boolean custom = true;

        public Extension(int value, String description, KmipSpec... supportedVersions) {
            this.value = value;
            this.description = description;
            this.supportedVersions = Set.of(supportedVersions);
        }

        @Override
        public boolean isSupportedFor(KmipSpec spec) {
            return supportedVersions.contains(spec);
        }
    }
}
