package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.Issuer;

import java.io.IOException;
import java.nio.ByteBuffer;

public class IssuerTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Issuer, Issuer.IssuerBuilder> {

    public IssuerTtlvDeserializer() {
        super(Issuer.kmipTag, Issuer.encodingType);
    }

    @Override
    protected Issuer.IssuerBuilder createBuilder() {
        return Issuer.builder();
    }

    @Override
    protected void setValue(Issuer.IssuerBuilder builder, byte[] tagBytes, ByteBuffer byteBuffer, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(byteBuffer, String.class));
    }

    @Override
    protected Issuer build(Issuer.IssuerBuilder builder) {
        return builder.build();
    }
}
