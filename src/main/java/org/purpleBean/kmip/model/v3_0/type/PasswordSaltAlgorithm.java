package org.purpleBean.kmip.model.v3_0.type;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;

import java.util.Set;

/**
 * KMIP PasswordSaltAlgorithm dataType (Enumeration).
 *
 * <p>Introduced in KMIP v3.0. Specifies the cryptographic algorithm used to compute
 * the password salt. Uses {@link CryptographicAlgorithm} values but is encoded under
 * its own tag ({@code PASSWORD_SALT_ALGORITHM = 0x4201A3}).</p>
 */
@Data
@Builder(toBuilder = true)
public class PasswordSaltAlgorithm implements KmipDataType {

    public static final KmipTag kmipTag = KmipTag.Standard.PASSWORD_SALT_ALGORITHM.inst();
    public static final EncodingType encodingType = EncodingType.ENUMERATION;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, PasswordSaltAlgorithm.class);
            KmipEnumeration.register(spec, kmipTag.getValue(), PasswordSaltAlgorithm::fromName, PasswordSaltAlgorithm::fromValue);
        }
    }

    @NonNull
    private final CryptographicAlgorithm.Value value;

    @Builder
    private PasswordSaltAlgorithm(@NonNull CryptographicAlgorithm.Value value) {
        this.value = value;
        validate();
    }

    public static PasswordSaltAlgorithm of(@NonNull CryptographicAlgorithm.Value value) {
        return new PasswordSaltAlgorithm(value);
    }

    public static CryptographicAlgorithm.Value fromName(String name) {
        return CryptographicAlgorithm.fromName(name);
    }

    public static CryptographicAlgorithm.Value fromValue(int value) {
        return CryptographicAlgorithm.fromValue(value);
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        if (!value.isSupported()) {
            throw new IllegalArgumentException(String.format("Value '%s' for PasswordSaltAlgorithm is not supported for KMIP spec %s", value.getDescription(), KmipContext.getSpec()));
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
    public Object getValue() {
        return value.getValue();
    }

    @Override
    public boolean isSupported() {
        return supportedVersions.contains(KmipContext.getSpec()) && value.isSupported();
    }
}
