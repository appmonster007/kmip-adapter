package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.AlternativeNameValue;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.enumeration.AlternativeNameType;
import org.purpleBean.kmip.common.enumeration.State;

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

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ALTERNATIVE_NAME);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
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

    public static AlternativeName of(@NonNull AttributeName attributeName, @NonNull AttributeValue.Value attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValue.Structure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValue().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
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
                && alternativeNameType.isSupported()
                && alternativeNameValue.isSupported();
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
    public AttributeValue.Value getAttributeValue() {
        return AttributeValue.Structure.of(getValues());
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