package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class AttributeValueDateTime implements AttributeValue {
    public static final KmipTag kmipTag = AttributeValue.kmipTag;
    public static final EncodingType encodingType = EncodingType.DATE_TIME;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, AttributeValueDateTime.class);
        }
    }

    @NonNull
    private final OffsetDateTime value;

    public static AttributeValueDateTime of(@NonNull OffsetDateTime value) {
        return AttributeValueDateTime.builder().value(value).build();
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
        return supportedVersions.contains(spec);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttributeValueDateTime that = (AttributeValueDateTime) o;
        // Compare OffsetDateTime up to seconds to avoid flakiness
        return this.value.withNano(0).equals(that.value.withNano(0));
    }

    @Override
    public int hashCode() {
        return Objects.hash(value.withNano(0));
    }
}
