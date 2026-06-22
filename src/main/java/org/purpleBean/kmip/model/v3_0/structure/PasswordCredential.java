package org.purpleBean.kmip.model.v3_0.structure;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.type.IterationCount;
import org.purpleBean.kmip.model.core.type.Password;
import org.purpleBean.kmip.model.core.type.PasswordSalt;
import org.purpleBean.kmip.model.core.type.PasswordSaltAlgorithm;
import org.purpleBean.kmip.model.core.type.SaltedPassword;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * KMIP PasswordCredential structure (KMIP v3.0).
 *
 * <p>Encodes a password-based credential per KMIP v3.0. All fields are optional
 * at the encoding level; the application is responsible for ensuring at least
 * one meaningful field is present.</p>
 *
 * <ul>
 *   <li>{@code password}              — optional TextString (tag PASSWORD 0x4200A1)</li>
 *   <li>{@code passwordSalt}          — optional ByteString (tag PASSWORD_SALT 0x4201A2)</li>
 *   <li>{@code passwordSaltAlgorithm} — optional Enumeration (tag PASSWORD_SALT_ALGORITHM 0x4201A3)</li>
 *   <li>{@code saltedPassword}        — optional ByteString (tag SALTED_PASSWORD 0x4201A4)</li>
 *   <li>{@code iterationCount}        — optional Integer (tag ITERATION_COUNT 0x42003C)</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class PasswordCredential implements KmipStructure {

    public static final KmipTag kmipTag = KmipTag.Standard.PASSWORD_CREDENTIAL.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, PasswordCredential.class);
        }
    }

    private final Password password;
    private final PasswordSalt passwordSalt;
    private final PasswordSaltAlgorithm passwordSaltAlgorithm;
    private final SaltedPassword saltedPassword;
    private final IterationCount iterationCount;

    @Builder
    private PasswordCredential(
            Password password,
            PasswordSalt passwordSalt,
            PasswordSaltAlgorithm passwordSaltAlgorithm,
            SaltedPassword saltedPassword,
            IterationCount iterationCount) {
        this.password = password;
        this.passwordSalt = passwordSalt;
        this.passwordSaltAlgorithm = passwordSaltAlgorithm;
        this.saltedPassword = saltedPassword;
        this.iterationCount = iterationCount;
        validate();
    }

    public static PasswordCredential of(
            Password password,
            PasswordSalt passwordSalt,
            PasswordSaltAlgorithm passwordSaltAlgorithm,
            SaltedPassword saltedPassword,
            IterationCount iterationCount) {
        return PasswordCredential.builder()
                .password(password)
                .passwordSalt(passwordSalt)
                .passwordSaltAlgorithm(passwordSaltAlgorithm)
                .saltedPassword(saltedPassword)
                .iterationCount(iterationCount)
                .build();
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
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(password, passwordSalt, passwordSaltAlgorithm, saltedPassword, iterationCount)
                .filter(Objects::nonNull)
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }
}
