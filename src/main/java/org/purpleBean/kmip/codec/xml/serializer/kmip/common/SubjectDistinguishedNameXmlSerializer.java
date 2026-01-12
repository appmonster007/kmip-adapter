package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.SubjectDistinguishedName;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

import java.nio.ByteBuffer;

public class SubjectDistinguishedNameXmlSerializer extends AbstractKmipXmlSerializer<SubjectDistinguishedName, ByteBuffer> {

    public SubjectDistinguishedNameXmlSerializer() {
        super(SubjectDistinguishedName::getValue);
    }
}