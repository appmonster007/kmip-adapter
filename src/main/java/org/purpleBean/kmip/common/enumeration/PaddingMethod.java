package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies the
 * padding method to be used in cryptographic operations.
 * <p>
 * Padding is a process of adding data to a message before encryption to ensure that
 * the message length is a multiple of the block size of the cipher. This enumeration
 * lists the padding methods supported by KMIP.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code NONE}: No padding is used.</li>
 *   <li>{@code OAEP}: Optimal Asymmetric Encryption Padding.</li>
 *   <li>{@code PKCS5}: PKCS#5 padding.</li>
 *   <li>{@code SSL3}: SSL 3.0 padding.</li>
 *   <li>{@code ZEROS}: Padding with zeros.</li>
 *   <li>{@code ANSI_X9_23}: ANSI X9.23 padding.</li>
 *   <li>{@code ISO_10126}: ISO 10126 padding.</li>
 *   <li>{@code PKCS1_V1_5}: PKCS#1 v1.5 padding.</li>
 *   <li>{@code X9_31}: ANSI X9.31 padding.</li>
 *   <li>{@code PSS}: Probabilistic Signature Scheme padding.</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see BlockCipherMode
 */
@Data
@Builder(toBuilder = true)
public class PaddingMethod implements KmipEnumeration {
    public static final KmipTag kmipTag = KmipTag.Standard.PADDING_METHOD.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);
    private static final Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
    private static final Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

    static {
        for (Standard s : Standard.values()) {
            VALUE_REGISTRY.put(s.value, s);
            DESCRIPTION_REGISTRY.put(s.description, s);
        }

        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, PaddingMethod.class);
        }
    }

    @NonNull
    private final Value value;

    public PaddingMethod(@NonNull Value value) {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for PaddingMethod is not supported for KMIP spec %s", value.getDescription(), spec)
            );
        }
        this.value = value;
    }

    public static PaddingMethod of(@NonNull Value value) {
        return new PaddingMethod(value);
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
        Value existingEnumByValue = VALUE_REGISTRY.get(value);
        Value existingEnumByDescription = EXTENSION_DESCRIPTION_REGISTRY.get(description);
        if (existingEnumByValue != null || existingEnumByDescription != null) {
            return existingEnumByValue != null ? existingEnumByValue : existingEnumByDescription;
        }
        Extension custom = new Extension(value, description, supportedVersions);
        VALUE_REGISTRY.putIfAbsent(custom.getValue(), custom);
        DESCRIPTION_REGISTRY.putIfAbsent(custom.getDescription(), custom);
        EXTENSION_DESCRIPTION_REGISTRY.putIfAbsent(custom.getDescription(), custom);
        return custom;
    }

    /**
     * Look up by name.
     */
    public static Value fromName(String name) {
        KmipSpec spec = KmipContext.getSpec();
        Value v = DESCRIPTION_REGISTRY.get(name);
        return Optional.ofNullable(v)
                .filter(Value::isSupported)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("No PaddingMethod value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No PaddingMethod value found for %d in KMIP spec %s", value, spec)
                ));
    }

    /**
     * Get registered values.
     */
    public static Collection<Value> registeredValues() {
        return List.copyOf(EXTENSION_DESCRIPTION_REGISTRY.values());
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

    public int getValue() {
        return value.getValue();
    }

    /**
     * The standard enumeration of Padding Methods.
     */
    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        NONE(0x00000001, "None", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        OAEP(0x00000002, "Oaep", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS5(0x00000003, "Pkcs5", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        SSL3(0x00000004, "Ssl3", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ZEROS(0x00000005, "Zeros", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ANSI_X9_23(0x00000006, "AnsiX923", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        ISO_10126(0x00000007, "Iso10126", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PKCS1_V1_5(0x00000008, "Pkcs1V15", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        X9_31(0x00000009, "X931", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0),
        PSS(0x0000000A, "Pss", KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);

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
        public PaddingMethod inst() {
            return PaddingMethod.of(this);
        }
    }

    /**
     * An interface representing a Padding Method value, which can be either a standard
     * value or a custom extension.
     */
    public interface Value {
        /**
         * @return the integer value of the enumeration.
         */
        int getValue();

        /**
         * @return the description of the enumeration.
         */
        String getDescription();

        /**
         * @return true if the enumeration is supported in the current KMIP context, false otherwise.
         */
        boolean isSupported();

        /**
         * @return true if the enumeration is a custom extension, false otherwise.
         */
        boolean isCustom();

        /**
         * @return a new instance of the {@link PaddingMethod} with the current value.
         */
        PaddingMethod inst();
    }

    /**
     * Represents a custom, vendor-specific Padding Method.
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
        public PaddingMethod inst() {
            return PaddingMethod.of(this);
        }
    }
}