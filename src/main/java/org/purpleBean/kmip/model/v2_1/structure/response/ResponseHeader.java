package org.purpleBean.kmip.model.v2_1.structure.response;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponseHeaderStructure;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;
import org.purpleBean.kmip.model.core.structure.Nonce;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;
import org.purpleBean.kmip.model.core.type.BatchCount;
import org.purpleBean.kmip.model.core.type.TimeStamp;
import org.purpleBean.kmip.model.v2_1.type.ServerCorrelationValue;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class ResponseHeader implements ResponseHeaderStructure {

    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ResponseHeader.class);
            ResponseHeaderStructure.register(spec, ResponseHeader.class, ResponseHeader::of);
        }
    }

    @NonNull
    private final ProtocolVersion protocolVersion;

    @NonNull
    private final TimeStamp timeStamp;

    private final Nonce nonce;

    private final ServerCorrelationValue serverCorrelationValue;

    @Singular
    private final List<AttestationType> attestationTypes;

    @NonNull
    private final BatchCount batchCount;

    @Builder
    private ResponseHeader(
            @NonNull ProtocolVersion protocolVersion,
            @NonNull TimeStamp timeStamp,
            Nonce nonce,
            ServerCorrelationValue serverCorrelationValue,
            List<AttestationType> attestationTypes,
            @NonNull BatchCount batchCount
    ) {
        this.protocolVersion = protocolVersion;
        this.timeStamp = timeStamp;
        this.nonce = nonce;
        this.serverCorrelationValue = serverCorrelationValue;
        this.attestationTypes = attestationTypes;
        this.batchCount = batchCount;
        validate();
    }

    public static ResponseHeader of(List<KmipDataType> values) {
        var builder = ResponseHeader.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(ProtocolVersion.kmipTag)) builder.protocolVersion((ProtocolVersion) map.get(ProtocolVersion.kmipTag).getFirst());
        if (map.containsKey(TimeStamp.kmipTag)) builder.timeStamp((TimeStamp) map.get(TimeStamp.kmipTag).getFirst());
        if (map.containsKey(Nonce.kmipTag)) builder.nonce((Nonce) map.get(Nonce.kmipTag).getFirst());
        if (map.containsKey(ServerCorrelationValue.kmipTag)) builder.serverCorrelationValue((ServerCorrelationValue) map.get(ServerCorrelationValue.kmipTag).getFirst());
        if (map.containsKey(AttestationType.kmipTag)) map.get(AttestationType.kmipTag).forEach(item -> builder.attestationType((AttestationType) item));
        if (map.containsKey(BatchCount.kmipTag)) builder.batchCount((BatchCount) map.get(BatchCount.kmipTag).getFirst());
        return builder.build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
    }

    @Override
    public KmipTag getKmipTag() { return kmipTag; }

    @Override
    public EncodingType getEncodingType() { return encodingType; }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(
                        protocolVersion,
                        timeStamp,
                        nonce,
                        serverCorrelationValue,
                        attestationTypes,
                        batchCount)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }
}
