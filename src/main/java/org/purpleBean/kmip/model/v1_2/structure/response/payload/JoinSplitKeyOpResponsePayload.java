package org.purpleBean.kmip.model.v1_2.structure.response.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class JoinSplitKeyOpResponsePayload implements ResponsePayloadStructure {

    private static final Operation.Value operation = Operation.Standard.JOIN_SPLIT_KEY;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, JoinSplitKeyOpResponsePayload.class);
            ResponsePayloadStructure.register(spec, operation, JoinSplitKeyOpResponsePayload.class, JoinSplitKeyOpResponsePayload::of);
        }
    }

    // Null check disabled due to 2.3.49 TC-SJ-4-12 - Register and Split / Join with XOR
    // @NonNull
    private final ObjectType objectType;

    @NonNull
    private final UniqueIdentifier uniqueIdentifier;

    private final TemplateAttribute templateAttribute;

    @Builder
    private JoinSplitKeyOpResponsePayload(
            // Null check disabled due to 2.3.49 TC-SJ-4-12 - Register and Split / Join with XOR
            // @NonNull
            ObjectType objectType,
            @NonNull UniqueIdentifier uniqueIdentifier,
            TemplateAttribute templateAttribute
    ) {
        this.objectType = objectType;
        this.uniqueIdentifier = uniqueIdentifier;
        this.templateAttribute = templateAttribute;
        validate();
    }

    public static JoinSplitKeyOpResponsePayload of(List<KmipDataType> values) {
        var builder = JoinSplitKeyOpResponsePayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(ObjectType.kmipTag)) {
            builder.objectType((ObjectType) map.get(ObjectType.kmipTag).getFirst());
        }
        if (map.containsKey(UniqueIdentifier.kmipTag)) {
            builder.uniqueIdentifier((UniqueIdentifier) map.get(UniqueIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(TemplateAttribute.kmipTag)) {
            builder.templateAttribute((TemplateAttribute) map.get(TemplateAttribute.kmipTag).getFirst());
        }
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
                        objectType,
                        uniqueIdentifier,
                        templateAttribute)
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
