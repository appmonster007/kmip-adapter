package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.DataByteString;
import org.purpleBean.kmip.model.core.type.MacData;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.MacVerifyOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class MacVerifyOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<MacVerifyOpRequestPayload, MacVerifyOpRequestPayload.MacVerifyOpRequestPayloadBuilder> {

    public MacVerifyOpRequestPayloadTtlvDeserializer() {
        super(MacVerifyOpRequestPayload.kmipTag, MacVerifyOpRequestPayload.encodingType);
    }

    @Override
    protected MacVerifyOpRequestPayload.MacVerifyOpRequestPayloadBuilder createBuilder() {
        return MacVerifyOpRequestPayload.builder();
    }

    @Override
    protected void setValue(MacVerifyOpRequestPayload.MacVerifyOpRequestPayloadBuilder builder, byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
        switch (nodeTag) {
            case KmipTag.Standard.UNIQUE_IDENTIFIER ->
                    builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_PARAMETERS ->
                    builder.cryptographicParameters(mapper.readValue(p, CryptographicParameters.class));
            case KmipTag.Standard.DATA -> builder.data(mapper.readValue(p, DataByteString.class));
            case KmipTag.Standard.MAC_DATA -> builder.macData(mapper.readValue(p, MacData.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected MacVerifyOpRequestPayload build(MacVerifyOpRequestPayload.MacVerifyOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}