package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.nio.ByteBuffer;
import java.util.Set;

/**
 * KMIP DigestValue dataType.
 */
@Data
@Builder
public class DigestValue implements KmipDataType {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.DIGEST_VALUE);
    public static final EncodingType encodingType = EncodingType.BYTE_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, DigestValue.class);
        }
    }

    @NonNull
    private final ByteBuffer value;

    public static DigestValue of(@NonNull ByteBuffer value) {
        return DigestValue.builder().value(value).build();
    }

    public static DigestValue of(byte[] value) {
        return DigestValue.builder().value(ByteBuffer.wrap(value)).build();
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