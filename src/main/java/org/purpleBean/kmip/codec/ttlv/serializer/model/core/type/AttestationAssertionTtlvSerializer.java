package org.purpleBean.kmip.codec.ttlv.serializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.serializer.api.AbstractKmipDataTypeTtlvSerializer;
import org.purpleBean.kmip.model.core.type.AttestationAssertion;

import java.nio.ByteBuffer;

public class AttestationAssertionTtlvSerializer extends AbstractKmipDataTypeTtlvSerializer<AttestationAssertion, ByteBuffer> {

    public AttestationAssertionTtlvSerializer() {
        super(AttestationAssertion::getValue);
    }
}