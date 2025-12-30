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
 * KMIP OriginalCreationDate attribute.
 */
@Data
@Builder
public class OriginalCreationDate implements KmipDataType, KmipAttribute {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ORIGINAL_CREATION_DATE);
    public static final EncodingType encodingType = EncodingType.DATE_TIME;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, OriginalCreationDate.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, OriginalCreationDate.class, OriginalCreationDate::of);
        }
    }

    @NonNull
    private final OffsetDateTime value;

    public static OriginalCreationDate of(@NonNull OffsetDateTime value) {
        return OriginalCreationDate.builder().value(value).build();
    }

    public static OriginalCreationDate of(@NonNull AttributeName attributeName, @NonNull AttributeValue.Value attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValue.DateTime dateTime)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        return new OriginalCreationDate(dateTime.getValue());
    }

    @Override
    public AttributeValue.Value getAttributeValue() {
        return AttributeValue.DateTime.of(value);
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
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OriginalCreationDate that = (OriginalCreationDate) o;
        // Compare OffsetDateTime up to seconds to avoid flakiness
        return this.value.withNano(0).equals(that.value.withNano(0));
    }

    @Override
    public int hashCode() {
        return Objects.hash(value.withNano(0));
    }
}
