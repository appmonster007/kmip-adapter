package org.purplebean.kmip.model.core.type;

import lombok.*;
import org.purplebean.kmip.api.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP StorageStatusMask dataType.
 */
@Data
@Builder(toBuilder = true)
public class StorageStatusMask implements KmipMaskType {

    public static final KmipTag kmipTag = KmipTag.Standard.STORAGE_STATUS_MASK.inst();
    public static final EncodingType encodingType = EncodingType.INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, StorageStatusMask.class);
            KmipMaskType.register(spec, kmipTag.getValue(), StorageStatusMask::fromMaskString);
        }

        for (StorageStatusMask.MaskEnum.Standard s : StorageStatusMask.MaskEnum.Standard.values()) {
            StorageStatusMask.MaskEnum.VALUE_REGISTRY.put(s.value, s);
            StorageStatusMask.MaskEnum.DESCRIPTION_REGISTRY.put(s.description, s);
        }
    }


    @NonNull
    private final Integer value;

    @Builder
    private StorageStatusMask(@NonNull Integer value) {
        this.value = value;
        validate();
    }

    public static StorageStatusMask of(@NonNull Integer value) {
        return new StorageStatusMask(value);
    }

    public static StorageStatusMask fromMaskString(@NonNull String value) {
        int mask = StorageStatusMask.MaskEnum.fromMaskString(value);
        return StorageStatusMask.builder().value(mask).build();
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

    public String getMaskString() {
        return StorageStatusMask.MaskEnum.toMaskString(value);
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
                            String.format("No StorageStatusMask value found for '%s' in KMIP spec %s", name, spec)
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
                            String.format("No StorageStatusMask value found for %d in KMIP spec %s", value, spec)
                    ));
        }

        static String toMaskString(int value) {
            StringBuilder sb = new StringBuilder();
            VALUE_REGISTRY.values().stream()
                    .filter(entry -> (value & entry.getValue()) != 0)
                    .sorted(Comparator.comparing(Value::getValue))
                    .forEach(entry -> sb.append(entry.getDescription()).append(" "));
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
            ON_LINE_STORAGE(1, "OnLineStorage"),
            ARCHIVAL_STORAGE(2, "ArchivalStorage");

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
