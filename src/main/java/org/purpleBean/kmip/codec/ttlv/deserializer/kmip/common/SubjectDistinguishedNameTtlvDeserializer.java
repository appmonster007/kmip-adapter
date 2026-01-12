package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.AbstractKmipTtlvDeserializer;
import org.purpleBean.kmip.common.SubjectDistinguishedName;

import java.nio.ByteBuffer;

public class SubjectDistinguishedNameTtlvDeserializer extends AbstractKmipTtlvDeserializer<SubjectDistinguishedName, ByteBuffer> {

    public SubjectDistinguishedNameTtlvDeserializer() {
        super(SubjectDistinguishedName.kmipTag, SubjectDistinguishedName.encodingType, ByteBuffer.class, value -> SubjectDistinguishedName.builder().value(value).build());
    }
}