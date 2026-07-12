package org.purpleBean.kmip.codec.xml.model.v2_1.type;

import org.junit.jupiter.api.DisplayName;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.api.*;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.*;
import org.purpleBean.kmip.model.core.type.*;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.purpleBean.kmip.model.v2_1.type.Comment;
import org.purpleBean.kmip.test.suite.AbstractXmlSerializationTestSuite;

@DisplayName("Comment Xml Serialization Tests")
class CommentXmlTest extends AbstractXmlSerializationTestSuite<Comment> {

    @Override
    public Class<Comment> type() {
        return Comment.class;
    }

    @Override
    public Comment createDefault() {
        return Comment.of("default-string");
    }

    @Override
    public Comment createVariant() {
        return Comment.of("variant-string");
    }
}