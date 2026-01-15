package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.StringUtils;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.State;

import java.util.Set;

/**
 * KMIP LeaseTime dataType.
 */
@Data
@Builder(toBuilder = true)
public class LeaseTime implements KmipDataType, KmipAttribute {

    public static final KmipTag kmipTag = KmipTag.Standard.LEASE_TIME.inst();
    public static final EncodingType encodingType = EncodingType.INTERVAL;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, LeaseTime.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, LeaseTime.class, LeaseTime::of);
        }
    }

    @NonNull
    private final Integer value;

    public static LeaseTime of(@NonNull Integer value) {
        return LeaseTime.builder().value(value).build();
    }

    public static LeaseTime of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueInterval interval)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        return new LeaseTime(interval.getValue());
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
    public boolean isServerModifiable(State state) {
        return true;
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
        return AttributeValueInterval.of(value);
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }

    @Override
    public String getCanonicalName() {
        return getAttributeName().getValue();
    }
}
