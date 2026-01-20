package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class ReKeyKeyPairOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.RE_KEY_KEY_PAIR;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, ReKeyKeyPairOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, ReKeyKeyPairOpRequestPayload.class, ReKeyKeyPairOpRequestPayload::of);
        }
    }

    private final PrivateKeyUniqueIdentifier privateKeyUniqueIdentifier;
    private final Offset offset;
    private final CommonTemplateAttribute commonTemplateAttribute;
    private final PrivateKeyTemplateAttribute privateKeyTemplateAttribute;
    private final PublicKeyTemplateAttribute publicKeyTemplateAttribute;

    @Builder
    private ReKeyKeyPairOpRequestPayload(
            PrivateKeyUniqueIdentifier privateKeyUniqueIdentifier,
            Offset offset,
            CommonTemplateAttribute commonTemplateAttribute,
            PrivateKeyTemplateAttribute privateKeyTemplateAttribute,
            PublicKeyTemplateAttribute publicKeyTemplateAttribute
    ) {
        this.privateKeyUniqueIdentifier = privateKeyUniqueIdentifier;
        this.offset = offset;
        this.commonTemplateAttribute = commonTemplateAttribute;
        this.privateKeyTemplateAttribute = privateKeyTemplateAttribute;
        this.publicKeyTemplateAttribute = publicKeyTemplateAttribute;
        validate();
    }

    public static ReKeyKeyPairOpRequestPayload of(
            PrivateKeyUniqueIdentifier privateKeyUniqueIdentifier,
            Offset offset,
            CommonTemplateAttribute commonTemplateAttribute,
            PrivateKeyTemplateAttribute privateKeyTemplateAttribute,
            PublicKeyTemplateAttribute publicKeyTemplateAttribute
    ) {
        return ReKeyKeyPairOpRequestPayload.builder()
                .privateKeyUniqueIdentifier(privateKeyUniqueIdentifier)
                .offset(offset)
                .commonTemplateAttribute(commonTemplateAttribute)
                .privateKeyTemplateAttribute(privateKeyTemplateAttribute)
                .publicKeyTemplateAttribute(publicKeyTemplateAttribute)
                .build();
    }

    public static ReKeyKeyPairOpRequestPayload of(List<KmipDataType> values) {
        var builder = ReKeyKeyPairOpRequestPayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(PrivateKeyUniqueIdentifier.kmipTag)) {
            builder.privateKeyUniqueIdentifier((PrivateKeyUniqueIdentifier) map.get(PrivateKeyUniqueIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(Offset.kmipTag)) {
            builder.offset((Offset) map.get(Offset.kmipTag).getFirst());
        }
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
        isSupported();
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
                        privateKeyUniqueIdentifier,
                        offset,
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