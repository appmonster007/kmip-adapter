package org.purpleBean.kmip.model.v2_1.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.enumeration.ProcessingStage;
import org.purpleBean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purpleBean.kmip.model.v2_1.type.SubmissionDate;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class AsynchronousRequest implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.ASYNCHRONOUS_REQUEST.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, AsynchronousRequest.class);
        }
    }

    @NonNull
    private final AsynchronousCorrelationValue asynchronousCorrelationValue;
    @NonNull
    private final Operation operation;
    @NonNull
    private final SubmissionDate submissionDate;
    @NonNull
    private final ProcessingStage processingStage;

    @Builder
    private AsynchronousRequest(@NonNull AsynchronousCorrelationValue asynchronousCorrelationValue,
                                @NonNull Operation operation,
                                @NonNull SubmissionDate submissionDate,
                                @NonNull ProcessingStage processingStage) {
        this.asynchronousCorrelationValue = asynchronousCorrelationValue;
        this.operation = operation;
        this.submissionDate = submissionDate;
        this.processingStage = processingStage;
        validate();
    }

    public static AsynchronousRequest of(@NonNull AsynchronousCorrelationValue correlationValue,
                                         @NonNull Operation operation,
                                         @NonNull SubmissionDate submissionDate,
                                         @NonNull ProcessingStage processingStage) {
        return AsynchronousRequest.builder()
                .asynchronousCorrelationValue(correlationValue)
                .operation(operation)
                .submissionDate(submissionDate)
                .processingStage(processingStage)
                .build();
    }

    public static AsynchronousRequest of(@NonNull KmipDataType value) {
        if (!(value instanceof KmipStructure structure)) throw new IllegalArgumentException("Invalid value: " + value);
        var builder = AsynchronousRequest.builder();
        for (KmipDataType field : structure.getValue()) {
            if (field instanceof AsynchronousCorrelationValue v) builder.asynchronousCorrelationValue(v);
            else if (field instanceof Operation o) builder.operation(o);
            else if (field instanceof SubmissionDate d) builder.submissionDate(d);
            else if (field instanceof ProcessingStage s) builder.processingStage(s);
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
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(asynchronousCorrelationValue, operation, submissionDate, processingStage)
                .filter(Objects::nonNull)
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }
}
