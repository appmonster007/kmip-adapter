package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP Process Request Payload.
 */
@Data
@Builder(toBuilder = true)
public class ProcessOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.PROCESS;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ProcessOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, ProcessOpRequestPayload.class, ProcessOpRequestPayload::of);
        }
    }

    @NonNull
    private final UniqueIdentifier uniqueIdentifier;

    private final AsynchronousCorrelationValue asynchronousCorrelationValue;

    private final ManagedObject object;

    @Builder
    private ProcessOpRequestPayload(
            @NonNull UniqueIdentifier uniqueIdentifier,
            AsynchronousCorrelationValue asynchronousCorrelationValue,
            ManagedObject object
    ) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.asynchronousCorrelationValue = asynchronousCorrelationValue;
        this.object = object;
        validate();
    }

    public static ProcessOpRequestPayload of(List<KmipDataType> values) {
        var builder = ProcessOpRequestPayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(UniqueIdentifier.kmipTag)) {
            builder.uniqueIdentifier((UniqueIdentifier) map.get(UniqueIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(AsynchronousCorrelationValue.kmipTag)) {
            builder.asynchronousCorrelationValue((AsynchronousCorrelationValue) map.get(AsynchronousCorrelationValue.kmipTag).getFirst());
        }
        values.stream()
                .filter(v -> !v.getKmipTag().equals(UniqueIdentifier.kmipTag)
                        && !v.getKmipTag().equals(AsynchronousCorrelationValue.kmipTag))
                .filter(v -> v instanceof ManagedObject)
                .findFirst()
                .ifPresent(v -> builder.object((ManagedObject) v));
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
                        asynchronousCorrelationValue,
                        object)
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
