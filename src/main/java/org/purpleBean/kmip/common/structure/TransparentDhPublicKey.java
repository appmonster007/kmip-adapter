package org.purpleBean.kmip.common.structure;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.KeyFormatType;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class TransparentDhPublicKey implements KeyMaterial, KmipStructure {
    public static final EncodingType encodingType = EncodingType.STRUCTURE;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KeyMaterial.register(spec, encodingType, KeyFormatType.Standard.TRANSPARENT_DH_PUBLIC_KEY, TransparentDhPublicKey.class, TransparentDhPublicKey::of);
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

    public static TransparentDhPublicKey of(@NonNull KeyMaterial value) {
        if (!(value instanceof KmipStructure structure)) {
            throw new IllegalArgumentException("Invalid key material: " + value);
        }
        Map<KmipTag, List<KmipDataType>> map = structure.getValues().stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
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
        return Stream.of(p, q, g, j, y).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static class TransparentDhPublicKeyBuilder {
        public TransparentDhPublicKey build() {
            validate();
            return new TransparentDhPublicKey(p, q, g, j, y);
        }

        private void validate() {
            Objects.requireNonNull(p, "p cannot be null");
            Objects.requireNonNull(g, "g cannot be null");
            Objects.requireNonNull(y, "y cannot be null");
        }
    }
}