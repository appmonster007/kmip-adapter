package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.AttestationMeasurement;

import java.nio.ByteBuffer;

public class AttestationMeasurementTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttestationMeasurement, ByteBuffer> {

    public AttestationMeasurementTtlvDeserializer() {
        super(AttestationMeasurement.kmipTag, AttestationMeasurement.encodingType, ByteBuffer.class, value -> AttestationMeasurement.builder().value(value).build());
    }
}