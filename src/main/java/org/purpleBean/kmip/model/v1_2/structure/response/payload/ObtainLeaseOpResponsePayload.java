package org.purpleBean.kmip.model.v1_2.structure.response.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.type.LastChangeDate;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class ObtainLeaseOpResponsePayload implements ResponsePayloadStructure {

    private static final Operation.Value operation = Operation.Standard.OBTAIN_LEASE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ObtainLeaseOpResponsePayload.class);
            ResponsePayloadStructure.register(spec, operation, ObtainLeaseOpResponsePayload.class, ObtainLeaseOpResponsePayload::of);
        }
    }

    @NonNull
    private final UniqueIdentifier uniqueIdentifier;

    @NonNull
    private final LeaseTime leaseTime;

    @NonNull
    private final LastChangeDate lastChangeDate;

    @Builder
    private ObtainLeaseOpResponsePayload(
            @NonNull UniqueIdentifier uniqueIdentifier,
            @NonNull LeaseTime leaseTime,
            @NonNull LastChangeDate lastChangeDate
    ) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.leaseTime = leaseTime;
        this.lastChangeDate = lastChangeDate;
        validate();
    }

    public static ObtainLeaseOpResponsePayload of(List<KmipDataType> values) {
        var builder = ObtainLeaseOpResponsePayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(UniqueIdentifier.kmipTag)) {
            builder.uniqueIdentifier((UniqueIdentifier) map.get(UniqueIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(LeaseTime.kmipTag)) {
            builder.leaseTime((LeaseTime) map.get(LeaseTime.kmipTag).getFirst());
        }
        if (map.containsKey(LastChangeDate.kmipTag)) {
            builder.lastChangeDate((LastChangeDate) map.get(LastChangeDate.kmipTag).getFirst());
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
                        leaseTime,
                        lastChangeDate)
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
