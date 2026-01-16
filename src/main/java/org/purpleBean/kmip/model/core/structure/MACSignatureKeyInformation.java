package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class MACSignatureKeyInformation implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.MAC_SIGNATURE_KEY_INFORMATION.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, MACSignatureKeyInformation.class);
        }
    }

    @NonNull
    private final UniqueIdentifier uniqueIdentifier;
    private final CryptographicParameters cryptographicParameters;

    @Builder
    private MACSignatureKeyInformation(@NonNull UniqueIdentifier uniqueIdentifier, CryptographicParameters cryptographicParameters) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.cryptographicParameters = cryptographicParameters;
        validate();
    }

    public static MACSignatureKeyInformation of(@NonNull UniqueIdentifier uniqueIdentifier, CryptographicParameters cryptographicParameters) {
        return MACSignatureKeyInformation.builder().uniqueIdentifier(uniqueIdentifier).cryptographicParameters(cryptographicParameters).build();
    }

    private void validate() {
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
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return Stream.of(uniqueIdentifier, cryptographicParameters)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}