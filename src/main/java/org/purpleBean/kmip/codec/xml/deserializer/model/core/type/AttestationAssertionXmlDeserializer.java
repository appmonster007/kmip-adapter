package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.AttestationAssertion;

import java.nio.ByteBuffer;

public class AttestationAssertionXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<AttestationAssertion, ByteBuffer> {

    public AttestationAssertionXmlDeserializer() {
        super(AttestationAssertion.kmipTag, AttestationAssertion.encodingType, ByteBuffer.class, value -> AttestationAssertion.builder().value(value).build());
    }
}