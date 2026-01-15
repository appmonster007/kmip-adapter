package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.util.Set;

/**
 * KMIP CertificateSubjectDistinguishedName dataType.
 */
@Data
@Builder(toBuilder = true)
public class CertificateSubjectDistinguishedName implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.CERTIFICATE_SUBJECT_DISTINGUISHED_NAME.inst();
    public static final EncodingType encodingType = EncodingType.TEXT_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_1);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CertificateSubjectDistinguishedName.class);
        }
    }


    @NonNull
    private final String value;

    public static CertificateSubjectDistinguishedName of(@NonNull String value) {
        return CertificateSubjectDistinguishedName.builder().value(value).build();
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