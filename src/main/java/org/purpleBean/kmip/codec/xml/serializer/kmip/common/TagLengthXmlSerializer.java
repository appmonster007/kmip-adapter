package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.TagLength;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class TagLengthXmlSerializer extends AbstractKmipXmlSerializer<TagLength, Integer> {

    public TagLengthXmlSerializer() {
        super(TagLength::getValue);
    }
}