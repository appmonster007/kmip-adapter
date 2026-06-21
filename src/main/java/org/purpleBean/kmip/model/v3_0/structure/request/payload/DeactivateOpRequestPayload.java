package org.purpleBean.kmip.model.v3_0.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.util.List;
import java.util.Set;

/**
 * KMIP Deactivate Request Payload (stub). Field-level design pending — currently registers as an
 * empty structure so the codec registry is populated; downstream work needs to add the
 * actual request fields per OASIS KMIP spec for Deactivate.
 */
@Data
@Builder(toBuilder = true)
public class DeactivateOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.DEACTIVATE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, DeactivateOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, DeactivateOpRequestPayload.class, DeactivateOpRequestPayload::of);
        }
    }

    @Builder
    private DeactivateOpRequestPayload() {
        validate();
    }

    public static DeactivateOpRequestPayload of(List<KmipDataType> values) {
        return DeactivateOpRequestPayload.builder().build();
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
    public KmipDataType[] getValue() { return new KmipDataType[0]; }

    @Override
    public Operation getCorrespondingOperation() { return operation.inst(); }
}
