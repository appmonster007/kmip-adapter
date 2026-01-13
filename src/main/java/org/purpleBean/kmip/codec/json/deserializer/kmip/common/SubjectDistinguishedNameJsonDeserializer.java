package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.SubjectDistinguishedName;

import java.nio.ByteBuffer;

public class SubjectDistinguishedNameJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<SubjectDistinguishedName, ByteBuffer> {

    public SubjectDistinguishedNameJsonDeserializer() {
        super(SubjectDistinguishedName.kmipTag, SubjectDistinguishedName.encodingType, ByteBuffer.class, value -> SubjectDistinguishedName.builder().value(value).build());
    }
}