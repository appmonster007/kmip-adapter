package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.CancellationResult;

import java.io.IOException;

public class CancellationResultXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<CancellationResult, CancellationResult.CancellationResultBuilder> {

    public CancellationResultXmlDeserializer() {
        super(CancellationResult.kmipTag, CancellationResult.encodingType);
    }

    @Override
    protected CancellationResult.CancellationResultBuilder createBuilder() {
        return CancellationResult.builder();
    }

    @Override
    protected void setValue(CancellationResult.CancellationResultBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(CancellationResult.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected CancellationResult build(CancellationResult.CancellationResultBuilder builder) {
        return builder.build();
    }
}