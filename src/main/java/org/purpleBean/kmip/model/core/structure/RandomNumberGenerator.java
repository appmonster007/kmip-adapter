package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.model.v2_1.structure.RngParameters;
import org.purpleBean.kmip.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP §4.46 Random Number Generator — attribute wrapping an RngParameters structure.
 */
@Data
@Builder(toBuilder = true)
public class RandomNumberGenerator implements KmipStructure, KmipAttribute {
    public static final KmipTag kmipTag = KmipTag.Standard.RANDOM_NUMBER_GENERATOR.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, RandomNumberGenerator.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, RandomNumberGenerator.class, RandomNumberGenerator::of);
        }
    }

    @NonNull
    private final RngParameters rngParameters;

    @Builder
    private RandomNumberGenerator(@NonNull RngParameters rngParameters) {
        this.rngParameters = rngParameters;
        validate();
    }

    public static RandomNumberGenerator of(@NonNull RngParameters rngParameters) {
        return RandomNumberGenerator.builder().rngParameters(rngParameters).build();
    }

    public static RandomNumberGenerator of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue.getValue() instanceof KmipDataType[] structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = Stream.of(structure).collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        RngParameters rng = (RngParameters) map.get(RngParameters.kmipTag).getFirst();
        return of(rng);
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
    }

    @Override
    public KmipTag getKmipTag() { return kmipTag; }

    @Override
    public EncodingType getEncodingType() { return encodingType; }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(rngParameters).filter(Objects::nonNull).map(KmipDataType.class::cast).toArray(KmipDataType[]::new);
    }

    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValue.ofStructure(getValue());
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
    public boolean isAlwaysPresent() { return false; }

    @Override
    public boolean isServerInitializable() { return true; }

    @Override
    public boolean isClientInitializable() { return true; }

    @Override
    public boolean isServerModifiable(@NonNull State state) { return false; }

    @Override
    public boolean isClientModifiable(@NonNull State state) { return false; }

    @Override
    public boolean isClientDeletable() { return false; }

    @Override
    public boolean isMultiInstanceAllowed() { return false; }
}
