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
public class TransparentDhPrivateKey implements KeyMaterial, KmipStructure {
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);
    private static final KeyFormatType.Value keyFormatTypeValue = KeyFormatType.Standard.TRANSPARENT_DH_PRIVATE_KEY;

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KeyMaterial.register(spec, encodingType, keyFormatTypeValue, TransparentDhPrivateKey.class, TransparentDhPrivateKey::of);
        }
    }

    @NonNull
    private final P p;
    private final Q q;
    @NonNull
    private final G g;
    private final J j;
    @NonNull
    private final X x;

    @Builder
    private TransparentDhPrivateKey(@NonNull P p, Q q, @NonNull G g, J j, @NonNull X x) {
        this.p = p;
        this.q = q;
        this.g = g;
        this.j = j;
        this.x = x;
        validate();
    }

    public static TransparentDhPrivateKey of(@NonNull KeyMaterial value) {
        if (!(value instanceof KmipStructure structure)) {
            throw new IllegalArgumentException("Invalid key material: " + value);
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return TransparentDhPrivateKey.of(
                (P) map.get(P.kmipTag).getFirst(),
                (Q) map.get(Q.kmipTag).getFirst(),
                (G) map.get(G.kmipTag).getFirst(),
                (J) map.get(J.kmipTag).getFirst(),
                (X) map.get(X.kmipTag).getFirst()
        );
    }

    public static TransparentDhPrivateKey of(@NonNull P p, Q q, @NonNull G g, J j, @NonNull X x) {
        return TransparentDhPrivateKey.builder().p(p).q(q).g(g).j(j).x(x).build();
    }

    private void validate() {
        isSupported();
        Objects.requireNonNull(p, "p cannot be null");
        Objects.requireNonNull(g, "g cannot be null");
        Objects.requireNonNull(x, "x cannot be null");
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
        return Stream.of(p, q, g, j, x).filter(Objects::nonNull).collect(Collectors.toList());
    }

}