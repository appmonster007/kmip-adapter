package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.type.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class TransparentRsaPrivateKey implements KeyMaterial, KmipStructure {
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);
    private static final KeyFormatType.Value keyFormatTypeValue = KeyFormatType.Standard.TRANSPARENT_RSA_PRIVATE_KEY;

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KeyMaterial.register(spec, encodingType, keyFormatTypeValue, TransparentRsaPrivateKey.class, TransparentRsaPrivateKey::of);
        }
    }

    @NonNull
    private final Modulus modulus;
    private final PrivateExponent privateExponent;
    private final PublicExponent publicExponent;
    private final P p;
    private final Q q;
    private final PrimeExponentP primeExponentP;
    private final PrimeExponentQ primeExponentQ;
    private final CRTCoefficient crtCoefficient;

    @Builder
    private TransparentRsaPrivateKey(
            @NonNull Modulus modulus,
            PrivateExponent privateExponent,
            PublicExponent publicExponent,
            P p,
            Q q,
            PrimeExponentP primeExponentP,
            PrimeExponentQ primeExponentQ,
            CRTCoefficient crtCoefficient
    ) {
        this.modulus = modulus;
        this.privateExponent = privateExponent;
        this.publicExponent = publicExponent;
        this.p = p;
        this.q = q;
        this.primeExponentP = primeExponentP;
        this.primeExponentQ = primeExponentQ;
        this.crtCoefficient = crtCoefficient;
        validate();
    }

    public static TransparentRsaPrivateKey of(@NonNull KeyMaterial value) {
        if (!(value instanceof KmipStructure structure)) {
            throw new IllegalArgumentException("Invalid key material: " + value);
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return TransparentRsaPrivateKey.of(
                (Modulus) map.get(Modulus.kmipTag).getFirst(),
                (PrivateExponent) map.get(PrivateExponent.kmipTag).getFirst(),
                (PublicExponent) map.get(PublicExponent.kmipTag).getFirst(),
                (P) map.get(P.kmipTag).getFirst(),
                (Q) map.get(Q.kmipTag).getFirst(),
                (PrimeExponentP) map.get(PrimeExponentP.kmipTag).getFirst(),
                (PrimeExponentQ) map.get(PrimeExponentQ.kmipTag).getFirst(),
                (CRTCoefficient) map.get(CRTCoefficient.kmipTag).getFirst()
        );
    }

    public static TransparentRsaPrivateKey of(@NonNull Modulus modulus, PrivateExponent privateExponent, PublicExponent publicExponent, P p, Q q, PrimeExponentP primeExponentP, PrimeExponentQ primeExponentQ, CRTCoefficient crtCoefficient) {
        return TransparentRsaPrivateKey.builder()
                .modulus(modulus)
                .privateExponent(privateExponent)
                .publicExponent(publicExponent)
                .p(p)
                .q(q)
                .primeExponentP(primeExponentP)
                .primeExponentQ(primeExponentQ)
                .crtCoefficient(crtCoefficient)
                .build();
    }

    private void validate() {
        Objects.requireNonNull(modulus, "modulus cannot be null");
        if (privateExponent == null && (p == null || q == null) && (primeExponentP == null || primeExponentQ == null)) {
            throw new IllegalStateException("One of Private Exponent, (P and Q), or (Prime Exponent P and Prime Exponent Q) must be present");
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
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return Stream.of(modulus, privateExponent, publicExponent, p, q, primeExponentP, primeExponentQ, crtCoefficient)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public KeyFormatType getKeyFormatType() {
        return keyFormatTypeValue.inst();
    }
}