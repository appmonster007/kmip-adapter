package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.StringUtils;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.AlternativeNameType;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AlternativeNameValue;
import org.purpleBean.kmip.model.core.type.AttributeName;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * KMIP AlternativeName attribute structure.
 *
 * <p>Represents a AlternativeName in KMIP.</p>
 */
@Data
@Builder(toBuilder = true)
public class AlternativeName implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = KmipTag.Standard.ALTERNATIVE_NAME.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

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

    public static AlternativeName of(@NonNull AlternativeNameValue alternativeNameValue, @NonNull AlternativeNameType alternativeNameType) {
        return AlternativeName.builder()
                .alternativeNameValue(alternativeNameValue)
                .alternativeNameType(alternativeNameType)
                .build();
    }

    public static AlternativeName of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueStructure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return AlternativeName.builder()
                .alternativeNameValue((AlternativeNameValue) map.get(AlternativeNameValue.kmipTag).get(0))
                .alternativeNameType((AlternativeNameType) map.get(AlternativeNameType.kmipTag).get(0))
                .build();
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
        return List.of(alternativeNameValue, alternativeNameType);
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec)
                && getValues().stream().allMatch(KmipDataType::isSupported);
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
        return getAttributeName().getValue();
    }

    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValueStructure.of(getValues());
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }

    public static class AlternativeNameBuilder {
        public AlternativeName build() {
            validate();
            return new AlternativeName(
                    alternativeNameValue,
                    alternativeNameType
            );
        }

        private void validate() {
            Objects.requireNonNull(alternativeNameValue, "AlternativeNameValue cannot be null");
            Objects.requireNonNull(alternativeNameType, "AlternativeNameType cannot be null");
        }
    }
}