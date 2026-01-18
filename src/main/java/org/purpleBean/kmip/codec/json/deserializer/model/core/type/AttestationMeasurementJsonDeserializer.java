package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttestationMeasurement;

import java.nio.ByteBuffer;

public class AttestationMeasurementJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttestationMeasurement, ByteBuffer> {

    public AttestationMeasurementJsonDeserializer() {
        super(AttestationMeasurement.kmipTag, AttestationMeasurement.encodingType, ByteBuffer.class, value -> AttestationMeasurement.builder().value(value).build());
    }
}