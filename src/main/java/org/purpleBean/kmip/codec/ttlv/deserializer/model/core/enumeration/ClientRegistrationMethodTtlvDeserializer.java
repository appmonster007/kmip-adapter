package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.ClientRegistrationMethod;

import java.io.IOException;
import java.nio.ByteBuffer;

public class ClientRegistrationMethodTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<ClientRegistrationMethod, ClientRegistrationMethod.ClientRegistrationMethodBuilder> {

    public ClientRegistrationMethodTtlvDeserializer() {
        super(ClientRegistrationMethod.kmipTag, ClientRegistrationMethod.encodingType);
    }

    @Override
    protected ClientRegistrationMethod.ClientRegistrationMethodBuilder createBuilder() {
        return ClientRegistrationMethod.builder();
    }

    @Override
    protected void setValue(ClientRegistrationMethod.ClientRegistrationMethodBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(ClientRegistrationMethod.fromValue(value));
    }

    @Override
    protected ClientRegistrationMethod build(ClientRegistrationMethod.ClientRegistrationMethodBuilder builder) {
        return builder.build();
    }
}
