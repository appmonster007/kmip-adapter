package org.purpleBean.kmip.model.v3_0.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.util.Set;

/**
 * KMIP OtpDigits dataType (Integer).
 *
 * <p>Introduced in KMIP v3.0. Specifies the number of digits in the generated OTP.</p>
 */
@Data
@Builder(toBuilder = true)
public class OtpDigits implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.OTP_DIGITS.inst();
    public static final EncodingType encodingType = EncodingType.INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, OtpDigits.class);
        }
    }

    @NonNull
    private final Integer value;

    @Builder
    private OtpDigits(@NonNull Integer value) {
        this.value = value;
        validate();
    }

    public static OtpDigits of(@NonNull Integer value) {
        return new OtpDigits(value);
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
        return supportedVersions.contains(KmipContext.getSpec());
    }
}
