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
 * KMIP CompromiseOccurrenceDate attribute.
 */
@Data
@Builder(toBuilder = true)
public class CompromiseOccurrenceDate implements KmipDataType, KmipAttribute {
    public static final KmipTag kmipTag = KmipTag.Standard.COMPROMISE_OCCURRENCE_DATE.inst();
    public static final EncodingType encodingType = EncodingType.DATE_TIME;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CompromiseOccurrenceDate.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, CompromiseOccurrenceDate.class, CompromiseOccurrenceDate::of);
        }
    }

    @NonNull
    private final OffsetDateTime value;

    @Builder
    private CompromiseOccurrenceDate(@NonNull OffsetDateTime value) {
        this.value = value;
        validate();
    }

    public static CompromiseOccurrenceDate of(@NonNull OffsetDateTime value) {
        return new CompromiseOccurrenceDate(value);
    }

    public static CompromiseOccurrenceDate of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue.getValue() instanceof OffsetDateTime value)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        return CompromiseOccurrenceDate.builder().value(value).build();
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
        return false;
    }

    @Override
    public boolean isServerModifiable(@NonNull State state) {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompromiseOccurrenceDate that = (CompromiseOccurrenceDate) o;
        // Compare OffsetDateTime up to seconds to avoid flakiness
        return this.value.withNano(0).atZoneSameInstant(ZoneOffset.UTC).equals(that.value.withNano(0).atZoneSameInstant(ZoneOffset.UTC));
    }

    @Override
    public int hashCode() {
        return Objects.hash(value.withNano(0).atZoneSameInstant(ZoneOffset.UTC));
    }
}
