package org.purpleBean.kmip.model.v1_2.structure.response.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
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
public class CertifyOpResponsePayload implements ResponsePayloadStructure {

    private static final Operation.Value operation = Operation.Standard.CERTIFY;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CertifyOpResponsePayload.class);
            ResponsePayloadStructure.register(spec, operation, CertifyOpResponsePayload.class, CertifyOpResponsePayload::of);
        }
    }

    @NonNull
    private final UniqueIdentifier uniqueIdentifier;
    private final TemplateAttribute templateAttribute;

    @Builder
    private CertifyOpResponsePayload(
            @NonNull UniqueIdentifier uniqueIdentifier,
            TemplateAttribute templateAttribute
    ) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.templateAttribute = templateAttribute;
        validate();
    }

    public static CertifyOpResponsePayload of(
            @NonNull UniqueIdentifier uniqueIdentifier,
            TemplateAttribute templateAttribute
    ) {
        return CertifyOpResponsePayload.builder()
                .uniqueIdentifier(uniqueIdentifier)
                .templateAttribute(templateAttribute)
                .build();
    }

    public static CertifyOpResponsePayload of(List<KmipDataType> values) {
        var builder = CertifyOpResponsePayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
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
        return Stream.of(
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
