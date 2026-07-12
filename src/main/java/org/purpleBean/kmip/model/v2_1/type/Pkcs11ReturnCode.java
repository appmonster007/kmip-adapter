package org.purpleBean.kmip.model.v2_1.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;

import java.util.Set;

/**
 * KMIP Pkcs11ReturnCode dataType.
 */
@Data
@Builder(toBuilder = true)
public class Pkcs11ReturnCode implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.PKCS_11_RETURN_CODE.inst();
    public static final EncodingType encodingType = EncodingType.INTEGER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, Pkcs11ReturnCode.class);
        }
    }

    @NonNull
    private final Integer value;

    @Builder
    private Pkcs11ReturnCode(@NonNull Integer value) {
        this.value = value;
        validate();
    }

    public static Pkcs11ReturnCode of(@NonNull Integer value) {
        return new Pkcs11ReturnCode(value);
    }

    public static Pkcs11ReturnCode of(int value) {
        return new Pkcs11ReturnCode(value);
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
