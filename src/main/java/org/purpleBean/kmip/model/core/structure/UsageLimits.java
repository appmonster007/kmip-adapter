package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.enumeration.UsageLimitsUnit;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.core.type.UsageLimitsCount;
import org.purpleBean.kmip.model.core.type.UsageLimitsTotal;
import org.purpleBean.kmip.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP UsageLimits attribute structure.
 *
 * <p>Represents a UsageLimits in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class UsageLimits implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = KmipTag.Standard.USAGE_LIMITS.inst();
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

    @Builder
    private UsageLimits(
            @NonNull UsageLimitsTotal usageLimitsTotal,
            @NonNull UsageLimitsCount usageLimitsCount,
            @NonNull UsageLimitsUnit usageLimitsUnit
    ) {
        this.usageLimitsTotal = usageLimitsTotal;
        this.usageLimitsCount = usageLimitsCount;
        this.usageLimitsUnit = usageLimitsUnit;
        validate();
    }

    public static UsageLimits of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue.getValue() instanceof KmipDataType[] structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = Stream.of(structure).collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return UsageLimits.builder()
                .usageLimitsTotal((UsageLimitsTotal) map.get(UsageLimitsTotal.kmipTag).get(0))
                .usageLimitsCount((UsageLimitsCount) map.get(UsageLimitsCount.kmipTag).get(0))
                .usageLimitsUnit((UsageLimitsUnit) map.get(UsageLimitsUnit.kmipTag).get(0))
                .build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        Objects.requireNonNull(usageLimitsTotal, "UsageLimitsTotal cannot be null");
        Objects.requireNonNull(usageLimitsCount, "UsageLimitsCount cannot be null");
        Objects.requireNonNull(usageLimitsUnit, "UsageLimitsUnit cannot be null");
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
    public KmipDataType[] getValue() {
        return Stream.of(usageLimitsTotal, usageLimitsCount, usageLimitsUnit)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
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
        return kmipTag.getDescription();
    }

    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValue.ofStructure(getValue());
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }
}
