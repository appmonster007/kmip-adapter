package org.purpleBean.kmip.model.v2_1.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.v2_1.enumeration.TicketType;
import org.purpleBean.kmip.model.v2_1.type.TicketValue;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP Ticket structure (V2.1+, tag 0x420149).
 *
 * <p>Contains a TicketType and a TicketValue opaque byte token, used in Login and DelegatedLogin operations.
 */
@Data
@Builder(toBuilder = true)
public class Ticket implements KmipStructure {

    public static final KmipTag kmipTag = KmipTag.Standard.TICKET.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, Ticket.class);
        }
    }

    @NonNull
    private final TicketType ticketType;
    @NonNull
    private final TicketValue ticketValue;

    @Builder
    private Ticket(@NonNull TicketType ticketType, @NonNull TicketValue ticketValue) {
        this.ticketType = ticketType;
        this.ticketValue = ticketValue;
        validate();
    }

    public static Ticket of(List<KmipDataType> values) {
        Map<KmipTag, List<KmipDataType>> map = values.stream()
                .collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return Ticket.builder()
                .ticketType((TicketType) map.get(TicketType.kmipTag).get(0))
                .ticketValue((TicketValue) map.get(TicketValue.kmipTag).get(0))
                .build();
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
        return Stream.of(ticketType, ticketValue)
                .filter(Objects::nonNull)
                .toArray(KmipDataType[]::new);
    }
}
