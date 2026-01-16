package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.AttestationMeasurement;

import java.nio.ByteBuffer;

public class AttestationMeasurementJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttestationMeasurement, ByteBuffer> {

    public AttestationMeasurementJsonSerializer() {
        super(AttestationMeasurement::getValue);
    }
}