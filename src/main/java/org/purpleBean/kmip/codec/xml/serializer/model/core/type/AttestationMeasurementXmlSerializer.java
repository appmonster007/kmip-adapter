package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.AttestationMeasurement;

import java.nio.ByteBuffer;

public class AttestationMeasurementXmlSerializer extends AbstractKmipDataTypeXmlSerializer<AttestationMeasurement, ByteBuffer> {

    public AttestationMeasurementXmlSerializer() {
        super(AttestationMeasurement::getValue);
    }
}