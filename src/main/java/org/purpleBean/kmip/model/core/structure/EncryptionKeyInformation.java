package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;
import java.util.Map;
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

    public static EncryptionKeyInformation of(@NonNull KmipDataType value) {
        if (!(value instanceof KmipStructure structure)) {
            throw new IllegalArgumentException("Invalid value: " + value);
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return EncryptionKeyInformation.builder()
                .uniqueIdentifier((UniqueIdentifier) map.get(UniqueIdentifier.kmipTag).get(0))
                .cryptographicParameters((CryptographicParameters) map.get(CryptographicParameters.kmipTag).get(0))
                .build();
    }

    public static EncryptionKeyInformation of(@NonNull UniqueIdentifier uniqueIdentifier, CryptographicParameters cryptographicParameters) {
        return EncryptionKeyInformation.builder()
                .uniqueIdentifier(uniqueIdentifier)
                .cryptographicParameters(cryptographicParameters)
                .build();
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
        return Stream.of(uniqueIdentifier, cryptographicParameters).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static class EncryptionKeyInformationBuilder {
        public EncryptionKeyInformation build() {
            validate();
            return new EncryptionKeyInformation(
                    uniqueIdentifier,
                    cryptographicParameters
            );
        }

        private void validate() {
            Objects.requireNonNull(uniqueIdentifier, "uniqueIdentifier cannot be null");
        }
    }
}