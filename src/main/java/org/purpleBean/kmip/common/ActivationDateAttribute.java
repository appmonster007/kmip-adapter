package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipAttribute;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
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
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ACTIVATION_DATE);
    private final EncodingType encodingType = EncodingType.DATE_TIME;

    private final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    // Capability flags — adjust based on attribute semantics
    private final boolean alwaysPresent = false;
    private final boolean serverInitializable = true;
    private final boolean clientInitializable = true;
    private final boolean clientDeletable = false;
    private final boolean multiInstanceAllowed = false;

    @NonNull
    private final OffsetDateTime dateTime;

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
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return supportedVersions.contains(spec);
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
