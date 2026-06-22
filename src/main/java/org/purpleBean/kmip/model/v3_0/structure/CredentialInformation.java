package org.purpleBean.kmip.model.v3_0.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

/**
 * KMIP CredentialInformation structure (KMIP v3.0).
 *
 * <p>Contains one or more {@link CredentialType} values indicating the types of
 * credentials associated with a managed object.</p>
 *
 * <ul>
 *   <li>{@code credentialTypes} — one or more CredentialType enumerations (tag CREDENTIAL_TYPE 0x420024)</li>
 * </ul>
 */
@Data
@Builder(toBuilder = true)
public class CredentialInformation implements KmipStructure {

    public static final KmipTag kmipTag = KmipTag.Standard.CREDENTIAL_INFORMATION.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CredentialInformation.class);
        }
    }

    @NonNull
    @Singular
    private final List<CredentialType> credentialTypes;

    @Builder
    private CredentialInformation(@NonNull List<CredentialType> credentialTypes) {
        this.credentialTypes = (credentialTypes == null) ? Collections.emptyList() : List.copyOf(credentialTypes);
        validate();
    }

    public static CredentialInformation of(@NonNull List<CredentialType> credentialTypes) {
        return CredentialInformation.builder()
                .credentialTypes(credentialTypes)
                .build();
    }

    public static CredentialInformation of(@NonNull CredentialType credentialType) {
        return CredentialInformation.builder()
                .credentialType(credentialType)
                .build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        if (credentialTypes == null || credentialTypes.isEmpty()) {
            throw new IllegalArgumentException("CredentialInformation requires at least one CredentialType");
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
        return credentialTypes.stream()
                .filter(Objects::nonNull)
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }
}
