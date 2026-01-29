package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.VendorIdentification;

import java.io.IOException;
import java.nio.ByteBuffer;

public class VendorIdentificationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<VendorIdentification, VendorIdentification.VendorIdentificationBuilder> {

    public VendorIdentificationTtlvDeserializer() {
        super(VendorIdentification.kmipTag, VendorIdentification.encodingType);
    }

    @Override
    protected VendorIdentification.VendorIdentificationBuilder createBuilder() {
        return VendorIdentification.builder();
    }

    @Override
    protected void setValue(VendorIdentification.VendorIdentificationBuilder builder, byte[] tagBytes, ByteBuffer p, TtlvMapper mapper) throws IOException {
        builder.value(mapper.readValue(p, String.class));
    }

    @Override
    protected VendorIdentification build(VendorIdentification.VendorIdentificationBuilder builder) {
        return builder.build();
    }
}