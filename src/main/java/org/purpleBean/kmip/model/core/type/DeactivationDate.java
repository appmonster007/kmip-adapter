package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.util.StringUtils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.Set;

/**
 * KMIP DeactivationDate attribute.
 */
@Data
@Builder(toBuilder = true)
public class DeactivationDate implements KmipDataType, KmipAttribute {
    public static final KmipTag kmipTag = KmipTag.Standard.DEACTIVATION_DATE.inst();
    public static final EncodingType encodingType = EncodingType.DATE_TIME;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, DeactivationDate.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, DeactivationDate.class, DeactivationDate::of);
        }
    }

    @NonNull
    private final OffsetDateTime value;

    @Builder
    private DeactivationDate(@NonNull OffsetDateTime value) {
        this.value = value;
        validate();
    }

    public static DeactivationDate of(@NonNull OffsetDateTime value) {
        return new DeactivationDate(value);
    }

    public static DeactivationDate of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue.getValue() instanceof OffsetDateTime value)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        return DeactivationDate.builder().value(value).build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        // No validation needed for this structure
    }

    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValue.ofDateTime(value);
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.convertPascalToTitleCase(kmipTag.getDescription()));
    }

    @Override
    public String getCanonicalName() {
        return kmipTag.getDescription();
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
    public boolean isClientModifiable(@NonNull State state) {
        return state.getIntValue() == State.Standard.PRE_ACTIVE.getValue() ||
                state.getIntValue() == State.Standard.ACTIVE.getValue();
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
    public boolean isAlwaysPresent() {
        return false;
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
    public boolean isServerModifiable(@NonNull State state) {
        return state.getIntValue() == State.Standard.PRE_ACTIVE.getValue() ||
                state.getIntValue() == State.Standard.ACTIVE.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeactivationDate that = (DeactivationDate) o;
        // Compare OffsetDateTime up to seconds to avoid flakiness
        return this.value.withNano(0).atZoneSameInstant(ZoneOffset.UTC).equals(that.value.withNano(0).atZoneSameInstant(ZoneOffset.UTC));
    }

    @Override
    public int hashCode() {
        return Objects.hash(value.withNano(0).atZoneSameInstant(ZoneOffset.UTC));
    }
}