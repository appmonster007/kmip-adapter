package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.AttributeIndex;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP Attribute structure.
 */
@Data
@Builder
public class Attribute implements KmipStructure {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
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

    public static Attribute of(@NonNull String name, @NonNull Object value) {
        return Attribute.of(CustomAttribute.of(name, value));
    }

    public static Attribute of(@NonNull KmipAttribute attribute) {
        return Attribute.builder()
                .attributeName(attribute.getAttributeName())
                .attributeValue(attribute.getAttributeValue())
                .build();
    }

    public static KmipAttribute toKmipAttribute(@NonNull Attribute attribute) {
        KmipSpec spec = KmipContext.getSpec();
        String name = attribute.getAttributeName().getValue();
        KmipTag.Value attrTag;
        if (CustomAttribute.isValidCustomAttributeName(name)) {
            attrTag = KmipTag.Standard.ATTRIBUTE;
        } else {
            attrTag = KmipTag.fromName(spec, StringUtils.covertTitleToPascalCase(name));
        }
        return KmipAttribute.getAttributeBuilderFromRegistry(
                attrTag,
                attribute.getAttributeValue().getEncodingType()
        ).apply(attribute.getAttributeName(), attribute.getAttributeValue());
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
    public List<KmipDataType> getValues() {
        return Stream.of(attributeName, attributeIndex, attributeValue)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
