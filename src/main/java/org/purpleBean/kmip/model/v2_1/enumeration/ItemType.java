package org.purpleBean.kmip.model.v2_1.enumeration;

import lombok.*;
import org.purpleBean.kmip.api.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP ItemType enumeration.
 */
@Data
@Builder(toBuilder = true)
public class ItemType implements KmipEnumeration {
    public static final KmipTag kmipTag = KmipTag.Standard.EXTENSION_TYPE.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);
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
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ItemType.class);
            KmipEnumeration.register(spec, kmipTag.getValue(), ItemType::fromName, ItemType::fromValue);
        }
    }

    @NonNull
    private final Value value;

    @Builder
    private ItemType(@NonNull Value value) {
        this.value = value;
        validate();
    }

    public static ItemType of(@NonNull Value value) {
        return new ItemType(value);
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
                        String.format("No ItemType value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No ItemType value found for %d in KMIP spec %s", value, spec)
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
                    String.format("Value '%s' for ItemType is not supported for KMIP spec %s", value.getDescription(), spec)
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

    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        STRUCTURE(          0x00000001, "Structure",         KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INTEGER(            0x00000002, "Integer",           KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        LONG_INTEGER(       0x00000003, "LongInteger",       KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        BIG_INTEGER(        0x00000004, "BigInteger",        KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        ENUMERATION(        0x00000005, "Enumeration",       KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        BOOLEAN(            0x00000006, "Boolean",           KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        TEXT_STRING(        0x00000007, "TextString",        KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        BYTE_STRING(        0x00000008, "ByteString",        KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        DATE_TIME(          0x00000009, "DateTime",          KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        INTERVAL(           0x0000000A, "Interval",          KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        DATE_TIME_EXTENDED( 0x0000000B, "DateTimeExtended",  KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0),
        NAME_REFERENCE(     0x0000000C, "NameReference",     KmipSpec.V3_0),
        IDENTIFIER(         0x0000000D, "Identifier",        KmipSpec.V3_0),
        REFERENCE(          0x0000000E, "Reference",         KmipSpec.V3_0);

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
        public ItemType inst() {
            return ItemType.of(this);
        }
    }

    // ----- Value hierarchy -----
    public interface Value extends KmipEnumeration.Value<ItemType> {
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
        public boolean isSupported() {
            KmipSpec spec = KmipContext.getSpec();
            return supportedVersions.contains(spec);
        }

        @Override
        public ItemType inst() {
            return ItemType.of(this);
        }
    }
}