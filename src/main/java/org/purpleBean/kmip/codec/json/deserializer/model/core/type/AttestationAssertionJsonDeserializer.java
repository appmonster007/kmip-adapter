package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.AttestationAssertion;

import java.nio.ByteBuffer;

public class AttestationAssertionJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<AttestationAssertion, ByteBuffer> {

    public AttestationAssertionJsonDeserializer() {
        super(AttestationAssertion.kmipTag, AttestationAssertion.encodingType, ByteBuffer.class, value -> AttestationAssertion.builder().value(value).build());
    }
}