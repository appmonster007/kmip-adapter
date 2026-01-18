package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttestationMeasurement;

import java.nio.ByteBuffer;

public class AttestationMeasurementXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttestationMeasurement, ByteBuffer> {

    public AttestationMeasurementXmlDeserializer() {
        super(AttestationMeasurement.kmipTag, AttestationMeasurement.encodingType, ByteBuffer.class, value -> AttestationMeasurement.builder().value(value).build());
    }
}