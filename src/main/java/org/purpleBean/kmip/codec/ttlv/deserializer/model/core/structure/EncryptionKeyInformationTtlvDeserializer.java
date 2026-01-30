package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.io.IOException;
import java.nio.ByteBuffer;

public class EncryptionKeyInformationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<EncryptionKeyInformation, EncryptionKeyInformation.EncryptionKeyInformationBuilder> {

    public EncryptionKeyInformationTtlvDeserializer() {
        super(EncryptionKeyInformation.kmipTag, EncryptionKeyInformation.encodingType);
    }

    @Override
    protected EncryptionKeyInformation.EncryptionKeyInformationBuilder createBuilder() {
        return EncryptionKeyInformation.builder();
    }

    @Override
    protected void setValue(EncryptionKeyInformation.EncryptionKeyInformationBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
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
    protected EncryptionKeyInformation build(EncryptionKeyInformation.EncryptionKeyInformationBuilder builder) {
        return builder.build();
    }
}