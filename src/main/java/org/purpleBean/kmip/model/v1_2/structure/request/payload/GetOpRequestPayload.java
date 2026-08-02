package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.KeyCompressionType;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.enumeration.KeyWrapType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class GetOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.GET;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, GetOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, GetOpRequestPayload.class, GetOpRequestPayload::of);
        }
    }

    private final UniqueIdentifier uniqueIdentifier;
    private final KeyFormatType keyFormatType;
    private final KeyWrapType keyWrapType;
    private final KeyCompressionType keyCompressionType;
    private final KeyWrappingSpecification keyWrappingSpecification;

    @Builder
    private GetOpRequestPayload(
            UniqueIdentifier uniqueIdentifier,
            KeyFormatType keyFormatType,
            KeyWrapType keyWrapType,
            KeyCompressionType keyCompressionType,
            KeyWrappingSpecification keyWrappingSpecification
    ) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.keyFormatType = keyFormatType;
        this.keyWrapType = keyWrapType;
        this.keyCompressionType = keyCompressionType;
        this.keyWrappingSpecification = keyWrappingSpecification;
        validate();
    }

    public static GetOpRequestPayload of(List<KmipDataType> values) {
        var builder = GetOpRequestPayload.builder();
        values.forEach(value -> {
            if (value instanceof UniqueIdentifier) {
                builder.uniqueIdentifier((UniqueIdentifier) value);
            } else if (value instanceof KeyFormatType) {
                builder.keyFormatType((KeyFormatType) value);
            } else if (value instanceof KeyWrapType) {
                builder.keyWrapType((KeyWrapType) value);
            } else if (value instanceof KeyCompressionType) {
                builder.keyCompressionType((KeyCompressionType) value);
            } else if (value instanceof KeyWrappingSpecification) {
                builder.keyWrappingSpecification((KeyWrappingSpecification) value);
            }
        });
        return builder.build();
    }

    public static GetOpRequestPayload of(
            UniqueIdentifier uniqueIdentifier,
            KeyFormatType keyFormatType,
            KeyCompressionType keyCompressionType,
            KeyWrappingSpecification keyWrappingSpecification
    ) {
        return GetOpRequestPayload.builder()
                .uniqueIdentifier(uniqueIdentifier)
                .keyFormatType(keyFormatType)
                .keyCompressionType(keyCompressionType)
                .keyWrappingSpecification(keyWrappingSpecification)
                .build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        // No validation required for this structure
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
        return Stream.of(
                        uniqueIdentifier,
                        keyFormatType,
                        keyWrapType,
                        keyCompressionType,
                        keyWrappingSpecification)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public Operation getCorrespondingOperation() {
        return operation.inst();
    }
}
