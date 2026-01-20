package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class CreateKeyPairOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.CREATE_KEY_PAIR;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, CreateKeyPairOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, CreateKeyPairOpRequestPayload.class, CreateKeyPairOpRequestPayload::of);
        }
    }

    private final CommonTemplateAttribute commonTemplateAttribute;
    private final PrivateKeyTemplateAttribute privateKeyTemplateAttribute;
    private final PublicKeyTemplateAttribute publicKeyTemplateAttribute;

    @Builder
    private CreateKeyPairOpRequestPayload(
            CommonTemplateAttribute commonTemplateAttribute,
            PrivateKeyTemplateAttribute privateKeyTemplateAttribute,
            PublicKeyTemplateAttribute publicKeyTemplateAttribute
    ) {
        this.commonTemplateAttribute = commonTemplateAttribute;
        this.privateKeyTemplateAttribute = privateKeyTemplateAttribute;
        this.publicKeyTemplateAttribute = publicKeyTemplateAttribute;
        validate();
    }

    public static CreateKeyPairOpRequestPayload of(
            CommonTemplateAttribute commonTemplateAttribute,
            PrivateKeyTemplateAttribute privateKeyTemplateAttribute,
            PublicKeyTemplateAttribute publicKeyTemplateAttribute
    ) {
        return CreateKeyPairOpRequestPayload.builder()
                .commonTemplateAttribute(commonTemplateAttribute)
                .privateKeyTemplateAttribute(privateKeyTemplateAttribute)
                .publicKeyTemplateAttribute(publicKeyTemplateAttribute)
                .build();
    }

    public static CreateKeyPairOpRequestPayload of(List<KmipDataType> values) {
        var builder = CreateKeyPairOpRequestPayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(CommonTemplateAttribute.kmipTag)) {
            builder.commonTemplateAttribute((CommonTemplateAttribute) map.get(CommonTemplateAttribute.kmipTag).getFirst());
        }
        if (map.containsKey(PrivateKeyTemplateAttribute.kmipTag)) {
            builder.privateKeyTemplateAttribute((PrivateKeyTemplateAttribute) map.get(PrivateKeyTemplateAttribute.kmipTag).getFirst());
        }
        if (map.containsKey(PublicKeyTemplateAttribute.kmipTag)) {
            builder.publicKeyTemplateAttribute((PublicKeyTemplateAttribute) map.get(PublicKeyTemplateAttribute.kmipTag).getFirst());
        }
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
                        commonTemplateAttribute,
                        privateKeyTemplateAttribute,
                        publicKeyTemplateAttribute)
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