package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.InteropFunction;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.type.InteropIdentifier;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * KMIP Interop Request Payload (V2_1, V3_0).
 *
 * <p>Per KMIP v2.1 spec:
 * <ul>
 *   <li>InteropFunction — Required</li>
 *   <li>InteropIdentifier — Optional</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class InteropOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.INTEROP;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, InteropOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, InteropOpRequestPayload.class, InteropOpRequestPayload::of);
        }
    }

    @NonNull
    private final InteropFunction interopFunction;
    private final InteropIdentifier interopIdentifier;

    @Builder
    private InteropOpRequestPayload(
            @NonNull InteropFunction interopFunction,
            InteropIdentifier interopIdentifier
    ) {
        this.interopFunction = interopFunction;
        this.interopIdentifier = interopIdentifier;
        validate();
    }

    public static InteropOpRequestPayload of(List<KmipDataType> values) {
        var builder = InteropOpRequestPayload.builder();
        values.forEach(value -> {
            if (value instanceof InteropFunction) builder.interopFunction((InteropFunction) value);
            else if (value instanceof InteropIdentifier) builder.interopIdentifier((InteropIdentifier) value);
        });
        return builder.build();
    }

    public static InteropOpRequestPayload of(
            @NonNull InteropFunction interopFunction,
            InteropIdentifier interopIdentifier
    ) {
        return InteropOpRequestPayload.builder()
                .interopFunction(interopFunction)
                .interopIdentifier(interopIdentifier)
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
        return Stream.of(interopFunction, interopIdentifier)
                .filter(Objects::nonNull)
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public Operation getCorrespondingOperation() { return operation.inst(); }
}
