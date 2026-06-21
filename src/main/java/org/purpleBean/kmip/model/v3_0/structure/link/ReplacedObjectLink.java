package org.purpleBean.kmip.model.v3_0.structure.link;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class ReplacedObjectLink implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.REPLACED_OBJECT_LINK.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ReplacedObjectLink.class);
            // TODO: Add other register calls as required
        }
    }

    // TODO: Add fields for the structure, e.g.:
    // @NonNull
    // private final Field1 field1;
    // @NonNull
    // @Singular
    // private final List<Field2> field2s;

    // TODO: Add a custom constructor for the builder
    @Builder
    private ReplacedObjectLink(
            // @NonNull Field1 field1,
            // List<Field2> field2s
    ) {
        // this.field1 = field1;
        // this.field2s = (field2s == null) ? Collections.emptyList() : field2s;
        validate();
    }

    // TODO: Add an 'of' method with a single argument of KmipStructure, if required
    // public static ReplacedObjectLink of(@NonNull KmipDataType value) {
    //     if (!(value instanceof KmipStructure structure)) {
    //         throw new IllegalArgumentException("Invalid value: " + value);
    //     }
    //     Map<KmipTag, List<KmipDataType>> map = Stream.of(structure.getValue()).collect(Collectors.groupingBy(KmipDataType::getKmipTag));
    //     // TODO: Update to extract fields from the map and call the other 'of' method
    //     // ReplacedObjectLinkBuilder builder = ReplacedObjectLink.builder();
    //     // if (map.containsKey(Field1.kmipTag)) {
    //     //     builder.field1((Field1) map.get(Field1.kmipTag).getFirst());
    //     // }
    //     // if (map.containsKey(Field2.kmipTag)) {
    //     //     map.get(Field2.kmipTag).forEach(item -> builder.field2((Field2) item));
    //     // }
    //     // return builder.build();
    //     throw new UnsupportedOperationException("Not yet implemented");
    // }

    // TODO: Add an 'of' method with all the fields as arguments
    public static ReplacedObjectLink of(
                // @NonNull Field1 field1,
                // @NonNull Field2 field2
    ) {
        return ReplacedObjectLink.builder()
                     // .field1(field1)
                     // .field2(field2)
                     .build();
    }

    // TODO: Add a validate method
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
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        // TODO: Return a list of all the fields
        // return Stream.of(
        //                 field1,
        //                 field2s)
        //         .filter(Objects::nonNull)
        //         .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
        //         .map(KmipDataType.class::cast)
        //         .toArray(KmipDataType[]::new);
        return new KmipDataType[0];
    }
}