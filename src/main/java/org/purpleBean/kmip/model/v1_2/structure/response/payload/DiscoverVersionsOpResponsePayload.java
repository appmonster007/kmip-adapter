package org.purpleBean.kmip.model.v1_2.structure.response.payload;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.ProtocolVersion;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class DiscoverVersionsOpResponsePayload implements ResponsePayloadStructure {

    private static final Operation.Value operation = Operation.Standard.DISCOVER_VERSIONS;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, DiscoverVersionsOpResponsePayload.class);
            ResponsePayloadStructure.register(spec, operation, DiscoverVersionsOpResponsePayload.class, DiscoverVersionsOpResponsePayload::of);
        }
    }

    @Singular
    private final List<ProtocolVersion> protocolVersions;

    @Builder
    private DiscoverVersionsOpResponsePayload(
            List<ProtocolVersion> protocolVersions
    ) {
        this.protocolVersions = protocolVersions;
        validate();
    }

    public static DiscoverVersionsOpResponsePayload of(List<KmipDataType> values) {
        var builder = DiscoverVersionsOpResponsePayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(ProtocolVersion.kmipTag)) {
            map.get(ProtocolVersion.kmipTag).forEach(item -> builder.protocolVersion((ProtocolVersion) item));
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
        return Stream.of(protocolVersions)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public Operation getCorrespondingOperation() {
        return operation.inst();
    }
}
