package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.kmip.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.MediaIdentifier;

public class MediaIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<MediaIdentifier, String> {

    public MediaIdentifierXmlDeserializer() {
        super(MediaIdentifier.kmipTag, MediaIdentifier.encodingType, String.class, value -> MediaIdentifier.builder().value(value).build());
    }
}