package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.util.StringUtils;

import java.util.Objects;
import java.util.Set;

/**
 * KMIP CryptographicLength attribute.
 * Represents the length in bits of a cryptographic key or secret.
 */
@Data
@Builder(toBuilder = true)
public class CryptographicLength implements KmipAttribute, KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.CRYPTOGRAPHIC_LENGTH.inst();
    public static final EncodingType encodingType = EncodingType.INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        // Register with KmipDataType and KmipAttribute
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CryptographicLength.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, CryptographicLength.class, CryptographicLength::of);
        }
    }

    @NonNull
    private final Integer value;

    @Builder
    private CryptographicLength(@NonNull Integer value) {
        this.value = value;
        validate();
    }

    /**
     * Creates a new CryptographicLength instance from an AttributeValue.
     *
     * @param attributeValue the attribute value to convert from
     * @return a new CryptographicLength instance
     * @throws IllegalArgumentException if the attribute value is invalid
     */
    public static CryptographicLength of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue.getValue() instanceof Integer value)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        return new CryptographicLength(value);
    }

    /**
     * Creates a new CryptographicLength instance with the specified bit length.
     *
     * @param value the length in bits
     * @return a new CryptographicLength instance
     */
    public static CryptographicLength of(int value) {
        return new CryptographicLength(value);
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
        Objects.requireNonNull(spec, "KMIP spec cannot be null");
        return supportedVersions.contains(spec);
    }

    // KmipAttribute implementation
    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValue.ofInteger(value);
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }

    @Override
    public String getCanonicalName() {
        return kmipTag.getDescription();
    }

    @Override
    public boolean isAlwaysPresent() {
        return false;
    }

    @Override
    public boolean isServerInitializable() {
        return false;
    }

    @Override
    public boolean isClientInitializable() {
        return true;
    }

    @Override
    public boolean isServerModifiable(State state) {
        return state != null && state.getIntValue() == State.Standard.PRE_ACTIVE.getValue();
    }

    @Override
    public boolean isClientModifiable(State state) {
        return state != null && state.getIntValue() == State.Standard.PRE_ACTIVE.getValue();
    }

    @Override
    public boolean isClientDeletable() {
        return false;
    }

    @Override
    public boolean isMultiInstanceAllowed() {
        return false;
    }
}
