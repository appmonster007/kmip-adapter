package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.structure.DerivationParameters;
import org.purpleBean.kmip.model.core.type.DerivationData;
import org.purpleBean.kmip.model.core.type.InitializationVector;

import java.io.IOException;

public class DerivationParametersJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<DerivationParameters, DerivationParameters.DerivationParametersBuilder> {

    public DerivationParametersJsonDeserializer() {
        super(DerivationParameters.kmipTag, DerivationParameters.encodingType);
    }

    @Override
    protected DerivationParameters.DerivationParametersBuilder createBuilder() {
        return DerivationParameters.builder();
    }

    @Override
    protected void setValue(DerivationParameters.DerivationParametersBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromName(tag);
        switch (nodeTag) {
            case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
                    builder.cryptographicParameters(ctxt.readValue(p, CryptographicParameters.class));
            case KmipTag.Standard.INITIALIZATION_VECTOR ->
                    builder.initializationVector(ctxt.readValue(p, InitializationVector.class));
            case KmipTag.Standard.DERIVATION_DATA -> builder.derivationData(ctxt.readValue(p, DerivationData.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DerivationParameters build(DerivationParameters.DerivationParametersBuilder builder) {
        return builder.build();
    }
}
