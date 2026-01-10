package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.Set;

/**
 * KMIP OperationPolicyName dataType.
 */
@Data
@Builder(toBuilder = true)
public class OperationPolicyName implements KmipDataType, KmipAttribute {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.OPERATION_POLICY_NAME);
    public static final EncodingType encodingType = EncodingType.TEXT_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, OperationPolicyName.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, OperationPolicyName.class, OperationPolicyName::of);
        }
    }


    @NonNull
    private final String value;

    public static OperationPolicyName of(@NonNull String value) {
        return OperationPolicyName.builder().value(value).build();
    }

    public static OperationPolicyName of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueTextString textString)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        return new OperationPolicyName(textString.getValue());
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
        return false;
    }

    @Override
    public boolean isClientDeletable() {
        return false;
    }

    @Override
    public boolean isMultiInstanceAllowed() {
        return false;
    }

    @Override
    public AttributeValue getAttributeValue() {
        return AttributeValueTextString.of(value);
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }

    @Override
    public String getCanonicalName() {
        return getAttributeName().getValue();
    }
}
