package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttestationAssertion;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttestationAssertionXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttestationAssertion, AttestationAssertion.AttestationAssertionBuilder> {

    public AttestationAssertionXmlDeserializer() {
        super(AttestationAssertion.kmipTag, AttestationAssertion.encodingType);
    }

    @Override
    protected AttestationAssertion.AttestationAssertionBuilder createBuilder() {
        return AttestationAssertion.builder();
    }

    @Override
    protected void setValue(AttestationAssertion.AttestationAssertionBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected AttestationAssertion build(AttestationAssertion.AttestationAssertionBuilder builder) {
        return builder.build();
    }
}