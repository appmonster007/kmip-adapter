package org.purpleBean.kmip.model.core.type.vendor;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

/**
 * KMIP TtlvDataType dataType.
 */
@Data
@Builder(toBuilder = true)
public class TtlvDataType implements KmipDataType {

    @NonNull
    private final KmipTag kmipTag;
    @NonNull
    private final EncodingType encodingType;
    @NonNull
    private final Object value;

    @Builder
    private TtlvDataType(
            @NonNull KmipTag kmipTag,
            @NonNull EncodingType encodingType,
            @NonNull Object value
    ) {
        this.value = value;
        this.kmipTag = kmipTag;
        this.encodingType = encodingType;
        validate();

        for (KmipSpec spec : kmipTag.getSupportedVersions()) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion || !kmipTag.isCustom()) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, TtlvDataType.class);
        }
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        if (!encodingType.getClazz().isInstance(value)) {
            throw new IllegalArgumentException(String.format("Invalid value for %s: %s", KmipContext.getSpec(), value));
        }
        // No validation needed for this structure
    }

    @Override
    public boolean isSupported() {
        return kmipTag.isSupported();
    }
}