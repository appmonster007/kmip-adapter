package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestHeaderStructure;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;
import org.purpleBean.kmip.model.core.enumeration.BatchErrorContinuationOption;
import org.purpleBean.kmip.model.core.type.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class RequestHeader implements RequestHeaderStructure {

    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.V1_0, KmipSpec.V1_1, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, RequestHeader.class);
            RequestHeaderStructure.register(spec, encodingType, RequestHeader.class, RequestHeader::of);
        }
    }

    @NonNull
    private final ProtocolVersion protocolVersion;

    private final MaximumResponseSize maximumResponseSize;

    private final AsynchronousIndicator asynchronousIndicator;

    private final AttestationCapableIndicator attestationCapableIndicator;

    @Singular
    private final List<AttestationType> attestationTypes;

    private final Authentication authentication;

    private final BatchErrorContinuationOption batchErrorContinuationOption;

    private final BatchOrderOption batchOrderOption;

    private final TimeStamp timeStamp;

    @NonNull
    private final BatchCount batchCount;

    public static RequestHeader of(List<KmipDataType> values) {
        var builder = RequestHeader.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(ProtocolVersion.kmipTag)) {
            builder.protocolVersion((ProtocolVersion) map.get(ProtocolVersion.kmipTag).get(0));
        }
        if (map.containsKey(MaximumResponseSize.kmipTag)) {
            builder.maximumResponseSize((MaximumResponseSize) map.get(MaximumResponseSize.kmipTag).get(0));
        }
        if (map.containsKey(AsynchronousIndicator.kmipTag)) {
            builder.asynchronousIndicator((AsynchronousIndicator) map.get(AsynchronousIndicator.kmipTag).get(0));
        }
        if (map.containsKey(AttestationCapableIndicator.kmipTag)) {
            builder.attestationCapableIndicator((AttestationCapableIndicator) map.get(AttestationCapableIndicator.kmipTag).get(0));
        }
        if (map.containsKey(AttestationType.kmipTag)) {
            builder.attestationTypes(map.get(AttestationType.kmipTag).stream().map(AttestationType.class::cast).toList());
        }
        if (map.containsKey(Authentication.kmipTag)) {
            builder.authentication((Authentication) map.get(Authentication.kmipTag).get(0));
        }
        if (map.containsKey(BatchErrorContinuationOption.kmipTag)) {
            builder.batchErrorContinuationOption((BatchErrorContinuationOption) map.get(BatchErrorContinuationOption.kmipTag).get(0));
        }
        if (map.containsKey(BatchOrderOption.kmipTag)) {
            builder.batchOrderOption((BatchOrderOption) map.get(BatchOrderOption.kmipTag).get(0));
        }
        if (map.containsKey(TimeStamp.kmipTag)) {
            builder.timeStamp((TimeStamp) map.get(TimeStamp.kmipTag).get(0));
        }
        if (map.containsKey(BatchCount.kmipTag)) {
            builder.batchCount((BatchCount) map.get(BatchCount.kmipTag).get(0));
        }

        return builder.build();
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
    public List<KmipDataType> getValues() {
        return Stream.of(
                        protocolVersion,
                        maximumResponseSize,
                        asynchronousIndicator,
                        attestationCapableIndicator,
                        attestationTypes,
                        authentication,
                        batchErrorContinuationOption,
                        batchOrderOption,
                        timeStamp,
                        batchCount
                )
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }
}
