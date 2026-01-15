package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipStructureXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.structure.MACSignatureKeyInformation;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;

import java.io.IOException;

public class MACSignatureKeyInformationXmlDeserializer extends AbstractKmipStructureXmlDeserializer<MACSignatureKeyInformation, MACSignatureKeyInformation.MACSignatureKeyInformationBuilder> {

    public MACSignatureKeyInformationXmlDeserializer() {
        super(MACSignatureKeyInformation.kmipTag);
    }

    @Override
    protected MACSignatureKeyInformation.MACSignatureKeyInformationBuilder createBuilder() {
        return MACSignatureKeyInformation.builder();
    }

    @Override
    protected void setValue(MACSignatureKeyInformation.MACSignatureKeyInformationBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
                    builder.cryptographicParameters(ctxt.readValue(p, CryptographicParameters.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected MACSignatureKeyInformation build(MACSignatureKeyInformation.MACSignatureKeyInformationBuilder builder) {
        return builder.build();
    }
}