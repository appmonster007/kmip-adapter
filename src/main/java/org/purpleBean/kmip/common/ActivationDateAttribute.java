package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.enumeration.State;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * KMIP ActivationDate attribute.
 */
@Data
@Builder
public class ActivationDateAttribute implements KmipAttribute {
    private static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ACTIVATION_DATE);
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);
    private static EncodingType encodingType = EncodingType.DATE_TIME;

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ActivationDateAttribute.class);
            KmipAttribute.register(spec, kmipTag.getValue(), encodingType, ActivationDateAttribute.class);
        }
    }

    // Capability flags — adjust based on attribute semantics
    private final boolean alwaysPresent = false;
    private final boolean serverInitializable = true;
    private final boolean clientInitializable = true;
    private final boolean clientDeletable = false;
    private final boolean multiInstanceAllowed = false;
    @NonNull
    private final OffsetDateTime dateTime;

    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public EncodingType getEncodingType() {
        return encodingType;
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return supportedVersions.contains(spec);
    }

    @Override
    public boolean isClientModifiable(@NonNull State state) {
        // PRE_ACTIVE is modifiable by default, adjust as needed
        return state.getValue().getValue() == State.Standard.PRE_ACTIVE.getValue();
    }

    @Override
    public boolean isServerModifiable(@NonNull State state) {
        // PRE_ACTIVE is modifiable by default, adjust as needed
        return state.getValue().getValue() == State.Standard.PRE_ACTIVE.getValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActivationDateAttribute that = (ActivationDateAttribute) o;
        // Compare OffsetDateTime up to seconds to avoid flakiness
        return this.dateTime.withNano(0).equals(that.dateTime.withNano(0));
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime.withNano(0));
    }
}
