package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.util.Set;

/**
 * KMIP PublicKeyUniqueIdentifier dataType.
 */
@Data
@Builder(toBuilder = true)
public class PublicKeyUniqueIdentifier implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.PUBLIC_KEY_UNIQUE_IDENTIFIER.inst();
    public static final EncodingType encodingType = EncodingType.TEXT_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, PublicKeyUniqueIdentifier.class);
        }
    }


    @NonNull
    private final String value;

    @Builder
    private PublicKeyUniqueIdentifier(@NonNull String value) {
        this.value = value;
        validate();
    }

    public static PublicKeyUniqueIdentifier of(@NonNull String value) {
        return new PublicKeyUniqueIdentifier(value);
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
