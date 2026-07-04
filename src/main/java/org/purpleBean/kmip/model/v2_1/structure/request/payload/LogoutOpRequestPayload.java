package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.v2_1.structure.Ticket;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * KMIP Logout Request Payload (V2.1+, §6.1.31).
 *
 * <p>Fields:
 * <ul>
 *   <li>Ticket — Required — the ticket to be invalidated</li>
 * </ul>
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

    @NonNull
    private final Ticket ticket;

    @Builder
    private LogoutOpRequestPayload(@NonNull Ticket ticket) {
        this.ticket = ticket;
        validate();
    }

    public static LogoutOpRequestPayload of(List<KmipDataType> values) {
        var builder = LogoutOpRequestPayload.builder();
        values.forEach(value -> {
            if (value instanceof Ticket) builder.ticket((Ticket) value);
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
        return Stream.of(ticket).filter(Objects::nonNull).map(KmipDataType.class::cast).toArray(KmipDataType[]::new);
    }

    @Override
    public Operation getCorrespondingOperation() { return operation.inst(); }
}
