package org.purpleBean.kmip.model.core.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.nio.ByteBuffer;
import java.util.Set;

/**
 * KMIP NonceValue dataType.
 */
@Data
@Builder(toBuilder = true)
public class NonceValue implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.NONCE_VALUE.inst();
    public static final EncodingType encodingType = EncodingType.BYTE_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, NonceValue.class);
        }
    }

    @NonNull
    private final ByteBuffer value;

    public static NonceValue of(@NonNull ByteBuffer value) {
        return NonceValue.builder().value(value).build();
    }

    public static NonceValue of(byte[] value) {
        return of(ByteBuffer.wrap(value));
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