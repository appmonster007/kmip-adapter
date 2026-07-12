package org.purpleBean.kmip.model.v2_1.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.type.AttributeReferenceTag;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class GetAttributesOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.GET_ATTRIBUTES;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V2_1, KmipSpec.V3_0);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, GetAttributesOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, GetAttributesOpRequestPayload.class, GetAttributesOpRequestPayload::of);
        }
    }

    private final UniqueIdentifier uniqueIdentifier;

    @Singular
    private final List<AttributeReferenceTag> attributeReferences;

    @Builder
    private GetAttributesOpRequestPayload(
            UniqueIdentifier uniqueIdentifier,
            List<AttributeReferenceTag> attributeReferences
    ) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.attributeReferences = (attributeReferences == null) ? Collections.emptyList() : attributeReferences;
        validate();
    }

    public static GetAttributesOpRequestPayload of(List<KmipDataType> values) {
        var builder = GetAttributesOpRequestPayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(UniqueIdentifier.kmipTag)) {
            builder.uniqueIdentifier((UniqueIdentifier) map.get(UniqueIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(AttributeReferenceTag.kmipTag)) {
            map.get(AttributeReferenceTag.kmipTag).forEach(item -> builder.attributeReference((AttributeReferenceTag) item));
        }
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
        return Stream.of(uniqueIdentifier, attributeReferences)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .toArray(KmipDataType[]::new);
    }

    @Override
    public Operation getCorrespondingOperation() { return operation.inst(); }
}
