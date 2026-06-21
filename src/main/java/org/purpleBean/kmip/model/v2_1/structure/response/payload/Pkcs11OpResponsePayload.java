package org.purpleBean.kmip.model.v2_1.structure.response.payload;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.util.List;
import java.util.Set;

/**
 * KMIP Pkcs11 Response Payload (stub). Field-level design pending — currently registers as an
 * empty structure so the codec registry is populated; downstream work needs to add the
 * actual request fields per OASIS KMIP spec for Pkcs11.
 */
@Data
@Builder(toBuilder = true)
public class Pkcs11OpResponsePayload implements ResponsePayloadStructure {

    private static final Operation.Value operation = Operation.Standard.PKCS_11;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, Pkcs11OpResponsePayload.class);
            ResponsePayloadStructure.register(spec, operation, Pkcs11OpResponsePayload.class, Pkcs11OpResponsePayload::of);
        }
    }

    @Builder
    private Pkcs11OpResponsePayload() {
        validate();
    }

    public static Pkcs11OpResponsePayload of(List<KmipDataType> values) {
        return Pkcs11OpResponsePayload.builder().build();
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
