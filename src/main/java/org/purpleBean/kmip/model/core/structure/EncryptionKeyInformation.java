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
public class EncryptionKeyInformation implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.ENCRYPTION_KEY_INFORMATION.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, EncryptionKeyInformation.class);
        }
    }

    @NonNull
    private final UniqueIdentifier uniqueIdentifier;
    private final CryptographicParameters cryptographicParameters;

    @Builder
    private EncryptionKeyInformation(@NonNull UniqueIdentifier uniqueIdentifier, CryptographicParameters cryptographicParameters) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.cryptographicParameters = cryptographicParameters;
        validate();
    }

    public static EncryptionKeyInformation of(@NonNull UniqueIdentifier uniqueIdentifier, CryptographicParameters cryptographicParameters) {
        return EncryptionKeyInformation.builder()
                .uniqueIdentifier(uniqueIdentifier)
                .cryptographicParameters(cryptographicParameters)
                .build();
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
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(uniqueIdentifier, cryptographicParameters)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }
}
