package org.purpleBean.kmip.model.v2_1.structure.response.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.IVCounterNonce;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.type.AuthenticatedEncryptionTag;
import org.purpleBean.kmip.model.v2_1.type.CorrelationValue;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class EncryptOpResponsePayload implements ResponsePayloadStructure {

    private static final Operation.Value operation = Operation.Standard.ENCRYPT;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, EncryptOpResponsePayload.class);
            ResponsePayloadStructure.register(spec, operation, EncryptOpResponsePayload.class, EncryptOpResponsePayload::of);
        }
    }

    @NonNull
    private final UniqueIdentifier uniqueIdentifier;
    private final DataByteString data;
    private final IVCounterNonce ivCounterNonce;
    private final CorrelationValue correlationValue;
    private final AuthenticatedEncryptionTag authenticatedEncryptionTag;

    @Builder
    private EncryptOpResponsePayload(
            @NonNull UniqueIdentifier uniqueIdentifier,
            DataByteString data,
            IVCounterNonce ivCounterNonce,
            CorrelationValue correlationValue,
            AuthenticatedEncryptionTag authenticatedEncryptionTag
    ) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.data = data;
        this.ivCounterNonce = ivCounterNonce;
        this.correlationValue = correlationValue;
        this.authenticatedEncryptionTag = authenticatedEncryptionTag;
        validate();
    }

    public static EncryptOpResponsePayload of(List<KmipDataType> values) {
        var builder = EncryptOpResponsePayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(UniqueIdentifier.kmipTag)) {
            builder.uniqueIdentifier((UniqueIdentifier) map.get(UniqueIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(DataByteString.kmipTag)) {
            builder.data((DataByteString) map.get(DataByteString.kmipTag).getFirst());
        }
        if (map.containsKey(IVCounterNonce.kmipTag)) {
            builder.ivCounterNonce((IVCounterNonce) map.get(IVCounterNonce.kmipTag).getFirst());
        }
        if (map.containsKey(CorrelationValue.kmipTag)) {
            builder.correlationValue((CorrelationValue) map.get(CorrelationValue.kmipTag).getFirst());
        }
        if (map.containsKey(AuthenticatedEncryptionTag.kmipTag)) {
            builder.authenticatedEncryptionTag((AuthenticatedEncryptionTag) map.get(AuthenticatedEncryptionTag.kmipTag).getFirst());
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
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(uniqueIdentifier, data, ivCounterNonce, correlationValue, authenticatedEncryptionTag)
                .filter(Objects::nonNull)
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public Operation getCorrespondingOperation() {
        return operation.inst();
    }
}
