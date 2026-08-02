package org.purpleBean.kmip.model.core.type;

import lombok.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP CryptographicUsageMask dataType.
 */
@Data
@Builder(toBuilder = true)
public class CryptographicUsageMask implements KmipMaskType, KmipAttribute {

    public static final KmipTag kmipTag = KmipTag.Standard.CRYPTOGRAPHIC_USAGE_MASK.inst();
    public static final EncodingType encodingType = EncodingType.INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CryptographicUsageMask.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, CryptographicUsageMask.class, CryptographicUsageMask::of);
            KmipMaskType.register(spec, kmipTag.getValue(), CryptographicUsageMask::fromMaskString);
        }

        for (MaskEnum.Standard s : MaskEnum.Standard.values()) {
            MaskEnum.VALUE_REGISTRY.put(s.value, s);
            MaskEnum.DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }

    @NonNull
    private final Integer value;

    @Builder
    private CryptographicUsageMask(@NonNull Integer value) {
        this.value = value;
        validate();
    }

    public static CryptographicUsageMask of(@NonNull Integer value) {
        return new CryptographicUsageMask(value);
    }

    public static CryptographicUsageMask of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue.getValue() instanceof Integer value)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        return CryptographicUsageMask.builder().value(value).build();
    }

    public static CryptographicUsageMask fromMaskString(@NonNull String value) {
        int mask = MaskEnum.fromMaskString(value);
        return CryptographicUsageMask.builder().value(mask).build();
    }


    public String getMaskString() {
        return MaskEnum.toMaskString(value);
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        // No validation needed for this structure
    }

    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public EncodingType getEncodingType() {
        return encodingType;
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec);
    }

    @Override
    public boolean isAlwaysPresent() {
        return true;
    }

    @Override
    public boolean isServerInitializable() {
        return true;
    }

    @Override
    public boolean isClientInitializable() {
        return true;
    }

    @Override
    public boolean isServerModifiable(State state) {
        return true;
    }

    @Override
    public boolean isClientModifiable(State state) {
        return false;
    }

    @Override
    public boolean isClientDeletable() {
        return false;
    }

    @Override
    public boolean isMultiInstanceAllowed() {
        return false;
    }

    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValue.ofMaskInteger(value, getMaskString());
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.convertPascalToTitleCase(kmipTag.getDescription()));
    }

    @Override
    public String getCanonicalName() {
        return kmipTag.getDescription();
    }

    public interface MaskEnum {
        Map<Integer, Value> VALUE_REGISTRY = new ConcurrentHashMap<>();
        Map<String, Value> DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();
        Map<String, Value> EXTENSION_DESCRIPTION_REGISTRY = new ConcurrentHashMap<>();

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
        static Value register(int value, @NonNull String description) {
            checkValidExtensionValue(value);
            if (description.trim().isEmpty()) {
                throw new IllegalArgumentException("Description cannot be empty");
            }

            Value existingEnumByValue = VALUE_REGISTRY.get(value);
            Value existingEnumByDescription = EXTENSION_DESCRIPTION_REGISTRY.get(description);
            if (existingEnumByValue != null || existingEnumByDescription != null) {
                return existingEnumByValue != null ? existingEnumByValue : existingEnumByDescription;
            }
            Extension custom = new Extension(value, description);
            VALUE_REGISTRY.putIfAbsent(custom.getValue(), custom);
            DESCRIPTION_REGISTRY.putIfAbsent(custom.getDescription(), custom);
            EXTENSION_DESCRIPTION_REGISTRY.putIfAbsent(custom.getDescription(), custom);
            return custom;
        }

        /**
         * Look up by name.
         */
        static Value fromName(String name) {
            KmipSpec spec = KmipContext.getSpec();
            Value v = DESCRIPTION_REGISTRY.get(name);
            return Optional.ofNullable(v)
                    .orElseThrow(() -> new NoSuchElementException(
                            String.format("No CryptographicUsageMask value found for '%s' in KMIP spec %s", name, spec)
                    ));
        }

        /**
         * Look up by value.
         */
        static Value fromValue(int value) {
            KmipSpec spec = KmipContext.getSpec();
            Value v = VALUE_REGISTRY.get(value);
            return Optional.ofNullable(v)
                    .orElseThrow(() -> new NoSuchElementException(
                            String.format("No CryptographicUsageMask value found for %d in KMIP spec %s", value, spec)
                    ));
        }

        static String toMaskString(int value) {
            StringBuilder sb = new StringBuilder();
            VALUE_REGISTRY.entrySet().stream()
                    .sorted(java.util.Map.Entry.<Integer, Value>comparingByKey().reversed())
                    .forEach(entry -> {
                        if ((value & entry.getValue().getValue()) != 0) {
                            sb.append(entry.getValue().getDescription()).append(" ");
                        }
                    });
            return sb.toString().trim();
        }

        static int fromMaskString(String value) {
            List<String> maskNames = List.of(value.split(" "));
            int maskValue = 0;
            for (String maskName : maskNames) {
                if (maskName.isEmpty()) continue;
                maskValue |= fromName(maskName).getValue();
            }
            return maskValue;
        }

        /**
         * Get registered values.
         */
        static Collection<Value> registeredValues() {
            return List.copyOf(EXTENSION_DESCRIPTION_REGISTRY.values());
        }

        @Getter
        @AllArgsConstructor
        @ToString
        enum Standard implements Value {
            SIGN(1, "Sign"),
            VERIFY(2, "Verify"),
            ENCRYPT(4, "Encrypt"),
            DECRYPT(8, "Decrypt"),
            WRAP_KEY(16, "WrapKey"),
            UNWRAP_KEY(32, "UnwrapKey"),
            EXPORT(64, "Export"),
            MAC_GENERATE(128, "MACGenerate"),
            MAC_VERIFY(256, "MACVerify"),
            DERIVE_KEY(512, "DeriveKey"),
            CONTENT_COMMITMENT_NON_REPUDIATION(1_024, "ContentCommitment"),
            KEY_AGREEMENT(2_048, "KeyAgreement"),
            CERTIFICATE_SIGN(4_096, "CertificateSign"),
            CRL_SIGN(8_192, "CRLSign"),
            GENERATE_CRYPTOGRAM(16_384, "GenerateCryptogram"),
            VALIDATE_CRYPTOGRAM(32_768, "ValidateCryptogram"),
            TRANSLATE_ENCRYPT(65_536, "TranslateEncrypt"),
            TRANSLATE_DECRYPT(131_072, "TranslateDecrypt"),
            TRANSLATE_WRAP(262_144, "TranslateWrap"),
            TRANSLATE_UNWRAP(524_288, "TranslateUnwrap");

            private final int value;
            private final String description;
            private final boolean custom = false;
        }

        interface Value {
            int getValue();

            String getDescription();

            boolean isCustom();

        }

        @Getter
        @AllArgsConstructor
        @ToString
        class Extension implements Value {
            private final int value;
            private final String description;
            private final boolean custom = true;
        }
    }
}
