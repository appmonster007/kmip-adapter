package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.v2_1.structure.Attributes;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class RegisterOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.REGISTER;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, RegisterOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, RegisterOpRequestPayload.class, RegisterOpRequestPayload::of);
        }
    }

    @NonNull
    private final ObjectType objectType;
    @NonNull
    private final Attributes attributes;
    @NonNull
    private final ManagedObject object;

    @Builder
    private RegisterOpRequestPayload(
            @NonNull ObjectType objectType,
            @NonNull Attributes attributes,
            @NonNull ManagedObject object
    ) {
        this.objectType = objectType;
        this.attributes = attributes;
        this.object = object;
        validate();
    }

    public static RegisterOpRequestPayload of(List<KmipDataType> values) {
        var builder = RegisterOpRequestPayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(ObjectType.kmipTag)) {
            builder.objectType((ObjectType) map.get(ObjectType.kmipTag).getFirst());
        }
        if (map.containsKey(Attributes.kmipTag)) {
            builder.attributes((Attributes) map.get(Attributes.kmipTag).getFirst());
        }
        values.stream()
                .filter(v -> !v.getKmipTag().equals(ObjectType.kmipTag) && !v.getKmipTag().equals(Attributes.kmipTag))
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
    public KmipTag getKmipTag() { return kmipTag; }

    @Override
    public EncodingType getEncodingType() { return encodingType; }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return supportedVersions.contains(spec) && Stream.of(getValue()).allMatch(KmipDataType::isSupported);
    }

    @Override
    public KmipDataType[] getValue() {
        return Stream.of(objectType, attributes, object)
                .filter(Objects::nonNull)
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public Operation getCorrespondingOperation() { return operation.inst(); }
}
