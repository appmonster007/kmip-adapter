package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.CredentialType;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
@Builder(toBuilder = true)
public class Credential implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.CREDENTIAL.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, Credential.class);
        }
    }

    @NonNull
    private final CredentialType credentialType;

    @NonNull
    private final CredentialValue credentialValue;

    @Builder
    private Credential(
            @NonNull CredentialType credentialType,
            @NonNull CredentialValue credentialValue
    ) {
        this.credentialType = credentialType;
        this.credentialValue = credentialValue;
        validate();
    }

    public static Credential of(
            @NonNull CredentialType credentialType,
            @NonNull CredentialValue credentialValue
    ) {
        return Credential.builder()
                .credentialType(credentialType)
                .credentialValue(credentialValue)
                .build();
    }

    private void validate() {
        Objects.requireNonNull(credentialType, "CredentialType cannot be null");
        Objects.requireNonNull(credentialValue, "CredentialValue cannot be null");
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
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return List.of(credentialType, credentialValue);
    }
}