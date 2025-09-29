package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP Attribute structure.
 */
@Data
@Builder
public class CustomAttribute implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ATTRIBUTE);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CustomAttribute.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, CustomAttribute.class, CustomAttribute::of);
        }
    }

    @NonNull
    private final AttributeName attributeName;
    @NonNull
    private final AttributeValue attributeValue;

    public CustomAttribute(AttributeName attributeName, AttributeValue attributeValue) {
        if (!isValidCustomAttributeName(attributeName.getValue())) {
            throw new IllegalArgumentException("Custom attribute name is invalid");
        }
        if (!isValidCustomAttributeValue(attributeValue)) {
            throw new IllegalArgumentException("Custom attribute value is invalid");
        }
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    private static boolean isValidCustomAttributeValue(@NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() == EncodingType.STRUCTURE) {
            return attributeValue.getValues().stream().noneMatch(value -> value instanceof KmipStructure);
        }
        return true;
    }

    public static CustomAttribute of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        return CustomAttribute.builder().attributeName(attributeName).attributeValue(attributeValue).build();
    }

    public static CustomAttribute of(@NonNull String name, @NonNull AttributeValue value) {
        return of(AttributeName.of(name), value);
    }

    public static CustomAttribute of(@NonNull AttributeName name, @NonNull Object... values) {
        return of(name, List.of(values));
    }

    public static CustomAttribute of(@NonNull String name, @NonNull Object... values) {
        return of(AttributeName.of(name), AttributeValue.of(List.of(values)));
    }

    public static CustomAttribute of(@NonNull AttributeName name, @NonNull Object value) {
        return of(name, AttributeValue.of(value));
    }

    public static CustomAttribute of(@NonNull String name, @NonNull Object value) {
        return of(AttributeName.of(name), AttributeValue.of(value));
    }

    public static boolean isValidCustomAttributeName(@NonNull String name) {
        return isCustomServerAttribute(name) || isCustomClientAttribute(name);
    }

    public static boolean isValidCustomAttributeName(@NonNull AttributeName name) {
        return isValidCustomAttributeName(name.getValue());
    }

    public static boolean isCustomServerAttribute(@NonNull String name) {
        Pattern pattern = Pattern.compile("^y-.*?", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(name).matches();
    }

    public static boolean isCustomClientAttribute(@NonNull String name) {
        Pattern pattern = Pattern.compile("^x-.*?", Pattern.CASE_INSENSITIVE);
        return pattern.matcher(name).matches();
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
        return Stream.of(attributeName, attributeValue)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
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
        return CustomAttribute.isCustomServerAttribute(attributeName.getValue());
    }

    @Override
    public boolean isClientModifiable(State state) {
        return CustomAttribute.isCustomClientAttribute(attributeName.getValue());
    }

    @Override
    public boolean isClientDeletable() {
        return CustomAttribute.isCustomClientAttribute(attributeName.getValue());
    }

    @Override
    public boolean isMultiInstanceAllowed() {
        return true;
    }

    @Override
    public String getCanonicalName() {
        return getAttributeName().getValue();
    }
}
