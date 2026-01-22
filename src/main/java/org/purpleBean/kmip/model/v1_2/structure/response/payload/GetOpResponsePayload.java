package org.purpleBean.kmip.model.v1_2.structure.response.payload;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.api.response.ResponsePayloadStructure;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.Operation;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder(toBuilder = true)
public class GetOpResponsePayload implements ResponsePayloadStructure {

    private static final Operation.Value operation = Operation.Standard.GET;
    private static final Set<KmipSpec> supportedVersions = Set.of(KmipSpec.UnknownVersion, KmipSpec.V1_2);

    static {
        for (KmipSpec spec : supportedVersions) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, GetOpResponsePayload.class);
            ResponsePayloadStructure.register(spec, operation, GetOpResponsePayload.class, GetOpResponsePayload::of);
        }
    }

    @NonNull
    private final ObjectType objectType;

    @NonNull
    private final UniqueIdentifier uniqueIdentifier;

    private final Certificate certificate;
    private final SymmetricKey symmetricKey;
    private final PrivateKey privateKey;
    private final PublicKey publicKey;
    private final SplitKey splitKey;
    private final Template template;
    private final SecretData secretData;
    private final OpaqueObject opaqueObject;

    @Builder
    private GetOpResponsePayload(
            @NonNull ObjectType objectType,
            @NonNull UniqueIdentifier uniqueIdentifier,
            Certificate certificate,
            SymmetricKey symmetricKey,
            PrivateKey privateKey,
            PublicKey publicKey,
            SplitKey splitKey,
            Template template,
            SecretData secretData,
            OpaqueObject opaqueObject
    ) {
        this.objectType = objectType;
        this.uniqueIdentifier = uniqueIdentifier;
        this.certificate = certificate;
        this.symmetricKey = symmetricKey;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.splitKey = splitKey;
        this.template = template;
        this.secretData = secretData;
        this.opaqueObject = opaqueObject;
        validate();
    }

    public static GetOpResponsePayload of(List<KmipDataType> values) {
        var builder = GetOpResponsePayload.builder();
        Map<KmipTag, List<KmipDataType>> map = values.stream().collect(Collectors.groupingBy(KmipDataType::getKmipTag));
        if (map.containsKey(ObjectType.kmipTag)) {
            builder.objectType((ObjectType) map.get(ObjectType.kmipTag).getFirst());
        }
        if (map.containsKey(UniqueIdentifier.kmipTag)) {
            builder.uniqueIdentifier((UniqueIdentifier) map.get(UniqueIdentifier.kmipTag).getFirst());
        }
        if (map.containsKey(Certificate.kmipTag)) {
            builder.certificate((Certificate) map.get(Certificate.kmipTag).getFirst());
        }
        if (map.containsKey(SymmetricKey.kmipTag)) {
            builder.symmetricKey((SymmetricKey) map.get(SymmetricKey.kmipTag).getFirst());
        }
        if (map.containsKey(PrivateKey.kmipTag)) {
            builder.privateKey((PrivateKey) map.get(PrivateKey.kmipTag).getFirst());
        }
        if (map.containsKey(PublicKey.kmipTag)) {
            builder.publicKey((PublicKey) map.get(PublicKey.kmipTag).getFirst());
        }
        if (map.containsKey(SplitKey.kmipTag)) {
            builder.splitKey((SplitKey) map.get(SplitKey.kmipTag).getFirst());
        }
        if (map.containsKey(Template.kmipTag)) {
            builder.template((Template) map.get(Template.kmipTag).getFirst());
        }
        if (map.containsKey(SecretData.kmipTag)) {
            builder.secretData((SecretData) map.get(SecretData.kmipTag).getFirst());
        }
        if (map.containsKey(OpaqueObject.kmipTag)) {
            builder.opaqueObject((OpaqueObject) map.get(OpaqueObject.kmipTag).getFirst());
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
        return supportedVersions.contains(spec) && getValues().stream().allMatch(KmipDataType::isSupported);
    }

    @Override
    public List<KmipDataType> getValues() {
        return Stream.of(
                        objectType,
                        uniqueIdentifier,
                        certificate,
                        symmetricKey,
                        privateKey,
                        publicKey,
                        splitKey,
                        template,
                        secretData,
                        opaqueObject)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Operation getCorrespondingOperation() {
        return operation.inst();
    }
}
