package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.RevocationReason;
import org.purpleBean.kmip.model.core.type.CompromiseOccurrenceDate;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class RevokeOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.REVOKE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, RevokeOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, RevokeOpRequestPayload.class, RevokeOpRequestPayload::of);
        }
    }

    private final UniqueIdentifier uniqueIdentifier;

    @NonNull
    private final RevocationReason revocationReason;

    private final CompromiseOccurrenceDate compromiseOccurrenceDate;

    @Builder
    private RevokeOpRequestPayload(
            UniqueIdentifier uniqueIdentifier,
            @NonNull RevocationReason revocationReason,
            CompromiseOccurrenceDate compromiseOccurrenceDate
    ) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.revocationReason = revocationReason;
        this.compromiseOccurrenceDate = compromiseOccurrenceDate;
        validate();
    }

    public static RevokeOpRequestPayload of(List<KmipDataType> values) {
        var builder = RevokeOpRequestPayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(UniqueIdentifier.kmipTag)) {
            builder.uniqueIdentifier((UniqueIdentifier) map.get(UniqueIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(RevocationReason.kmipTag)) {
            builder.revocationReason((RevocationReason) map.get(RevocationReason.kmipTag).getFirst());
        }
        if (map.containsKey(CompromiseOccurrenceDate.kmipTag)) {
            builder.compromiseOccurrenceDate((CompromiseOccurrenceDate) map.get(CompromiseOccurrenceDate.kmipTag).getFirst());
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
        return Stream.of(
                        uniqueIdentifier,
                        revocationReason,
                        compromiseOccurrenceDate)
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
