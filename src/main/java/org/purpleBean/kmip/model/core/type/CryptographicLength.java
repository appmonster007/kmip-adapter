package org.purpleBean.kmip.model.core.type;

import lombok.*;
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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    /**
     * Creates a new CryptographicLength instance from an AttributeValue.
     *
     * @param attributeValue the attribute value to convert from
     * @return a new CryptographicLength instance
     * @throws IllegalArgumentException if the attribute value is invalid
     */
    public static CryptographicLength of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueInteger integer)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        return new CryptographicLength(integer.getValue());
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
        return AttributeValueInteger.of(value);
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }

    @Override
    public String getCanonicalName() {
        return "CryptographicLength";
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
        return state != null && state.getValue() == State.Standard.PRE_ACTIVE.getValue();
    }

    @Override
    public boolean isClientModifiable(State state) {
        return state != null && state.getValue() == State.Standard.PRE_ACTIVE.getValue();
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
