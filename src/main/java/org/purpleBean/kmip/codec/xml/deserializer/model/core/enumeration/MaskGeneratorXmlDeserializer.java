package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.MaskGenerator;

import java.io.IOException;

public class MaskGeneratorXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<MaskGenerator, MaskGenerator.MaskGeneratorBuilder> {

    public MaskGeneratorXmlDeserializer() {
        super(MaskGenerator.kmipTag, MaskGenerator.encodingType);
    }

    @Override
    protected MaskGenerator.MaskGeneratorBuilder createBuilder() {
        return MaskGenerator.builder();
    }

    @Override
    protected void setValue(MaskGenerator.MaskGeneratorBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(MaskGenerator.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected MaskGenerator build(MaskGenerator.MaskGeneratorBuilder builder) {
        return builder.build();
    }
}