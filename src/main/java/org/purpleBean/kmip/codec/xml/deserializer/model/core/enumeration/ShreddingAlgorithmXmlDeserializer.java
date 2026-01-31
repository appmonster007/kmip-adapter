package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ShreddingAlgorithm;

import java.io.IOException;

public class ShreddingAlgorithmXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ShreddingAlgorithm, ShreddingAlgorithm.ShreddingAlgorithmBuilder> {

    public ShreddingAlgorithmXmlDeserializer() {
        super(ShreddingAlgorithm.kmipTag, ShreddingAlgorithm.encodingType);
    }

    @Override
    protected ShreddingAlgorithm.ShreddingAlgorithmBuilder createBuilder() {
        return ShreddingAlgorithm.builder();
    }

    @Override
    protected void setValue(ShreddingAlgorithm.ShreddingAlgorithmBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ShreddingAlgorithm.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected ShreddingAlgorithm build(ShreddingAlgorithm.ShreddingAlgorithmBuilder builder) {
        return builder.build();
    }
}