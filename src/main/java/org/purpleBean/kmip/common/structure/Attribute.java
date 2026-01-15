package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.AttributeIndex;
import org.purpleBean.kmip.common.AttributeName;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP Attribute structure.
 */
@Data
@Builder(toBuilder = true)
public class Attribute implements KmipStructure {

    public static final KmipTag kmipTag = KmipTag.Standard.ATTRIBUTE.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, Attribute.class);
        }
    }

    @NonNull
    private final AttributeName attributeName;
    private final AttributeIndex attributeIndex;
    @NonNull
    private final AttributeValue attributeValue;

    public static Attribute of(@NonNull String name, @NonNull AttributeValue value) {
        return Attribute.of(CustomAttribute.of(name, value));
    }

    public static Attribute of(@NonNull KmipAttribute attribute) {
        return Attribute.builder()
                .attributeName(attribute.getAttributeName())
                .attributeValue(attribute.getAttributeValue())
                .build();
    }

    public static KmipAttribute toKmipAttribute(@NonNull Attribute attribute) {
        String name = attribute.getAttributeName().getValue();
        KmipTag.Value attrTag;
        EncodingType encodingType;
        if (CustomAttribute.isValidCustomAttributeName(name)) {
            attrTag = KmipTag.Standard.ATTRIBUTE;
            encodingType = EncodingType.STRUCTURE;
        } else {
            attrTag = KmipTag.fromName(StringUtils.covertTitleToPascalCase(name));
            encodingType = attribute.getAttributeValue().getEncodingType();
        }
        BiFunction<AttributeName, AttributeValue, ? extends KmipAttribute> attributeBuilder = KmipAttribute.getAttributeBuilderFromRegistry(
                attrTag,
                encodingType
        );
        return attributeBuilder.apply(attribute.getAttributeName(), attribute.getAttributeValue());
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
        return supportedVersions.contains(spec)
                && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return Stream.of(attributeName, attributeIndex, attributeValue)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
