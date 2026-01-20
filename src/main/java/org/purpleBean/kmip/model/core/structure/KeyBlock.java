package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.KeyCompressionType;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.type.CryptographicLength;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class KeyBlock implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.KEY_BLOCK.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, KeyBlock.class);
        }
    }

    @NonNull
    private final KeyFormatType keyFormatType;

    private final KeyCompressionType keyCompressionType;

    private final KeyValue keyValue;

    private final CryptographicAlgorithm cryptographicAlgorithm;

    private final CryptographicLength cryptographicLength;

    private final KeyWrappingData keyWrappingData;

    @Builder
    private KeyBlock(
            @NonNull KeyFormatType keyFormatType,
            KeyCompressionType keyCompressionType,
            KeyValue keyValue,
            CryptographicAlgorithm cryptographicAlgorithm,
            CryptographicLength cryptographicLength,
            KeyWrappingData keyWrappingData
    ) {
        this.keyFormatType = keyFormatType;
        this.keyCompressionType = keyCompressionType;
        this.keyValue = keyValue;
        this.cryptographicAlgorithm = cryptographicAlgorithm;
        this.cryptographicLength = cryptographicLength;
        this.keyWrappingData = keyWrappingData;
        validate();
    }

    public static KeyBlock of(
            @NonNull KeyFormatType keyFormatType,
            KeyCompressionType keyCompressionType,
            KeyValue keyValue,
            CryptographicAlgorithm cryptographicAlgorithm,
            CryptographicLength cryptographicLength,
            KeyWrappingData keyWrappingData
    ) {
        return KeyBlock.builder()
                .keyFormatType(keyFormatType)
                .keyCompressionType(keyCompressionType)
                .keyValue(keyValue)
                .cryptographicAlgorithm(cryptographicAlgorithm)
                .cryptographicLength(cryptographicLength)
                .keyWrappingData(keyWrappingData)
                .build();
    }

    private void validate() {
        isSupported();
        Objects.requireNonNull(keyFormatType, "KeyFormatType cannot be null");
        if (cryptographicAlgorithm != null && cryptographicLength == null) {
            throw new IllegalStateException("CryptographicLength must be present if CryptographicAlgorithm is present");
        }
        if (cryptographicAlgorithm == null && cryptographicLength != null) {
            throw new IllegalStateException("CryptographicAlgorithm must be present if CryptographicLength is present");
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
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return Stream.of(
                        keyFormatType,
                        keyCompressionType,
                        keyValue,
                        cryptographicAlgorithm,
                        cryptographicLength,
                        keyWrappingData)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}