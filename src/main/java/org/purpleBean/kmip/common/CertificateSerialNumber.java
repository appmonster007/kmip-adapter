package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.nio.ByteBuffer;
import java.util.Set;

/**
 * KMIP CertificateSerialNumber dataType.
 */
@Data
@Builder
public class CertificateSerialNumber implements KmipDataType {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.CERTIFICATE_SERIAL_NUMBER);
    public static final EncodingType encodingType = EncodingType.BYTE_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CertificateSerialNumber.class);
        }
    }

    @NonNull
    private final ByteBuffer value;

    public static CertificateSerialNumber of(@NonNull ByteBuffer value) {
        return CertificateSerialNumber.builder().value(value).build();
    }

    public static CertificateSerialNumber of(byte[] value) {
        return CertificateSerialNumber.builder().value(ByteBuffer.wrap(value)).build();
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
