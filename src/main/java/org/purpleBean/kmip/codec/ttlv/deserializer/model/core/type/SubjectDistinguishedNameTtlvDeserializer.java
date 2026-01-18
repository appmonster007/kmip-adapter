package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.type;

import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.model.core.type.SubjectDistinguishedName;

import java.nio.ByteBuffer;

public class SubjectDistinguishedNameTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<SubjectDistinguishedName, ByteBuffer> {

    public SubjectDistinguishedNameTtlvDeserializer() {
        super(SubjectDistinguishedName.kmipTag, SubjectDistinguishedName.encodingType, ByteBuffer.class, value -> SubjectDistinguishedName.builder().value(value).build());
    }
}