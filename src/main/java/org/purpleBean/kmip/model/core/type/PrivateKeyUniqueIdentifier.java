package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.util.Set;

/**
 * KMIP {@code PrivateKeyUniqueIdentifier} dataType ({@code 0x420066}), encoded as a {@code TextString} (v1.2-v2.1).
 * <p>
 * Sibling of {@link org.purpleBean.kmip.model.v3_0.type.PrivateKeyUniqueIdentifier} (KMIP 3.0-only {@code Identifier}
 * wire type) — the two are unrelated Java types that happen to share a KMIP tag and value shape.
 */
@Data
@Builder(toBuilder = true)
public class PrivateKeyUniqueIdentifier implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.PRIVATE_KEY_UNIQUE_IDENTIFIER.inst();
    public static final EncodingType encodingType = EncodingType.TEXT_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2, KmipSpec.V1_3, KmipSpec.V1_4, KmipSpec.V2_0, KmipSpec.V2_1);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, PrivateKeyUniqueIdentifier.class);
        }
    }

    @NonNull
    private final String value;

    @Builder
    private PrivateKeyUniqueIdentifier(@NonNull String value) {
        this.value = value;
        validate();
    }

    public static PrivateKeyUniqueIdentifier of(@NonNull String value) {
        return new PrivateKeyUniqueIdentifier(value);
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
        return supportedVersions.contains(spec);
    }
}
