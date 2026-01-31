package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.RngAlgorithm;

import java.io.IOException;

public class RngAlgorithmXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<RngAlgorithm, RngAlgorithm.RngAlgorithmBuilder> {

    public RngAlgorithmXmlDeserializer() {
        super(RngAlgorithm.kmipTag, RngAlgorithm.encodingType);
    }

    @Override
    protected RngAlgorithm.RngAlgorithmBuilder createBuilder() {
        return RngAlgorithm.builder();
    }

    @Override
    protected void setValue(RngAlgorithm.RngAlgorithmBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(RngAlgorithm.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected RngAlgorithm build(RngAlgorithm.RngAlgorithmBuilder builder) {
        return builder.build();
    }
}