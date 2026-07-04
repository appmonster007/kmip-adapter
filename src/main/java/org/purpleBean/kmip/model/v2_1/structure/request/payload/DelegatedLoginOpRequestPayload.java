package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.util.List;
import java.util.Set;

/**
 * KMIP DelegatedLogin Request Payload (V2.1+, §6.1.12).
 *
 * <p>Per spec, required field: Rights (structure containing Right sub-structures that define
 * allowed operations, objects, and object groups). Optional fields: LeaseTime, RequestCount, UsageLimits.
 * Full implementation is deferred until Rights/Right structures are implemented (they depend on
 * Operations, Objects, ObjectGroups sub-structures that do not yet exist in the model layer).
 */
@Data
@Builder(toBuilder = true)
public class DelegatedLoginOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.DELEGATED_LOGIN;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, DelegatedLoginOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, DelegatedLoginOpRequestPayload.class, DelegatedLoginOpRequestPayload::of);
        }
    }

    @Builder
    private DelegatedLoginOpRequestPayload() {
        validate();
    }

    public static DelegatedLoginOpRequestPayload of(List<KmipDataType> values) {
        return DelegatedLoginOpRequestPayload.builder().build();
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
