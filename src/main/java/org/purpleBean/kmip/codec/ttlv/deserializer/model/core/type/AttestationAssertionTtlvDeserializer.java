package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.AttestationAssertion;

import java.nio.ByteBuffer;

public class AttestationAssertionTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<AttestationAssertion, ByteBuffer> {

    public AttestationAssertionTtlvDeserializer() {
        super(AttestationAssertion.kmipTag, AttestationAssertion.encodingType, ByteBuffer.class, value -> AttestationAssertion.builder().value(value).build());
    }
}