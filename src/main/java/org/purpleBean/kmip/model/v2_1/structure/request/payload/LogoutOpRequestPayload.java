package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;

import java.util.List;
import java.util.Set;

/**
 * KMIP Logout Request Payload (stub).
 *
 * <p>Per KMIP v2.1 spec §6.1.31:
 * <ul>
 *   <li>Ticket — Required — the ticket to be invalidated</li>
 * </ul>
 * Blocked: {@code Ticket} structure not yet implemented.
 */
@Data
@Builder(toBuilder = true)
public class LogoutOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.LOGOUT;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, LogoutOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, LogoutOpRequestPayload.class, LogoutOpRequestPayload::of);
        }
    }

    @Builder
    private LogoutOpRequestPayload() {
        validate();
    }

    public static LogoutOpRequestPayload of(List<KmipDataType> values) {
        return LogoutOpRequestPayload.builder().build();
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
