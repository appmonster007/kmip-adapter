package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.MediaIdentifier;

public class MediaIdentifierXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<MediaIdentifier, String> {

    public MediaIdentifierXmlDeserializer() {
        super(MediaIdentifier.kmipTag, MediaIdentifier.encodingType, String.class, value -> MediaIdentifier.builder().value(value).build());
    }
}