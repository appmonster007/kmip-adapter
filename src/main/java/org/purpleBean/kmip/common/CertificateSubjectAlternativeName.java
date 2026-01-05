package org.purpleBean.kmip.common;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.util.Set;

/**
 * KMIP CertificateSubjectAlternativeName dataType.
 */
@Data
@Builder(toBuilder = true)
public class CertificateSubjectAlternativeName implements KmipDataType {

    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.CERTIFICATE_SUBJECT_ALTERNATIVE_NAME);
    public static final EncodingType encodingType = EncodingType.TEXT_STRING;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CertificateSubjectAlternativeName.class);
        }
    }


    @NonNull
    private final String value;

    public static CertificateSubjectAlternativeName of(@NonNull String value) {
        return CertificateSubjectAlternativeName.builder().value(value).build();
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