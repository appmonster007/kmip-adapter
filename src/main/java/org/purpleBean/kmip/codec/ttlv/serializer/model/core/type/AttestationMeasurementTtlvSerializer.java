package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.kmip.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.AttestationMeasurement;

import java.nio.ByteBuffer;

public class AttestationMeasurementTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttestationMeasurement, ByteBuffer> {

    public AttestationMeasurementTtlvSerializer() {
        super(AttestationMeasurement::getValue);
    }
}