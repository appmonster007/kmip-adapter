package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.MacOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MacOpRequestPayloadTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<MacOpRequestPayload, MacOpRequestPayload.MacOpRequestPayloadBuilder> {

    public MacOpRequestPayloadTtlvDeserializer() {
        super(MacOpRequestPayload.kmipTag);
    }

    @Override
    protected MacOpRequestPayload.MacOpRequestPayloadBuilder createBuilder() {
        return MacOpRequestPayload.builder();
    }

    @Override
    protected void setValue(MacOpRequestPayload.MacOpRequestPayloadBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
                    builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
            case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected MacOpRequestPayload build(MacOpRequestPayload.MacOpRequestPayloadBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return MacOpRequestPayload.encodingType;
    }
}
