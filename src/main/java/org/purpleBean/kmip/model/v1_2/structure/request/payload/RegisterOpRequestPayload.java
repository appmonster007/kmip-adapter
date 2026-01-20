package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;

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
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

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
    private final TemplateAttribute templateAttribute;
    @NonNull
    private final ManagedObject object;

    @Builder
    private RegisterOpRequestPayload(
            @NonNull ObjectType objectType,
            @NonNull TemplateAttribute templateAttribute,
            @NonNull ManagedObject object
    ) {
        this.objectType = objectType;
        this.templateAttribute = templateAttribute;
        this.object = object;
        validate();
    }

    public static RegisterOpRequestPayload of(
            @NonNull ObjectType objectType,
            @NonNull TemplateAttribute templateAttribute,
            @NonNull ManagedObject object
    ) {
        return RegisterOpRequestPayload.builder()
                .objectType(objectType)
                .templateAttribute(templateAttribute)
                .object(object)
                .build();
    }

    public static RegisterOpRequestPayload of(List<KmipDataType> values) {
        var builder = RegisterOpRequestPayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(ObjectType.kmipTag)) {
            builder.objectType((ObjectType) map.get(ObjectType.kmipTag).getFirst());
        }
        if (map.containsKey(TemplateAttribute.kmipTag)) {
            builder.templateAttribute((TemplateAttribute) map.get(TemplateAttribute.kmipTag).getFirst());
        }
        // The object itself doesn't have a fixed tag, so we need to find the one that is not ObjectType or TemplateAttribute
        values.stream()
                .filter(v -> !v.getKmipTag().equals(ObjectType.kmipTag) && !v.getKmipTag().equals(TemplateAttribute.kmipTag))
                .filter(v -> v instanceof ManagedObject)
                .findFirst()
                .ifPresent(v -> builder.object((ManagedObject) v));

        return builder.build();
    }

    private void validate() {
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
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return Stream.of(
                        objectType,
                        templateAttribute,
                        object)
                .filter(Objects::nonNull)
                .flatMap(val -> val instanceof List ? ((List<?>) val).stream() : Stream.of(val))
                .map(KmipDataType.class::cast)
                .collect(Collectors.toList());
    }

    @Override
    public Operation getCorrespondingOperation() {
        return operation.inst();
    }
}