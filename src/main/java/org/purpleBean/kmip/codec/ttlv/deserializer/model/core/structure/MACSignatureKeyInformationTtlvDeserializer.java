package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.structure.MACSignatureKeyInformation;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MACSignatureKeyInformationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MACSignatureKeyInformation, MACSignatureKeyInformation.MACSignatureKeyInformationBuilder> {

    public MACSignatureKeyInformationTtlvDeserializer() {
        super(MACSignatureKeyInformation.kmipTag, MACSignatureKeyInformation.encodingType);
    }

    @Override
    protected MACSignatureKeyInformation.MACSignatureKeyInformationBuilder createBuilder() {
        return MACSignatureKeyInformation.builder();
    }

    @Override
    protected void setValue(MACSignatureKeyInformation.MACSignatureKeyInformationBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
                    builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected MACSignatureKeyInformation build(MACSignatureKeyInformation.MACSignatureKeyInformationBuilder builder) {
        return builder.build();
    }
}