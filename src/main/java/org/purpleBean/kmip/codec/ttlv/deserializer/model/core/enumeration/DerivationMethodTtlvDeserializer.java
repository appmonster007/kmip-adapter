package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.DerivationMethod;

import java.io.IOException;
import java.nio.ByteBuffer;

public class DerivationMethodTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<DerivationMethod, DerivationMethod.DerivationMethodBuilder> {

    public DerivationMethodTtlvDeserializer() {
        super(DerivationMethod.kmipTag, DerivationMethod.encodingType);
    }

    @Override
    protected DerivationMethod.DerivationMethodBuilder createBuilder() {
        return DerivationMethod.builder();
    }

    @Override
    protected void setValue(DerivationMethod.DerivationMethodBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(DerivationMethod.fromValue(value));
    }

    @Override
    protected DerivationMethod build(DerivationMethod.DerivationMethodBuilder builder) {
        return builder.build();
    }
}
