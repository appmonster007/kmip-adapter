package org.purpleBean.kmip.common.enumeration;

import lombok.*;
import org.purpleBean.kmip.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies the
 * type of data being processed in a cryptographic operation.
 * <p>
 * This enumeration is used to distinguish between different types of data that can be
 * the subject of a cryptographic operation, such as data to be encrypted, decrypted,
 * or signed.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code DECRYPT}: Data to be decrypted.</li>
 *   <li>{@code ENCRYPT}: Data to be encrypted.</li>
 *   <li>{@code HASH}: Data to be hashed.</li>
 *   <li>{@code MACMAC_DATA}: Data for a MAC (Message Authentication Code) operation.</li>
 *   <li>{@code RNG_RETRIEVE}: Data retrieved from a Random Number Generator.</li>
 *   <li>{@code SIGN_SIGNATURE_DATA}: Data to be signed.</li>
 *   <li>{@code SIGNATURE_VERIFY}: Data to be used for signature verification.</li>
 * </ul>
 *
 * @see KmipEnumeration
 */
@Data
@Builder(toBuilder = true)
public class DataEnumeration implements KmipEnumeration {
    public static final KmipTag kmipTag = KmipTag.Standard.DATA.inst();
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
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, DataEnumeration.class);
        }
    }

    @NonNull
    private final Value value;

    public DataEnumeration(@NonNull Value value) {
        // KMIP spec compatibility validation
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupported()) {
            throw new IllegalArgumentException(
                    String.format("Value '%s' for DataEnumeration is not supported for KMIP spec %s", value.getDescription(), spec)
            );
        }
        this.value = value;
    }

    public static DataEnumeration of(@NonNull Value value) {
        return new DataEnumeration(value);
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
                        String.format("No DataEnumeration value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No DataEnumeration value found for %d in KMIP spec %s", value, spec)
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
     * The standard enumeration of Data Enumerations.
     */
    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        DECRYPT(0x00000001, "Decrypt", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        ENCRYPT(0x00000002, "Encrypt", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        HASH(0x00000003, "Hash", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        MACMAC_DATA(0x00000004, "MacmacData", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        RNG_RETRIEVE(0x00000005, "RngRetrieve", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SIGN_SIGNATURE_DATA(0x00000006, "SignSignatureData", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        SIGNATURE_VERIFY(0x00000007, "SignatureVerify", KmipSpec.UnknownVersion, KmipSpec.V3_0);

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
        public DataEnumeration inst() {
            return DataEnumeration.of(this);
        }
    }

    /**
     * An interface representing a Data Enumeration value, which can be either a standard
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
         * @return a new instance of the {@link DataEnumeration} with the current value.
         */
        DataEnumeration inst();
    }

    /**
     * Represents a custom, vendor-specific Data Enumeration.
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
        public DataEnumeration inst() {
            return DataEnumeration.of(this);
        }
    }
}