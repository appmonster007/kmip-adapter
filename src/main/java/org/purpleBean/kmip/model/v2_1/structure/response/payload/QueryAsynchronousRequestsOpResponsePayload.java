package org.purpleBean.kmip.model.v2_1.structure.response.payload;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class QueryAsynchronousRequestsOpResponsePayload implements ResponsePayloadStructure {

    private static final Operation.Value operation = Operation.Standard.QUERY_ASYNCHRONOUS_REQUESTS;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, QueryAsynchronousRequestsOpResponsePayload.class);
            ResponsePayloadStructure.register(spec, operation, QueryAsynchronousRequestsOpResponsePayload.class, QueryAsynchronousRequestsOpResponsePayload::of);
        }
    }

    @Singular
    private final List<AsynchronousCorrelationValue> asynchronousCorrelationValues;

    @Builder
    private QueryAsynchronousRequestsOpResponsePayload(List<AsynchronousCorrelationValue> asynchronousCorrelationValues) {
        this.asynchronousCorrelationValues = (asynchronousCorrelationValues == null) ? Collections.emptyList() : asynchronousCorrelationValues;
        validate();
    }

    public static QueryAsynchronousRequestsOpResponsePayload of(List<KmipDataType> values) {
        QueryAsynchronousRequestsOpResponsePayloadBuilder builder = QueryAsynchronousRequestsOpResponsePayload.builder();
        values.stream()
                .filter(v -> v instanceof AsynchronousCorrelationValue)
                .forEach(v -> builder.asynchronousCorrelationValue((AsynchronousCorrelationValue) v));
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
    public boolean isSupported() { return supportedVersions.contains(KmipContext.getSpec()); }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(asynchronousCorrelationValues)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public Operation getCorrespondingOperation() { return operation.inst(); }
}
