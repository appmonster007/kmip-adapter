package org.purpleBean.kmip.model.v3_0.structure.response.payload;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.util.List;
import java.util.Set;

/**
 * KMIP CreateGroup Response Payload (stub). Field-level design pending — currently registers as an
 * empty structure so the codec registry is populated; downstream work needs to add the
 * actual request fields per OASIS KMIP spec for CreateGroup.
 */
@Data
@Builder(toBuilder = true)
public class CreateGroupOpResponsePayload implements ResponsePayloadStructure {

    private static final Operation.Value operation = Operation.Standard.CREATE_GROUP;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CreateGroupOpResponsePayload.class);
            ResponsePayloadStructure.register(spec, operation, CreateGroupOpResponsePayload.class, CreateGroupOpResponsePayload::of);
        }
    }

    @Builder
    private CreateGroupOpResponsePayload() {
        validate();
    }

    public static CreateGroupOpResponsePayload of(List<KmipDataType> values) {
        return CreateGroupOpResponsePayload.builder().build();
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
