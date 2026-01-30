package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.NetworkIdentifier;

import java.io.IOException;
import java.nio.ByteBuffer;

public class NetworkIdentifierTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<NetworkIdentifier, NetworkIdentifier.NetworkIdentifierBuilder> {

    public NetworkIdentifierTtlvDeserializer() {
        super(NetworkIdentifier.kmipTag, NetworkIdentifier.encodingType);
    }

    @Override
    protected NetworkIdentifier.NetworkIdentifierBuilder createBuilder() {
        return NetworkIdentifier.builder();
    }

    @Override
    protected void setValue(NetworkIdentifier.NetworkIdentifierBuilder builder, byte[] tag, byte type, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected NetworkIdentifier build(NetworkIdentifier.NetworkIdentifierBuilder builder) {
        return builder.build();
    }
}
