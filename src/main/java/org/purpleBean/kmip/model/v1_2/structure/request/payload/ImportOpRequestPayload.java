package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.Attributes;
import org.purpleBean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purpleBean.kmip.model.core.type.ReplaceExisting;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP Import Request Payload.
 */
@Data
@Builder(toBuilder = true)
public class ImportOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.IMPORT;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ImportOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, ImportOpRequestPayload.class, ImportOpRequestPayload::of);
        }
    }

    private final UniqueIdentifier uniqueIdentifier;

    @NonNull
    private final ObjectType objectType;

    private final ReplaceExisting replaceExisting;

    private final KeyWrappingSpecification keyWrappingSpecification;

    @NonNull
    private final ManagedObject object;

    @Builder
    private ImportOpRequestPayload(
            UniqueIdentifier uniqueIdentifier,
            @NonNull ObjectType objectType,
            ReplaceExisting replaceExisting,
            KeyWrappingSpecification keyWrappingSpecification,
            @NonNull ManagedObject object
    ) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.objectType = objectType;
        this.replaceExisting = replaceExisting;
        this.keyWrappingSpecification = keyWrappingSpecification;
        this.object = object;
        validate();
    }

    public static ImportOpRequestPayload of(List<KmipDataType> values) {
        var builder = ImportOpRequestPayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(UniqueIdentifier.kmipTag)) {
            builder.uniqueIdentifier((UniqueIdentifier) map.get(UniqueIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(ObjectType.kmipTag)) {
            builder.objectType((ObjectType) map.get(ObjectType.kmipTag).getFirst());
        }
        if (map.containsKey(ReplaceExisting.kmipTag)) {
            builder.replaceExisting((ReplaceExisting) map.get(ReplaceExisting.kmipTag).getFirst());
        }
        if (map.containsKey(KeyWrappingSpecification.kmipTag)) {
            builder.keyWrappingSpecification((KeyWrappingSpecification) map.get(KeyWrappingSpecification.kmipTag).getFirst());
        }
        values.stream()
                .filter(v -> !v.getKmipTag().equals(ObjectType.kmipTag)
                        && !v.getKmipTag().equals(UniqueIdentifier.kmipTag)
                        && !v.getKmipTag().equals(ReplaceExisting.kmipTag)
                        && !v.getKmipTag().equals(KeyWrappingSpecification.kmipTag))
                .filter(v -> v instanceof ManagedObject)
                .findFirst()
                .ifPresent(v -> builder.object((ManagedObject) v));
        return builder.build();
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
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(
                        uniqueIdentifier,
                        objectType,
                        replaceExisting,
                        keyWrappingSpecification,
                        object)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public Operation getCorrespondingOperation() {
        return operation.inst();
    }
}
