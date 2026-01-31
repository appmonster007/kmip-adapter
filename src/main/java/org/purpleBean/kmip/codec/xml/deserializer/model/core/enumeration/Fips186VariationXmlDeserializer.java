package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.Fips186Variation;

import java.io.IOException;

public class Fips186VariationXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Fips186Variation, Fips186Variation.Fips186VariationBuilder> {

    public Fips186VariationXmlDeserializer() {
        super(Fips186Variation.kmipTag, Fips186Variation.encodingType);
    }

    @Override
    protected Fips186Variation.Fips186VariationBuilder createBuilder() {
        return Fips186Variation.builder();
    }

    @Override
    protected void setValue(Fips186Variation.Fips186VariationBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(Fips186Variation.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected Fips186Variation build(Fips186Variation.Fips186VariationBuilder builder) {
        return builder.build();
    }
}