package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.type.DerivationData;
import org.purpleBean.kmip.model.core.type.InitializationVector;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class DerivationParameters implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.DERIVATION_PARAMETERS.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, DerivationParameters.class);
        }
    }

    private final CryptographicParameters cryptographicParameters;
    private final InitializationVector initializationVector;
    private final DerivationData derivationData;

    @Builder
    private DerivationParameters(
            CryptographicParameters cryptographicParameters,
            InitializationVector initializationVector,
            DerivationData derivationData
    ) {
        this.cryptographicParameters = cryptographicParameters;
        this.initializationVector = initializationVector;
        this.derivationData = derivationData;
        validate();
    }

    public static DerivationParameters of(List<KmipDataType> values) {
        var builder = DerivationParameters.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(CryptographicParameters.kmipTag)) {
            builder.cryptographicParameters((CryptographicParameters) map.get(CryptographicParameters.kmipTag).getFirst());
        }
        if (map.containsKey(InitializationVector.kmipTag)) {
            builder.initializationVector((InitializationVector) map.get(InitializationVector.kmipTag).getFirst());
        }
        if (map.containsKey(DerivationData.kmipTag)) {
            builder.derivationData((DerivationData) map.get(DerivationData.kmipTag).getFirst());
        }
        return builder.build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        // Add validation logic here
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
        return Stream.of(
                        cryptographicParameters,
                        initializationVector,
                        derivationData)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .collect(Collectors.toList());
    }
}