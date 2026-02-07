package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.type.AttributeIndex;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.AttributeValue;
import org.purpleBean.kmip.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * KMIP Attribute structure.
 */
@Data
@Builder(toBuilder = true)
public class Attribute implements KmipStructure {

    public static final KmipTag kmipTag = KmipTag.Standard.ATTRIBUTE.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_1, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, Attribute.class);
        }
    }

    @NonNull
    private final AttributeName attributeName;
    private final AttributeIndex attributeIndex;
    private final AttributeValue attributeValue;

    @Builder
    private Attribute(@NonNull AttributeName attributeName, AttributeIndex attributeIndex, AttributeValue attributeValue) {
        this.attributeName = attributeName;
        this.attributeIndex = attributeIndex;
        this.attributeValue = attributeValue;
        validate();
    }

    public static Attribute of(@NonNull String name, AttributeValue value) {
        return Attribute.of(CustomAttribute.of(name, value));
    }

    public static Attribute of(AttributeName name, AttributeValue value) {
        return new Attribute(name, AttributeIndex.of(0), value);
    }

    public static Attribute of(@NonNull KmipAttribute attribute) {
        return new Attribute(attribute.getAttributeName(), null, attribute.getAttributeValue());
    }

    public static KmipAttribute toKmipAttribute(@NonNull Attribute attribute) {
        String name = attribute.getAttributeName().getValue();
        KmipTag.Value attrTag;
        EncodingType encodingType;
        if (CustomAttribute.isValidCustomAttributeName(name)) {
            attrTag = KmipTag.Standard.ATTRIBUTE;
            encodingType = EncodingType.STRUCTURE;
        } else {
            attrTag = KmipTag.fromName(StringUtils.convertTitleToPascalCase(name));
            encodingType = attribute.getAttributeValue().getEncodingType();
        }
        BiFunction<AttributeName, AttributeValue, ? extends KmipAttribute> attributeBuilder = KmipAttribute.getAttributeBuilderFromRegistry(
                attrTag,
                encodingType
        );
        return attributeBuilder.apply(attribute.getAttributeName(), attribute.getAttributeValue());
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
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(attributeName, attributeIndex, attributeValue)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }
}
