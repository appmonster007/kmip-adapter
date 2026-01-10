package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.enumeration.State;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * KMIP ProtectStopDate attribute.
 */
@Data
@Builder(toBuilder = true)
public class ProtectStopDate implements KmipDataType, KmipAttribute {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.PROTECT_STOP_DATE);
    public static final EncodingType encodingType = EncodingType.DATE_TIME;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ProtectStopDate.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, ProtectStopDate.class, ProtectStopDate::of);
        }
    }

    @NonNull
    private final OffsetDateTime value;

    public static ProtectStopDate of(@NonNull OffsetDateTime value) {
        return ProtectStopDate.builder().value(value).build();
    }

    public static ProtectStopDate of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueDateTime dateTime)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        return new ProtectStopDate(dateTime.getValue());
    }

    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValueDateTime.of(value);
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }

    @Override
    public String getCanonicalName() {
        return getAttributeName().getValue();
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
        return (
                state.getValue().getValue() == State.Standard.PRE_ACTIVE.getValue()
                        || state.getValue().getValue() == State.Standard.ACTIVE.getValue()
        ) && value.withNano(0).isAfter(OffsetDateTime.now().withNano(0));
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
        return (
                state.getValue().getValue() == State.Standard.PRE_ACTIVE.getValue()
                        || state.getValue().getValue() == State.Standard.ACTIVE.getValue()
        ) && value.withNano(0).isAfter(OffsetDateTime.now().withNano(0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProtectStopDate that = (ProtectStopDate) o;
        // Compare OffsetDateTime up to seconds to avoid flakiness
        return this.value.withNano(0).equals(that.value.withNano(0));
    }

    @Override
    public int hashCode() {
        return Objects.hash(value.withNano(0));
    }
}
