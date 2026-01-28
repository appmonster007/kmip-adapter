package org.purpleBean.kmip.model.v1_2.structure.request.payload;

import lombok.Builder;
import lombok.Data;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.request.RequestPayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.CertificateRequestType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.CertificateRequest;
import org.purpleBean.kmip.model.core.type.Offset;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class RecertifyOpRequestPayload implements RequestPayloadStructure {

    private static final Operation.Value operation = Operation.Standard.RE_CERTIFY;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, RecertifyOpRequestPayload.class);
            RequestPayloadStructure.register(spec, operation, RecertifyOpRequestPayload.class, RecertifyOpRequestPayload::of);
        }
    }

    private final UniqueIdentifier uniqueIdentifier;
    private final CertificateRequestType certificateRequestType;
    private final CertificateRequest certificateRequest;
    private final Offset offset;
    private final TemplateAttribute templateAttribute;

    @Builder
    private RecertifyOpRequestPayload(
            UniqueIdentifier uniqueIdentifier,
            CertificateRequestType certificateRequestType,
            CertificateRequest certificateRequest,
            Offset offset,
            TemplateAttribute templateAttribute
    ) {
        this.uniqueIdentifier = uniqueIdentifier;
        this.certificateRequestType = certificateRequestType;
        this.certificateRequest = certificateRequest;
        this.offset = offset;
        this.templateAttribute = templateAttribute;
        validate();
    }

    public static RecertifyOpRequestPayload of(List<KmipDataType> values) {
        var builder = RecertifyOpRequestPayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(UniqueIdentifier.kmipTag)) {
            builder.uniqueIdentifier((UniqueIdentifier) map.get(UniqueIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(CertificateRequestType.kmipTag)) {
            builder.certificateRequestType((CertificateRequestType) map.get(CertificateRequestType.kmipTag).getFirst());
        }
        if (map.containsKey(CertificateRequest.kmipTag)) {
            builder.certificateRequest((CertificateRequest) map.get(CertificateRequest.kmipTag).getFirst());
        }
        if (map.containsKey(Offset.kmipTag)) {
            builder.offset((Offset) map.get(Offset.kmipTag).getFirst());
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
        if (certificateRequest != null && certificateRequestType == null) {
            throw new IllegalArgumentException("Certificate Request Type is REQUIRED if Certificate Request is present.");
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
                        certificateRequestType,
                        certificateRequest,
                        offset,
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
