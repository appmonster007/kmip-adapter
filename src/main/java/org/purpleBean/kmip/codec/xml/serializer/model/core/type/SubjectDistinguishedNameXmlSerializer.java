package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.SubjectDistinguishedName;

import java.nio.ByteBuffer;

public class SubjectDistinguishedNameXmlSerializer extends AbstractKmipDataTypeXmlSerializer<SubjectDistinguishedName, ByteBuffer> {

    public SubjectDistinguishedNameXmlSerializer() {
        super(SubjectDistinguishedName::getValue);
    }
}