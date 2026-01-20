package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.nio.ByteBuffer;
import java.util.Set;

/**
 * KMIP IssuerAlternativeName dataType.
 */
@Data
@Builder(toBuilder = true)
public class IssuerAlternativeName implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.ISSUER_ALTERNATIVE_NAME.inst();
    public static final EncodingType encodingType = EncodingType.BYTE_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, IssuerAlternativeName.class);
        }
    }

    @NonNull
    private final ByteBuffer value;

    @Builder
    private IssuerAlternativeName(@NonNull ByteBuffer value) {
        this.value = value;
        validate();
    }

    public static IssuerAlternativeName of(@NonNull ByteBuffer value) {
        return IssuerAlternativeName.builder().value(value).build();
    }

    public static IssuerAlternativeName of(byte[] value) {
        return IssuerAlternativeName.builder().value(ByteBuffer.wrap(value)).build();
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
