package org.purpleBean.kmip.codec.xml.deserializer.model.core.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;

import java.io.IOException;

public class AttestationTypeXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttestationType, AttestationType.AttestationTypeBuilder> {

    public AttestationTypeXmlDeserializer() {
        super(AttestationType.kmipTag, AttestationType.encodingType);
    }

    @Override
    protected AttestationType.AttestationTypeBuilder createBuilder() {
        return AttestationType.builder();
    }

    @Override
    protected void setValue(AttestationType.AttestationTypeBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(AttestationType.fromName(ctxt.readValue(p, String.class)));
    }

    @Override
    protected AttestationType build(AttestationType.AttestationTypeBuilder builder) {
        return builder.build();
    }
}