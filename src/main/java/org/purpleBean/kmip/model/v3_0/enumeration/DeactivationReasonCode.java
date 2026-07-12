package org.purpleBean.kmip.model.v3_0.enumeration;

import lombok.*;
import org.purpleBean.kmip.api.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * A KMIP (Key Management Interoperability Protocol) enumeration that specifies the
 * reason for the deactivation of a managed object.
 * <p>
 * This enumeration is used in the {@code DeactivationReason} structure to provide
 * a machine-readable code indicating why an object was deactivated.
 *
 * <p><b>Standards:</b></p>
 * <ul>
 *   <li>{@code UNSPECIFIED}: The reason for deactivation is not specified.</li>
 *   <li>{@code DEACTIVATION_DATE}: The object was deactivated because its deactivation date has passed.</li>
 *   <li>{@code PROTECT_STOP_DATE}: The object was deactivated because its protect stop date has passed.</li>
 *   <li>{@code USAGE_LIMIT}: The object was deactivated because its usage limit has been reached.</li>
 * </ul>
 *
 * @see KmipEnumeration
 * @see org.purpleBean.kmip.model.core.structure.DeactivationReason
 */
@Data
@Builder(toBuilder = true)
public class DeactivationReasonCode implements KmipEnumeration {
    public static final KmipTag kmipTag = KmipTag.Standard.DEACTIVATION_REASON_CODE.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);
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
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, DeactivationReasonCode.class);
            KmipEnumeration.register(spec, kmipTag.getValue(), DeactivationReasonCode::fromName, DeactivationReasonCode::fromValue);
        }
    }

    @NonNull
    private final Value value;

    @Builder
    private DeactivationReasonCode(@NonNull Value value) {
        this.value = value;
        validate();
    }

    public static DeactivationReasonCode of(@NonNull Value value) {
        return new DeactivationReasonCode(value);
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
                        String.format("No DeactivationReasonCode value found for '%s' in KMIP spec %s", name, spec)
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
                        String.format("No DeactivationReasonCode value found for %d in KMIP spec %s", value, spec)
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
                    String.format("Value '%s' for DeactivationReasonCode is not supported for KMIP spec %s", value.getDescription(), spec)
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
     * The standard enumeration of Deactivation Reason Codes.
     */
    @Getter
    @AllArgsConstructor
    @ToString
    public enum Standard implements Value {
        UNSPECIFIED(0x00000001, "Unspecified", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        DEACTIVATION_DATE(0x00000002, "DeactivationDate", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        PROTECT_STOP_DATE(0x00000003, "ProtectStopDate", KmipSpec.UnknownVersion, KmipSpec.V3_0),
        USAGE_LIMIT(0x00000004, "UsageLimit", KmipSpec.UnknownVersion, KmipSpec.V3_0);

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
        public DeactivationReasonCode inst() {
            return DeactivationReasonCode.of(this);
        }
    }

    /**
     * An interface representing a Deactivation Reason Code value, which can be either a standard
     * value or a custom extension.
     */
    public interface Value extends KmipEnumeration.Value<DeactivationReasonCode> {
    }

    /**
     * Represents a custom, vendor-specific Deactivation Reason Code.
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
        public DeactivationReasonCode inst() {
            return DeactivationReasonCode.of(this);
        }
    }
}
