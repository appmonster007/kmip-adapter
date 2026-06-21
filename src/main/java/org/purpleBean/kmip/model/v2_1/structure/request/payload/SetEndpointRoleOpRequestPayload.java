package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.util.List;
import java.util.Set;

/**
 * KMIP SetEndpointRole Request Payload (stub). Field-level design pending — currently registers as an
 * empty structure so the codec registry is populated; downstream work needs to add the
 * actual request fields per OASIS KMIP spec for SetEndpointRole.
 */
@Data
@Builder(toBuilder = true)
public class SetEndpointRoleOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.SET_ENDPOINT_ROLE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, SetEndpointRoleOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, SetEndpointRoleOpRequestPayload.class, SetEndpointRoleOpRequestPayload::of);
        }
    }

    @Builder
    private SetEndpointRoleOpRequestPayload() {
        validate();
    }

    public static SetEndpointRoleOpRequestPayload of(List<KmipDataType> values) {
        return SetEndpointRoleOpRequestPayload.builder().build();
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
