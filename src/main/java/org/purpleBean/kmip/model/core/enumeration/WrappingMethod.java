package org.purpleBean.kmip.model.core.enumeration;

import lombok.*;
import org.purpleBean.kmip.api.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies the
 * method used to wrap a key or other sensitive material.
 * <p>
 * Key wrapping is the process of encrypting a key with another key to protect it
 * while it is being stored or transmitted. This enumeration defines the different
 * techniques that can be used for this purpose, including encryption, signing, and
 * authenticated encryption.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code ENCRYPT}: The key is encrypted.</li>
 *   <li>{@code MAC_SIGN}: The key is authenticated with a Message Authentication Code (MAC) or a digital signature.</li>
 *   <li>{@code ENCRYPT_THEN_MAC_SIGN}: The key is first encrypted and then a MAC or signature is computed over the ciphertext.</li>
 *   <li>{@code MAC_SIGN_THEN_ENCRYPT}: A MAC or signature is computed over the plaintext key, and then both the key and the MAC/signature are encrypted together.</li>
 *   <li>{@code TR_31}: The key is wrapped according to the ANSI TR-31 standard.</li>
 * </ul>
 *
 * <p><b>Usage:</b></p>
 * This enumeration is used in the {@code KeyWrappingSpecification} structure to define
 * how a key should be wrapped.
 *
 * @see KmipEnumeration
 * @see org.purpleBean.kmip.model.core.structure.KeyWrappingSpecification
 */
@Data
@Builder(toBuilder = true)
public class WrappingMethod implements KmipEnumeration {
    public static final KmipTag kmipTag = KmipTag.Standard.WRAPPING_METHOD.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);
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
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, WrappingMethod.class);
            KmipEnumeration.register(spec, kmipTag.getValue(), WrappingMethod::fromName, WrappingMethod::fromValue);
        }
    }

    @NonNull
    private final Value value;

    @Builder
    private WrappingMethod(@NonNull Value value) {
        this.value = value;
        validate();
    }

    public static WrappingMethod of(@NonNull Value value) {
        return new WrappingMethod(value);
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
                        String.format("No WrappingMethod value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No WrappingMethod value found for %d in KMIP spec %s", value, spec)
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
                    String.format("Value '%s' for WrappingMethod is not supported for KMIP spec %s", value.getDescription(), spec)
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
     * The standard enumeration of Wrapping Methods.
     */
    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        ENCRYPT(0x00000001, "Encrypt", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        MAC_SIGN(0x00000002, "MacSign", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ENCRYPT_THEN_MAC_SIGN(0x00000003, "EncryptThenMacSign", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        MAC_SIGN_THEN_ENCRYPT(0x00000004, "MacSignThenEncrypt", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        TR_31(0x00000005, "Tr31", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);

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
        public WrappingMethod inst() {
            return WrappingMethod.of(this);
        }
    }

    /**
     * An interface representing a Wrapping Method value, which can be either a standard
     * value or a custom extension.
     */
    public interface Value extends KmipEnumeration.Value<WrappingMethod> {
    }

    /**
     * Represents a custom, vendor-specific Wrapping Method.
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
        public WrappingMethod inst() {
            return WrappingMethod.of(this);
        }
    }
}
