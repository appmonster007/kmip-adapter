package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.SignatureData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class SignatureVerifyOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.SIGNATURE_VERIFY;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, SignatureVerifyOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, SignatureVerifyOpRequestPayload.class, SignatureVerifyOpRequestPayload::of);
        }
    }

    private final UniqueIdentifier uniqueIdentifier;

    private final CryptographicParameters cryptographicParameters;

    private final DataByteString data;

    @NonNull
    private final SignatureData signatureData;

    @Builder
    private SignatureVerifyOpRequestPayload(
            UniqueIdentifier uniqueIdentifier,
            CryptographicParameters cryptographicParameters,
            DataByteString data,
            @NonNull SignatureData signatureData
    ) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.cryptographicParameters = cryptographicParameters;
        this.data = data;
        this.signatureData = signatureData;
        validate();
    }

    public static SignatureVerifyOpRequestPayload of(List<KmipDataType> values) {
        var builder = SignatureVerifyOpRequestPayload.builder();
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
        if (map.containsKey(SignatureData.kmipTag)) {
            builder.signatureData((SignatureData) map.get(SignatureData.kmipTag).getFirst());
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
        return supportedVersions.contains(spec) && getValue().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValue() {
        return Stream.of(
                        uniqueIdentifier,
                        cryptographicParameters,
                        data,
                        signatureData)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Operation getCorrespondingOperation() {
        return operation.inst();
    }
}
