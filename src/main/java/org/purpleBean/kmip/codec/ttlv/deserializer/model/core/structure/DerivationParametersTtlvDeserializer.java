package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.structure.DerivationParameters;
import org.purpleBean.kmip.model.core.type.DerivationData;
import org.purpleBean.kmip.model.core.type.InitializationVector;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DerivationParametersTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<DerivationParameters, DerivationParameters.DerivationParametersBuilder> {

    public DerivationParametersTtlvDeserializer() {
        super(DerivationParameters.kmipTag);
    }

    @Override
    protected DerivationParameters.DerivationParametersBuilder createBuilder() {
        return DerivationParameters.builder();
    }

    @Override
    protected void setValue(DerivationParameters.DerivationParametersBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
                    builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
            case KmipTag.Standard.INITIALIZATION_VECTOR ->
                    builder.initializationVector(mapper.readValue(p, InitializationVector.class));
            case KmipTag.Standard.DERIVATION_DATA -> builder.derivationData(mapper.readValue(p, DerivationData.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected DerivationParameters build(DerivationParameters.DerivationParametersBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return DerivationParameters.encodingType;
    }
}