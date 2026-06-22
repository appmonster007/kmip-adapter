package org.purpleBean.kmip.model.v2_1.structure.response.payload;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.util.List;
import java.util.Set;

/**
 * KMIP ReProvision Response Payload (V2_1).
 *
 * <p>Per KMIP v2.1 spec §6, this response payload defines no fields.
 */
@Data
@Builder(toBuilder = true)
public class ReProvisionOpResponsePayload implements ResponsePayloadStructure {

    private static final Operation.Value operation = Operation.Standard.RE_PROVISION;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ReProvisionOpResponsePayload.class);
            ResponsePayloadStructure.register(spec, operation, ReProvisionOpResponsePayload.class, ReProvisionOpResponsePayload::of);
        }
    }

    @Builder
    private ReProvisionOpResponsePayload() {
        validate();
    }

    public static ReProvisionOpResponsePayload of(List<KmipDataType> values) {
        return ReProvisionOpResponsePayload.builder().build();
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
