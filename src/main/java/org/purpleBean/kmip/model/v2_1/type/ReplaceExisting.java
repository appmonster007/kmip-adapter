package org.purpleBean.kmip.model.v2_1.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.util.Set;

/**
 * KMIP ReplaceExisting dataType.
 */
@Data
@Builder(toBuilder = true)
public class ReplaceExisting implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.REPLACE_EXISTING.inst();
    public static final EncodingType encodingType = EncodingType.BOOLEAN;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ReplaceExisting.class);
        }
    }

    @NonNull
    private final Boolean value;

    @Builder
    private ReplaceExisting(@NonNull Boolean value) {
        this.value = value;
        validate();
    }

    public static ReplaceExisting of(@NonNull Boolean value) {
        return new ReplaceExisting(value);
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        // No validation needed for this structure
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
}
