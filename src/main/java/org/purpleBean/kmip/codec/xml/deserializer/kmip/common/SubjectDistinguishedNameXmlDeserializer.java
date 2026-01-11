package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.SubjectDistinguishedName;

import java.nio.ByteBuffer;

public class SubjectDistinguishedNameXmlDeserializer extends AbstractKmipXmlDeserializer<SubjectDistinguishedName, ByteBuffer> {

    public SubjectDistinguishedNameXmlDeserializer() {
        super(SubjectDistinguishedName.kmipTag, SubjectDistinguishedName.encodingType, ByteBuffer.class, value -> SubjectDistinguishedName.builder().value(value).build());
    }
}