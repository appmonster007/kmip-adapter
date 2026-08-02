package org.purpleBean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.structure.DerivationParameters;
import org.purpleBean.kmip.model.core.type.DerivationData;
import org.purpleBean.kmip.model.core.type.InitializationVector;
import org.purpleBean.kmip.model.core.type.IterationCount;
import org.purpleBean.kmip.model.core.type.Salt;

import java.io.IOException;

public class DerivationParametersXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<DerivationParameters, DerivationParameters.DerivationParametersBuilder> {

    public DerivationParametersXmlDeserializer() {
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
            case KmipTag.Standard.SALT -> builder.salt(ctxt.readValue(p, Salt.class));
            case KmipTag.Standard.ITERATION_COUNT -> builder.iterationCount(ctxt.readValue(p, IterationCount.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DerivationParameters build(DerivationParameters.DerivationParametersBuilder builder) {
        return builder.build();
    }
}