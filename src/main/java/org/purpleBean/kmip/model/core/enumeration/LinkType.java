package org.purpleBean.kmip.model.core.enumeration;

import lombok.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.structure.Link;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies the
 * type of link between two managed objects.
 * <p>
 * Links are used to establish relationships between objects, such as a private key
 * and its corresponding public key, or a key and the certificate that contains it.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code CERTIFICATE_LINK}: A link to a certificate.</li>
 *   <li>{@code PUBLIC_KEY_LINK}: A link to a public key.</li>
 *   <li>{@code PRIVATE_KEY_LINK}: A link to a private key.</li>
 *   <li>{@code DERIVATION_BASE_OBJECT_LINK}: A link to the base object used in a key derivation.</li>
 *   <li>{@code DERIVED_KEY_LINK}: A link to a key derived from this object.</li>
 *   <li>{@code REPLACEMENT_OBJECT_LINK}: A link to an object that replaces this one.</li>
 *   <li>{@code REPLACED_OBJECT_LINK}: A link to an object that this one replaces.</li>
 *   <li>{@code PARENT_LINK}: A link to a parent object.</li>
 *   <li>{@code CHILD_LINK}: A link to a child object.</li>
 *   <li>{@code PREVIOUS_LINK}: A link to a previous version of this object.</li>
 *   <li>{@code NEXT_LINK}: A link to a next version of this object.</li>
 *   <li>{@code PKCS_12_CERTIFICATE_LINK}: A link to a certificate in a PKCS#12 bundle.</li>
 *   <li>{@code PKCS_12_PASSWORD_LINK}: A link to a password used for a PKCS#12 bundle.</li>
 *   <li>{@code WRAPPING_KEY_LINK}: A link to the key used to wrap this object.</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see Link
 */
@Data
@Builder(toBuilder = true)
public class LinkType implements KmipEnumeration {
    public static final KmipTag kmipTag = KmipTag.Standard.LINK_TYPE.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1);
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description.toLowerCase(Locale.ROOT), s);
        }

        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, LinkType.class);
            KmipEnumeration.register(spec, kmipTag.getValue(), LinkType::fromName, LinkType::fromValue);
        }
    }

    @NonNull
    private final Value value;

    @Builder
    private LinkType(@NonNull Value value) {
        this.value = value;
        validate();
    }

    public static LinkType of(@NonNull Value value) {
        return new LinkType(value);
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

        final String name = description.toLowerCase(Locale.ROOT);
        if (description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
        if (supportedVersions.isEmpty()) {
            throw new IllegalArgumentException("At least one supported version must be specified");
        }
        Value existingEnumByValue = VALUE_REGISTRY.get(value);
        Value existingEnumByDescription = EXTENSION_DESCRIPTION_REGISTRY.get(name);
        if (existingEnumByValue != null || existingEnumByDescription != null) {
            return existingEnumByValue != null ? existingEnumByValue : existingEnumByDescription;
        }
        Extension custom = new Extension(value, description, supportedVersions);
        VALUE_REGISTRY.putIfAbsent(value, custom);
        DESCRIPTION_REGISTRY.putIfAbsent(name, custom);
        EXTENSION_DESCRIPTION_REGISTRY.putIfAbsent(name, custom);
        return custom;
    }

    /**
     * Look up by name.
     */
    public static Value fromName(String name) {
        final String nameLowerCase = name.toLowerCase(Locale.ROOT);
        KmipSpec spec = KmipContext.getSpec();
        Value v = DESCRIPTION_REGISTRY.get(nameLowerCase);
        return Optional.ofNullable(v)
                .filter(Value::isSupported)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No LinkType value found for '%s' in KMIP spec %s", name, spec)
                ));
    }

    /**
     * Look up by value.
     */
    public static Value fromValue(int value) {
        KmipSpec spec = KmipContext.getSpec();
        Value v = VALUE_REGISTRY.get(value);
        return Optional.ofNullable(v)
                .filter(Value::isSupported)
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

    private void validate() {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for LinkType is not supported for KMIP spec %s", value.getDescription(), spec)
            );
        }
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
    }

    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public EncodingType getEncodingType() {
        return encodingType;
    }

    public String getDescription() {
        return value.getDescription();
    }

    public boolean isCustom() {
        return value.isCustom();
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && value.isSupported();
    }

    public int getIntValue() {
        return value.getValue();
    }

    /**
     * The standard enumeration of Link Types.
     */
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
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }

        @Override
        public LinkType inst() {
            return LinkType.of(this);
        }
    }

    /**
     * An interface representing a Link Type value, which can be either a standard
     * value or a custom extension.
     */
    public interface Value extends KmipEnumeration.Value<LinkType> {
    }

    /**
     * Represents a custom, vendor-specific Link Type.
     */
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
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }

        @Override
        public LinkType inst() {
            return LinkType.of(this);
        }
    }
}
