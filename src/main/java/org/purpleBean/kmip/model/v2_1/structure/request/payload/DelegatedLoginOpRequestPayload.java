package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.UsageLimits;
import org.purpleBean.kmip.model.core.type.LeaseTime;
import org.purpleBean.kmip.model.v2_1.structure.Rights;
import org.purpleBean.kmip.model.v2_1.type.RequestCount;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class DelegatedLoginOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.DELEGATED_LOGIN;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, DelegatedLoginOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, DelegatedLoginOpRequestPayload.class, DelegatedLoginOpRequestPayload::of);
        }
    }

    private final LeaseTime leaseTime;
    private final RequestCount requestCount;
    private final UsageLimits usageLimits;
    private final Rights rights;

    @Builder
    private DelegatedLoginOpRequestPayload(
            LeaseTime leaseTime,
            RequestCount requestCount,
            UsageLimits usageLimits,
            Rights rights
    ) {
        this.leaseTime = leaseTime;
        this.requestCount = requestCount;
        this.usageLimits = usageLimits;
        this.rights = rights;
        validate();
    }

    public static DelegatedLoginOpRequestPayload of(List<KmipDataType> values) {
        var builder = DelegatedLoginOpRequestPayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(LeaseTime.kmipTag)) {
            builder.leaseTime((LeaseTime) map.get(LeaseTime.kmipTag).getFirst());
        }
        if (map.containsKey(RequestCount.kmipTag)) {
            builder.requestCount((RequestCount) map.get(RequestCount.kmipTag).getFirst());
        }
        if (map.containsKey(UsageLimits.kmipTag)) {
            builder.usageLimits((UsageLimits) map.get(UsageLimits.kmipTag).getFirst());
        }
        if (map.containsKey(Rights.kmipTag)) {
            builder.rights((Rights) map.get(Rights.kmipTag).getFirst());
        }
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
        return Stream.of(leaseTime, requestCount, usageLimits, rights)
                .filter(Objects::nonNull)
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public Operation getCorrespondingOperation() { return operation.inst(); }
}
