package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.RevocationMessage;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * KMIP RevocationReason attribute structure.
 *
 * <p>Represents a RevocationReason in KMIP.</p>
 */
@Data
@Builder
public class RevocationReason implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.REVOCATION_REASON);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, RevocationReason.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, RevocationReason.class, RevocationReason::of);
        }
    }

    @NonNull
    private final RevocationReasonCode revocationReasonCode;
    private final RevocationMessage revocationMessage;

    public static RevocationReason of(@NonNull AttributeName attributeName, @NonNull AttributeValue.Value attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValue.Structure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        RevocationReasonBuilder builder = RevocationReason.builder();
        List<KmipDataType> fields = structure.getValue();
        for (KmipDataType field : fields) {
            if (field instanceof RevocationReasonCode revocationReasonCode) {
                builder.revocationReasonCode(revocationReasonCode);
            } else if (field instanceof RevocationMessage revocationMessage) {
                builder.revocationMessage(revocationMessage);
            } else {
                throw new IllegalArgumentException("Invalid field");
            }
        }
        return builder.build();
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
        return List.of(revocationReasonCode, revocationMessage);
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec)
                && revocationReasonCode.isSupported()
                && revocationMessage.isSupported();
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
        return false;
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

    public static class RevocationReasonBuilder {
        public RevocationReason build() {
            validate();
            return new RevocationReason(
                    revocationReasonCode,
                    revocationMessage
            );
        }

        private void validate() {
            Objects.requireNonNull(revocationReasonCode, "RevocationReasonCode cannot be null");
        }
    }
}