package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.structure.EncryptionKeyInformation;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.io.IOException;

public class EncryptionKeyInformationJsonDeserializer extends AbstractKmipStructureJsonDeserializer<EncryptionKeyInformation, EncryptionKeyInformation.EncryptionKeyInformationBuilder> {

    public EncryptionKeyInformationJsonDeserializer() {
        super(EncryptionKeyInformation.kmipTag, EncryptionKeyInformation.encodingType);
    }

    @Override
    protected EncryptionKeyInformation.EncryptionKeyInformationBuilder createBuilder() {
        return EncryptionKeyInformation.builder();
    }

    @Override
    protected void setValue(EncryptionKeyInformation.EncryptionKeyInformationBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
                    builder.cryptographicParameters(ctxt.readValue(p, CryptographicParameters.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected EncryptionKeyInformation build(EncryptionKeyInformation.EncryptionKeyInformationBuilder builder) {
        return builder.build();
    }
}