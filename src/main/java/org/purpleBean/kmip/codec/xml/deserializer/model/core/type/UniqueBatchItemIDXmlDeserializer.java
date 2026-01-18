package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.UniqueBatchItemID;

import java.nio.ByteBuffer;

public class UniqueBatchItemIDXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<UniqueBatchItemID, ByteBuffer> {

    public UniqueBatchItemIDXmlDeserializer() {
        super(UniqueBatchItemID.kmipTag, UniqueBatchItemID.encodingType, ByteBuffer.class, value -> UniqueBatchItemID.builder().value(value).build());
    }
}