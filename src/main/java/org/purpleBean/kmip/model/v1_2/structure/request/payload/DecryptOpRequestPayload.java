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

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class DecryptOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.DECRYPT;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, DecryptOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, DecryptOpRequestPayload.class, DecryptOpRequestPayload::of);
        }
    }

    private final UniqueIdentifier uniqueIdentifier;

    private final CryptographicParameters cryptographicParameters;

    @NonNull
    private final DataByteString data;

    private final IVCounterNonce ivCounterNonce;

    @Builder
    private DecryptOpRequestPayload(
            UniqueIdentifier uniqueIdentifier,
            CryptographicParameters cryptographicParameters,
            @NonNull DataByteString data,
            IVCounterNonce ivCounterNonce
    ) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.cryptographicParameters = cryptographicParameters;
        this.data = data;
        this.ivCounterNonce = ivCounterNonce;
        validate();
    }

    public static DecryptOpRequestPayload of(List<KmipDataType> values) {
        var builder = DecryptOpRequestPayload.builder();
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
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return Stream.of(
                        uniqueIdentifier,
                        cryptographicParameters,
                        data,
                        ivCounterNonce)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Operation getCorrespondingOperation() {
        return operation.inst();
    }
}
