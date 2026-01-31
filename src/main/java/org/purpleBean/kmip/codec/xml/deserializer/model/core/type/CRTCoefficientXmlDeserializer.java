package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.CRTCoefficient;

import java.io.IOException;
import java.math.BigInteger;

public class CRTCoefficientXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CRTCoefficient, CRTCoefficient.CRTCoefficientBuilder> {

    public CRTCoefficientXmlDeserializer() {
        super(CRTCoefficient.kmipTag, CRTCoefficient.encodingType);
    }

    @Override
    protected CRTCoefficient.CRTCoefficientBuilder createBuilder() {
        return CRTCoefficient.builder();
    }

    @Override
    protected void setValue(CRTCoefficient.CRTCoefficientBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, BigInteger.class));
    }

    @Override
    protected CRTCoefficient build(CRTCoefficient.CRTCoefficientBuilder builder) {
        return builder.build();
    }
}