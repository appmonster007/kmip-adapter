package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.Set;

/**
 * KMIP ContactInformation attribute.
 */
@Data
@Builder(toBuilder = true)
public class ContactInformation implements KmipDataType, KmipAttribute {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.CONTACT_INFORMATION);
    public static final EncodingType encodingType = EncodingType.TEXT_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ContactInformation.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, ContactInformation.class, ContactInformation::of);
        }
    }

    @NonNull
    private final String value;

    public static ContactInformation of(@NonNull String value) {
        return ContactInformation.builder().value(value).build();
    }

    public static ContactInformation of(@NonNull AttributeName attributeName, @NonNull AttributeValue.Value attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValue.TextString value)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        return new ContactInformation(value.getValue());
    }

    @Override
    public AttributeValue.Value getAttributeValue() {
        return AttributeValue.TextString.of(value);
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }

    @Override
    public String getCanonicalName() {
        return getAttributeName().getValue();
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
}