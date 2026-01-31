package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.ProtectStopDate;

import java.io.IOException;
import java.time.OffsetDateTime;

public class ProtectStopDateXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<ProtectStopDate, ProtectStopDate.ProtectStopDateBuilder> {

    public ProtectStopDateXmlDeserializer() {
        super(ProtectStopDate.kmipTag, ProtectStopDate.encodingType);
    }

    @Override
    protected ProtectStopDate.ProtectStopDateBuilder createBuilder() {
        return ProtectStopDate.builder();
    }

    @Override
    protected void setValue(ProtectStopDate.ProtectStopDateBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, OffsetDateTime.class));
    }

    @Override
    protected ProtectStopDate build(ProtectStopDate.ProtectStopDateBuilder builder) {
        return builder.build();
    }
}