package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.IVCounterNonce;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.type.CorrelationValue;
import org.purpleBean.kmip.model.v2_1.type.FinalIndicator;
import org.purpleBean.kmip.model.v2_1.type.InitIndicator;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class EncryptOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.ENCRYPT;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, EncryptOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, EncryptOpRequestPayload.class, EncryptOpRequestPayload::of);
        }
    }

    private final UniqueIdentifier uniqueIdentifier;

    private final CryptographicParameters cryptographicParameters;

    private final DataByteString data;

    private final IVCounterNonce ivCounterNonce;

    private final InitIndicator initIndicator;

    private final FinalIndicator finalIndicator;

    private final CorrelationValue correlationValue;

    @Builder
    private EncryptOpRequestPayload(
            UniqueIdentifier uniqueIdentifier,
            CryptographicParameters cryptographicParameters,
            DataByteString data,
            IVCounterNonce ivCounterNonce,
            InitIndicator initIndicator,
            FinalIndicator finalIndicator,
            CorrelationValue correlationValue
    ) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.cryptographicParameters = cryptographicParameters;
        this.data = data;
        this.ivCounterNonce = ivCounterNonce;
        this.initIndicator = initIndicator;
        this.finalIndicator = finalIndicator;
        this.correlationValue = correlationValue;
        validate();
    }

    public static EncryptOpRequestPayload of(List<KmipDataType> values) {
        var builder = EncryptOpRequestPayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(UniqueIdentifier.kmipTag)) {
            builder.uniqueIdentifier((UniqueIdentifier) map.get(UniqueIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(CryptographicParameters.kmipTag)) {
            builder.cryptographicParameters((CryptographicParameters) map.get(CryptographicParameters.kmipTag).getFirst());
        }
        if (map.containsKey(DataByteString.kmipTag)) {
            builder.data((DataByteString) map.get(DataByteString.kmipTag).getFirst());
        }
        if (map.containsKey(IVCounterNonce.kmipTag)) {
            builder.ivCounterNonce((IVCounterNonce) map.get(IVCounterNonce.kmipTag).getFirst());
        }
        if (map.containsKey(InitIndicator.kmipTag)) builder.initIndicator((InitIndicator) map.get(InitIndicator.kmipTag).getFirst());
        if (map.containsKey(FinalIndicator.kmipTag)) builder.finalIndicator((FinalIndicator) map.get(FinalIndicator.kmipTag).getFirst());
        if (map.containsKey(CorrelationValue.kmipTag)) builder.correlationValue((CorrelationValue) map.get(CorrelationValue.kmipTag).getFirst());
        return builder.build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
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
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(
                        uniqueIdentifier,
                        cryptographicParameters,
                        data,
                        ivCounterNonce,
                        initIndicator,
                        finalIndicator,
                        correlationValue)
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
