package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttestationMeasurement;

import java.io.IOException;
import java.nio.ByteBuffer;

public class AttestationMeasurementXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttestationMeasurement, AttestationMeasurement.AttestationMeasurementBuilder> {

    public AttestationMeasurementXmlDeserializer() {
        super(AttestationMeasurement.kmipTag, AttestationMeasurement.encodingType);
    }

    @Override
    protected AttestationMeasurement.AttestationMeasurementBuilder createBuilder() {
        return AttestationMeasurement.builder();
    }

    @Override
    protected void setValue(AttestationMeasurement.AttestationMeasurementBuilder builder, String tag, String type, JsonParser p, DeserializationContext ctxt) throws IOException {
        builder.value(ctxt.readValue(p, ByteBuffer.class));
    }

    @Override
    protected AttestationMeasurement build(AttestationMeasurement.AttestationMeasurementBuilder builder) {
        return builder.build();
    }
}