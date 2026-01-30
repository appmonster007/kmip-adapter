package org.purpleBean.kmip.model.core.type.vendor;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.util.Arrays;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TtlvDataType that)) return false;
        return Objects.equals(kmipTag, that.kmipTag) &&
                encodingType == that.encodingType &&
                deepEqualsValue(this.value, that.value);
    }

    private boolean deepEqualsValue(Object v1, Object v2) {
        if (v1 == v2) return true;
        if (v1 == null || v2 == null) return false;

        // Handle KmipDataType[] arrays
        if (v1 instanceof KmipDataType[] && v2 instanceof KmipDataType[]) {
            return Arrays.deepEquals((KmipDataType[]) v1, (KmipDataType[]) v2);
        }

        // Fallback to standard equals (for other types)
        return Objects.equals(v1, v2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kmipTag, encodingType, hashCodeValue(value));
    }

    private int hashCodeValue(Object value) {
        if (value instanceof KmipDataType[] kmipDataTypes) {
            return Arrays.hashCode(kmipDataTypes);
        }
        return Objects.hashCode(value);
    }
}