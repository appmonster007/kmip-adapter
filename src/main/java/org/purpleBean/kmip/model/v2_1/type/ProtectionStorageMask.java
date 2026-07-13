package org.purpleBean.kmip.model.v2_1.type;

import lombok.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.util.StringUtils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP ProtectionStorageMask datatype attribute.
 */
@Data
@Builder(toBuilder = true)
public class ProtectionStorageMask implements KmipMaskType, KmipAttribute {

    public static final KmipTag kmipTag = KmipTag.Standard.PROTECTION_STORAGE_MASK.inst();
    public static final EncodingType encodingType = EncodingType.INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ProtectionStorageMask.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, ProtectionStorageMask.class, ProtectionStorageMask::of);
            KmipMaskType.register(spec, kmipTag.getValue(), ProtectionStorageMask::fromMaskString);
        }

        for (MaskEnum.Standard s : MaskEnum.Standard.values()) {
            MaskEnum.VALUE_REGISTRY.put(s.value, s);
            MaskEnum.DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }

    @NonNull
    private final Integer value;

    @Builder
    private ProtectionStorageMask(@NonNull Integer value) {
        this.value = value;
        validate();
    }

    public static ProtectionStorageMask of(@NonNull Integer value) {
        return new ProtectionStorageMask(value);
    }

    public static ProtectionStorageMask of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue.getValue() instanceof Integer value)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        return new ProtectionStorageMask(value);
    }

    public static ProtectionStorageMask fromMaskString(@NonNull String value) {
        int mask = MaskEnum.fromMaskString(value);
        return new ProtectionStorageMask(mask);
    }

    public String getMaskString() {
        return MaskEnum.toMaskString(value);
    }

    private void validate() {
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
        return false;
    }

    @Override
    public boolean isServerModifiable(State state) {
        return false;
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

        static Value fromName(String name) {
            KmipSpec spec = KmipContext.getSpec();
            Value v = DESCRIPTION_REGISTRY.get(name);
            return Optional.ofNullable(v)
                    .orElseThrow(() -> new NoSuchElementException(
                            String.format("No ProtectionStorageMask value found for '%s' in KMIP spec %s", name, spec)
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

        @Getter
        @AllArgsConstructor
        @ToString
        enum Standard implements Value {
            SOFTWARE(0x00000001, "Software"),
            HARDWARE(0x00000002, "Hardware"),
            ON_PROCESSOR(0x00000004, "OnProcessor"),
            ON_SYSTEM(0x00000008, "OnSystem"),
            OFF_SYSTEM(0x00000010, "OffSystem"),
            HYPERVISOR(0x00000020, "Hypervisor"),
            OPERATING_SYSTEM(0x00000040, "OperatingSystem"),
            CONTAINER(0x00000080, "Container"),
            ON_PREMISES(0x00000100, "OnPremises"),
            OFF_PREMISES(0x00000200, "OffPremises"),
            SELF_MANAGED(0x00000400, "SelfManaged"),
            OUTSOURCED(0x00000800, "Outsourced"),
            VALIDATED(0x00001000, "Validated"),
            SAME_JURISDICTION(0x00002000, "SameJurisdiction");

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
