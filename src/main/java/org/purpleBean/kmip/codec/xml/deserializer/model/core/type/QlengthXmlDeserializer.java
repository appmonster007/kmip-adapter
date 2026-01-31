package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.Qlength;

import java.io.IOException;

public class QlengthXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Qlength, Qlength.QlengthBuilder> {

    public QlengthXmlDeserializer() {
        super(Qlength.kmipTag, Qlength.encodingType);
    }

    @Override
    protected Qlength.QlengthBuilder createBuilder() {
        return Qlength.builder();
    }

    @Override
    protected void setValue(Qlength.QlengthBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, Integer.class));
    }

    @Override
    protected Qlength build(Qlength.QlengthBuilder builder) {
        return builder.build();
    }
}