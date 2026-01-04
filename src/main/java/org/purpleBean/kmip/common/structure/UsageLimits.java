package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.UsageLimitsCount;
import org.purpleBean.kmip.common.UsageLimitsTotal;
import org.purpleBean.kmip.common.enumeration.State;
import org.purpleBean.kmip.common.enumeration.UsageLimitsUnit;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * KMIP UsageLimits attribute structure.
 *
 * <p>Represents a UsageLimits in KMIP.</p>
 */
@Data
@Builder
public class UsageLimits implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.USAGE_LIMITS);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, UsageLimits.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, UsageLimits.class, UsageLimits::of);
        }
    }

    @NonNull
    private final UsageLimitsTotal usageLimitsTotal;
    @NonNull
    private final UsageLimitsCount usageLimitsCount;
    @NonNull
    private final UsageLimitsUnit usageLimitsUnit;


    public static UsageLimits of(@NonNull AttributeName attributeName, @NonNull AttributeValue.Value attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValue.Structure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        UsageLimitsBuilder builder = UsageLimits.builder();
        List<KmipDataType> fields = structure.getValue();
        for (KmipDataType field : fields) {
            switch (field) {
                case UsageLimitsTotal usageLimitsTotal -> builder.usageLimitsTotal(usageLimitsTotal);
                case UsageLimitsCount usageLimitsCount -> builder.usageLimitsCount(usageLimitsCount);
                case UsageLimitsUnit usageLimitsUnit -> builder.usageLimitsUnit(usageLimitsUnit);
                case null, default -> throw new IllegalArgumentException("Invalid field");
            }
        }
        return builder.build();
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
    public List<KmipDataType> getValues() {
        return List.of(usageLimitsTotal, usageLimitsCount, usageLimitsUnit);
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec)
                && usageLimitsTotal.isSupported()
                && usageLimitsCount.isSupported()
                && usageLimitsUnit.isSupported();
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
    public boolean isServerModifiable(State state) {
        return true;
    }

    @Override
    public boolean isClientModifiable(State state) {
        return true;
    }

    @Override
    public boolean isClientDeletable() {
        return true;
    }

    @Override
    public boolean isMultiInstanceAllowed() {
        return false;
    }

    @Override
    public String getCanonicalName() {
        return getAttributeName().getValue();
    }

    @Override
    public AttributeValue.Value getAttributeValue() {
        return AttributeValue.Structure.of(getValues());
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }

    public static class UsageLimitsBuilder {
        public UsageLimits build() {
            validate();
            return new UsageLimits(
                    usageLimitsTotal,
                    usageLimitsCount,
                    usageLimitsUnit
            );
        }

        private void validate() {
            Objects.requireNonNull(usageLimitsTotal, "UsageLimitsTotal cannot be null");
            Objects.requireNonNull(usageLimitsCount, "UsageLimitsCount cannot be null");
            Objects.requireNonNull(usageLimitsUnit, "UsageLimitsUnit cannot be null");
        }
    }
}