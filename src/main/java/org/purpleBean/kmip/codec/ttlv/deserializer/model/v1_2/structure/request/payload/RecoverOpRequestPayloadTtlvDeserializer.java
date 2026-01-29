package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RecoverOpRequestPayload;

import java.io.IOException;
import java.nio.ByteBuffer;

public class RecoverOpRequestPayloadTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<RecoverOpRequestPayload, RecoverOpRequestPayload.RecoverOpRequestPayloadBuilder> {

    public RecoverOpRequestPayloadTtlvDeserializer() {
        super(RecoverOpRequestPayload.kmipTag, RecoverOpRequestPayload.encodingType);
    }

    @Override
    protected RecoverOpRequestPayload.RecoverOpRequestPayloadBuilder createBuilder() {
        return RecoverOpRequestPayload.builder();
    }

    @Override
    protected void setValue(RecoverOpRequestPayload.RecoverOpRequestPayloadBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        KmipTag.Value nodeTag = KmipTag.fromBytes(tagBytes);
        if (nodeTag.equals(KmipTag.Standard.UNIQUE_IDENTIFIER)) {
            builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
        } else {
            throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected RecoverOpRequestPayload build(RecoverOpRequestPayload.RecoverOpRequestPayloadBuilder builder) {
        return builder.build();
    }
}
