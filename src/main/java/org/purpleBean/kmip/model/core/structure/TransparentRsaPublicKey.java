package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.type.Modulus;
import org.purpleBean.kmip.model.core.type.PublicExponent;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
public class TransparentRsaPublicKey implements KeyMaterial, KmipStructure {
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KeyMaterial.register(spec, encodingType, KeyFormatType.Standard.TRANSPARENT_RSA_PUBLIC_KEY, TransparentRsaPublicKey.class, TransparentRsaPublicKey::of);
        }
    }

    @NonNull
    private final Modulus modulus;

    @NonNull
    private final PublicExponent publicExponent;

    @Builder
    private TransparentRsaPublicKey(@NonNull Modulus modulus, @NonNull PublicExponent publicExponent) {
        this.modulus = modulus;
        this.publicExponent = publicExponent;
        validate();
    }

    public static TransparentRsaPublicKey of(@NonNull KeyMaterial value) {
        if (!(value instanceof KmipStructure structure)) {
            throw new IllegalArgumentException("Invalid key material: " + value);
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return TransparentRsaPublicKey.of(
                (Modulus) map.get(Modulus.kmipTag).getFirst(),
                (PublicExponent) map.get(PublicExponent.kmipTag).getFirst()
        );
    }

    public static TransparentRsaPublicKey of(@NonNull Modulus modulus, @NonNull PublicExponent publicExponent) {
        return TransparentRsaPublicKey.builder().modulus(modulus).publicExponent(publicExponent).build();
    }

    private void validate() {
        Objects.requireNonNull(modulus, "modulus cannot be null");
        Objects.requireNonNull(publicExponent, "publicExponent cannot be null");
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
        return List.of(modulus, publicExponent);
    }
}