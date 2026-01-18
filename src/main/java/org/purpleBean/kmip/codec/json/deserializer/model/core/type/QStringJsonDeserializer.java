package org.purpleBean.kmip.codec.json.deserializer.model.core.type;

import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.type.QString;

import java.nio.ByteBuffer;

public class QStringJsonDeserializer extends AbstractKmipDataTypeJsonDeserializer<QString, ByteBuffer> {

    public QStringJsonDeserializer() {
        super(QString.kmipTag, QString.encodingType, ByteBuffer.class, value -> QString.builder().value(value).build());
    }
}