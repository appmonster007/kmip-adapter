package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.ActivationDate;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

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
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ActivationDate.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, ActivationDate.class, ActivationDate::of);
        }
    }

    @NonNull
    private final AttributeName attributeName;
    @NonNull
    private final AttributeValue attributeValue;

    public CustomAttribute(AttributeName attributeName, AttributeValue attributeValue) {
        if (!isCustomAttribute(attributeName.getValue())) {
            throw new IllegalArgumentException("Custom attribute name is invalid");
        }
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public static CustomAttribute of(@NonNull String name, @NonNull Object value) {
        return CustomAttribute.builder().attributeName(AttributeName.of(name)).attributeValue(AttributeValue.of(value)).build();
    }

    public static boolean isCustomAttribute(@NonNull String name) {
        return isCustomServerAttribute(name) || isCustomClientAttribute(name);
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
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return supportedVersions.contains(spec);
    }

    @Override
    public List<KmipDataType> getValues() {
        List<KmipDataType> values = new ArrayList<>();
        values.add(attributeName);
        values.add(attributeValue);
        return values;
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
}
