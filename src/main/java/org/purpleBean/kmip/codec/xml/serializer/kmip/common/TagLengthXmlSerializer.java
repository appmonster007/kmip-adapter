package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.TagLength;

public class TagLengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<TagLength, Integer> {

    public TagLengthXmlSerializer() {
        super(TagLength::getValue);
    }
}