package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * KMIP ReProvision Request Payload (V2_1, V3_0).
 *
 * <p>Per KMIP v2.1 spec:
 * <ul>
 *   <li>UniqueIdentifier — Required</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class ReProvisionOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.RE_PROVISION;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ReProvisionOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, ReProvisionOpRequestPayload.class, ReProvisionOpRequestPayload::of);
        }
    }

    @NonNull
    private final UniqueIdentifier uniqueIdentifier;

    @Builder
    private ReProvisionOpRequestPayload(@NonNull UniqueIdentifier uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
        validate();
    }

    public static ReProvisionOpRequestPayload of(List<KmipDataType> values) {
        var builder = ReProvisionOpRequestPayload.builder();
        values.forEach(value -> {
            if (value instanceof UniqueIdentifier) builder.uniqueIdentifier((UniqueIdentifier) value);
        });
        return builder.build();
    }

    public static ReProvisionOpRequestPayload of(@NonNull UniqueIdentifier uniqueIdentifier) {
        return ReProvisionOpRequestPayload.builder().uniqueIdentifier(uniqueIdentifier).build();
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
        return Stream.of(uniqueIdentifier)
                .filter(Objects::nonNull)
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public Operation getCorrespondingOperation() { return operation.inst(); }
}
