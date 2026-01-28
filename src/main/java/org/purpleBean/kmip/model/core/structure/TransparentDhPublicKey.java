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
public class TransparentDhPublicKey implements KeyMaterial, KmipStructure {
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);
    private static final KeyFormatType.Value keyFormatTypeValue = KeyFormatType.Standard.TRANSPARENT_DH_PUBLIC_KEY;

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KeyMaterial.register(spec, encodingType, keyFormatTypeValue, TransparentDhPublicKey.class, TransparentDhPublicKey::of);
        }
    }

    @NonNull
    private final P p;
    private final Q q;
    @NonNull
    private final G g;
    private final J j;
    @NonNull
    private final Y y;

    @Builder
    private TransparentDhPublicKey(@NonNull P p, Q q, @NonNull G g, J j, @NonNull Y y) {
        this.p = p;
        this.q = q;
        this.g = g;
        this.j = j;
        this.y = y;
        validate();
    }

    public static TransparentDhPublicKey of(@NonNull KeyMaterial value) {
        if (!(value instanceof KmipStructure structure)) {
            throw new IllegalArgumentException("Invalid key material: " + value);
        }
        Map<KmipTag, List<KmipDataType>> map = Stream.of(structure.getValue()).collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        return TransparentDhPublicKey.of(
                (P) map.get(P.kmipTag).getFirst(),
                (Q) map.get(Q.kmipTag).getFirst(),
                (G) map.get(G.kmipTag).getFirst(),
                (J) map.get(J.kmipTag).getFirst(),
                (Y) map.get(Y.kmipTag).getFirst()
        );
    }

    public static TransparentDhPublicKey of(@NonNull P p, Q q, @NonNull G g, J j, @NonNull Y y) {
        return TransparentDhPublicKey.builder().p(p).q(q).g(g).j(j).y(y).build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
        }
        Objects.requireNonNull(p, "p cannot be null");
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
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(p, q, g, j, y)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

}
