package org.purpleBean.kmip.common;

import lombok.*;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.KmipDataType;

import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP IssuerDistinguishedName dataType.
 */
@Data
@Builder
public class IssuerDistinguishedName implements KmipDataType {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ISSUER_DISTINGUISHED_NAME);
    public static final EncodingType encodingType = EncodingType.BYTE_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, IssuerDistinguishedName.class);
        }
    }

    @NonNull
    private final ByteBuffer value;
    
    public static IssuerDistinguishedName of(@NonNull ByteBuffer value) {
        return IssuerDistinguishedName.builder().value(value).build();
    }

    public static IssuerDistinguishedName of(byte[] value) {
        return IssuerDistinguishedName.builder().value(ByteBuffer.wrap(value)).build();
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
