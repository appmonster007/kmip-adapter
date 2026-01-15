package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.EncodingOption;
import org.purpleBean.kmip.model.core.enumeration.WrappingMethod;
import org.purpleBean.kmip.model.core.type.AttributeName;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class KeyWrappingSpecification implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.KEY_WRAPPING_SPECIFICATION.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, KeyWrappingSpecification.class);
        }
    }

    @NonNull
    private final WrappingMethod wrappingMethod;

    private final EncryptionKeyInformation encryptionKeyInformation;

    private final MACSignatureKeyInformation macSignatureKeyInformation;

    @NonNull
    @Singular
    private final List<AttributeName> attributeNames;

    private final EncodingOption encodingOption;

    // Custom constructor for Lombok's builder to handle the @Singular field
    @Builder
    private KeyWrappingSpecification(
            @NonNull WrappingMethod wrappingMethod,
            EncryptionKeyInformation encryptionKeyInformation,
            MACSignatureKeyInformation macSignatureKeyInformation,
            @NonNull List<AttributeName> attributeNames,
            EncodingOption encodingOption
    ) {
        this.wrappingMethod = wrappingMethod;
        this.encryptionKeyInformation = encryptionKeyInformation;
        this.macSignatureKeyInformation = macSignatureKeyInformation;
        this.attributeNames = attributeNames;
        this.encodingOption = encodingOption;
        validate();
    }

    public static KeyWrappingSpecification of(@NonNull KmipDataType value) {
        if (!(value instanceof KmipStructure structure)) {
            throw new IllegalArgumentException("Invalid value: " + value);
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream()
                .collect(Collectors.groupingBy(KmipDataType::getKmipTag));

        KeyWrappingSpecificationBuilder builder = KeyWrappingSpecification.builder();

        if (map.containsKey(WrappingMethod.kmipTag)) {
            builder.wrappingMethod((WrappingMethod) map.get(WrappingMethod.kmipTag).getFirst());
        }
        if (map.containsKey(EncryptionKeyInformation.kmipTag)) {
            builder.encryptionKeyInformation((EncryptionKeyInformation) map.get(EncryptionKeyInformation.kmipTag).getFirst());
        }
        if (map.containsKey(MACSignatureKeyInformation.kmipTag)) {
            builder.macSignatureKeyInformation((MACSignatureKeyInformation) map.get(MACSignatureKeyInformation.kmipTag).getFirst());
        }
        if (map.containsKey(AttributeName.kmipTag)) {
            map.get(AttributeName.kmipTag).forEach(item -> builder.attributeName((AttributeName) item));
        }
        if (map.containsKey(EncodingOption.kmipTag)) {
            builder.encodingOption((EncodingOption) map.get(EncodingOption.kmipTag).getFirst());
        }

        return builder.build();
    }

    private void validate() {
        Objects.requireNonNull(wrappingMethod, "wrappingMethod cannot be null");
        Objects.requireNonNull(attributeNames, "attributeNames cannot be null");
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
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return Stream.of(
                        wrappingMethod,
                        encryptionKeyInformation,
                        macSignatureKeyInformation,
                        attributeNames,
                        encodingOption)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .collect(Collectors.toList());
    }
}