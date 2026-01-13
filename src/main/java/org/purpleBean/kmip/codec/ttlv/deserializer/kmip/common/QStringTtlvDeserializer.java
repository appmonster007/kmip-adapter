package org.purpleBean.kmip.codec.ttlv.deserializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.deserializer.kmip.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.common.QString;

import java.nio.ByteBuffer;

public class QStringTtlvDeserializer extends AbstractKmipDataTypeTtlvDeserializer<QString, ByteBuffer> {

    public QStringTtlvDeserializer() {
        super(QString.kmipTag, QString.encodingType, ByteBuffer.class, value -> QString.builder().value(value).build());
    }
}