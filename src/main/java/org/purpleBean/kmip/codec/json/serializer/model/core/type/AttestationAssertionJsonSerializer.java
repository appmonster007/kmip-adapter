package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.AttestationAssertion;

import java.nio.ByteBuffer;

public class AttestationAssertionJsonSerializer extends AbstractKmipDataTypeJsonSerializer<AttestationAssertion, ByteBuffer> {

    public AttestationAssertionJsonSerializer() {
        super(AttestationAssertion::getValue);
    }
}