package org.purpleBean.kmip.model.v2_1.structure;

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
public class ObjectTypes implements KmipStructure {
    public static final KmipTag kmipTag = KmipTag.Standard.OBJECT_TYPES.inst();
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ObjectTypes.class);
        }
    }

    @NonNull
    @Singular("objectType")
    private final List<ObjectType> objectTypes;

    @Builder
    private ObjectTypes(List<ObjectType> objectTypes) {
        this.objectTypes = (objectTypes == null) ? Collections.emptyList() : objectTypes;
        validate();
    }

    public static ObjectTypes of(@NonNull List<ObjectType> objectTypes) {
        return ObjectTypes.builder().objectTypes(objectTypes).build();
    }

    private void validate() {
        if (!isSupported()) {
            throw new IllegalArgumentException(String.format("Unsupported object type for %s: %s", KmipContext.getSpec(), getKmipTag()));
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
        return supportedVersions.contains(spec) && objectTypes.stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return objectTypes.stream().map(KmipDataType.class::cast).toArray(KmipDataType[]::new);
    }
}