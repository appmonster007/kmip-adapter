package org.purpleBean.kmip.model.v1_2.structure.response.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * KMIP Import Response Payload (V1_2, V2_1, V3_0).
 *
 * <p>Per KMIP v1.2 spec:
 * <ul>
 *   <li>UniqueIdentifier — Required</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class ImportOpResponsePayload implements ResponsePayloadStructure {

    private static final Operation.Value operation = Operation.Standard.IMPORT;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ImportOpResponsePayload.class);
            ResponsePayloadStructure.register(spec, operation, ImportOpResponsePayload.class, ImportOpResponsePayload::of);
        }
    }

    @NonNull
    private final UniqueIdentifier uniqueIdentifier;

    @Builder
    private ImportOpResponsePayload(@NonNull UniqueIdentifier uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
        validate();
    }

    public static ImportOpResponsePayload of(List<KmipDataType> values) {
        var builder = ImportOpResponsePayload.builder();
        values.forEach(value -> {
            if (value instanceof UniqueIdentifier) builder.uniqueIdentifier((UniqueIdentifier) value);
        });
        return builder.build();
    }

    public static ImportOpResponsePayload of(@NonNull UniqueIdentifier uniqueIdentifier) {
        return ImportOpResponsePayload.builder().uniqueIdentifier(uniqueIdentifier).build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
    }

    @Override public KmipTag getKmipTag() { return kmipTag; }
    @Override public EncodingType getEncodingType() { return encodingType; }

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

    @Override public Operation getCorrespondingOperation() { return operation.inst(); }
}
