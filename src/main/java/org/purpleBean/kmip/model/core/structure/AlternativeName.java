package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AlternativeNameValue;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP AlternativeName attribute structure.
 *
 * <p>Represents a AlternativeName in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class AlternativeName implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = KmipTag.Standard.ALTERNATIVE_NAME.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, AlternativeName.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, AlternativeName.class, AlternativeName::of);
        }
    }

    @NonNull
    private final AlternativeNameValue alternativeNameValue;
    @NonNull
    private final AlternativeNameType alternativeNameType;

    @Builder
    private AlternativeName(@NonNull AlternativeNameValue alternativeNameValue, @NonNull AlternativeNameType alternativeNameType) {
        this.alternativeNameValue = alternativeNameValue;
        this.alternativeNameType = alternativeNameType;
        validate();
    }

    public static AlternativeName of(@NonNull AlternativeNameValue alternativeNameValue, @NonNull AlternativeNameType alternativeNameType) {
        return AlternativeName.builder()
                .alternativeNameValue(alternativeNameValue)
                .alternativeNameType(alternativeNameType)
                .build();
    }

    public static AlternativeName of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue.getValue() instanceof KmipDataType[] structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = Stream.of(structure).collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return AlternativeName.builder()
                .alternativeNameValue((AlternativeNameValue) map.get(AlternativeNameValue.kmipTag).get(0))
                .alternativeNameType((AlternativeNameType) map.get(AlternativeNameType.kmipTag).get(0))
                .build();
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
    public KmipDataType[] getValue() {
        return Stream.of(alternativeNameValue, alternativeNameType)
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
        return false;
    }

    @Override
    public boolean isServerModifiable(State state) {
        return false;
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
        return true;
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
        return AttributeName.of(StringUtils.convertPascalToTitleCase(kmipTag.getDescription()));
    }
}
