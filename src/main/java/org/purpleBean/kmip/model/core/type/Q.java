package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.math.BigInteger;
import java.util.Set;

/**
 * KMIP Q dataType.
 */
@Data
@Builder(toBuilder = true)
public class Q implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.Q.inst();
    public static final EncodingType encodingType = EncodingType.BIG_INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, Q.class);
        }
    }


    @NonNull
    private final BigInteger value;

    @Builder
    private Q(@NonNull BigInteger value) {
        this.value = value;
        validate();
    }

    public static Q of(@NonNull BigInteger value) {
        return new Q(value);
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
