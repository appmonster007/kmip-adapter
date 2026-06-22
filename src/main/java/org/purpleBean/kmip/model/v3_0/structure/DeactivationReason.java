package org.purpleBean.kmip.model.v3_0.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.DeactivationReasonCode;
import org.purpleBean.kmip.model.core.type.DeactivationMessage;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * KMIP DeactivationReason structure (KMIP v3.0).
 *
 * <p>Per KMIP v3.0 spec Table 97. Encodes the reason a managed object was deactivated.</p>
 *
 * <ul>
 *   <li>{@code deactivationReasonCode} — required enumeration (tag DEACTIVATION_REASON_CODE 0x4201B9)</li>
 *   <li>{@code deactivationMessage}    — optional TextString (tag DEACTIVATION_MESSAGE 0x4201B7)</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class DeactivationReason implements KmipStructure {

    public static final KmipTag kmipTag = KmipTag.Standard.DEACTIVATION_REASON.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, DeactivationReason.class);
        }
    }

    @NonNull
    private final DeactivationReasonCode deactivationReasonCode;

    private final DeactivationMessage deactivationMessage;

    @Builder
    private DeactivationReason(
            @NonNull DeactivationReasonCode deactivationReasonCode,
            DeactivationMessage deactivationMessage) {
        this.deactivationReasonCode = deactivationReasonCode;
        this.deactivationMessage = deactivationMessage;
        validate();
    }

    public static DeactivationReason of(
            @NonNull DeactivationReasonCode deactivationReasonCode,
            DeactivationMessage deactivationMessage) {
        return DeactivationReason.builder()
                .deactivationReasonCode(deactivationReasonCode)
                .deactivationMessage(deactivationMessage)
                .build();
    }

    public static DeactivationReason of(@NonNull DeactivationReasonCode deactivationReasonCode) {
        return DeactivationReason.builder()
                .deactivationReasonCode(deactivationReasonCode)
                .build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
    }

    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public EncodingType getEncodingType() {
        return encodingType;
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(deactivationReasonCode, deactivationMessage)
                .filter(Objects::nonNull)
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }
}
