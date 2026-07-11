package org.purpleBean.kmip.codec.ttlv.model.core.type;

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
import org.purpleBean.kmip.model.core.type.Comment;
import org.purpleBean.kmip.test.suite.AbstractTtlvSerializationTestSuite;

@DisplayName("Comment Ttlv Serialization Tests")
class CommentTtlvTest extends AbstractTtlvSerializationTestSuite<Comment> {

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