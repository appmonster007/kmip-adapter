package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.UniqueBatchItemID;

import java.nio.ByteBuffer;

public class UniqueBatchItemIDXmlDeserializer extends AbstractKmipXmlDeserializer<UniqueBatchItemID, ByteBuffer> {

    public UniqueBatchItemIDXmlDeserializer() {
        super(UniqueBatchItemID.kmipTag, UniqueBatchItemID.encodingType, ByteBuffer.class, value -> UniqueBatchItemID.builder().value(value).build());
    }
}