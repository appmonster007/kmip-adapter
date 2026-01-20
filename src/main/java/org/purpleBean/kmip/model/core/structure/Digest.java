package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.enumeration.State;
import org.purpleBean.kmip.model.core.type.AttributeName;
import org.purpleBean.kmip.model.core.type.DigestValue;
import org.purpleBean.kmip.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP Digest attribute structure.
 */
@Data
@Builder(toBuilder = true)
public class Digest implements KmipStructure, KmipAttribute {

    public static final KmipTag kmipTag = KmipTag.Standard.DIGEST.inst();
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

    @Builder
    private Digest(
            @NonNull HashingAlgorithm hashingAlgorithm,
            DigestValue digestValue,
            KeyFormatType keyFormatType
    ) {
        this.hashingAlgorithm = hashingAlgorithm;
        this.digestValue = digestValue;
        this.keyFormatType = keyFormatType;
        validate();
    }

    public static Digest of(@NonNull AttributeName attributeName, @NonNull AttributeValue attributeValue) {
        if (attributeValue.getEncodingType() != encodingType || !(attributeValue instanceof AttributeValueStructure structure)) {
            throw new IllegalArgumentException("Invalid attribute value");
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return Digest.builder()
                .hashingAlgorithm((HashingAlgorithm) map.get(HashingAlgorithm.kmipTag).get(0))
                .digestValue((DigestValue) map.get(DigestValue.kmipTag).get(0))
                .keyFormatType((KeyFormatType) map.get(KeyFormatType.kmipTag).get(0))
                .build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!hashingAlgorithm.isSupported()) {
            throw new IllegalArgumentException(
                    String.format("HashingAlgorithm is not supported for KMIP spec %s", spec)
            );
        }
        if (digestValue != null && !digestValue.isSupported()) {
            throw new IllegalArgumentException(
                    String.format("DigestValue is not supported for KMIP spec %s", spec)
            );
        }
        if (keyFormatType != null && !keyFormatType.isSupported()) {
            throw new IllegalArgumentException(
                    String.format("KeyFormatType is not supported for KMIP spec %s", spec)
            );
        }
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
                && getValues().stream().allMatch(KmipDataType::isSupported);
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
    public AttributeValue getAttributeValue() {
        return AttributeValueStructure.of(getValues());
    }

    @Override
    public AttributeName getAttributeName() {
        return AttributeName.of(StringUtils.covertPascalToTitleCase(kmipTag.getDescription()));
    }
}