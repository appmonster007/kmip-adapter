package org.purpleBean.kmip.codec.xml.deserializer.model.v2_1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.api.KmipAttribute;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.v2_1.structure.NewAttribute;

import java.io.IOException;

public class NewAttributeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<NewAttribute, NewAttribute.NewAttributeBuilder> {

    public NewAttributeXmlDeserializer() {
        super(NewAttribute.kmipTag, NewAttribute.encodingType);
    }

    @Override
    protected NewAttribute.NewAttributeBuilder createBuilder() {
        return NewAttribute.builder();
    }

    @Override
    protected void setValue(NewAttribute.NewAttributeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.attribute(ctxt.readValue(p, KmipAttribute.class));
    }

    @Override
    protected NewAttribute build(NewAttribute.NewAttributeBuilder builder) {
        return builder.build();
    }
}
