package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.nio.ByteBuffer;
import java.util.Set;

/**
 * KMIP IVCounterNonce dataType.
 */
@Data
@Builder(toBuilder = true)
public class IVCounterNonce implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.IV_COUNTER_NONCE.inst();
    public static final EncodingType encodingType = EncodingType.BYTE_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, IVCounterNonce.class);
        }
    }

    @NonNull
    private final ByteBuffer value;

    public static IVCounterNonce of(@NonNull ByteBuffer value) {
        return IVCounterNonce.builder().value(value).build();
    }

    public static IVCounterNonce of(byte[] value) {
        return IVCounterNonce.builder().value(ByteBuffer.wrap(value)).build();
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