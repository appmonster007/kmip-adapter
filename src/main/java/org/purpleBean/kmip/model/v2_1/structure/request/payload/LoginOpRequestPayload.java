package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.util.List;
import java.util.Set;

/**
 * KMIP Login Request Payload (V2.1+, §6.1.30).
 *
 * <p>Per spec, all request fields are optional (LeaseTime, RequestCount, UsageLimits).
 * None of these field types currently support V2.1 in the model layer; an empty
 * payload is spec-valid for the minimal case (server uses session defaults).
 * Full field implementation is deferred until those types are extended to V2.1.
 */
@Data
@Builder(toBuilder = true)
public class LoginOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.LOGIN;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, LoginOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, LoginOpRequestPayload.class, LoginOpRequestPayload::of);
        }
    }

    @Builder
    private LoginOpRequestPayload() {
        validate();
    }

    public static LoginOpRequestPayload of(List<KmipDataType> values) {
        return LoginOpRequestPayload.builder().build();
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
