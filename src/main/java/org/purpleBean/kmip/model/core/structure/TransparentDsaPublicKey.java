package org.purpleBean.kmip.model.core.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.KeyFormatType;
import org.purpleBean.kmip.model.core.type.G;
import org.purpleBean.kmip.model.core.type.P;
import org.purpleBean.kmip.model.core.type.Q;
import org.purpleBean.kmip.model.core.type.Y;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder(toBuilder = true)
public class TransparentDsaPublicKey implements KeyMaterial, KmipStructure {
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);
    private static final KeyFormatType.Value keyFormatTypeValue = KeyFormatType.Standard.TRANSPARENT_DSA_PUBLIC_KEY;

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KeyMaterial.register(spec, encodingType, keyFormatTypeValue, TransparentDsaPublicKey.class, TransparentDsaPublicKey::of);
        }
    }

    @NonNull
    private final P p;

    @NonNull
    private final Q q;

    @NonNull
    private final G g;

    @NonNull
    private final Y y;

    @Builder
    private TransparentDsaPublicKey(@NonNull P p, @NonNull Q q, @NonNull G g, @NonNull Y y) {
        this.p = p;
        this.q = q;
        this.g = g;
        this.y = y;
        validate();
    }

    public static TransparentDsaPublicKey of(@NonNull KeyMaterial value) {
        if (!(value instanceof KmipStructure structure)) {
            throw new IllegalArgumentException("Invalid key material: " + value);
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return TransparentDsaPublicKey.of(
                (P) map.get(P.kmipTag).getFirst(),
                (Q) map.get(Q.kmipTag).getFirst(),
                (G) map.get(G.kmipTag).getFirst(),
                (Y) map.get(Y.kmipTag).getFirst()
        );
    }

    public static TransparentDsaPublicKey of(@NonNull P p, @NonNull Q q, @NonNull G g, @NonNull Y y) {
        return TransparentDsaPublicKey.builder().p(p).q(q).g(g).y(y).build();
    }

    private void validate() {
        Objects.requireNonNull(p, "p cannot be null");
        Objects.requireNonNull(q, "q cannot be null");
        Objects.requireNonNull(g, "g cannot be null");
        Objects.requireNonNull(y, "y cannot be null");
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
        return List.of(p, q, g, y);
    }


}