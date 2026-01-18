package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.api.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.UniqueBatchItemID;

import java.nio.ByteBuffer;

public class UniqueBatchItemIDXmlSerializer extends AbstractKmipDataTypeXmlSerializer<UniqueBatchItemID, ByteBuffer> {

    public UniqueBatchItemIDXmlSerializer() {
        super(UniqueBatchItemID::getValue);
    }
}