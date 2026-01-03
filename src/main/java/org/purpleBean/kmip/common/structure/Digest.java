package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.AttributeName;
import org.purpleBean.kmip.common.AttributeValue;
import org.purpleBean.kmip.common.DigestValue;
import org.purpleBean.kmip.common.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;
import org.purpleBean.kmip.common.enumeration.State;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * KMIP Digest attribute structure.
 */
@Data
@Builder
public class Digest implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.DIGEST);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, Digest.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, Digest.class, Digest::of);
        }
    }

    @NonNull
    private final HashingAlgorithm hashingAlgorithm;
    private final DigestValue digestValue;
    private final KeyFormatType keyFormatType;

    public Digest(
            @NonNull HashingAlgorithm hashingAlgorithm,
            DigestValue digestValue,
            KeyFormatType keyFormatType
    ) {
        this.hashingAlgorithm = Objects.requireNonNull(hashingAlgorithm, "HashingAlgorithm cannot be null");
        this.digestValue = digestValue;
        this.keyFormatType = keyFormatType;
    }

    public static Digest of(@NonNull AttributeName attributeName, @NonNull AttributeValue.Value attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValue.Structure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        DigestBuilder builder = Digest.builder();
        List<KmipDataType> fields = structure.getValue();
        for (KmipDataType field : fields) {
            if (field instanceof HashingAlgorithm hashingAlgorithm) {
                builder.hashingAlgorithm(hashingAlgorithm);
            } else if (field instanceof DigestValue digestValue) {
                builder.digestValue(digestValue);
            } else if (field instanceof KeyFormatType keyFormatType) {
                builder.keyFormatType(keyFormatType);
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
        return Stream.of(hashingAlgorithm, digestValue, keyFormatType).filter(Objects::nonNull).toList();
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec)
                && hashingAlgorithm.isSupported()
                && digestValue.isSupported()
                && keyFormatType.isSupported();
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

    public static class DigestBuilder {
        public Digest build() {
            validate();
            return new Digest(hashingAlgorithm, digestValue, keyFormatType);
        }

        private void validate() {
            Objects.requireNonNull(hashingAlgorithm, "HashingAlgorithm cannot be null");

            // Validate KMIP spec compatibility
            KmipSpec spec = KmipContext.getSpec();
            if (!hashingAlgorithm.isSupported()) {
                throw new IllegalArgumentException(
                        String.format("HashingAlgorithm is not supported for KMIP spec %s", spec)
                );
            }
            if (!digestValue.isSupported()) {
                throw new IllegalArgumentException(
                        String.format("DigestValue is not supported for KMIP spec %s", spec)
                );
            }
            if (!keyFormatType.isSupported()) {
                throw new IllegalArgumentException(
                        String.format("KeyFormatType is not supported for KMIP spec %s", spec)
                );
            }
        }
    }
}