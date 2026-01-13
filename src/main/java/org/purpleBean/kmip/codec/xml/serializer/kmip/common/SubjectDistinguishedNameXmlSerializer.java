package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.SubjectDistinguishedName;

import java.nio.ByteBuffer;

public class SubjectDistinguishedNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<SubjectDistinguishedName, ByteBuffer> {

    public SubjectDistinguishedNameXmlSerializer() {
        super(SubjectDistinguishedName::getValue);
    }
}