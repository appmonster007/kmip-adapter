package org.purpleBean.kmip.common;

import lombok.*;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.KmipDataType;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * KMIP CertificateIssuerDistinguishedName dataType.
 */
@Data
@Builder(toBuilder = true)
public class CertificateIssuerDistinguishedName implements KmipDataType {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.CERTIFICATE_ISSUER_DISTINGUISHED_NAME);
    public static final EncodingType encodingType = EncodingType.TEXT_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_1);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CertificateIssuerDistinguishedName.class);
        }
    }

    @NonNull
    private final String value;

    public static CertificateIssuerDistinguishedName of(@NonNull String value) {
        return CertificateIssuerDistinguishedName.builder().value(value).build();
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