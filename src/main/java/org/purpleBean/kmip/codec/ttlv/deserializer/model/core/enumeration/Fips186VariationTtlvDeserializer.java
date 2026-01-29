package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.enumeration;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.Fips186Variation;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Fips186VariationTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<Fips186Variation, Fips186Variation.Fips186VariationBuilder> {

    public Fips186VariationTtlvDeserializer() {
        super(Fips186Variation.kmipTag, Fips186Variation.encodingType);
    }

    @Override
    protected Fips186Variation.Fips186VariationBuilder createBuilder() {
        return Fips186Variation.builder();
    }

    @Override
    protected void setValue(Fips186Variation.Fips186VariationBuilder builder, byte[] tagBytes, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
        Integer value = mapper.readValue(p, Integer.class);
        builder.value(Fips186Variation.fromValue(value));
    }

    @Override
    protected Fips186Variation build(Fips186Variation.Fips186VariationBuilder builder) {
        return builder.build();
    }
}
