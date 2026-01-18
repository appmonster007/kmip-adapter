package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.SubjectDistinguishedName;

import java.nio.ByteBuffer;

public class SubjectDistinguishedNameXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<SubjectDistinguishedName, ByteBuffer> {

    public SubjectDistinguishedNameXmlDeserializer() {
        super(SubjectDistinguishedName.kmipTag, SubjectDistinguishedName.encodingType, ByteBuffer.class, value -> SubjectDistinguishedName.builder().value(value).build());
    }
}