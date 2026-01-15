package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.MediaIdentifier;

public class MediaIdentifierXmlSerializer extends AbstractKmipDataTypeXmlSerializer<MediaIdentifier, String> {

    public MediaIdentifierXmlSerializer() {
        super(MediaIdentifier::getValue);
    }
}