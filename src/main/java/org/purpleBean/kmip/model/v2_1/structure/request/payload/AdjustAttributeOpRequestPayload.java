package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.v2_1.enumeration.AdjustmentType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.CurrentAttribute;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * KMIP AdjustAttribute Request Payload (V2_1, V3_0).
 *
 * <p>Per KMIP v2.1 spec:
 * <ul>
 *   <li>UniqueIdentifier — Optional</li>
 *   <li>CurrentAttribute — Required</li>
 *   <li>AdjustmentType — Required</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class AdjustAttributeOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.ADJUST_ATTRIBUTE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, AdjustAttributeOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, AdjustAttributeOpRequestPayload.class, AdjustAttributeOpRequestPayload::of);
        }
    }

    private final UniqueIdentifier uniqueIdentifier;

    @NonNull
    private final CurrentAttribute currentAttribute;

    @NonNull
    private final AdjustmentType adjustmentType;

    @Builder
    private AdjustAttributeOpRequestPayload(UniqueIdentifier uniqueIdentifier, @NonNull CurrentAttribute currentAttribute, @NonNull AdjustmentType adjustmentType) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.currentAttribute = currentAttribute;
        this.adjustmentType = adjustmentType;
        validate();
    }

    public static AdjustAttributeOpRequestPayload of(List<KmipDataType> values) {
        var builder = AdjustAttributeOpRequestPayload.builder();
        values.forEach(value -> {
            if (value instanceof UniqueIdentifier) builder.uniqueIdentifier((UniqueIdentifier) value);
            else if (value instanceof CurrentAttribute) builder.currentAttribute((CurrentAttribute) value);
            else if (value instanceof AdjustmentType) builder.adjustmentType((AdjustmentType) value);
        });
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
        return Stream.of(uniqueIdentifier, currentAttribute, adjustmentType)
                .filter(Objects::nonNull)
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public Operation getCorrespondingOperation() { return operation.inst(); }
}
