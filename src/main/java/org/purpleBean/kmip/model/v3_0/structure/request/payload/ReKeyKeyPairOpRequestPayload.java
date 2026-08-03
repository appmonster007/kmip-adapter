package org.purpleBean.kmip.model.v3_0.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.model.v2_1.structure.CommonAttributes;
import org.purpleBean.kmip.model.v2_1.structure.PrivateKeyAttributes;
import org.purpleBean.kmip.model.v2_1.structure.PublicKeyAttributes;
import org.purpleBean.kmip.model.v3_0.type.PrivateKeyUniqueIdentifier;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * KMIP ReKeyKeyPair Request Payload (V3_0).
 * <p>
 * Fork of {@link org.purpleBean.kmip.model.v1_2.structure.request.payload.ReKeyKeyPairOpRequestPayload} for
 * KMIP 3.0, where {@code PrivateKeyUniqueIdentifier} is encoded as {@code Identifier} rather than
 * {@code TextString} (see {@link PrivateKeyUniqueIdentifier}).
 */
@Data
@Builder(toBuilder = true)
public class ReKeyKeyPairOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.RE_KEY_KEY_PAIR;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V3_0);

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
    private final CommonAttributes commonAttributes;
    private final PrivateKeyAttributes privateKeyAttributes;
    private final PublicKeyAttributes publicKeyAttributes;

    @Builder
    private ReKeyKeyPairOpRequestPayload(
            PrivateKeyUniqueIdentifier privateKeyUniqueIdentifier,
            Offset offset,
            CommonTemplateAttribute commonTemplateAttribute,
            PrivateKeyTemplateAttribute privateKeyTemplateAttribute,
            PublicKeyTemplateAttribute publicKeyTemplateAttribute,
            CommonAttributes commonAttributes,
            PrivateKeyAttributes privateKeyAttributes,
            PublicKeyAttributes publicKeyAttributes
    ) {
        this.privateKeyUniqueIdentifier = privateKeyUniqueIdentifier;
        this.offset = offset;
        this.commonTemplateAttribute = commonTemplateAttribute;
        this.privateKeyTemplateAttribute = privateKeyTemplateAttribute;
        this.publicKeyTemplateAttribute = publicKeyTemplateAttribute;
        this.commonAttributes = commonAttributes;
        this.privateKeyAttributes = privateKeyAttributes;
        this.publicKeyAttributes = publicKeyAttributes;
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
        if (map.containsKey(CommonAttributes.kmipTag)) {
            builder.commonAttributes((CommonAttributes) map.get(CommonAttributes.kmipTag).getFirst());
        }
        if (map.containsKey(PrivateKeyAttributes.kmipTag)) {
            builder.privateKeyAttributes((PrivateKeyAttributes) map.get(PrivateKeyAttributes.kmipTag).getFirst());
        }
        if (map.containsKey(PublicKeyAttributes.kmipTag)) {
            builder.publicKeyAttributes((PublicKeyAttributes) map.get(PublicKeyAttributes.kmipTag).getFirst());
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
                        privateKeyUniqueIdentifier,
                        offset,
                        commonTemplateAttribute,
                        privateKeyTemplateAttribute,
                        publicKeyTemplateAttribute,
                        commonAttributes,
                        privateKeyAttributes,
                        publicKeyAttributes)
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
