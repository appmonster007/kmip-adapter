package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.v2_1.type.LogMessage;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * KMIP Log Request Payload (V2_1, V3_0).
 *
 * <p>Per KMIP v2.1 spec:
 * <ul>
 *   <li>LogMessage — Required</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class LogOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.LOG;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, LogOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, LogOpRequestPayload.class, LogOpRequestPayload::of);
        }
    }

    @NonNull
    private final LogMessage logMessage;

    @Builder
    private LogOpRequestPayload(@NonNull LogMessage logMessage) {
        this.logMessage = logMessage;
        validate();
    }

    public static LogOpRequestPayload of(List<KmipDataType> values) {
        var builder = LogOpRequestPayload.builder();
        values.forEach(value -> {
            if (value instanceof LogMessage) builder.logMessage((LogMessage) value);
        });
        return builder.build();
    }

    public static LogOpRequestPayload of(@NonNull LogMessage logMessage) {
        return LogOpRequestPayload.builder()
                .logMessage(logMessage)
                .build();
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
        return Stream.of(logMessage)
                .filter(Objects::nonNull)
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public Operation getCorrespondingOperation() { return operation.inst(); }
}
